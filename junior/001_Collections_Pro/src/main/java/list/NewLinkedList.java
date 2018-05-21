package list;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.04.2018
 */
public class NewLinkedList<E> {
    private transient int size;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount;


    public boolean add(Node<E> node) {
        modCount++;
        final Node<E> l = last;
        final Node<E> newNode = node;
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }


    public boolean hasCycle() {
        Node<E> inststance = first;
        Node<E> nextInststance = first.next;
        while (inststance != null) {
            while (nextInststance != null) {
                if (inststance == nextInststance) {
                    return true;
                }
                nextInststance = nextInststance.next;
            }
            inststance = inststance.next;
            if (inststance != null) {
                nextInststance = inststance.next;
            }
        }
        return false;
    }
}
