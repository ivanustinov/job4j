package appjsp.logic;

import appjsp.entities.User;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;

import java.util.ArrayList;
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


    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doAction(String action, Map map) {
        Function<Map, String> ans = postDispatch.get(action);
        String result = ans.apply(map);
        return result;
    }

    private ValidateService() {
        initPost();
    }

    public Function<Map, String> add() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String name = ((String[]) map.get("name"))[0];
                String login = ((String[]) map.get("login"))[0];
                String result = "Enter values for the name or/and login fields";
                if (!name.equals("") && !login.equals("")) {
                    store.add(name, login);
                    result = "User with name " + name + " and login " + login + " has been created";
                }
                return result;
            }
        };
    }


    public ArrayList<User> findAll() {
        return store.findAll();
    }


    public Function<Map, String> findById() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String id = ((String[]) map.get("id"))[0];
                String result = "no user in the store with such id";
                if (!id.equals("")) {
                    int i = Integer.parseInt(id);
                    User user = store.findById(i);
                    return user != null ? user.toString() : result;
                } else {
                    result = "Insert id";
                }
                return result;
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
                String result = "Enter values for the name or/and login fields";
                if (!newName.equals("") && !newLogin.equals("")) {
                    int i = Integer.parseInt(id);
                    store.update(i, newName, newLogin);
                    result = "User with id " + i + " has been updated";
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
                String result = "user with id " + id + " has been deleted";
                store.delete(Integer.parseInt(id));
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
        postDispatch.put("findById", findById());
    }


}
