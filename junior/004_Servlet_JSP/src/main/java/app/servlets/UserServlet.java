package app.servlets;

import app.logic.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
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
        Writer writer = response.getWriter();
        String[] action = null;
        if (!par.isEmpty() && (action = par.get("action")) != null && action.length != 0) {
            String actonValue = action[0];
            String method = request.getMethod();
            String str = logic.doAction(method, actonValue, par);
            writer.write(str);
        } else {
            writer.write("Insert some parameters or correct parameter's names");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> par = request.getParameterMap();
        Writer writer = response.getWriter();
        String[] action = null;
        if (!par.isEmpty() && (action = par.get("action")) != null && action.length != 0) {
            String actonValue = action[0];
            String method = request.getMethod();
            String str = logic.doAction(method, actonValue, par);
            writer.write(str);
        } else {
            writer.write("Insert some parameters or correct parameter's names");
        }
    }
}
