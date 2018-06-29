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
    private static final String FILENAME = "CreateDB";
    private static final String[] KEYS = new String[]{"url", "user", "password", "update", "insert", "delete",
            "select", "select by id", "select by name", "checkTable"};
    private static final HashMap<String, String> CONFIG = new HashMap<>();

    static {
//        initFile();
        readFile(FILENAME);
    }

    public HashMap<String, String> getConfig() {
        return CONFIG;
    }


//    public static void initFile() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("CreateDB"))) {
//            writer.write("jdbc:postgresql://localhost:5432/trackerdb\n" +
//                    "postgres\n" +
//                    "password\n" +
//                    "UPDATE tracker SET name = ?, id = ? WHERE id = ?\n" +
//                    "INSERT INTO tracker (id, name) VALUES (?, ?);\n" +
//                    "DELETE FROM tracker WHERE id = ?\n" +
//                    "SELECT * FROM tracker\n" +
//                    "SELECT * FROM tracker WHERE id = ?\n" +
//                    "SELECT * FROM tracker WHERE name = ?\n" +
//                    "CREATE TABLE IF NOT EXISTS tracker(id integer PRIMARY KEY, name text);");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void readFile(String filename) {
        String a;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            int i = 0;
            while ((a = reader.readLine()) != null) {
                CONFIG.put(KEYS[i++], a);
            }
        } catch (IOException y) {
            y.printStackTrace();
        }
    }
}
