package testTask.prefixtree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 14.05.2018
 */

public class TriePrefixFileRead {
    private final TrieNode root = new TrieNode();
    private int count = 1;
    private final String fileToFindIn;

    public TriePrefixFileRead(String fileToFindIn) {
        this.fileToFindIn = fileToFindIn;
    }

    static class TrieNode {
        Map<Character, TrieNode> children = new TreeMap<>();
        boolean leaf;
        TreeSet<Integer> numbers = new TreeSet<>();
    }

    public TrieNode loadFile() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToFindIn));
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
        return root;
    }

    public void put(String s) {
        TrieNode v = root;
        int n = count;
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
        v.numbers.add(n);
    }
}
