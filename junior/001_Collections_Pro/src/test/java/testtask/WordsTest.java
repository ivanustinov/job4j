package testtask;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 15.05.2018
 */
public class WordsTest {
    Words words;

    @Before
    public void create() {
        words = new Words();
    }
    @Test
    public void compareWords() {
        assertThat(words.compareWords("poct", "copt"), is(true));
        assertThat(words.compareWords("pooct", "copt"), is(false));
        assertThat(words.compareWords("room", "moor"), is(true));
    }
}