package xmljsltjdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.06.2018
 */
public class Config {
    String drivername;
    String connectionString;
    String insert;
    String delete;
    String select;
    String createTable;
    Properties property = new Properties();

    public Config(String file) {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file)) {
            property.load(inputStream);
            drivername = property.getProperty("drivername");
            connectionString = property.getProperty("connectionstring");
            insert = property.getProperty("insert");
            delete = property.getProperty("delete");
            select = property.getProperty("select");
            createTable = property.getProperty("createtable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
