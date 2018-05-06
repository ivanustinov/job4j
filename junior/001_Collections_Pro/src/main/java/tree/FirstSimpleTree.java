package tree;

import java.util.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 05.05.2018
 */
public class FirstSimpleTree<E extends Comparable<E>> implements SimpleTree<E> {
    private Node<E> root;

    public FirstSimpleTree(E value) {
        this.root = new Node<>(value);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> rsl = findBy(parent);
        if (rsl.isPresent()) {
            rsl.get().add(new Node<>(child));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Queue<Node<E>> nextData = initData();

            public Queue<Node<E>> initData() {
                Queue<Node<E>> data = new LinkedList<>();
                Queue<Node<E>> nextData = new LinkedList<>();
                data.offer(root);
                nextData.offer(root);
                while (!data.isEmpty()) {
                    Node<E> el = data.poll();
                    for (Node<E> child : el.leaves()) {
                        data.offer(child);
                        nextData.add(child);
                    }
                }
                System.out.println(nextData);
                return nextData;
            }

            @Override
            public boolean hasNext() {
                if (nextData.isEmpty()) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public E next() {
                if (nextData.isEmpty()) {
                    throw new NoSuchElementException();
                }
                return nextData.poll().getValue();
            }
        };
    }
}
