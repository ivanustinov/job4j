package app.persistent;

import app.entities.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public class MemoryStore implements Store {
    private static final MemoryStore INSTANSE = new MemoryStore();
    private static int id = 1;
    private final Map<Integer, User> map = new ConcurrentHashMap<>();

    public static MemoryStore getInstance() {
        return INSTANSE;
    }

    @Override
    public String add(String name, String login) {
        String result = "<p align=center>insert name or/and login field</p>";
        if (!name.equals("") && !login.equals("")) {
            int number = id++;
            User user = new User(number, name, login);
            map.put(number, user);
            result = "<p align='center'>user with name " + name + " and login " + login + " has been add</p>";
        }
        return result;
    }

    @Override
    public String delete(int id) {
        return map.remove(id) == null ? "<p align='center'>no user with such id in the store</p>" :
                "<p align='center'> user with id " + id +
                        " has been deleted</p>";
    }

    @Override
    public String update(int id, String newName, String newLogin) {
        User user = map.get(id);
        user.setName(newName);
        user.setLogin(newLogin);
        return "<p align='center'> user with id " + id + " has been updated</p>";
    }

    @Override
    public Collection<User> findAll() {
        return map.size() == 0 ? null : map.values();
    }


    @Override
    public String findById(int id) {
        User user = map.get(id);
        return user != null ? user.toString() : "no user in the store with such id";
    }
}
