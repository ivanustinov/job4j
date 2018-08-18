package appjsp.persistent;

import appjsp.entities.User;

import java.util.ArrayList;
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
    public void add(String name, String login) {
            int number = id++;
        User user = new User(number, name, login);
            map.put(number, user);
    }

    @Override
    public void delete(int id) {
        map.remove(id);
    }

    @Override
    public void update(int id, String newName, String newLogin) {
        User user = map.get(id);
        user.setName(newName);
        user.setLogin(newLogin);
    }

    @Override
    public ArrayList<User> findAll() {
        return map.size() == 0 ? null : (ArrayList<User>) map.values();
    }


    @Override
    public User findById(int id) {
        return null;
    }
}
