package list;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 25.04.2018
 */
public class MyQueueTest {
    MyQueue<String> queue = new MyQueue<>();
    @Before
    public void setUp() throws Exception {
        queue.push("Integer");
        queue.push("Long");
        queue.push("Double");
    }

    @Test
    public void testPopMethod() {
        assertThat(queue.pop(), is("Integer"));
        assertThat(queue.size, is(2));
        assertThat(queue.pop(), is("Long"));
        Iterator<String> it = queue.iterator();
        assertThat(it.next(), is("Double"));
        assertThat(it.hasNext(), is(false));
    }
}