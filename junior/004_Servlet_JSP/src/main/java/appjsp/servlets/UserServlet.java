package appjsp.servlets;

import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.10.2018
 */
@WebServlet(urlPatterns = {"/userServlet"})
public class UserServlet extends HttpServlet {
    private ValidateService actionService = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            actionService.doAction(action, req);
            try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                writer.append(req.getAttribute("result").toString());
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            String page = req.getParameter("page");
            if (page != null) {
                req.getRequestDispatcher(page).forward(req, resp);
            } else {
                req.getRequestDispatcher("WEB-INF/views/userpage.jsp").forward(req, resp);
            }
        }
    }
}
