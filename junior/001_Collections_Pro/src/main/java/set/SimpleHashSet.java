package set;

import java.util.Arrays;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.04.2018
 */
public class SimpleHashSet<E extends Element> {
    private int modCount;
    private Object[] container;
    private static final double INCREASECAPACITY = 1.5;

    public SimpleHashSet(int number) {
        this.container = new Object[number];
    }

    public int size() {
        return container.length;
    }

    public boolean add(E object) {
        modCount++;
        int index = hashFunction(object, container.length);
        if (container[index] != null) {
            int newLength = (int) (container.length * INCREASECAPACITY);
            Object[] containerNew = new Object[newLength];
            for (Object o : container) {
                if (o == null) {
                    continue;
                }
                index = hashFunction((E)o, newLength);
                containerNew[index] = o;
            }
            container = containerNew;
            add(object);
        } else {
            container[index] = object;
        }
        return true;
    }

    public boolean contains(E object) {
        int index = hashFunction(object, container.length);
        if (container[index] != null) {
            return true;
        } else return false;
    }

    public boolean remove(E object) {
        int index = hashFunction(object, container.length);
        if (contains(object)) {
            container[index] = null;
            return true;
        } else return false;
    }

    public int hashFunction(E object, int arrsize) {
        return object.getKey() % arrsize;
    }

}
