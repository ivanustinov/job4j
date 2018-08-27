package appjsp.persistent;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

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
            stm.execute("CREATE TABLE IF NOT EXISTS users(id serial PRIMARY KEY, role text, login text," +
                    "password text)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(UsersRoles role, String login, String password) {
        try (Connection connection = source.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO users(role, login, password)"
                     + "VALUES (?, ?, ?)")) {
            add.setString(1, role.toString());
            add.setString(2, login);
            add.setString(3, password);
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = source.getConnection();
             PreparedStatement delete = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            delete.setInt(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adminUpdate(int id, String newRole, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET role = ?, login = ?, " +
                     "password = ?"
                     + "WHERE id = ?")) {
            insert.setString(1, newRole);
            insert.setString(2, newLogin);
            insert.setString(3, newPassword);
            insert.setInt(4, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET login = ?, " +
                     "password = ?"
                     + "WHERE id = ?")) {
            insert.setString(1, newLogin);
            insert.setString(2, newPassword);
            insert.setInt(3, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = source.getConnection();
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                users.add(new User(rs.getInt(1), UsersRoles.valueOf(rs.getString(2)), rs.getString(3),
                        rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = source.getConnection();
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE id = " + id);
            while (rs.next()) {
                user = new User(id, UsersRoles.valueOf(rs.getString(2)), rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
