import java.util.Iterator;

public interface IWList<T> {

    int size();

    int capacity();

    boolean is_empty();

    T at(int index) throws IndexOutOfBoundsException;

    void push(T item);

    void insert(int index, T item);

    void prepend(T item);

    T pop();

    void delete(int index);

    void remove(T item);

    int find(T item);

}
