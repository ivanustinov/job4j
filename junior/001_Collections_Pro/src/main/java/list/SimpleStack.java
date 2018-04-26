package list;

import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class SimpleStack<E> extends MyLinkedList<E> {
    public E push(E item) {
        add(item);
        return item;
    }

    public E pop() {
        if (getSize() == 0) {
            throw new EmptyStackException();
        }
        int index = getSize() - 1;
        E obj = get(index);
        remove(obj);
        return obj;
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = getModCount();
            Node<E> inststance = last;

            @Override
            public boolean hasNext() {
                boolean result = false;
                Node<E> localInst = inststance;
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                while (localInst != null) {
                    if (localInst.item == null) {
                        localInst = localInst.prev;
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
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (inststance != null) {
                    E e = inststance.item;
                    inststance = inststance.prev;
                    return e != null ? e : next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
