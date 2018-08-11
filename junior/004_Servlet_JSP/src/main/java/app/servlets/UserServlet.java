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
        String action = request.getParameter("action");
        Writer writer = response.getWriter();
        String method = request.getMethod();
        if (!par.isEmpty()) {
            logic.doAction(method, action, par);
            doGet(request, response);
        } else {
            writer.write("Insert some parameters");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer writer = response.getWriter();
        Map<String, String[]> par = request.getParameterMap();
        String list = logic.doAction("GET", "findAll", par);
        writer.write("<!DOCTYPE html>" +
                "<html lang='en'>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<title>UserStore</title>" +
                "</head>" +
                "<body>" +
                "<h1 align='center'>UserStore App!</h1>" +
                list +
                "<form action='" + request.getContextPath() + "/create' method='get'>" +
                "<p align='center'><button type='submit'>CREATE USER</button></p>" +
                "</form>" +
                "</body>" +
                "</html>");
    }
}
