package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.04.2018
 */
public class ArrayIterator implements Iterator {
    int[][] ar;
    int row = 0;
    int colomn = 0;


    public ArrayIterator(int[][] ar) {
        this.ar = ar;
    }

    @Override
    public boolean hasNext() {
        if (ar[row].length > colomn) {
            return true;
        } else
            return ar.length > (1 + row) ? true : false;
    }

    @Override
    public Object next() {
        int result = 0;
        if (ar.length != 0 && ar[row].length > colomn) {
            result = ar[row][colomn++];
        } else if (++row < ar.length) {
            colomn = 0;
            result = ar[row][colomn++];
        } else throw new NoSuchElementException();
        return result;
    }
}
