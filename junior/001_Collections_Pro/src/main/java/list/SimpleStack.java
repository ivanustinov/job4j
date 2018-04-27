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
}
