package jdbc;

import java.sql.*;
import java.util.HashMap;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.06.2018
 */
public class Tracker implements AutoCloseable {

    private final HashMap<String, String> conf;
    private Connection connection;
    private Statement checkTable;

    static class Item {
        private int id;
        private String name;

        public Item(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    public int generateId() {
        return (int) (Math.random() * 1000);
    }

    public Tracker(Conf config) {
        conf = config.getConfig();
        try {
            connection = DriverManager.getConnection(conf.get("url"), conf.get("user"), conf.get("password"));
            checkTable = connection.createStatement();
            checkTable.execute(conf.get("checkTable"));
            checkTable.execute("DELETE FROM tracker");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Item add(Item item) {
        try (PreparedStatement prp = connection.prepareStatement(conf.get("insert"))) {
            prp.setInt(1, item.getId());
            prp.setString(2, item.getName());
            prp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    public void delete(Item item) {
        try (PreparedStatement prp = connection.prepareStatement(conf.get("delete"))) {
            prp.setInt(1, item.getId());
            prp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void replace(int id, Item item) {
        try (PreparedStatement prp = connection.prepareStatement(conf.get("update"))) {
            prp.setString(1, item.getName());
            prp.setInt(2, item.getId());
            prp.setInt(3, id);
            prp.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public HashMap<Integer, String> findAll() {
        HashMap<Integer, String> items = new HashMap<>();
        try (Statement prp = connection.createStatement()) {
            ResultSet rs = prp.executeQuery(conf.get("select"));
            while (rs.next()) {
                items.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public HashMap<Integer, String> findByName(String name) {
        HashMap<Integer, String> items = new HashMap<>();
        try (PreparedStatement prp = connection.prepareStatement(conf.get("select by name"))) {
            prp.setString(1, name);
            ResultSet rs = prp.executeQuery();
            while (rs.next()) {
                items.put(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public Item findById(int id) {
        Item item = null;
        try (PreparedStatement prp = connection.prepareStatement(conf.get("select by id"))) {
            prp.setInt(1, id);
            ResultSet rs = prp.executeQuery();
            while (rs.next()) {
                item = new Item(rs.getString("name"));
                item.setId(rs.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        checkTable.close();
    }

    public static void main(String[] args) {

        try (Tracker tracker = new Tracker(new Conf())) {
            Item first = new Item("Alex");
            Item second = new Item("Ivan");
            Item third = new Item("Ivan");
            Item forth = new Item("Ivan");
            first.setId(tracker.generateId());
            second.setId(tracker.generateId());
            third.setId(tracker.generateId());
            forth.setId(tracker.generateId());
            tracker.add(first);
            tracker.add(second);
            tracker.add(forth);
            tracker.delete(second);
            tracker.replace(first.getId(), third);
            System.out.println(tracker.findAll());
            System.out.println(tracker.findByName("Ivan"));
            System.out.println(second.getId());
            System.out.println(tracker.findById(third.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
