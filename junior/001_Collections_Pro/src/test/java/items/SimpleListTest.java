package items;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class SimpleListTest {
    SimpleList<Integer> list = new SimpleList<>();

    @Before
    public void setUp() throws Exception {
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
    }

    @Test
    public void testTheList() {
        assertThat(list.getContainer().length, is(5));
        assertThat(list.get(0), is(1));
        assertThat(list.get(3), is(4));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testArrayIndexOutOfBoundsExeption() {
        list.get(6);
    }

    @Test
    public void testMaxFill() {
        list.add(5);
        assertThat(list.getContainer().length, is(7));
        assertThat(list.get(4), is(5));
    }


    @Test(expected = ConcurrentModificationException.class)
    public void testIterator() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        list.add(3);
        it.next();
    }
}