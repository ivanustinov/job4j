package jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

        @Override
        public String toString() {
            return String.format("%s %d", name, id);
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

    public List<String> findAll() {
        List<String> items = new ArrayList<>();
        try (Statement prp = connection.createStatement()) {
            ResultSet rs = prp.executeQuery(conf.get("select"));
            while (rs.next()) {
                items.add(String.format("%s %d", rs.getString("name"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public List<String> findByName(String name) {
        List<String> items = new ArrayList<>();
        try (PreparedStatement prp = connection.prepareStatement(conf.get("select by name"))) {
            prp.setString(1, name);
            ResultSet rs = prp.executeQuery();
            while (rs.next()) {
                items.add(String.format("%s %d", rs.getString("name"), rs.getInt("id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public String findById(int id) {
        String item = "";
        try (PreparedStatement prp = connection.prepareStatement(conf.get("select by id"))) {
            prp.setInt(1, id);
            ResultSet rs = prp.executeQuery();
            rs.next();
            item = String.format("%s %d", rs.getString("name"), rs.getInt("id"));
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
            List<Item> list = new ArrayList<>();
            list.add(new Item("Alex"));
            list.add(new Item("Ivan"));
            list.add(new Item("Ivan"));
            list.add(new Item("Ivan"));
            for (Item item : list) {
                item.setId(tracker.generateId());
                tracker.add(item);
            }
            tracker.delete(list.get(3));
            Item jora = new Item("Jora");
            jora.setId(tracker.generateId());
            tracker.replace(list.get(0).getId(), jora);
            System.out.println(tracker.findAll());
            System.out.println(tracker.findByName("Ivan"));
            System.out.println(tracker.findById(jora.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
