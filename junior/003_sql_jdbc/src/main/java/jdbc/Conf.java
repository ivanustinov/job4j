package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.06.2018
 */
public class Conf {
    private String url;
    private String user;
    private String password;
    private String update;
    private String drivername;
    private String connectionString;
    private String createTable;
    private String delete;
    private String select;
    private String selectByName;
    private String selectById;
    private String insert;
    private Map<String, String> data = new HashMap<>();
    Properties property = new Properties();

    public Conf(String file) {
        try (InputStream inputStream = getClass().getResourceAsStream(file)) {
            property.load(inputStream);
            url = property.getProperty("url");
            user = property.getProperty("user");
            password = property.getProperty("password");
            update = property.getProperty("update");
            drivername = property.getProperty("drivername");
            connectionString = property.getProperty("connectionstring");
            createTable = property.getProperty("createtable");
            delete = property.getProperty("delete");
            select = property.getProperty("select");
            selectById = property.getProperty("selectById");
            selectByName = property.getProperty("selectByName");
            insert = property.getProperty("insert");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        data.put("url", url);
        data.put("user", user);
        data.put("password", password);
        data.put("update", update);
        data.put("drivername", drivername);
        data.put("connectionstring", connectionString);
        data.put("createtable", createTable);
        data.put("delete", delete);
        data.put("select", select);
        data.put("selectById", selectById);
        data.put("selectByName", selectByName);
        data.put("insert", insert);
    }

    public String get(String key) {
        return data.get(key);
    }
}
