package tree;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

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
        Iterator<Integer> it = tree.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(10));
        assertThat(it.next(), is(5));
        assertThat(it.next(), is(20));
        for (Integer integer : tree) {
            System.out.print(integer + " ");
        }
    }

}