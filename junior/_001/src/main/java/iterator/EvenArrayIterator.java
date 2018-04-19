package iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.04.2018
 */
public class EvenArrayIterator implements Iterator {
    int[] ar;
    int colomn = 0;


    public EvenArrayIterator(int[] ar) {
        this.ar = ar;
    }

    @Override
    public boolean hasNext() {
        int colomn = this.colomn;
        if (isNext(this.colomn)) {
            this.colomn = colomn;
            return true;
        } else return false;
    }

    public boolean isNext( int colomn) {
        if (colomn >= ar.length) {
            return false;
        }
        while (ar[colomn] % 2 != 0) {
            if (ar.length > ++colomn) {
                continue;
            }  else return false;
        }
        this.colomn = colomn;
        return true;
    }

    @Override
    public Object next() {
        if (isNext(colomn)) {
            int result = ar[colomn];
            isNext(++colomn);
            return result;
        } else throw new NoSuchElementException();
    }
}
