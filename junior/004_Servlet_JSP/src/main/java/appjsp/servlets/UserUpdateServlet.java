package appjsp.servlets;

import appjsp.logic.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 10.08.2018
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> par = request.getParameterMap();
        String action = request.getParameter("action");
        if (action != null) {
            String result = logic.doAction(action, par);
            request.setAttribute("result", result);
        }
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("views/update.jsp");
        requestDispatcher.forward(request, response);
    }
}
