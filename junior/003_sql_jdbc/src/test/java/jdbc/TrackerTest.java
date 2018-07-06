package jdbc;

import org.junit.Before;
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
    Conf conf = new Conf();

    @Before
    public void setUp() throws Exception {
        try (Tracker tracker = new Tracker(conf)) {
            items.add(new Tracker.Item("Alex"));
            items.add(new Tracker.Item("Ivan"));
            items.add(new Tracker.Item("Ivan"));
            items.add(new Tracker.Item("Ivan"));
            int a = 0;
            for (Tracker.Item item : items) {
                int id = tracker.generateId();
                this.id.add(id);
                item.setId(id);
                stringItems.add(item.toString());
                tracker.add(item);
            }
//            System.out.println(tracker.findAll());
//            System.out.println(tracker.findByName("Ivan"));
//            System.out.println(tracker.findById(id.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findAll() {
        try (Tracker tracker = new Tracker(conf)) {
            List<String> items = tracker.findAll();
            System.out.println(stringItems);
            System.out.println(items);
            assertThat(stringItems.containsAll(items), is(true));
            assertThat(stringItems.size() == items.size(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void findById() {
        try (Tracker tracker = new Tracker(conf)) {
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
                if (item.getName().equals("Ivan")) {
                    names.add(String.format("%s %d", item.getName(), item.getId()));
                }
            }
            System.out.println(names);
            System.out.println(items);
            assertThat(names.containsAll(items), is(true));
            assertThat(names.size() == items.size(), is(true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}