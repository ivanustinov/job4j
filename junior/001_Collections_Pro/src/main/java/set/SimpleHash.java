package set;

import java.util.HashSet;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 30.04.2018
 */
public class SimpleHash {
    public static void main(String[] args) {
        System.out.println(15 & (new String("Альфа").hashCode()));
        System.out.println(15 & (new String("Гамма").hashCode()));
        System.out.println(15 & (new String("Омега").hashCode()));
        System.out.println(15 & (new String("Эпсилон").hashCode()));
        System.out.println(15 & (new String("Эта").hashCode()));
        System.out.println(15 & (new String("Бета").hashCode()));
        HashSet<String> set = new HashSet<>();
        set.add("Гамма");
        set.add("Эта");
        set.add("Альфа");
        set.add("Эпсилон");
        set.add("Омега");
        set.add("Бета");
        System.out.println(set);

//        System.out.println(new String("key").hashCode());
//        Map<Integer, String> map = new HashMap<>(11, 0.5f);
//        map.put(0, "Zero");
//        map.put(11, "Eleven");
//        map.put(1, "One");
//        map.put(4, "One");
//        System.out.println(map);
//        Hashtable<Integer, String> table = new Hashtable<>(11, 0.5f);
//        table.put(0, "Zero");
//        table.put(11, "Eleven");
//        table.put(1, "One");
//        table.put(4, "One");
//        System.out.println(table);
    }


}
