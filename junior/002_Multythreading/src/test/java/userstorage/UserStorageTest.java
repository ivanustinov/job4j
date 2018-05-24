package userstorage;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static userstorage.UserStorage.User;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.05.2018
 */
public class UserStorageTest {
    UserStorage storage = new UserStorage();

    @Before
    public void fillTheStorage() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 20001; i++) {
                    storage.add(new User(i, 100));
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 20001; i < 40001; i++) {
                    storage.add(new User(i, 200));
                }
            }
        }).start();
    }


    @Test
    public void testingStorage() throws InterruptedException {
        Thread.sleep(100);
        assertThat(storage.getSize(), is(40000));
        storage.transfer(1, 40, 20);
        assertThat(storage.getUser(1), is(new User(1, 80)));
        assertThat(storage.getUser(40), is(new User(40, 120)));
    }
}