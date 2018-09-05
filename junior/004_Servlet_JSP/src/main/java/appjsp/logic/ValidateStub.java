package appjsp.logic;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 05.09.2018
 */
public class ValidateStub implements Validate {
    private final Map<String, Consumer<SessionRequestContext>> userDispatch = new HashMap<>();
    private final HashMap<Integer, User> users = new HashMap<>();
    private int id;

    public ValidateStub() {
        init();
    }

    @Override
    public User isCredentional(String login, String password) {
        return null;
    }

    @Override
    public String doAction(SessionRequestContext context) {
        String action = context.getParameter("action");
        if (action != null) {
            Consumer<SessionRequestContext> ans = userDispatch.get(action);
            ans.accept(context);
        }
        return context.getParameter("page");
    }

    @Override
    public Consumer<SessionRequestContext> add() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String login = context.getParameter("login");
                String password = context.getParameter("password");
                UsersRoles role = UsersRoles.valueOf(context.getParameter("role"));
                if (!login.equals("") && !password.equals("")) {
                    users.put(id, new User(id, role, login, password));
                    id++;
                }
            }
        };
    }

    @Override
    public Consumer<SessionRequestContext> findById() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String id = context.getParameter("id");
                String result = "Insert id";
                if (!id.equals("")) {
                    int i = Integer.parseInt(id);
                    User user = users.get(i);
                    result = (user == null ? "no user in the store with such id" : user.toString());
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    @Override
    public Consumer<SessionRequestContext> userUpdate() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String newLogin = context.getParameter("login");
                String newPassword = context.getParameter("password");
                int id = (int) context.getSessionAttribute("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    User user = users.get(id);
                    user.setLogin(newLogin);
                    user.setPassword(newPassword);
                    result = "User with id " + id + " has been updated";
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    @Override
    public Consumer<SessionRequestContext> adminUpdate() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                UsersRoles newRole = UsersRoles.valueOf(context.getParameter("role"));
                String newLogin = context.getParameter("login");
                String newPassword = context.getParameter("password");
                String id = context.getParameter("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    int i = Integer.parseInt(id);
                    User user = users.get(i);
                    user.setPassword(newPassword);
                    user.setLogin(newLogin);
                    user.setRole(newRole);
                    result = "User with id " + i + " has been updated";
                }
                context.setRequestAttribute("result", result);
            }
        };
    }

    @Override
    public Consumer<SessionRequestContext> delete() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                String id = context.getParameter("id");
                String result = "user with id " + id + " has been deleted";
                users.remove(Integer.parseInt(id));
                context.setRequestAttribute("result", result);
            }
        };
    }

    @Override
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

    @Override
    public List<User> getAll() {
        return new ArrayList<>(users.values());
    }
}
