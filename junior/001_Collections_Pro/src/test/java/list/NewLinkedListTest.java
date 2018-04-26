package list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 26.04.2018
 */

public class NewLinkedListTest {
    NewLinkedList<Integer> list = new NewLinkedList<>();
    Node<Integer> first = new Node<>(1);
    Node<Integer> second = new Node<>(2);
    Node<Integer> third = new Node<>(3);

    @Before
    public void setUp() throws Exception {
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(first);
    }

    @Test
    public void testCycleMethod() {
        assertThat(list.hasCycle(), is(true));
    }
}