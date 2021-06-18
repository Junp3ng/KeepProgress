import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class WArrayList<T> implements Iterable<T>, IWList<T>{

    private int length = 0;

    private Object[] array;

    WArrayList(int size){
        array = new Object[size];
        length = size;
    }

    WArrayList(){
        array = new Object[0];
    }




    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public boolean add(T element) {
        Object[] temp = new Object[length + 1];
        if (length() >= 0) System.arraycopy(array, 0, temp, 0, length());
        temp[length] = element;
        array = temp;
        return true;
    }

    @Override
    public boolean add(int index, T element)  {
        if (index > length || index < 0) {
            return false;
        }
        Object[] temp = new Object[length + 1];
        System.arraycopy(array, 0, temp, 0, index);
        temp[index] = element;
        if (length - index >= 0) System.arraycopy(array, index, temp, index + 1, length - index);
        array = temp;

        return true;
    }

    @Override
    public boolean remove(T element) {
        int findIndex = -1;
        for (int i = 0; i < length(); i++) {
            if (array[i] == element) {
                findIndex = i;
                break;
            }
        }
        if (findIndex != -1) {
            removeAt(findIndex);
        }

        return findIndex != -1;
    }

    @Override
    public boolean removeAt(int index) {
        if (index >= length() || index < 0) {
            return false;
        }
        for (int i = index; i < length() - 1; i++) {
            array[i] = array[i + 1];
        }
        length --;
        return true;
    }

    @Override
    public boolean clearAll() {
        return false;
    }

    @Override
    public int length() {
        return 0;
    }

    public static void main(String[] args) {
        Object[] o = new Object[8];
        o[0] = 1;
        o[1] = 'c';
        System.out.println("0:" + o[0] + " 1:" + o[1]);
    }
}
