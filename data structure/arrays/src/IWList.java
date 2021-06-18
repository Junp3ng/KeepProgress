import java.util.Iterator;

public interface IWList<T> {

    boolean add(T element);

    boolean add(int index, T element);


    boolean remove(T element);

    boolean removeAt(int index);

    boolean clearAll();

    int length();
}
