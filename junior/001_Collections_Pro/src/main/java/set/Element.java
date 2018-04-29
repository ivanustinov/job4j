package set;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.04.2018
 */
public class Element {
    private int key;

    public Element(int key) {
        this.key = key;
    }

    /**
     * Gets key
     *
     * @return value of key
     */

    public int getKey() {
        return key;
    }

    @Override
    public String toString() {
        return String.valueOf(getKey());
    }
}
