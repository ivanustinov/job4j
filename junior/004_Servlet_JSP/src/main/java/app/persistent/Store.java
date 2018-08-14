package app.persistent;

import app.entities.User;

import java.util.Collection;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store {
    User add(String name, String login);

    User delete(int id);

    User update(int id, String newName, String newLogin);

    Collection<User> findAll();

    User findById(int id);
}
