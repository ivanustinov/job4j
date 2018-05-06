package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 06.05.2018
 */
public class BynarySearchTreeTest {
    BynarySearchTree<Integer> tree = new BynarySearchTree<>(1);
    @Before
    public void initTree() {
        tree.add(10);
        tree.add(20);
        tree.add(5);
        tree.add(15);
        tree.add(30);
    }

    @Test
    public void testIterator() {
        for (Integer integer : tree) {
            System.out.print(integer +" ");
        }
    }

}