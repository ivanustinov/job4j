package testtask;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.05.2018
 */
public class WordsTrie {
    public boolean compareWords(String word, String nextWord) {
        Boolean result = false;
        int a = 0;
        List<Character> word1 = new ArrayList<>();
        for (char c : word.toCharArray()) {
            word1.add(c);
        }
        for (Character c : nextWord.toCharArray()) {
            if (word1.contains(c)) {
                word1.remove(c);
                continue;
            } else {
                break;
            }
        }
        if (word1.size() == 0) {
            result = true;
        }
        return result;
    }
}
