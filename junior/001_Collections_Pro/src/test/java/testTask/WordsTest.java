package testTask;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 15.05.2018
 */
public class WordsTest {
    @Test
    public void compareWords() {
        Words words = new Words();
        System.out.println(words.compareWords("copt", "poct"));
    }
}