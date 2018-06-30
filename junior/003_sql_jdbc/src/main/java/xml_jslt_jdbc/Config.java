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
    public static final String CONNECTIONSTRING = "jdbc:sqlite:store.db";
    public static final String UPDATE = "UPDATE tracker SET name = ?, id = ? WHERE id = ?";
    public static final String CREATETABLE = "CREATE TABLE IF NOT EXISTS entry(feild SERIAL PRIMARY KEY)";
    public static final String DELETE = "DELETE FROM entry";
    public static final String INSERT = "INSERT INTO entry VALUES(DEFAULT)";


//    ;
//    DELETE FROM tracker WHERE id = ?
//    SELECT * FROM tracker
//    SELECT * FROM tracker WHERE id = ?
//    SELECT * FROM tracker WHERE name = ?
//    CREATE TABLE IF NOT EXISTS tracker(id integer PRIMARY KEY, name text);
}
