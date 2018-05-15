package testTask;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 15.05.2018
 */
public class Words {
    public boolean compareWords(String word, String nextWord) {

        Boolean result = false;
        List<Character> word1 = new ArrayList<>();
        for (char c : word.toCharArray()) {
            word1.add(c);
        }
        List<Character> word2 = new ArrayList<>();
        for (char c : nextWord.toCharArray()) {
            word2.add(c);
        }
        if (word1.containsAll(word2) && word1.size() == word2.size()) {
            result = true;
        }
        return result;
    }
}
