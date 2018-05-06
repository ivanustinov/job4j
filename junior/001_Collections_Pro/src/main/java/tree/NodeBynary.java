package tree;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 06.05.2018
 */
public class NodeBynary<E extends Comparable<E>> {
    private NodeBynary<E> left;
    private NodeBynary<E> right;
    private final E value;

    public NodeBynary(final E value) {
        this.value = value;
    }
    public int compare(E value) {
        int result = 0;
        int a = this.value.compareTo(value);
        if (a == -1 || a == 0) {
            result = -1;
        }
        return result;
    }

    public void add(NodeBynary<E> node) {
        if (this.compare(node.getValue()) == -1) {
            right = node;
        } else {
            left = node;
        }
    }
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Gets value
     *
     * @return value of value
     */

    public E getValue() {
        return value;
    }

    /**
     * Gets left
     *
     * @return value of left
     */

    public NodeBynary<E> getLeft() {
        return left;
    }

    /**
     * Gets right
     *
     * @return value of right
     */

    public NodeBynary<E> getRight() {
        return right;
    }
}
