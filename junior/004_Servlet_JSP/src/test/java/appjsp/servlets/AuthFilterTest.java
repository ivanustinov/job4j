package appjsp.servlets;

import org.junit.Test;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static org.mockito.Mockito.*;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 04.09.2018
 */
public class AuthFilterTest {
    @Test
    public void doFilterTest() throws Exception {
        HttpServletRequest servletRequest = mock(HttpServletRequest.class);
        HttpServletResponse servletResponse = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        HttpSession session = mock(HttpSession.class);
        RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(servletRequest.getSession()).thenReturn(session);
        when(servletRequest.getRequestDispatcher("/")).thenReturn(dispatcher);
        when(servletRequest.getRequestURI()).thenReturn("/");
        AuthFilter authFilter = new AuthFilter();
        authFilter.doFilter(servletRequest, servletResponse, filterChain);
        verify(session, times(1)).getAttribute("login");
    }
}