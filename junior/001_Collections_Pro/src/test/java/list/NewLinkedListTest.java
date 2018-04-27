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

    @Test
    public void testCycleMethod() {
        NewLinkedList<Integer> list = new NewLinkedList<>();
        Node<Integer> first = new Node<>(1);
        Node<Integer> second = new Node<>(2);
        Node<Integer> third = new Node<>(3);
        list.add(first);
        list.add(second);
        list.add(third);
        list.add(first);
        assertThat(list.hasCycle(), is(true));
    }

    @Test
    public void testCycleMethodWhenFalse() {
        NewLinkedList<Integer> list = new NewLinkedList<>();
        list.add(new Node<>(1)) ;
        list.add(new Node<>(2)) ;
        list.add(new Node<>(3)) ;
        assertThat(list.hasCycle(), is(false));
    }
}