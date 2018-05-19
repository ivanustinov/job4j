package testTask.hashtree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.05.2018
 */
public class TrieHashFileFindString {
    private final TrieHashFileRead fileRead;

    public TrieHashFileFindString(String fileName) {
        this.fileRead = new TrieHashFileRead(fileName);
    }

    public Set<Integer> find(String stringToFind) {
        Map<Integer, Character> children = fileRead.loadFile();
        Set<Integer> indexes = new HashSet<>();
        char[] chars = stringToFind.toLowerCase().toCharArray();
        Iterator<Integer> it = children.keySet().iterator();
        int index = 0, i = 0, c;
        if (!it.hasNext()) {
            return null;
        }
        do {
            c = it.next();
            if (i == 0) {
                index = c;
            }
            if (chars[i++] != children.get(c)) {
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
        TrieHashFileFindString trie = new TrieHashFileFindString("Page.txt");
        System.out.println(trie.find("when"));
    }
}
