package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
        initFile();
        readFile("CreateDB");
        return config;
    }


    public void initFile() {
        try (PrintWriter out = new PrintWriter("CreateDB")) {
            out.print("jdbc:postgresql://localhost:5432/trackerdb\n"
                    + "postgres\n"
                    + "password\n"
                    + "UPDATE tracker SET name = ?, id = ? WHERE id = ?\n"
                    + "INSERT INTO tracker (id, name) VALUES (?, ?);\n"
                    + "DELETE FROM tracker WHERE id = ?\n"
                    + "SELECT * FROM tracker\n"
                    + "SELECT * FROM tracker WHERE id = ?\n"
                    + "SELECT * FROM tracker WHERE name = ?\n"
                    + "CREATE TABLE IF NOT EXISTS tracker(id integer PRIMARY KEY, name text);");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
