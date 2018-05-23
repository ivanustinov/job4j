package testtask;

import java.util.Collection;
import java.util.TreeMap;

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
        TreeMap<Integer, Character> word1 = new TreeMap<>();
        for (char c : word.toCharArray()) {
            word1.put(a++, c);
        }
        Collection<Character> w = word1.values();
        for (char c : nextWord.toCharArray()) {
            if (w.remove(c)) {
                continue;
            } else {
                break;
            }
        }
        if (w.size() == 0) {
            result = true;
        }
        return result;
    }
}
