package items;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */

@ThreadSafe
public class SimpleLinkedList<E> implements Iterable<E> {
    @GuardedBy("this")
    private transient int size;
    transient Node<E> first;
    transient Node<E> last;
    private int modCount;

    /**
     * Gets size
     *
     * @return value of size
     */

    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> inststance = first;

            @Override
            public boolean hasNext() {
                boolean result = false;
                Node<E> localInst = inststance;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (localInst != null) {
                    if (localInst.item == null) {
                        localInst = localInst.next;
                        continue;
                    } else {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (inststance != null) {
                    E e = inststance.item;
                    inststance = inststance.next;
                    return e != null ? e : next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }

    /**
     * Gets modCount
     *
     * @return value of modCount
     */

    public synchronized int getModCount() {
        return modCount;
    }

    public synchronized boolean remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public synchronized E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }
        x.item = null;
        size--;
        modCount++;
        return element;
    }

    public class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        public Node(Node<E> prev, E item, Node<E> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    public synchronized boolean add(E e) {
        modCount++;
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        return true;
    }

    public synchronized boolean contains(E object) {
        for (E e : this) {
            if (e.equals(object)) {
                return true;
            }
        }
        return false;
    }


    public synchronized E get(int index) {
        if (index >= 0 && index < size) {
            return node(index).item;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }


    public synchronized Node<E> node(int index) {
        // assert isElementIndex(index);
        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++) {
                x = x.next;
            }
            return x;

        } else {
            Node<E> x = last;
            for (int i = size - 1; i > index; i--) {
                x = x.prev;
            }
            return x;
        }

    }
}