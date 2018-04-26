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
public class SimpleStackTest {
    SimpleStack<String> stack = new SimpleStack<>();
    @Before
    public void setUp() throws Exception {
        stack.push("Integer");
        stack.push("Long");
        stack.push("Double");
    }

    @Test
    public void testPopMethod() {
        assertThat(stack.pop(), is("Double"));
        assertThat(stack.getSize(), is(2));
        assertThat(stack.pop(), is("Long"));
        Iterator<String> it = stack.iterator();
        assertThat(it.next(), is("Integer"));
        assertThat(it.hasNext(), is(false));
    }
}