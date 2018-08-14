package appjsp.persistent;

import java.util.Collection;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store<T> {
    T add(String name, String login);

    T delete(int id);

    T update(int id, String newName, String newLogin);

    Collection<T> findAll();

    T findById(int id);
}
