package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.06.2018
 */
public class Conf {
    private final String[] keys = new String[]{"url", "user", "password", "update", "insert", "delete",
            "select", "select by id", "select by name", "checkTable"};
    private final HashMap<String, String> config = new HashMap<>();


    public HashMap<String, String> getConfig() {
        readFile("CreateDB");
        return config;
    }



    public void readFile(String filename) {
        String a;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int i = 0;
            while ((a = reader.readLine()) != null) {
                config.put(keys[i++], a);
            }
        } catch (IOException y) {
            y.printStackTrace();
        }
    }
}
