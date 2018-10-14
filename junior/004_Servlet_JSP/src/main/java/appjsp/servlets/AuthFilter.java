package appjsp.servlets;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.08.2018
 */
//@WebFilter(dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST}, urlPatterns = { "/*" })
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (request.getRequestURI().contains("/index.html")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("/WEB-INF/views/authentification.jsp").forward(request, response);
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
