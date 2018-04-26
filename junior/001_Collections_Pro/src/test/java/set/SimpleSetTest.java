package set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.04.2018
 */
public class SimpleSetTest {
    SimpleSet<String> set = new SimpleSet<>();
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