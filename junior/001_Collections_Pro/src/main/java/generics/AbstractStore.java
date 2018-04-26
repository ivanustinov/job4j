package generics;

import java.util.NoSuchElementException;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.04.2018
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {

    private Base[] bases;
    private int position = 0;

    public AbstractStore(int size) {
        bases = new Base[size];
    }

    @Override
    public void add(E model) {
        bases[position++] = model;
    }

    @Override
    public boolean replace(String id, E model) {
        boolean result = false;
        for (int i = 0; i < bases.length; i++) {
            if (bases[i] == null) {
                continue;
            }
            if (bases[i].getId().equals(id)) {
                bases[i] = model;
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        for (int i = 0; i < bases.length; i++) {
            if (bases[i] == null) {
                continue;
            }
            if (bases[i].getId().equals(id)) {
                bases[i] = null;
                result = true;
            }
        }
        return result;
    }

    @Override
    public E findById(String id) {
        E user = null;
        for (Base base : bases) {
            if (base == null) {
                continue;
            }
            if (base.getId().equals(id)) {
                user = (E) base;
            }
        }
        return user;
    }
}
