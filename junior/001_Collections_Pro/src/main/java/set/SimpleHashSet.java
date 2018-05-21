package set;

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
        while (container[index] != null) {
            index++;
            if (index == container.length) {
                int oldCapacity = container.length;
                Object[] oldMap = container;
                int newCapacity = (int) (oldCapacity * INCREASECAPACITY);
                Object[] newMap = new Object[newCapacity];
                modCount++;
                container = newMap;
                for (int i = oldCapacity; i-- > 0;) {
                    E old = (E) oldMap[i];
                    if (old != null) {
                        index = hashFunction(old, newCapacity);
                        newMap[index] = old;
                    }
                }
            }
        }
        container[index] = object;
        return true;

    }

    public boolean contains(E object) {
        int index = hashFunction(object, container.length);
        return container[index] != null;
    }

    public boolean remove(E object) {
        int index = hashFunction(object, container.length);
        if (contains(object)) {
            container[index] = null;
            return true;
        } else {
            return false;
        }
    }

    public int hashFunction(E object, int arrsize) {
        return object.getKey() % arrsize;
    }

}
