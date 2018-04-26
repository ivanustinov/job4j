package generics;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 22.04.2018
 */
public class UserStoreTest {
    UserStore storeOfUsers = new UserStore(4);
    User a = new User("1");
    User b = new User("2");
    User c = new User("1");
    User d = new User("2");
    User e = new User("5");
    User f = null;

    @Before
    public void fillTheStore() {
        storeOfUsers.add(a);
        storeOfUsers.add(b);
        storeOfUsers.add(c);
        storeOfUsers.add(d);
    }

    @Test
    public void findByIdTest() {
        assertThat(storeOfUsers.findById("1"), is(c));
        assertThat(storeOfUsers.findById("2"), is(d));
        assertThat(storeOfUsers.findById("3"), is(f));
        assertThat(storeOfUsers.findById("4"), is(f));
    }

    @Test
    public void replaceTheUserTest() {
        storeOfUsers.replace("2", e);
        assertThat(storeOfUsers.findById("2"), is(f));
        assertThat(storeOfUsers.findById("5"), is(e));
    }

    @Test
    public void deleteTheUserTest() {
        storeOfUsers.delete("1");
        assertThat(storeOfUsers.findById("1"), is(f));
    }


}