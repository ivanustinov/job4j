package appjsp.persistent;

import appjsp.entities.User;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 13.08.2018
 */
public class DbStore implements Store<User> {
    private final BasicDataSource source = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    private DbStore() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        source.setUrl("jdbc:postgresql://localhost:5432/usersdb");
        source.setUsername("postgres");
        source.setPassword("password");
        source.setMinIdle(5);
        source.setMaxIdle(10);
        source.setMaxOpenPreparedStatements(100);
        try (Connection connection = source.getConnection();
             Statement stm = connection.createStatement()) {
            stm.execute("CREATE TABLE IF NOT EXISTS users(id serial PRIMARY KEY, name text, login text)");
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean add(String name, String login) {
        try (Connection connection = source.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO users(name, login)"
                     + "VALUES (?, ?)")) {
            add.setString(1, name);
            add.setString(2, login);
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = source.getConnection();
             PreparedStatement delete = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(int id, String newName, String newLogin) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET name = ?, login = ?" +
                     "WHERE id = ?")) {
            insert.setString(1, newName);
            insert.setString(2, newLogin);
            insert.setInt(3, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        return null;
    }

//    public static void main(String[] args) {
//        DbStore dbStore = getInstance();
//            dbStore.add("Ivan", "Port");
//            System.out.println("ghg");
//    }
}
