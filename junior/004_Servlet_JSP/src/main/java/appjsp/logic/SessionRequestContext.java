package appjsp.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 23.08.2018
 */
public class SessionRequestContext {
    private Map<String, String> par = new HashMap<>();
    private Map<String, Object> attributes = new HashMap<>();
    private HttpSession session;

    public void extractValues(HttpServletRequest request) {
        Map<String, String[]> par = request.getParameterMap();
        for (String parName : par.keySet()) {
            this.par.put(parName, par.get(parName)[0]);
        }
        Enumeration<String> attrNames = request.getAttributeNames();
        while (attrNames.hasMoreElements()) {
            String element = attrNames.nextElement();
            attributes.put(element, request.getAttribute(element));
        }
        session = request.getSession();
    }


    public void insertAttributes(HttpServletRequest request) {
        for (Map.Entry<String, Object> stringObjectEntry : attributes.entrySet()) {
            request.setAttribute(stringObjectEntry.getKey(), stringObjectEntry.getValue());
        }
    }

    public String getParameter(String name) {
        return par.get(name);
    }

    public void setRequestAttribute(String attr, Object value) {
        attributes.put(attr, value);
    }

    public HttpSession getSession() {
        return session;
    }

    public Object getSessionAttribute(String name) {
        return session.getAttribute(name);
    }
}
