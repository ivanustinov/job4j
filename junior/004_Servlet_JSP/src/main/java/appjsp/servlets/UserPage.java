package appjsp.servlets;

import appjsp.entities.User;
import appjsp.entities.enums.UsersRoles;
import appjsp.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import static appjsp.entities.enums.UsersRoles.ADMIN;
import static appjsp.entities.enums.UsersRoles.USER;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.10.2018
 */
@WebServlet(urlPatterns = {"/userPage"})
public class UserPage extends HttpServlet {
    private ValidateService actionService = ValidateService.getInstance();
    private final Map<UsersRoles, String> roleDispatch = new HashMap<>();

    @Override
    public void init() throws ServletException {
        roleDispatch.put(USER, "WEB-INF/views/userpage.jsp");
        roleDispatch.put(ADMIN, "WEB-INF/views/list.jsp");
    }

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
        String page = req.getParameter("page");
        if (action != null) {
            actionService.doAction(action, req);
            if (page != null) {
                req.getRequestDispatcher(page).forward(req, resp);
            } else {
                try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
                    writer.append(req.getAttribute("result").toString());
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (page != null) {
                req.getRequestDispatcher(page).forward(req, resp);
            } else {
                HttpSession session = req.getSession();
                User user = (User) session.getAttribute("user");
                page = roleDispatch.get(user.getRole());
                req.getRequestDispatcher(page).forward(req, resp);
            }
        }
    }
}
