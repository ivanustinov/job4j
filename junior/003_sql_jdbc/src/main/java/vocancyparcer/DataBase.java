package vocancyparcer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Map;
import java.util.Properties;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 16.07.2018
 */
public class DataBase {
    private static final Logger LOGGER = LogManager.getLogger(SQLRUParser.class.getName());
    private final Properties property = new Properties();
    private String url;
    private String user;
    private String password;
    private PrefixJava prefixJava = new PrefixJava();


    public DataBase(String fileProperties) {
        try (InputStream inputStream = getClass().getResourceAsStream(fileProperties)) {
            property.load(inputStream);
            url = property.getProperty("jdbc.url");
            user = property.getProperty("jdbc.user");
            password = property.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insert(Map<String, String> list) {
        try (Connection connection = DriverManager.getConnection(this.url, user, password);
             Statement stm = connection.createStatement();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO javadevelopers2(url, vocancy)"
                     + "VALUES (?, ?)")) {
            stm.execute("CREATE TABLE IF NOT EXISTS javadevelopers2(url text PRIMARY KEY, vocancy text )");
//            stm.execute("DELETE FROM javadevelopers2");
            for (String url : list.keySet()) {
                String job = list.get(url);
                if (prefixJava.put(job)) {
                    LOGGER.info(job);
                    insert.setString(1, url);
                    insert.setString(2, job);
                    insert.executeUpdate();
                } else {
                    continue;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
