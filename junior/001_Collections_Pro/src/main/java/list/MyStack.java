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
public class MyStack<E> extends MyLinkedList<E> {
    public E push(E item) {
        add(item);
        return item;
    }

    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        int index = size - 1;
        E obj = get(index);
        remove(obj);
        return obj;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = getModCount();
            int g = size - 1;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (g >= 0) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (g >= 0) {
                    return get(g--);
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
