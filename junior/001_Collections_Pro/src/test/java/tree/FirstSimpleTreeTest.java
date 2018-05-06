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
 * @since 05.05.2018
 */
public class FirstSimpleTreeTest {

    FirstSimpleTree<Integer> tree = new FirstSimpleTree<>(1);

    @Before
    public void fillTheTable() {
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 6);
        tree.add(4, 7);
        tree.add(1, 8);
    }

    @Test
    public void testToFillTree() {
        assertThat(tree.findBy(4).isPresent(), is(true));
    }

    @Test
    public void testIterator() {
        Iterator<Integer> it = tree.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
    }

}