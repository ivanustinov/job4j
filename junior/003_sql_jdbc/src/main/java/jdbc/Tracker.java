package jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.06.2018
 */
public class Tracker implements AutoCloseable {
    //JDBC URL, username and PASSWORD of MySQL server
    private static final String URL;
    private static final String USER;
    private static final String PASSWORD;
    private static final String FILENAME = "CreateDB";

    private static final String[] NAMES = {"Ivan", "Petr", "Konstantin", "Roman"};

    //JDBC variables for opening and managing connection
    private static Connection connection;
    private static Statement stmt;

    static {
        String urlUserPassword = readFile(FILENAME);
        String[] ur = urlUserPassword.split(" ");
        URL = ur[0];
        USER = ur[1];
        PASSWORD = ur[2];
        try (Connection connectio_n = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stm_t = connectio_n.createStatement()) {
            connection = connectio_n;
            stmt = stm_t;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

//    public static String initFile() {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME))) {
//            writer.write("jdbc:postgresql://localhost:5432/trackerdb postgres password");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return FILENAME;
//    }

    public static String readFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        String a;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((a = reader.readLine()) != null) {
                builder.append(a);
            }
        } catch (IOException y) {
            y.printStackTrace();
        }
        return builder.toString();
    }


    @Override
    public void close() throws Exception {
        connection.close();
        stmt.close();
    }

    public void checkTable() throws SQLException {
        stmt.execute("CREATE TABLE IF NOT EXISTS tracker(" +
                "id SERIAL PRIMARY KEY," +
                "name text);");
    }


    public void setInsertNames() {
        try (PreparedStatement insertNames = connection.prepareStatement("INSERT INTO tracker (name) VALUES (?);")) {
            for (String name : NAMES) {
                insertNames.setString(1, name);
                insertNames.addBatch();
            }
            insertNames.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try (Tracker tracker = new Tracker()) {
            tracker.checkTable();
            tracker.setInsertNames();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
