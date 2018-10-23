package appjsp.persistent;

import appjsp.entities.User;
import appjsp.entities.enums.Cities;
import appjsp.entities.enums.Countries;
import appjsp.entities.enums.UsersRoles;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;

import static appjsp.entities.enums.Cities.NOVGOROD;
import static appjsp.entities.enums.Countries.RUSSIA;
import static appjsp.entities.enums.UsersRoles.ADMIN;

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
            stm.execute("CREATE TABLE IF NOT EXISTS users(id text PRIMARY KEY, role text, coutry text, " +
                    "city text, login text, password text)");
            ResultSet rs = stm.executeQuery("SELECT * FROM users");
            int number = 0;
            while (rs.next()) {
                number++;
            }
            if (number == 0) {
                User petr = new User(ADMIN, RUSSIA, NOVGOROD, "Petr", "petr");
                stm.executeUpdate(String.format("INSERT INTO users VALUES ('%s', '%s', '%s', '%s', '%s', '%s')", petr.getId(),
                        petr.getRole(), petr.getCountry(), petr.getCity(), petr.getLogin(), petr.getPassword()));
            }
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
             PreparedStatement add = connection.prepareStatement("INSERT INTO users VALUES (?, ?, ?, ?, ?, ?)")) {
            add.setString(1, user.getId());
            add.setString(2, user.getRole().toString());
            add.setString(3, user.getCountry().toString());
            add.setString(4, user.getCity().toString());
            add.setString(5, user.getLogin());
            add.setString(6, user.getPassword());
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
    public void adminUpdate(String id, String newRole, String newCountry, String newCity, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET role = ?, country = ?"
                     + "city = ?, login = ?, password = ? WHERE id = ?")) {
            insert.setString(1, newRole);
            insert.setString(2, newCountry);
            insert.setString(3, newCity);
            insert.setString(4, newLogin);
            insert.setString(5, newPassword);
            insert.setString(6, id);
            insert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String id, String newCountry, String newCity, String newLogin, String newPassword) {
        try (Connection connection = source.getConnection();
             PreparedStatement insert = connection.prepareStatement("UPDATE users SET country = ?, "
                     + " city = ?, login = ?, password = ? WHERE id = ?")) {
            insert.setString(1, newCountry);
            insert.setString(2, newCity);
            insert.setString(3, newLogin);
            insert.setString(4, newPassword);
            insert.setString(5, id);
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
                users.add(new User(rs.getString(1), UsersRoles.valueOf(rs.getString(2)),
                        Countries.valueOf(rs.getString(3)),
                        Cities.valueOf(rs.getString(4)), rs.getString(5),
                        rs.getString(6)));
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
            ResultSet rs = stm.executeQuery("SELECT * FROM users WHERE id = '" + id + "'");
            while (rs.next()) {
                user = new User(rs.getString(1), UsersRoles.valueOf(rs.getString(2)), Countries.valueOf(rs.getString(3)),
                        Cities.valueOf(rs.getString(4)), rs.getString(5), rs.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
