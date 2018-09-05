package appjsp.servlets;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.logic.Validate;
import appjsp.logic.ValidateService;
import appjsp.logic.ValidateStub;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.08.2018
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidateService.class)
public class ControllerTest {

    @Test
    public void addUser() throws ServletException, IOException {
        Controller controller = new Controller();
        Validate validateStub = new ValidateStub();
        HashMap<String, String[]> par = new HashMap<>();
        String page = "WEB-INF/views/create.jsp";
        par.put("page", new String[]{page});
        par.put("action", new String[]{"add"});
        par.put("login", new String[]{"Petr"});
        par.put("password", new String[]{"petr"});
        par.put("role", new String[]{"USER"});
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        PowerMockito.mockStatic(ValidateService.class);
        when(request.getParameterMap()).thenReturn(par);
        when(ValidateService.getInstance()).thenReturn(validateStub);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher(page)).thenReturn(requestDispatcher);
        controller.doPost(request, response);
        assertThat(validateStub.getAll().iterator().next(), is(new User(0, UsersRoles.USER, "Petr", "petr")));
    }


    @Test
    public void updateAdmin() throws ServletException, IOException {
        Controller controller = new Controller();
        Validate validateStub = new ValidateStub();
        HashMap<String, String[]> par = new HashMap<>();
        par.put("page", new String[]{"WEB-INF/views/adminupdate.jsp"});
        par.put("action", new String[]{"adminUpdate"});
        par.put("login", new String[]{"Ivan"});
        par.put("password", new String[]{"ivan"});
        par.put("role", new String[]{"ADMIN"});
        par.put("id", new String[]{"0"});
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        PowerMockito.mockStatic(ValidateService.class);
        when(request.getParameterMap()).thenReturn(par);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("WEB-INF/views/adminupdate.jsp")).thenReturn(requestDispatcher);
        when(ValidateService.getInstance()).thenReturn(validateStub);
        controller.doPost(request, response);
        assertThat(validateStub.getAll().iterator().next(), is(new User(0, UsersRoles.ADMIN, "Ivan", "ivan")));
    }

    @Test
    public void updateUser() throws ServletException, IOException {
        Controller controller = new Controller();
        Validate validateStub = new ValidateStub();
        HashMap<String, String[]> par = new HashMap<>();
        par.put("page", new String[]{"WEB-INF/views/update.jsp"});
        par.put("action", new String[]{"update"});
        par.put("login", new String[]{"Alex"});
        par.put("password", new String[]{"alex"});
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        PowerMockito.mockStatic(ValidateService.class);
        when(request.getParameterMap()).thenReturn(par);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("id")).thenReturn(0);
        when(request.getRequestDispatcher("WEB-INF/views/update.jsp")).thenReturn(requestDispatcher);
        when(ValidateService.getInstance()).thenReturn(validateStub);
        controller.doPost(request, response);
        assertThat(validateStub.getAll().iterator().next(), is(new User(0, UsersRoles.USER, "Alex", "alex")));
    }

    @Test
    public void delete() throws ServletException, IOException {
        Controller controller = new Controller();
        Validate validateStub = new ValidateStub();
        HashMap<String, String[]> par = new HashMap<>();
        par.put("page", new String[]{"WEB-INF/views/list.jsp"});
        par.put("action", new String[]{"delete"});
        par.put("id", new String[]{"0"});
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
        when(request.getParameterMap()).thenReturn(par);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("WEB-INF/views/list.jsp")).thenReturn(requestDispatcher);
        when(ValidateService.getInstance()).thenReturn(validateStub);
        controller.doPost(request, response);
        User user = null;
        assertThat(validateStub.getAll().iterator().next(), is(user));
    }

}