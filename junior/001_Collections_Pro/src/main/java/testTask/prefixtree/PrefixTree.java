package testTask.prefixtree;

import testTask.FileRead;

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
            count++;
        }
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean leaf;
        TreeSet<Integer> numbers = new TreeSet<>();
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
        if (v.leaf == true) {
            return v.numbers;
        } else {
            return null;
        }
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
        v.leaf = true;
        v.numbers.add(number);
    }

    public static void main(String[] args) {
        PrefixTree trie = new PrefixTree(new FileRead().readFile("Page.txt"));
        System.out.println(trie.indexOf("when"));
    }
}
