package appjsp.servlets;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static appjsp.entities.UsersRoles.ADMIN;
import static appjsp.entities.UsersRoles.USER;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.08.2018
 */
public class SignIn extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();
    private final Map<UsersRoles, String> roleDispatch = new HashMap<>();

    @Override
    public void init() throws ServletException {
        roleDispatch.put(USER, "userServlet");
        roleDispatch.put(ADMIN, "adminServlet");
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String result = "Enter values for the name or/and login fields";
        String page = "WEB-INF/views/authentification.jsp";
        if (login != null && password != null && login.equals("") && !password.equals("")) {
            User user = logic.isCredentional(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                UsersRoles role = user.getRole();
                page = roleDispatch.get(role);
                resp.sendRedirect(page);
                return;
            } else {
                result = "Wrong password or login";
                req.setAttribute("result", result);
            }
        } else {
            req.setAttribute("result", result);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String result = "Enter values for the name or/and login fields";
        String page = "WEB-INF/views/authentification.jsp";
        if (login != null && password != null && login.equals("") && !password.equals("")) {
            User user = logic.isCredentional(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                UsersRoles role = user.getRole();
                page = roleDispatch.get(role);
                resp.sendRedirect(page);
                return;
            } else {
                result = "Wrong password or login";
                req.setAttribute("result", result);
            }
        } else {
            req.setAttribute("result", result);
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }
}