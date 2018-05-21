package map;

import java.util.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 01.05.2018
 */
public class SimpleHashMap<K, V> implements Iterable {
    private Entry<?, ?>[] table = new Entry[11];
    private int modCount = 0;
    private int count;
    private float loadFactor = 0.75f;
    private int threshold = (int) (table.length * loadFactor);

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            int expectedModCount = modCount;
            int newInst = 0;
            int ifElse = count;

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (ifElse > 0) {
                    result = true;
                }
                return result;
            }

            @Override
            public V next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                for (int i = newInst; i < table.length; i++) {
                    if (table[i] != null) {
                        newInst = i;
                        ifElse--;
                        return (V) table[newInst++].value;
                    } else {
                        continue;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    public int tableSize() {
        return table.length;
    }

    private static class Entry<K, V> {
        final int hash;
        final K key;
        V value;

        protected Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }

        public int hashCode() {
            return hash ^ Objects.hashCode(value);
        }

        public String toString() {
            return key.toString() + "=" + value.toString();
        }
    }

    public boolean insert(K key, V value) {
        // Make sure the value is not null
        if (value == null) {
            throw new NullPointerException();
        }
        // Makes sure the key is not already in the hashtable.
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;
        Entry<?, ?> e = table[index];
        while (e != null) {
            if (e.key.equals(key) && e.hash == hash) {
                ((Entry<K, V>) e).value = value;
                return true;
            } else {
                index++;
                if (index == table.length) {
                    rehash();
                    insert(key, value);
                }
                e = table[index];
            }
        }
        addEntry(hash, key, value, index);
        return true;
    }

    private void addEntry(int hash, K key, V value, int index) {
        modCount++;
        if (count >= threshold) {
            // Rehash the table if the threshold is exceeded
            rehash();
            index = (hash & 0x7FFFFFFF) % table.length;
        }
        // Creates the new entry.
        table[index] = new Entry<>(hash, key, value);
        count++;
    }


    protected void rehash() {
        int oldCapacity = table.length;
        Entry<?, ?>[] oldMap = table;
        int newCapacity = (oldCapacity << 1) + 1;
        Entry<?, ?>[] newMap = new Entry<?, ?>[newCapacity];
        modCount++;
        threshold = (int) (newCapacity * loadFactor);
        table = newMap;
        for (int i = oldCapacity; i-- > 0;) {
            Entry<K, V> old = (Entry<K, V>) oldMap[i];
            if (old != null) {
                int index = (old.hash & 0x7FFFFFFF) % newCapacity;
                newMap[index] = old;
            }
        }
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;
        Entry<?, ?> e = table[index];
        while (e != null) {
            if ((e.hash == hash) && e.key.equals(key)) {
                return (V) e.value;
            } else {
                index++;
                if (index == table.length) {
                    return null;
                }
                e = table[index];
            }
        }
        return null;
    }

    public boolean remove(Object key) {
        int hash = key.hashCode();
        int index = (hash & 0x7FFFFFFF) % table.length;
        Entry<?, ?> e = table[index];
        while (e != null) {
            if ((e.hash == hash) && e.key.equals(key)) {
                table[index] = null;
                return true;
            } else {
                index++;

                if (index == table.length) {
                    return false;
                }
                e = table[index];
            }
        }
        return false;
    }
}
