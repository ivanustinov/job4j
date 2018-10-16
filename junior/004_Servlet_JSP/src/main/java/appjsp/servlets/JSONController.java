package appjsp.servlets;

import appjsp.entities.IndexUser;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 12.10.2018
 */
@WebServlet(name = "JSONController", urlPatterns = {"/json"})
public class JSONController extends HttpServlet {
    private final Map<String, IndexUser> users = new ConcurrentHashMap<>();

    @Override
    public void init() throws ServletException {
        IndexUser user = new IndexUser("Petr", "Ivanov", "ivanov@email.ru");
        IndexUser user2 = new IndexUser("Petr", "Ivanov", "ivanov@email.ru");
        users.put(user.getId(), user);
        users.put(user2.getId(), user2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String user;
            while ((user = reader.readLine()) != null) {
                stringBuilder.append(user);
            }
        }
        System.out.println(stringBuilder.toString());
        ObjectMapper mapper = new ObjectMapper();
        IndexUser indexUser = mapper.readValue(stringBuilder.toString(), IndexUser.class);
        users.put(indexUser.getId(), indexUser);
        System.out.println(users.size());
        System.out.println("POST");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json");
        PrintWriter writer = new PrintWriter(response.getOutputStream());
        writer.append("[");
        int i = 1;
        for (String s : users.keySet()) {
            IndexUser user = users.get(s);
            writer.append("{\"firstName\":\"" + user.getFirstName() + "\", \"serName\":\"" + user.getSerName()
                    + "\", \"email\":\"" + user.getEmail() + "\"}");
            if (i++ == users.size() || users.size() == 0) {
                continue;
            } else {
                writer.append(", ");
            }
        }
        writer.append("]");
        writer.flush();
        System.out.println("GET");
    }
}
