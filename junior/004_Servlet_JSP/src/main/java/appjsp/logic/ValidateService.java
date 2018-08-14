package appjsp.logic;

import appjsp.entities.User;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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
    private final Map<String, Function<Map, String>> postDispatch = new HashMap<>();
    private final Map<String, Function<Map, String>> getDispatch = new HashMap<>();


    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doAction(String method, String action, Map map) {
        Function<Map, String> ans;
        if (method.equals("POST")) {
            ans = postDispatch.get(action);
        } else {
            ans = getDispatch.get(action);
        }
        return ans == null ? "There is no such action for the " + method + " request" : ans.apply(map);
    }

    private ValidateService() {
        initPost();
        initGet();
    }

    public Function<Map, String> add() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String name = ((String[]) map.get("name"))[0];
                String login = ((String[]) map.get("login"))[0];
                String result = "<p align=center>insert name or/and login field</p>";
                if (!name.equals("") && !login.equals("")) {
                    if (store.add(name, login)) {
                        result = "<p align='center'>user with name " + name + " and login " + login + " has been add</p>";
                    }
                }
                return result;
            }
        };
    }

    public Function<Map, String> findAll() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                Collection<User> users = store.findAll();
                String result = "<p align = 'center'> there are no users in the store</p>";
                if (users.size() != 0) {
                    result = createUsersTable(users);
                }
                return result;
            }
        };
    }

    public String createUsersTable(Collection<User> users) {
        StringBuffer buffer = new StringBuffer("<table align='center' border='2' cellspacing='0' cellpadding='2'>" +
                "<caption>users in the store</caption>" +
                "<tr><th>ID</th><th>NAME</th><th colspan='2'>ACTIONS</th></tr>");
        for (User user : users) {
            String name = user.getName();
            String login = user.getLogin();
            int id = user.getId();
            buffer.append("<tr><td>" + id + "</td><td>" + name + "</td><td>" +
                    "<form action='/edit' method='post'>" +
                    "<input type='hidden' name='login' value ='" + login + "'>" +
                    "<input type='hidden' name='name' value = '" + name + "'>" +
                    "<input type='hidden' name='id' value = '" + id + "'>" +
                    "<button type='submit'>UPDATE</button></td><td>" +
                    "</form>" +
                    "<form method='post'>" +
                    "<input type='hidden' name='action' value = 'delete'>" +
                    "<input type='hidden' name='id' value = '" + id + "'>" +
                    "<button type='submit'>DELETE</button>" +
                    "</form>" +
                    "</td></tr>");
        }
        buffer.append("</table>");
        return buffer.toString();
    }

    public Function<Map, String> findById() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] id = (String[]) map.get("id");
                String result = "no user in the store with such id";
                User user = null;
                if (id != null && id.length != 0) {
                    int i = Integer.parseInt(id[0]);
                    user = store.findById(i);
                } else {
                    result = "Insert id parameter";
                }
                return user == null ? result : user.toString();
            }
        };
    }

    public Function<Map, String> update() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String newName = ((String[]) map.get("name"))[0];
                String newLogin = ((String[]) map.get("login"))[0];
                String id = ((String[]) map.get("id"))[0];
                String result = "<p align='center'>insert name or/and login parameter</p>";
                if (!newName.equals("") && !newLogin.equals("")) {
                    int i = Integer.parseInt(id);
                    if (store.update(i, newName, newLogin)) {
                        result = "<p align='center'> user with id " + id + " has been updated</p>";
                    }
                }
                return result;
            }
        };
    }

    public Function<Map, String> delete() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String id = ((String[]) map.get("id"))[0];
                String result = "<p align='center'>no user with such id in the store</p>";
                if (store.delete(Integer.parseInt(id))) {
                    result = "<p align='center'> user with id " + id + " has been deleted</p>";
                }
                return result;
            }
        };
    }

    /**
     * Init's postDispatch.
     *
     * @return current object.
     */
    public void initPost() {
        postDispatch.put("add", add());
        postDispatch.put("update", update());
        postDispatch.put("delete", delete());
    }

    public void initGet() {
        getDispatch.put("findById", findById());
        getDispatch.put("findAll", findAll());
    }

}
