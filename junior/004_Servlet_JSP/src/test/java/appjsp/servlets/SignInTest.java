package appjsp.servlets;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.DbStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.08.2018
 */
public class SignInTest {
    SignIn signIn = new SignIn();
    int id;
    DbStore store = DbStore.getInstance();

    @Before
    public void setUp() throws Exception {
        ArrayList<User> usersBefore = DbStore.getInstance().findAll();
        store.add(UsersRoles.USER, "Petr", "petr");
        ArrayList<User> usersAfter = DbStore.getInstance().findAll();
        usersAfter.removeAll(usersBefore);
        User user = usersAfter.get(0);
        id = user.getId();
    }

    @Test
    public void loginUser() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("login")).thenReturn("Petr");
        when(request.getParameter("password")).thenReturn("petr");
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/contr")).thenReturn(requestDispatcher);
        signIn.doPost(request, response);
        assertThat(session.getAttribute("login"), is("Petr"));
    }


    @After
    public void tearDown() throws Exception {
        store.delete(id);
    }

}