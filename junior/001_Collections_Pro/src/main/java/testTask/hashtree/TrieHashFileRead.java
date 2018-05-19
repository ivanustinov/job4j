package testTask.hashtree;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 17.05.2018
 */
public class TrieHashFileRead {
    private final String fileName;

    public TrieHashFileRead(String fileName) {
        this.fileName = fileName;
    }

    public Map<Integer, Character> loadFile() {
        Map<Integer, Character> children = new HashMap<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String a;
            int count = 1;
            while ((a = reader.readLine()) != null) {
                for (Character s : a.toLowerCase().toCharArray()) {
                    children.put(count, s);
                    count++;
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return children;
    }
}