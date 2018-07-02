package xml_jslt_jdbc;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.06.2018
 */
public class Config {

    public static final String DRIVERNAME = "org.sqlite.JDBC";
    public static final String CONNECTIONSTRING = "jdbc:sqlite:test.db";
    public static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS entry(" +
            "field int NOT NULL PRIMARY KEY AUTOINCREMENT)";
    public static final String DELETE = "DELETE FROM entry";
    public static final String SELECT = "SELECT * FROM entry";
    public static final String INSERT = "INSERT INTO entry  VALUES (?)";
}
