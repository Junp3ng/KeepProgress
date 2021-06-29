

public class WLinkedListWithSentinel<T> implements IWLinkedList<T> {

    private int mSize = 0;
    private IWLinkedList.LinkNode<T> head ;
    private IWLinkedList.LinkNode<T> tail;

    WLinkedListWithSentinel() {
        head = new IWLinkedList.LinkNode<T>(null);
        tail = new IWLinkedList.LinkNode<T>(null);
        head.next = tail;
        head.pre = tail;
        tail.next = head;
        tail.pre = head;
    }

    @Override
    public int size() {
        return mSize;
    }

    @Override
    public boolean empty() {
        return mSize == 0;
    }

    @Override
    public T value_at(int index) {
        LinkNode<T> node = at(index);
        if (node == null) return null;
        return node.value;
    }

    private LinkNode<T> at(int index) {
        if (index >= mSize || index < 0) {
            throw new IndexOutOfBoundsException("index invalid " + index);
        }
        LinkNode<T> node = head;
        for (int i = 0; i <= index; i ++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public void push_front(T value) {
        head.next = new LinkNode<T>(value, head.next, head);
        mSize++;
    }

    @Override
    public T pop_front() {
        if (empty()) return null;
        IWLinkedList.LinkNode<T> node = head.next;
        head.next = node.next;
        mSize--;
        return node.value;
    }

    @Override
    public void push_back(T value) {
        tail.pre = new LinkNode<T>(value, tail, tail.pre);
        mSize++;
    }

    @Override
    public T pop_back() {
        if (empty()) {
            return null;
        }
        IWLinkedList.LinkNode<T> node = tail.pre;
        tail.pre = node.pre;
        mSize--;
        return node.value;
    }

    @Override
    public T front() {
        if (empty()) {
            return null;
        }
        return head.next.value;
    }

    @Override
    public T back() {
        if (empty()) {
            return null;
        }
        return tail.pre.value;
    }

    @Override
    public void insert(int index, T value) {
        if (index == mSize) {
            tail.pre = new IWLinkedList.LinkNode<T>(value, tail, tail.pre);
            mSize++;
            return;
        }
        IWLinkedList.LinkNode<T> node = at(index);
        assert node != null;
        node.pre.next = new LinkNode<T>(value, node, node.pre);
        mSize++;
    }

    @Override
    public void erase(int index) {
        IWLinkedList.LinkNode<T> node = at(index);
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    @Override
    public T value_n_from_end(int n) {
        if (n >= mSize) {
            return head.next.value;
        } else if (n <= 0) {
            return tail.pre.value;
        }
        LinkNode<T> node = node_n_from_end(n, tail);

        return node.value;
    }

    @Override
    public void reverse() {
        if (empty() || mSize == 1) return;

        reverse(head, tail);

    }

    private void exchange_value(LinkNode<T> node1, LinkNode<T> node2) {
        var temp =node1.value;
        node1.value = node2.value;
        node2.value = temp;
    }

    private void reverse(LinkNode<T> head, LinkNode<T> tail) {
        if (head == tail) {
            return;
        }
        exchange_value(head, tail);
        if (head.next != tail) {
            reverse(head.next, tail.pre);
        }
    }

    @Override
    public void remove_value(T value) {
        var temp = head;
        while (temp != tail) {
            if (temp.value == value) {
                break;
            }
            temp = temp.next;
        }
        if (temp == tail) {
            return;
        }
        temp.pre.next = temp.next;
        temp.next.pre = temp.pre;
    }

    private LinkNode<T> node_n_from_end(int n, LinkNode<T> tail) {
        if (n < 0) {
            return tail;
        }
        return node_n_from_end(n - 1, tail.pre);
    }

    public static void main(String[] args) {
        // TODO 链表完成了，有待验证
    }
}
