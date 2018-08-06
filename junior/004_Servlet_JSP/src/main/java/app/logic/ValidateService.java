package app.logic;

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
    private final Map<String, Function<Map, String>> getDispatch = new HashMap<>();


    public static ValidateService getInstance() {
        return INSTANCE;
    }

    public String doPostAction(String action, Map map) {
        Function<Map, String> ans = dispatch.get(action);
        return ans == null ? "There is no such action for the POST request" : ans.apply(map);
    }

    public String doGetAction(String action, Map map) {
        Function<Map, String> ans = getDispatch.get(action);
        return ans == null ? "There is no such action for the GET request" : ans.apply(map);
    }


    private ValidateService() {
        initPost();
        initGet();
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
                String[] id = (String[]) map.get("id");
                if (id != null && id.length != 0 && newName != null && newName.length != 0) {
                    int i = Integer.parseInt(id[0]);
                    return store.update(i, newName[0]);
                }
                return "insert id or/and name parameter";
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
    public void initPost() {
        dispatch.put("add", add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
    }

    public void initGet() {
        getDispatch.put("findById", findById());
        getDispatch.put("findAll", findAll());
    }

}
