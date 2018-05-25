package list;

import java.util.EmptyStackException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class SimpleQueue<E> extends SimpleLinkedList<E> {
    public E push(E item) {
        add(item);
        return item;
    }

    public E pop() {
        if (getSize() == 0) {
            throw new EmptyStackException();
        }
        E obj = first.item;
        remove(obj);
        return obj;
    }
}
