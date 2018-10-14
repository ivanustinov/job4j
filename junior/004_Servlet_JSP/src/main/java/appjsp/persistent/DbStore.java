package appjsp.persistent;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

import static appjsp.entities.UsersRoles.ADMIN;

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
            stm.execute("CREATE TABLE IF NOT EXISTS users(id text PRIMARY KEY, role text, login text,"
                    + "password text)");
            ResultSet rs = stm.executeQuery("SELECT * FROM users");
            int number = 0;
            while (rs.next()) {
                number++;
            }
            if (number == 0) {
                User petr = new User(ADMIN, "Petr", "petr");
                String id = petr.getId();
                stm.executeUpdate("INSERT INTO users VALUES ('" + id + "', 'ADMIN', 'Petr', 'petr')");
            }
            System.out.println(number);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        try (Connection connection = source.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?)")) {
            add.setString(1, user.getId());
            add.setString(2, user.getRole().toString());
            add.setString(3, user.getLogin());
            add.setString(4, user.getPassword());
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        try (Connection connection = source.getConnection();
             PreparedStatement delete = connection.prepareStatement("DELETE FROM users WHERE id = ?")) {
            delete.setString(1, id);
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void adminUpdate(String id, String newRole, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET role = ?, login = ?, "
                     + "password = ?"
                     + "WHERE id = ?")) {
            insert.setString(1, newRole);
            insert.setString(2, newLogin);
            insert.setString(3, newPassword);
            insert.setString(4, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET login = ?, "
                     + "password = ?"
                     + "WHERE id = ?")) {
            insert.setString(1, newLogin);
            insert.setString(2, newPassword);
            insert.setString(3, id);
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
                users.add(new User(rs.getString(1), UsersRoles.valueOf(rs.getString(2)), rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findById(String id) {
        User user = null;
        try (Connection connection = source.getConnection();
             Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE id = " + id);
            while (rs.next()) {
                user = new User(UsersRoles.valueOf(rs.getString(2)), rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
