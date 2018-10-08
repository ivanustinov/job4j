package appjsp.servlets;

import appjsp.entities.User;
import appjsp.logic.Validate;
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
    private final Validate logic = ValidateService.getInstance();

    @Override
    public void init() throws ServletException {

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
                session.setAttribute("user", user);
                page = "contr";
            } else {
                result = "Wrong password or login";
            }
        }
        req.setAttribute("result", result);
        req.getRequestDispatcher(page).forward(req, resp);
    }

}