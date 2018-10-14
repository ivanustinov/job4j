package appjsp.logic;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 03.08.2018
 */
public class ValidateService {
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store<User> store = DbStore.getInstance();
    private final Map<String, Consumer<HttpServletRequest>> actions = new HashMap<>();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    private ValidateService() {
        actions.put("add", add());
        actions.put("delete", delete());
        actions.put("adminUpdate", adminUpdate());
        actions.put("userUpdate", userUpdate());
        actions.put("findById", findById());
        actions.put("logOut", logOut());
    }

    public void doAction(String action, HttpServletRequest request) {
        actions.get(action).accept(request);
    }

    public Consumer<HttpServletRequest> add() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                String role = req.getParameter("role");
                String result = "Enter values for the login or/and password fields";
                if (!login.equals("") && !password.equals("")) {
                    User user = new User(UsersRoles.valueOf(role), login, password);
                    store.add(user);
                    result = "User " + login + " has been created";
                }
                req.setAttribute("result", result);
            }
        };
    }

    public Consumer<HttpServletRequest> delete() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String id = req.getParameter("id");
                String result = "user with id " + id + " has been deleted";
                store.delete(id);
                req.setAttribute("result", result);
            }
        };
    }

    public Consumer<HttpServletRequest> adminUpdate() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String newLogin = req.getParameter("login");
                String newPassword = req.getParameter("password");
                String newRole = req.getParameter("role");
                String id = req.getParameter("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    store.adminUpdate(id, newRole, newLogin, newPassword);
                    result = "User with id " + id + " has been updated";
                }
                req.setAttribute("result", result);
            }
        };
    }

    public Consumer<HttpServletRequest> userUpdate() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String newLogin = req.getParameter("login");
                String newPassword = req.getParameter("password");
                String id = req.getParameter("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    store.update(id, newLogin, newPassword);
                    result = "User with id " + id + " has been updated";
                }
                req.setAttribute("result", result);
                req.getSession().setAttribute("user", findUserById(id));
            }
        };
    }

    public Consumer<HttpServletRequest> logOut() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                req.getSession().invalidate();
            }
        };
    }

    public Consumer<HttpServletRequest> findById() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String id = req.getParameter("id");
                String result = "Insert id";
                if (!id.equals("")) {
                    User user = store.findById(id);
                    result = (user == null ? "No user in the store with such id" : user.toString());
                }
                req.setAttribute("result", result);
            }
        };
    }

    public List<User> getAll() {
        return store.findAll();
    }

    public User isCredentional(String login, String password) {
        User userToLogin = null;
        for (User user : store.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                userToLogin = user;
            }
        }
        return userToLogin;
    }

    public User findUserById(String id) {
        return store.findById(id);
    }
}
