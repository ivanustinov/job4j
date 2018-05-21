package testtask.hashtree;

import testtask.FileRead;

import java.util.*;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.05.2018
 */
public class Cashe {
    private int count = 1;
    private final Map<Integer, Character> numbersOfCharacters = new HashMap<>();
    private final Set<Integer> indexes = new HashSet<>();

    public Cashe(String text) {
        for (Character character : text.toLowerCase().toCharArray()) {
            numbersOfCharacters.put(count++, character);
        }
    }

    public Set<Integer> indexOf(String stringToFind) {
        char[] chars = stringToFind.toLowerCase().toCharArray();
        Iterator<Integer> it = numbersOfCharacters.keySet().iterator();
        int index = 0, i = 0, c;
        if (!it.hasNext()) {
            return null;
        }
        do {
            c = it.next();
            if (i == 0) {
                index = c;
            }
            if (chars[i++] != numbersOfCharacters.get(c)) {
                i = 0;
            }
            if (i == chars.length) {
                i = 0;
                indexes.add(index);
            }
        }
        while (it.hasNext());
        return indexes;
    }

    // Usage example
    public static void main(String[] args) {
        Cashe trie = new Cashe(new FileRead().readFile("Page.txt"));
        System.out.println(trie.indexOf("when"));
    }
}
