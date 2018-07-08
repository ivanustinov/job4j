package jdbc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 05.07.2018
 */
public class TrackerTest {
    List<Integer> id = new ArrayList<>();
    List<String> stringItems = new ArrayList<>();
    List<Tracker.Item> items = new ArrayList<>();
    Conf conf = new Conf("/postgres.properties");

    public void setUp(Tracker tracker) {
        try {
            items.add(new Tracker.Item("Alex"));
            items.add(new Tracker.Item("Ivan"));
            items.add(new Tracker.Item("Ivan"));
            items.add(new Tracker.Item("Ivan"));
            for (Tracker.Item item : items) {
                int id = tracker.generateId();
                this.id.add(id);
                item.setId(id);
                stringItems.add(item.toString());
                tracker.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        try (Tracker tracker = new Tracker(conf)) {
            setUp(tracker);
            List<String> items = tracker.findAll();
            assertThat(stringItems.containsAll(items), is(true));
            assertThat(stringItems.size() == items.size(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try (Tracker tracker = new Tracker(conf)) {
            setUp(tracker);
            int id = this.id.get(3);
            assertThat(tracker.findById(id), is(items.get(3).toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findByName() {
        try (Tracker tracker = new Tracker(conf)) {
            List<String> items = tracker.findByName("Ivan");
            List<String> names = new ArrayList<>();
            for (Tracker.Item item : this.items) {
                if (item.getName().equals("Ivan"))
                    names.add(item.toString());
            }
            assertThat(names.containsAll(items), is(true));
            assertThat(names.size() == items.size(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}