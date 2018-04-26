package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * class for twodimensional massive iterator.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.1
 * @since 19.04.2018
 */
public class ArrayIterator implements Iterator {
    private int[][] ar;
    private int row;
    private int colomn;

    /**
     * Constructor.
     *
     * @param ar matrix of numbers.
     */
    public ArrayIterator(int[][] ar) {
        row = 0;
        colomn = 0;
        this.ar = ar;
    }

    /**
     * Return true if there is one element more in the Iterator.
     *
     * @return boolean.
     */
    @Override
    public boolean hasNext() {
        if (ar[row].length > colomn) {
            return true;
        } else {
            return ar.length > (row + 1) ? true : false;
        }
    }

    /**
     * Return current Object of the Iterator and replace the index
     * to next value.
     * else throw NoSuchElementExeption.
     *
     * @return Object.
     */
    @Override
    public Object next() {
        int result = 0;
        if (ar.length > 0 && colomn < ar[row].length) {
            result = ar[row][colomn++];
        } else if (++row < ar.length && ar[row].length > 0) {
            colomn = 0;
            result = ar[row][colomn++];
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}
