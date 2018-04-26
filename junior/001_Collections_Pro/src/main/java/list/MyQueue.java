package list;

import java.util.EmptyStackException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class MyQueue<E> extends MyLinkedList<E> {
    public E push(E item) {
        add(item);
        return item;
    }

    public E pop() {
        if (getSize() == 0) {
            throw new EmptyStackException();
        }
        E obj = get(0);
        remove(obj);
        return obj;
    }
}
