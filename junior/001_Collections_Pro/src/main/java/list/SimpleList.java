package list;


import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Exersise for leaning list generic.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class SimpleList<E> implements Iterable<E> {
    Object[] container;
    private int position = 0;
    private static final double INCREASECAPACITY = 1.5;
    private int modCount = 0;

    /**
     * Constructor.
     * size by default= 5.
     */
    public SimpleList() {
        this.container = new Object[5];
    }

    /**
     * Add the new element to the list, if nesessary increases it's size.
     *
     * @param object E.
     */
    public void add(E object) {
        modCount++;
        int f = position, newLength = 0;
        if (++f >= container.length) {
            newLength = (int) (container.length * INCREASECAPACITY);
            container = Arrays.copyOf(container, newLength);
            container[position++] = object;
        } else {
            container[position++] = object;
        }
    }

    /**
     * @param index int.
     * @return the element with index.
     */
    public E get(int index) {
        if (index <= position && index >= 0) {
            return (E) container[index];
        }
        throw new ArrayIndexOutOfBoundsException();
    }


    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            int g = 0;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (g < position) {
                    result = true;
                }
                return result;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (g < position) {
                    return (E) container[g++];
                }
                throw new NoSuchElementException();
            }
        };
    }
}
