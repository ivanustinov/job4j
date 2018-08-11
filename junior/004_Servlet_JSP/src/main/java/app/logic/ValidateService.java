package app.logic;

import app.entities.User;
import app.persistent.MemoryStore;

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
    private final MemoryStore store = MemoryStore.getInstance();
    private final Map<String, Function<Map, String>> postDispatch = new HashMap<>();
    private final Map<String, Function<Map, String>> getDispatch = new HashMap<>();


    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doAction(String method, String action, Map map) {
        Function<Map, String> ans = null;
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


    public Function<Map, String> findAll() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                Collection<User> users = store.findAll();
                return users == null ? "<p align = 'center'> there are no users in the store</p>" : createUsersTable(users);
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


    public Function<Map, String> add() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] names = (String[]) map.get("name");
                String[] logins = (String[]) map.get("login");
                return store.add(names[0], logins[0]);
            }
        };
    }

    public Function<Map, String> findById() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] id = (String[]) map.get("id");
                if (id != null && id.length != 0) {
                    int i = Integer.parseInt(id[0]);
                    return store.findById(i);
                }
                return "Insert id parameter";
            }
        };
    }

    public Function<Map, String> update() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] newName = (String[]) map.get("name");
                String[] newLogin = (String[]) map.get("login");
                String[] id = (String[]) map.get("id");
                if (!newName[0].equals("") && !newLogin[0].equals("")) {
                    int i = Integer.parseInt(id[0]);
                    return store.update(i, newName[0], newLogin[0]);
                }
                return "<p align='center'>insert name or/and login parameter</p>";
            }
        };
    }

    public Function<Map, String> delete() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] id = (String[]) map.get("id");
                return !id[0].equals("") ? store.delete(Integer.parseInt(id[0])) : "insert id parameter";
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
