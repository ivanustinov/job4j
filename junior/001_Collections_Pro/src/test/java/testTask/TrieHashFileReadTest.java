package testTask;

import org.junit.Before;
import org.junit.Test;
import testTask.hashtree.TrieHashFileFindString;

import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 17.05.2018
 */
public class TrieHashFileReadTest {
    TrieHashFileFindString fileToFindIn;

    @Before
    public void create() {
        fileToFindIn = new TrieHashFileFindString("Page.txt");
    }

    @Test
    public void testMethod() {
        Set<Integer> indexes = fileToFindIn.find("when");
        assertThat(indexes.contains(1), is(true));
        assertThat(indexes.contains(7), is(true));
        assertThat(indexes.contains(56), is(true));
        assertThat(indexes.contains(113), is(true));
        assertThat(indexes.size(), is(4));
    }
}