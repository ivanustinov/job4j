package appjsp.servlets;

import appjsp.entities.User;
import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.08.2018
 */
public class SignIn extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/contr").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String result = "Enter values for the name or/and login fields";
        String page = "WEB-INF/views/authentification.jsp";
        if (!login.equals("") && !password.equals("")) {
            User user = logic.isCredentional(login, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("login", login);
                session.setAttribute("role", user.getRole());
                session.setAttribute("id", user.getId());
                req.getRequestDispatcher("/contr").forward(req, resp);
                return;
            } else {
                result = "Wrong password or login";
            }
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
