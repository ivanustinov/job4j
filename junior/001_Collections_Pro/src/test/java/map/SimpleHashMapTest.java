package map;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 01.05.2018
 */
public class SimpleHashMapTest {
    SimpleHashMap<Integer, String> map = new SimpleHashMap<>();

    @Before
    public void setUp() throws Exception {
        map.insert(15, "Ivan");
        map.insert(4, "Grigorii");
        map.insert(17, "Gosha");
        map.insert(39, "Gosha");
        map.insert(0, "Alex");
        map.insert(3, "Maga");
        map.insert(5, "Maga");
        map.insert(8, "Avdotia");
    }

    @Test
    public void tryingToTest() {
        assertThat(map.get(15), is("Ivan"));
        assertThat(map.tableSize(), is(11));
        map.insert(15, "Maga");
        System.out.println(map);
        assertThat(map.remove(15), is(true));
//        map.insert(2, "Iosif");
        map.insert(19, "Iosif");
        assertThat(map.tableSize(), is(23));
        System.out.println(map);
    }

    @Test
    public void testIterator() {
        SimpleHashMap<Integer, String> map = new SimpleHashMap<>();
        map.insert(15, "Ivan");
        map.insert(16, "Grigorii");
        map.insert(17, "Gosha");
        System.out.println(map);
        Iterator<String> it = map.iterator();
        assertThat(it.next(), is("Ivan"));
        assertThat(it.next(), is("Grigorii"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Gosha"));
        assertThat(it.hasNext(), is(false));
    }
}