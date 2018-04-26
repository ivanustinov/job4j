package list;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.04.2018
 */

public class Node<E> {
    E value;
    Node<E> next;

    public Node(E value) {
        this.value = value;
    }
}
