package set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.04.2018
 */
public class SetOnLinkedListTest {
    SetOnLinkedList<String> set = new SetOnLinkedList<>();
    Iterator<String> it;

    @Before
    public void setUp() throws Exception {
        set.add("Integer");
        set.add("Long");
        set.add("Double");
        set.add("Integer");
        it = set.iterator();
    }

    @Test
    public void testIteratorMethod() {
        assertThat(it.next(), is("Integer"));
        assertThat(it.next(), is("Long"));
        assertThat(it.next(), is("Double"));
        assertThat(it.hasNext(), is(false));
    }

}