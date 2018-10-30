package appjsp.servlets;

import appjsp.entities.User;
import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String result = "";
        resp.setContentType("text/json");
        User user = logic.isCredentional(login, password);
        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            result = "success";
        } else {
            result = "Wrong password or login";
        }
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append("{\"result\":\"" + result + "\"}");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}