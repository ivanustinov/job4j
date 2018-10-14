package appjsp.servlets;

import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.10.2018
 */
@WebServlet(urlPatterns = {"/adminServlet"})
public class AdminServlet extends HttpServlet {
    private ValidateService actionService = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            actionService.doAction(action, req);
        }
        String page = "/adminPage";
        if (req.getParameter("page") != null) {
            page = req.getParameter("page");
        }
        req.getRequestDispatcher(page).forward(req, resp);
    }


}
