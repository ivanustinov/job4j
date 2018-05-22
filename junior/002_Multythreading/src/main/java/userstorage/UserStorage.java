package userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.05.2018
 */
@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> storage = Collections.synchronizedMap(new TreeMap<>());

    public static class User {
        private int id;
        private int amount;

        public User(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return id + " " + amount;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            User user = (User) obj;
            return id == user.id && amount == user.amount;
        }
    }


    public boolean add(User user) {
        if (storage.get(user.id) == null) {
            storage.put(user.id, user);
            return true;
        }
        return false;
    }

    public boolean update(User user) {
        if (storage.get(user.id) != null) {
            storage.put(user.id, user);
            return true;
        }
        return false;
    }

    public boolean delete(User user) {
        if (storage.get(user.id) != null) {
            storage.remove(user.id);
            return true;
        }
        return false;
    }

    public void transfer(int fromId, int toId, int amount) {
        User from = storage.get(fromId);
        User to = storage.get(toId);
        if (from.amount < amount) {
            to.amount += from.amount;
            from.amount = 0;
        } else {
            from.amount -= amount;
            to.amount += amount;
        }
    }

    public int getSize() {
        return storage.size();
    }

    public User getUser(int a) {
        return storage.get(a);
    }

    @Override
    public String toString() {
        return String.valueOf(storage.size());
    }
}
