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
        String str = logic.doAction(par.get("action")[0], par);
        Writer writer = response.getWriter();
        writer.write(str);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> par = request.getParameterMap();
        if (par.size() != 0) {
            String str = logic.doAction(par.get("action")[0], par);
            Writer writer = response.getWriter();
            writer.write(str);
        }
    }
}
