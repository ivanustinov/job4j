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
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 09.08.2018
 */
public class UserCreateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer writer = resp.getWriter();
        String resultparameter = (String) req.getAttribute("result");
        if (resultparameter == null) {
            resultparameter = "<p align='center'>result of creating</p>";
        }
        writer.write("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>Create User</title>" +
                "</head>" +
                "<body>" +
                "<form method='post'>" +
                "<fieldset>" +
                "<legend align='center'>Create User</legend>" +
                "<input type='hidden' name='action' value ='add'>" +
                "<p align='center'>NAME: <input type='text' name='name'></p>" +
                "<p align='center'>LOGIN: <input type='text' name='login'></p>" +
                "<p align='center'><button type='submit'>CREATE</button></p>" +
                resultparameter +
                "</fieldset>" +
                "</form>" +
                "<form action='" + req.getContextPath() + "/list' method='get'>" +
                "<p align='center'><button align='center' type='submit'>VIEW LIST</button></p>" +
                "</form>" +
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> par = req.getParameterMap();
        String action = req.getParameter("action");
        String result = logic.doAction("POST", action, par);
        req.setAttribute("result", result);
        doGet(req, resp);
    }
}
