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
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private final static DbStore INSTANCE = new DbStore();

    //jdbc:sqlite:junior//004_Servlet_JSP/users.db
    //jdbc:postgresql://localhost:5432/usersdb
    private DbStore() {
        SOURCE.setUrl("jdbc:sqlite:junior//004_Servlet_JSP/users");
//        SOURCE.setUsername("postgres");
//        SOURCE.setPassword("password");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        try (Connection connection = SOURCE.getConnection();
             Statement stm = connection.createStatement()) {
            stm.execute("CREATE TABLE IF NOT EXISTS users(id integer PRIMARY KEY , name text, login text)");
            System.out.println("Table created");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }

    @Override
    public String add(String name, String login) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement add = connection.prepareStatement("INSERT INTO users(name, login)"
                     + "VALUES (?, ?)")) {
            add.setString(1, name);
            add.setString(2, login);
            add.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "<p align='center'>user with name " + name + " and login " + login + " has been add</p>";
    }

    @Override
    public String delete(int id) {
        return null;
    }

    @Override
    public String update(int id, String newName, String newLogin) {
        return null;
    }

    @Override
    public Collection<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        try (Connection connection = SOURCE.getConnection();
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
    public String findById(int id) {
        return null;
    }

//    public static void main(String[] args) {
//        DbStore dbStore = getInstance();
//        dbStore.add("Ivan", "Bogun");
//        if (dbStore.findAll().size() == 0) {
//            System.out.println("ghg");
//        }
//    }
}
