package appjsp.persistent;

import appjsp.entities.UsersRoles;

import java.util.ArrayList;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public interface Store<T> {
    void add(UsersRoles roles, String login, String password);

    void delete(int id);

    void adminUpdate(int id, String newRole, String newLogin, String newPassword);

    void update(int id, String newLogin, String newPassword);

    ArrayList<T> findAll();

    T findById(int id);
}
