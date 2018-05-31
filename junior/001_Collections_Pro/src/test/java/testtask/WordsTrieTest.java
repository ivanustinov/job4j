package testtask;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */
public class WordsTrieTest {

    @Test
    public void compareWords() {
        WordsTrie words = new WordsTrie();
        assertThat(words.compareWords("poct", "copt"), is(true));
        assertThat(words.compareWords("pooct", "copt"), is(false));
        assertThat(words.compareWords("room", "mooooor"), is(false));
    }
}