package appjsp.logic;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.DbStore;
import appjsp.persistent.Store;

import java.util.ArrayList;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.08.2018
 */
public class PageServise {
    private final Store<User> store = DbStore.getInstance();

    public String initPage(SessionRequestContext context) {
        String page = context.getParameter("page");
        if (page == null) {
            page = homepage(context);
        }
        return page;
    }

    public void initUserPage(SessionRequestContext context) {
        User user = store.findById((int) (context.getSessionAttribute("id")));
        context.setRequestAttribute("user", user);
    }

    public void initAdminPage(SessionRequestContext context) {
        ArrayList<User> users = findAll();
        context.setRequestAttribute("users", users);
        context.setRequestAttribute("size", users.size());
    }

    public String homepage(SessionRequestContext context) {
        UsersRoles role = (UsersRoles) context.getSessionAttribute("role");
        String page = "WEB-INF/views/userpage.jsp";
        if (role.equals(UsersRoles.ADMIN)) {
            page = "WEB-INF/views/list.jsp";
            initAdminPage(context);
        } else {
            initUserPage(context);
        }
        return page;
    }

    public ArrayList<User> findAll() {
        return store.findAll();
    }
}
