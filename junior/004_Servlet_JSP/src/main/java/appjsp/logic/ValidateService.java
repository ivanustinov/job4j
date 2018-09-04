package appjsp.logic;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;

import java.util.HashMap;
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
    private final Map<String, Consumer<SessionRequestContext>> userDispatch = new HashMap<>();
    private final PageServise pageServise = new PageServise(store);

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doAction(SessionRequestContext context) {
        String action = context.getParameter("action");
        if (action != null) {
            Consumer<SessionRequestContext> ans = userDispatch.get(action);
            ans.accept(context);
        }
        return pageServise.initPage(context);
    }

    private ValidateService() {
        init();
    }

    public Consumer<SessionRequestContext> add() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String login = context.getParameter("login");
                String password = context.getParameter("password");
                UsersRoles role = UsersRoles.valueOf(context.getParameter("role"));
                String result = "Enter values for the login or/and password fields";
                if (!login.equals("") && !password.equals("")) {
                    store.add(role, login, password);
                    result = "User with login " + login + " has been created";
                }
                context.setRequestAttribute("result", result);
            }
        };
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

    public Consumer<SessionRequestContext> findById() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String id = context.getParameter("id");
                String result = "Insert id";
                if (!id.equals("")) {
                    int i = Integer.parseInt(id);
                    User user = store.findById(i);
                    result = (user == null ? "no user in the store with such id" : user.toString());
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    public Consumer<SessionRequestContext> userUpdate() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String newLogin = context.getParameter("login");
                String newPassword = context.getParameter("password");
                int id = (int) context.getSessionAttribute("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    store.update(id, newLogin, newPassword);
                    result = "User with id " + id + " has been updated";
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    public Consumer<SessionRequestContext> adminUpdate() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String newRole = context.getParameter("role");
                String newLogin = context.getParameter("login");
                String newPassword = context.getParameter("password");
                String id = context.getParameter("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    int i = Integer.parseInt(id);
                    store.adminUpdate(i, newRole, newLogin, newPassword);
                    result = "User with id " + i + " has been updated";
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    public Consumer<SessionRequestContext> delete() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String id = context.getParameter("id");
                String result = "user with id " + id + " has been deleted";
                store.delete(Integer.parseInt(id));
                context.setRequestAttribute("result", result);
            }
        };
    }


    public Consumer<SessionRequestContext> logOut() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                context.getSession().invalidate();
            }
        };
    }

    public void init() {
        userDispatch.put("add", add());
        userDispatch.put("update", userUpdate());
        userDispatch.put("adminUpdate", adminUpdate());
        userDispatch.put("delete", delete());
        userDispatch.put("findById", findById());
        userDispatch.put("logOut", logOut());
    }
}
