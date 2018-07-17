package vocancyparcer;

import java.util.Map;
import java.util.TreeMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 16.07.2018
 */
public class PrefixJava {
    private TrieNode root = new TrieNode();

    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean leaf;
    }


    public boolean indexOf(String stringToFind, TrieNode root) {
        TrieNode v = root;
        for (char ch : stringToFind.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return false;
            } else {
                v = v.children.get(ch);
            }
        }
        return v.leaf;
    }

    public boolean put(String s) {
        TrieNode v = new TrieNode();
        for (String word : s.split(" ")) {
            TrieNode d = v;
            for (char c : word.toLowerCase().toCharArray()) {
                if (c == '.' || c == ',' || c == '!' || c == '?' || c == ':') {
                    continue;
                }
                if (!d.children.containsKey(c)) {
                    d.children.put(c, new TrieNode());
                }
                d = d.children.get(c);
            }
            d.leaf = true;
        }
        return indexOf("java", v);
    }

    public static void main(String[] args) {
        PrefixJava java = new PrefixJava();
        String text = "Senior JavaScript";
        System.out.println(text.contains("Java"));
        System.out.println(java.put(text));
    }
}
