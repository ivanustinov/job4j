package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.04.2018
 */
public class PrimeIterator implements Iterator {
    private int[] ar;
    private int colomn;

    public PrimeIterator(int[] ar) {
        this.ar = ar;
        colomn = 0;
    }

    /**
     * Return true if there is one element more in the Iterator.
     *
     * @return boolean.
     */
    @Override
    public boolean hasNext() {
        int position = colomn;
        while (position < ar.length) {
            if (isSimple(ar[position])) {
                return true;
            } else {
                position++;
            }
        }
        return false;
    }

    /**
     * Return true if the number is simple number.
     * else return false.
     *
     * @param number
     * @return boolean.
     */
    public boolean isSimple(int number) {
        return (number == 2 || number == 3 || number % 2 != 0 && number % 3 != 0 && number > 1);
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
        while (colomn < ar.length) {
            if (isSimple(ar[colomn])) {
                return ar[colomn++];
            } else {
                colomn++;
            }
        }
        throw new NoSuchElementException();
    }
}
