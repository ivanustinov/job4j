package generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 21.04.2018
 */
public class SimpleArray<T> implements Iterable {
    private Object[] a;
    private int position = 0;

    /**
     * Constructor
     *
     * @param size .
     */
    public SimpleArray(int size) {
        a = new Object[size];
    }

    /**
     * Add the object to the array.
     *
     * @param model left.
     */
    public void add(T model) {
        a[position++] = model;
    }

    /**
     * Assign a reference value model to an array cell with a number position.
     *
     * @param position int.
     * @param model    T.
     */
    public void set(int position, T model) {
        if (position > a.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        a[position] = model;
    }

    /**
     * Delete a reference value of an array cell with a number position.
     *
     * @param position .
     */
    public void delete(int position) {
        if (position > a.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        a[position] = null;
    }

    /**
     * Return a reference value of an array cell with a number position.
     *
     * @return T.
     */
    public T get(int position) {
        if (position > a.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) a[position];
    }

    /**
     * iterator.
     *
     * @return Iterator.
     */
    @Override
    public Iterator iterator() {
        return new Iterator() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < a.length;
            }

            @Override
            public Object next() {
                if (index < a.length) {
                    return a[index++];
                } else {
                    throw new NoSuchElementException();
                }
            }
        };
    }
}
