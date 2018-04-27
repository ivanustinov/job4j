package list;

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


public class SimpleLinkedListTest {

    SimpleLinkedList<String> list = new SimpleLinkedList<>();

    @Before
    public void setUp() throws Exception {
        list.add("Ivan");
        list.add("Alexandr");
        list.add("Gosha");

    }


    @Test
    public void testMetodGet() {
        assertThat(list.get(1), is("Alexandr"));

    }


    @Test
    public void testIterator() {
        Iterator<String> it = list.iterator();
        assertThat(it.next(), is("Ivan"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Alexandr"));
        assertThat(it.next(), is("Gosha"));
        assertThat(it.hasNext(), is(false));

    }

    @Test(expected = ConcurrentModificationException.class)
    public void testIteratorAfterAddingNewObject() {
        Iterator<String> it = list.iterator();
        list.add("Salman");
        it.next();
        it = list.iterator();
        assertThat(it.next(), is("Ivan"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Alexandr"));
        assertThat(it.next(), is("Gosha"));
        assertThat(it.next(), is("Salman"));
    }
} 