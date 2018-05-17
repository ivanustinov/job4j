package testTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeSet;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 17.05.2018
 */
public class TrieHashTest {
    TrieHash tree = new TrieHash();
    @Before
    public void create() {
        tree.loadFile("Page.txt");
    }

    @Test
    public void testMethod() {
        TreeSet<Integer> numbers = new TreeSet<>();
        numbers.add(1);
        numbers.add(7);
        numbers.add(19);
        assertThat(tree.find("When"), is(numbers));
    }
}