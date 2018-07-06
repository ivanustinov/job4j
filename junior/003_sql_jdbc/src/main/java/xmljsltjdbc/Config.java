package xmljsltjdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.06.2018
 */
public class Config {

    private final String[] keys = new String[]{"drivername", "connectionstring", "createtable",
            "delete", "select", "insert"};
    private final HashMap<String, String> config = new HashMap<>();


    public HashMap<String, String> getConfig() {
        initFile();
        readFile("CreateSQLDB");
        return config;
    }


    public void initFile() {
        try (PrintWriter out = new PrintWriter("CreateSQLDB")) {
            out.print("org.sqlite.JDBC\n"
                    + "jdbc:sqlite:test.db\n"
                    + "CREATE TABLE IF NOT EXISTS entry(field int NOT NULL PRIMARY KEY AUTOINCREMENT)\n"
                    + "DELETE FROM entry\n"
                    + "SELECT * FROM entry\n"
                    + "INSERT INTO entry  VALUES (?)\n");
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
