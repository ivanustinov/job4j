package app.persistent;

import app.entities.User;

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
    public String add(String name) {
        int number = id++;
        User user = new User(number, name);
        map.put(number, user);
        return "user with name " + name + " has been add";
    }

    @Override
    public String delete(int id) {
        return map.remove(id) == null ? "no user with such id in the store" : "user with id " + id + " has been deleted";
    }

    @Override
    public String update(int id, String newName) {
        User user = map.get(id);
        String answer = "no user in the store with such id";
        if (user != null) {
            user.setName(newName);
            answer = "user with id " + id + " has been updated";
        }
        return answer;
    }

    @Override
    public String findAll() {
        return map.size() == 0 ? null : map.toString();
    }

    @Override
    public User findById(int id) {
        return map.get(id);
    }
}
