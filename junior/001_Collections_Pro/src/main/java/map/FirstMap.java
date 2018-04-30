package map;

import org.junit.Test;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 29.04.2018
 */
public class FirstMap {
    Map<User, String> map = new HashMap<>();

    @Test
    public void put() {
        map.put(new User("Ivan", 3, new GregorianCalendar(1985, 8, 20)), "First");
        map.put(new User("Ivan", 3, new GregorianCalendar(1985, 8, 20)), "Second");
        System.out.println(map);
    }
}
