package appjsp.persistent;

import appjsp.entities.User;

import java.util.ArrayList;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store<T> {
    void add(User user);

    void delete(String id);

    void adminUpdate(User user);

    void update(String id, String newCountry, String newCity, String newLogin, String newPassword);

    ArrayList<T> findAll();

    T findById(String id);
}
