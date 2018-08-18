package appjsp.servlets;

import appjsp.entities.User;
import appjsp.logic.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 01.08.2018
 */
public class UserServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> par = request.getParameterMap();
        String action = request.getParameter("action");
        String result = logic.doAction(action, par);
        request.setAttribute("postResult", result);
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<User> users = logic.findAll();
        request.setAttribute("users", users);
        request.setAttribute("size", users.size());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/views/list.jsp");
        requestDispatcher.forward(request, response);
    }
}
