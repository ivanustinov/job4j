package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator of even numbers.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.04.2018
 */

public class EvenArrayIterator implements Iterator {
    private int[] ar;
    private int colomn;

    /**
     * Constructor.
     *
     * @param ar massive of numbers.
     */
    public EvenArrayIterator(int[] ar) {
        this.ar = ar;
        colomn = 0;
    }

    /**
     * Return true if there is one even number more in the Iterator.
     * else return false.
     *
     * @return boolean.
     */
    @Override
    public boolean hasNext() {
        int position = colomn;
        while (position < ar.length) {
            if (ar[position] % 2 == 0) {
                return true;
            } else {
                position++;
            }
        }
        return false;
    }

    /**
     * Return current even number of the Iterator and replace the index
     * to next even number.
     * else throw NoSuchElementExeption.
     *
     * @return Object.
     */
    @Override
    public Object next() {
        while (colomn < ar.length) {
            if (ar[colomn] % 2 == 0) {
                return ar[colomn++];
            } else {
                colomn++;
            }
        }
        throw new NoSuchElementException();
    }
}
