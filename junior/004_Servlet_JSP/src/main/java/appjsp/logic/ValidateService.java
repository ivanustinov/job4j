package appjsp.logic;

import appjsp.entities.User;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        actions.put("getAll", getAll());
        actions.put("logOut", logOut());
    }

    public void doAction(String action, HttpServletRequest request) {
        actions.get(action).accept(request);
    }

    public Consumer<HttpServletRequest> add() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                ObjectMapper mapper = new ObjectMapper();
                User user = null;
                String f = req.getParameter("user");
                System.out.println(f);
                try {
                    user = mapper.readValue(f, User.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                store.add(user);
                req.setAttribute("result", "User " + user.getLogin() + " has been created");
            }
        };
    }

    public Consumer<HttpServletRequest> delete() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                String id = req.getParameter("id");
                store.delete(id);
                String result = "user with id " + id + " has been deleted";
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
                String newCountry = req.getParameter("country");
                String newCity = req.getParameter("city");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    store.adminUpdate(id, newCountry, newCity, newRole, newLogin, newPassword);
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
                String newCountry = req.getParameter("country");
                String newCity = req.getParameter("city");
                String id = req.getParameter("id");
                String result = "Enter values for the name or/and login fields";
                if (!newLogin.equals("") && !newLogin.equals("")) {
                    store.update(id, newCountry, newLogin, newLogin, newPassword);
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

    public Consumer<HttpServletRequest> getAll() {
        return new Consumer<HttpServletRequest>() {
            @Override
            public void accept(HttpServletRequest req) {
                StringBuilder json = new StringBuilder();
                int i = 1;
                json.append("[");
                List<User> users = store.findAll();
                for (User user : users) {
                    json.append("{\"role\" : \"" + user.getRole() + "\", "
                            + "\"country\" : \"" + user.getCountry() + "\", "
                            + "\"id\" : \"" + user.getId() + "\", "
                            + "\"city\" : \"" + user.getCity() + "\", "
                            + "\"login\" : \"" + user.getLogin() + "\", "
                            + "\"password\" : \"" + user.getPassword() + "\"}");
                    if (i++ == users.size() || users.size() == 0) {
                        break;
                    } else {
                        json.append(", ");
                    }
                }
                json.append("]");
                req.setAttribute("result", json.toString());
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

    public User findUserById(String id) {
        return store.findById(id);
    }
}
