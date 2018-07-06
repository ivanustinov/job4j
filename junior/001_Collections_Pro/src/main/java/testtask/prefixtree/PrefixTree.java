package testtask.prefixtree;

import testtask.FileRead;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 19.05.2018
 */
public class PrefixTree {
    private final TrieNode root = new TrieNode();
    private int count = 1;

    public PrefixTree(String text) {
        for (String s : text.split(" ")) {
            put(s);
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean leaf;
        Set<Integer> numbers = new TreeSet<>();
    }


    public Set<Integer> indexOf(String stringToFind) {
        TrieNode v = root;
        for (char ch : stringToFind.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                return null;
            } else {
                v = v.children.get(ch);
            }
        }
        return v.leaf ? v.numbers : null;
    }

    public void put(String s) {
        TrieNode v = root;
        int number = count;
        for (char c : s.toLowerCase().toCharArray()) {
            if (c == '.' || c == ',' || c == '!' || c == '?' || c == ':') {
                count++;
                continue;
            }
            if (!v.children.containsKey(c)) {
                v.children.put(c, new TrieNode());
            }
            v = v.children.get(c);
            count++;
        }
        count++;
        v.leaf = true;
        v.numbers.add(number);
    }

    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree(new FileRead().readFile("Page.txt"));
        System.out.println(trie.indexOf("when"));
    }
}
