package set;

import list.SimpleList;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Set based on the massive.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.04.2018
 */
public class SimpleSet<E> implements Iterable<E> {

    private SimpleList<E> simpleList;

    public SimpleSet() {
        simpleList = new SimpleList<>();
    }

    public void add(E object) {
        if (!simpleList.contains(object)) {
            simpleList.add(object);
        }
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = simpleList.getModCount();
            int newInst = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != simpleList.getModCount()) {
                    throw new ConcurrentModificationException();
                }
                if (newInst < simpleList.getPosition()) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != simpleList.getModCount()) {
                    throw new ConcurrentModificationException();
                } else if (newInst < simpleList.getPosition()) {
                    return (E) simpleList.getContainer()[newInst++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
