package app.logic;

import app.entities.User;
import app.persistent.MemoryStore;

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
    private final Map<String, Function<Map, String>> dispatch = new HashMap<>();


    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doAction(String action, Map map) {
        return dispatch.get(action).apply(map);
    }


    private ValidateService() {
        init();
    }


    public Function<Map, String> findById() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                int[] id = (int[]) map.get("id");
                User user = store.findById(id[0]);
                return user == null ? "there is no user with such id in the store" : user.toString();
            }
        };
    }

    public Function<Map, String> findAll() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String list = store.findAll();
                return list == null ? "there ara no users in the store" : list;
            }
        };
    }


    public Function<Map, String> add() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] name = (String[]) map.get("name");
                return name != null ? store.add((name[0])) : "insert name parameter";
            }
        };
    }

    public Function<Map, String> update() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] newName = (String[]) map.get("name");
                String[] id = (String[]) map.get("id");
                return newName != null ? (id != null ? store.update(Integer.parseInt(id[0]), newName[0]) :
                        "insert id parameter") : "insert name parameter";
            }
        };
    }

    public Function<Map, String> delete() {
        return new Function<Map, String>() {
            @Override
            public String apply(Map map) {
                String[] id = (String[]) map.get("id");
                return id != null ? store.delete(Integer.parseInt(id[0])) : "insert id parameter";
            }
        };
    }


    /**
     * Init's dispatch.
     *
     * @return current object.
     */
    public void init() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
        dispatch.put("findByID", findById());
        dispatch.put("findAll", findAll());
    }

}
