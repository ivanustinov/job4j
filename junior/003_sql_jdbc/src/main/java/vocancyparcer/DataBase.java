package vocancyparcer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.List;
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
    private static int i = 1;
    private final Properties property = new Properties();
    private String url;
    private String user;
    private String password;


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

    public void insert(List<String> list) {
        try (Connection connection = DriverManager.getConnection(this.url, user, password);
             Statement stm = connection.createStatement();
             PreparedStatement insert = connection.prepareStatement("INSERT INTO javadevelopers2 (id, vocancy)"
                     + "VALUES (?, ?)")) {
            stm.execute("CREATE TABLE IF NOT EXISTS javadevelopers2(id integer PRIMARY KEY, vocancy text )");
//            stm.execute("DELETE FROM javadevelopers2");
            for (String job : list) {
                if ((job.contains("Java") || job.contains("java") || job.contains("JAVA"))) {
                    LOGGER.info(job);
                    insert.setInt(1, i++);
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
