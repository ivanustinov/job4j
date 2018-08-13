package appjsp.persistent;

import appjsp.entities.User;

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
public class MemoryStore implements Store<User> {
    private static final MemoryStore INSTANSE = new MemoryStore();
    private static int id = 1;
    private final Map<Integer, User> map = new ConcurrentHashMap<>();

    public static MemoryStore getInstance() {
        return INSTANSE;
    }

    @Override
    public String add(String name, String login) {
        int number = id++;
        User user = new User(number, name, login);
        map.put(number, user);
        return "<p align='center'>user with name " + name + " and login " + login + " has been add</p>";
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
