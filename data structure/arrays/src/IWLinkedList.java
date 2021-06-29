import java.util.LinkedList;

public interface IWLinkedList<T> {
    int size();

    boolean empty();

    T value_at(int index);

    void push_front(T value);

    T pop_front();

    void push_back(T value);

    T pop_back();

    T front();

    T back();

    void insert(int index, T value);

    void erase(int index);

    T value_n_from_end(int n);

    void reverse();

    void remove_value(T value);

    public static class LinkNode<E> {
        LinkNode<E> next;
        LinkNode<E> pre;
        E value;
        LinkNode(E value, LinkNode<E> next, LinkNode<E> pre) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        LinkNode(E value) {
            this(value, null, null);
        }
    }
}
