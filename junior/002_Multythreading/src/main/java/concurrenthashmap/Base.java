package concurrenthashmap;

/**
 * //TODO work comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.05.2018
 */
public class Base {
    private static int id;
    private int version;

    public Base() {
        id++;
    }

    public int getId() {
        return id;
    }

    public void changeData() {
        version++;
    }

    public int getVersion() {
        return version;
    }
}
