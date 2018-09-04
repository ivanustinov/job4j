package appjsp.servlets;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.DbStore;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.08.2018
 */
public class SignInTest {

    @Test
    public void loginUser() throws ServletException, IOException {
        DbStore store = DbStore.getInstance();
        ArrayList<User> usersBefore = store.findAll();
        store.add(UsersRoles.USER, "Petr", "petr");
        ArrayList<User> usersAfter = store.findAll();
        usersAfter.removeAll(usersBefore);
        User petr = usersAfter.get(0);
        int id = petr.getId();
        SignIn signIn = new SignIn();
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getParameter("login")).thenReturn("Petr");
        when(request.getParameter("password")).thenReturn("petr");
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("/contr")).thenReturn(requestDispatcher);
        signIn.doPost(request, response);
        verify(session, times(1)).setAttribute("login", "Petr");
        store.delete(id);
    }

}