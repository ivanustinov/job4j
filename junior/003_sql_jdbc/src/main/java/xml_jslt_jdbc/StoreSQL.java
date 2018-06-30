package xml_jslt_jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.06.2018
 */
public class StoreSQL implements AutoCloseable {
    private Connection connection;
    private Config config;

    public StoreSQL(Config config) {
        this.config = config;
    }

    public void run() {
        try {
            Class.forName(config.DRIVERNAME);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return;
        }
        try {
            connection = DriverManager.getConnection(config.CONNECTIONSTRING);
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return;
        }
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public void fillAllTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute(config.CREATETABLE);
        stmt.execute(config.DELETE);
        for (int i = 1; i < 10000; i++) {
            stmt.execute(config.INSERT);
        }
        stmt.close();
    }

    public static void main(String[] args) {
        try (StoreSQL store = new StoreSQL(new Config())) {
            store.run();
            store.fillAllTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
