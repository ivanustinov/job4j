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
    public User add(String name, String login) {
        User user = null;
        if (!name.equals("") && !login.equals("")) {
            int number = id++;
            user = new User(number, name, login);
            map.put(number, user);
        }
        return user;
    }

    @Override
    public User delete(int id) {
        return map.remove(id);
    }

    @Override
    public User update(int id, String newName, String newLogin) {
        User user = map.get(id);
        user.setName(newName);
        user.setLogin(newLogin);
        return user;
    }

    @Override
    public Collection<User> findAll() {
        return map.size() == 0 ? null : map.values();
    }


    @Override
    public User findById(int id) {
        return map.get(id);
    }
}
