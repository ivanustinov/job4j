package app.persistent;

import app.entities.User;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store {
    String add(String name);

    String delete(int id);

    String update(int id, String newName);

    String findAll();

    User findById(int id);
}
