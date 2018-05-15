package testTask;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.05.2018
 */
public class WordIndexTest {
    @Test
    public void create() {
        WordIndex index = new WordIndex();
        index.LoadFile("Страница.txt");
        System.out.println(index.getIndexes4Word("All"));
    }

}