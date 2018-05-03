package set;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.04.2018
 */
public class SimpleHash {
    public static void main(String[] args) {
        System.out.println(new String("key").hashCode());
        Map<Integer, String> map = new HashMap<>(11, 0.5f);
        map.put(0, "Zero");
        map.put(11, "Eleven");
        map.put(1, "One");
        map.put(4, "One");
        System.out.println(map);
        Hashtable<Integer, String> table = new Hashtable<>(11, 0.5f);
        table.put(0, "Zero");
        table.put(11, "Eleven");
        table.put(1, "One");
        table.put(4, "One");
        System.out.println(table);
    }


}
