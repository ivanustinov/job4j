package iterator;

import java.util.Iterator;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.04.2018
 */
public class Converter {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> a = it.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (a.hasNext()) {
                    result = true;
                } else if (it.hasNext()) {
                    a = it.next();
                    result = a.hasNext();
                }
                return result;
            }

            @Override
            public Integer next() {
                while (!a.hasNext()) {
                    a = it.next();
                }
                return a.next();
            }
        };
    }
}
