package testTask.prefixtree;

import java.util.Set;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.05.2018
 */
public class TriePrefixFileFindString {
    private final TriePrefixFileRead fileRead;

    public TriePrefixFileFindString(String fileToFindIn) {
        this.fileRead = new TriePrefixFileRead(fileToFindIn);
    }

    public Set<Integer> findString(String stringToFind) {
        TriePrefixFileRead.TrieNode v = fileRead.loadFile();
        for (char ch : stringToFind.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return null;
            } else {
                v = v.children.get(ch);
            }
        }
        if (v.leaf == true) {
            return v.numbers;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        TriePrefixFileFindString findString = new TriePrefixFileFindString("Page.txt");
        System.out.println(findString.findString("when"));
    }
}
