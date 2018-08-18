package appjsp.persistent;

import java.util.ArrayList;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store<T> {
    void add(String name, String login);

    void delete(int id);

    void update(int id, String newName, String newLogin);

    ArrayList<T> findAll();

    T findById(int id);
}
