package xml_jslt_jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void connect() {
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

    public void createTable() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(config.CREATETABLE);
            stmt.execute(config.DELETE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generate(int n) {
        try (PreparedStatement prp = connection.prepareStatement(config.INSERT)) {
            for (int i = 1; i < n; i++) {
                prp.setInt(1, i);
                prp.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<StoreXML.Field> selectTable() {
        List<StoreXML.Field> values = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(config.SELECT)) {
            while (rs.next()) {
                values.add(new StoreXML.Field(Integer.valueOf(rs.getString(1))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return values;
    }


}
