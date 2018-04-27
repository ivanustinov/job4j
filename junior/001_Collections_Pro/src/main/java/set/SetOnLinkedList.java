package set;

import list.SimpleLinkedList;

import java.util.Iterator;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.04.2018
 */
public class SetOnLinkedList<E> implements Iterable<E> {

    SimpleLinkedList<E> list;

    public SetOnLinkedList() {
        list = new SimpleLinkedList<>();
    }

    public void add(E object) {
            if (!list.contains(object)) {
                list.add(object);
            }
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }
}
