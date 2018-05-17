package testTask;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.05.2018
 */

public class TriePrefix {
    TrieNode root = new TrieNode();
    int count = 1;

    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
        boolean leaf;
        TreeSet<Integer> numbers = new TreeSet<>();
    }

    public void LoadFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String a;

            while (!((a = reader.readLine()) == null)) {
                for (String s : a.split(" ")) {
                    put(s);
                    count++;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put(String s) {
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
            if (!v.children.containsKey(ch)) {
                v.children.put(ch, new TrieNode());
            }
            v = v.children.get(ch);
        }
        v.leaf = true;
        v.numbers.add(count);
    }

    public Set<Integer> find(String s) {
        TrieNode v = root;
        for (char ch : s.toLowerCase().toCharArray()) {
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

    // Usage example
    public static void main(String[] args) {
        TriePrefix trie = new TriePrefix();
        trie.LoadFile("Page.txt");
        System.out.println(trie.find("when"));

    }
}
