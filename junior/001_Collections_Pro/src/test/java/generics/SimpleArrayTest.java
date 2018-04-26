package generics;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.04.2018
 */
public class SimpleArrayTest {
    SimpleArray<Integer> simple = new SimpleArray<>(4);
    Integer e = null;

    @Before
    public void fillSimpleArray() {
        simple.add(1);
        simple.add(2);
        simple.add(3);
    }

    @Test
    public void testAddMethod() {
        assertThat(simple.get(0), is(1));
        assertThat(simple.get(1), is(2));
    }

    @Test
    public void testSetMethod() {
        simple.set(0, 8);
        assertThat(simple.get(0), is(8));
    }
    @Test
    public void testDeleteMethod() {
        simple.delete(2);
        assertThat(simple.get(2), is(e));
    }

    @Test
    public void testIterator() {
        Iterator iterator = simple.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(e));
        assertThat(iterator.hasNext(), is(false));

    }
}