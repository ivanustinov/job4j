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
 * @since 17.05.2018
 */
public class TrieHash {
    Map<String, TreeSet<Integer>> children = new HashMap<>();


    public void loadFile(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String a;
            int count = 1;
            while (!((a = reader.readLine()) == null)) {
                for (String s : a.split(" ")) {
                    put(s.toLowerCase(), count);
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

    public void put(String s, Integer number) {
            if (!children.containsKey(s)) {
                children.put(s, new TreeSet<>());
                children.get(s).add(number);
            }
            children.get(s).add(number);
    }

    public Set<Integer> find(String s) {
        return children.get(s.toLowerCase());
    }

    // Usage example
    public static void main(String[] args) {
        TrieHash trie = new TrieHash();
        trie.loadFile("Page.txt");
        System.out.println(trie.find("When"));
    }
}