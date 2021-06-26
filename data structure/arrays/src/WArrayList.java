import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class WArrayList<T> implements IWList<T>{

    private Object[] array;
    private int mCapacity = 2;
    private int mSize = 0;

    WArrayList() {
        array = new Object[mCapacity];
    }



    @Override
    public int size() {
        return mSize;
    }

    @Override
    public int capacity() {
        return mCapacity;
    }

    @Override
    public boolean is_empty() {
        return mSize == 0;
    }

    @Override
    public T at(int index) throws IndexOutOfBoundsException {
        isValid(index);

        return (T) array[index];
    }

    @Override
    public void push(T item) {
        insert(mSize, item);
    }

    @Override
    public void insert(int index, T item) throws IndexOutOfBoundsException{
        if (index > mSize) {
            throw new IndexOutOfBoundsException("index=" + index + " must <= " + mSize);
        } else if (index < 0) {
            throw new IllegalArgumentException("index=" + index + " must >= 0");
        }
        System.arraycopy(array, index, array, index + 1, mSize - index);
        array[index] = item;
        mSize ++;
        resize();
    }

    @Override
    public void prepend(T item) {
        insert(0, item);
    }

    @Override
    public T pop() {
        if (is_empty()) {
            return null;
        }
        int index = mSize - 1;
        T result = at(index);
        delete(index);
        return result;
    }

    @Override
    public void delete(int index) {
        isValid(index);

        System.arraycopy(array, index + 1, array, index, mSize - index - 1);
        mSize --;
        resize();
    }

    private void isValid(int index) {
        if (index >= mSize) {
            throw new IndexOutOfBoundsException("index=" + index + " must < " + mSize);
        } else if (index < 0) {
            throw new IllegalArgumentException("index=" + index + " must >= 0");
        }
    }

    @Override
    public void remove(T item) {
        int index = find(item);
        if (index != -1) {
            delete(index);
        }
    }

    @Override
    public int find(T item) {
        for (int i = 0; i < mSize; i++) {
            if (array[i] == item) {
                return i;
            }
        }
        return -1;
    }

    private void resize() {
        if (mSize >= mCapacity) {
            mCapacity *= 2;
            Object[] newArray = new Object[mCapacity];
            System.arraycopy(array, 0, newArray, 0, mSize);
            array = newArray;
        } else if (mSize < mCapacity / 4) {
            mCapacity /= 2;
            Object[] newArray = new Object[mCapacity];
            System.arraycopy(array, 0, newArray, 0, mSize);
            array = newArray;
        }
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < mSize; i++) {
            buffer.append(array[i]);
            if (i != mSize - 1) {
                buffer.append(" ");
            }
        }


        return "WArrayList{"+ buffer +"}";
    }

    public static void main(String[] args) {
        WArrayList<String> list = new WArrayList<>();
        list.push("hello");
        list.push("world");
        list.insert(1, "my");
        System.out.println(list);
        list.delete(1);
        System.out.println(list);
        for (int i = 0; i < 100; i++) {
            list.push("hello index " + i);
        }
        System.out.println(list);
        System.out.println("size=" + list.size() + " capacity=" + list.capacity());
        for (int i = 0; i < 80; i++) {
            list.pop();
        }
        System.out.println("size=" + list.size() + " capacity=" + list.capacity());


    }
}
