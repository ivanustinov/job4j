package appjsp.logic;

import appjsp.entities.User;

import java.util.List;
import java.util.function.Consumer;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 05.09.2018
 */
public interface Validate {
    Consumer<SessionRequestContext> add();

    Consumer<SessionRequestContext> delete();

    Consumer<SessionRequestContext> userUpdate();

    Consumer<SessionRequestContext> adminUpdate();

    Consumer<SessionRequestContext> findById();

    Consumer<SessionRequestContext> logOut();

    User isCredentional(String login, String password);

    String doAction(SessionRequestContext context);

    List<User> getAll();
}
