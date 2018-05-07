package tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 06.05.2018
 */
public class BynarySearchTree<E extends Comparable<E>> implements Iterable<E> {
    NodeBynary<E> root;


    public BynarySearchTree(E value) {
        root = new NodeBynary<>(value);
    }


    public void add(E value) {
        NodeBynary<E> toFind = root;
        NodeBynary<E> parent = toFind;
        while (toFind != null) {
            parent = toFind;
            if (toFind.compare(value) == -1) {
                toFind = toFind.getRight();
            } else {
                toFind = toFind.getLeft();
            }
        }
        parent.add(new NodeBynary<>(value));
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<NodeBynary<E>> queue = setQueue();

            public Queue<NodeBynary<E>> setQueue() {
                queue = new LinkedList<>();
                initData(root);
                return queue;
            }

            public void initData(NodeBynary<E> node) {
                if (node != null) {
                    queue.offer(node);
                    initData(node.getLeft());
                    initData(node.getRight());
                }
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }

            @Override
            public E next() {
                if (queue.isEmpty()) {
                    throw new NoSuchElementException();
                }
                return queue.poll().getValue();
            }
        };
    }
}
