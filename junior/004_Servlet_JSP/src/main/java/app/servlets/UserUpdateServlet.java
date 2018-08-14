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
 * @since 10.08.2018
 */
public class UserUpdateServlet extends HttpServlet {
    private final ValidateService logic = ValidateService.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> par = request.getParameterMap();
        String action = request.getParameter("action");
        if (action == null) {
            doGet(request, response);
        } else {
            String result = logic.doAction("POST", action, par);
            request.setAttribute("result", result);
            doGet(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        String resultparameter = (String) request.getAttribute("result");
        if (resultparameter == null) {
            resultparameter = "<p align='center'>result of updating</p>";
        }
        String name = request.getParameter("name");
        String login = request.getParameter("login");
        String id = request.getParameter("id");
        writer.write("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>Update User</title>" +
                "</head>" +
                "<body>" +
                "<form method='post'>" +
                "<fieldset>" +
                "<legend align='center'>Insert new Parameters</legend>" +
                "<input type='hidden' name='action' value ='update'>" +
                "<input type='hidden' name='id' value = '" + id + "'>" +
                "<p align='center'>NAME: <input type='text' name='name' value='" + name + "'></p>" +
                "<p align='center'>LOGIN: <input type='text' name='login' value='" + login + "'></p>" +
                "<p align='center'><button type='submit'>UPDATE</button></p>" +
                resultparameter +
                "</fieldset>" +
                "</form>" +
                "<form action='/list' method='get'>" +
                "<p align='center'><button type='submit'>VIEW LIST</button></p>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
}
