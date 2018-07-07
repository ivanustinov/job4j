package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.06.2018
 */
public class Conf {
    String url;
    String user;
    String password;
    String update;
    String drivername;
    String connectionString;
    String createTable;
    String delete;
    String select;
    String selectByName;
    String selectById;
    String insert;
    Properties property = new Properties();

    public Conf(String file) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file)) {
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
}
