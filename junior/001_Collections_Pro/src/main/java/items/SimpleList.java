package items;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Exersise for leaning items generics.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
@ThreadSafe
public class SimpleList<E> implements Iterable<E> {
    @GuardedBy("this")
    private Object[] container  = new Object[5];
    private int position = 0;
    private static final double INCREASECAPACITY = 1.5;
    private int modCount = 0;


    /**
     * Gets container
     *
     * @return value of container
     */

    public synchronized Object[] getContainer() {
        return container;
    }

    /**
     * Add the new element to the items, if nesessary increases it's size.
     *
     * @param object E.
     */
    public synchronized void add(E object) {
        modCount++;
        int f = position, newLength;
        if (++f >= container.length) {
            newLength = (int) (container.length * INCREASECAPACITY);
            container = Arrays.copyOf(container, newLength);
            container[position++] = object;
        } else {
            container[position++] = object;
        }
    }

    public synchronized boolean contains(E object) {
        for (E e : this) {
            if (e.equals(object)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param index int.
     * @return the element with index.
     */
    public synchronized E get(int index) {
        if (index <= position && index >= 0) {
            return (E) container[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }


    @Override
    public synchronized Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int newInst = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (newInst < position) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (newInst < position) {
                    return (E) container[newInst++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
