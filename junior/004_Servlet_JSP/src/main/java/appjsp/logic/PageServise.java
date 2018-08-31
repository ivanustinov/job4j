package appjsp.logic;

import appjsp.entities.User;
import appjsp.entities.UsersRoles;
import appjsp.persistent.Store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 27.08.2018
 */
public class PageServise {
    private final Store<User> store;
    private final Map<UsersRoles, String> roleDispatch = new HashMap<>();
    private final Map<String, Consumer<SessionRequestContext>> pageDispatch = new HashMap<>();


    public String initPage(SessionRequestContext context) {
        String page = context.getParameter("page");
        if (page == null) {
            UsersRoles role = (UsersRoles) context.getSessionAttribute("role");
            page = roleDispatch.get(role);
        }
        Consumer<SessionRequestContext> ans = pageDispatch.get(page);
        ans.accept(context);
        return page;
    }


    public PageServise(Store<User> store) {
        this.store = store;
        initHomePage();
        initRedirectPage();
    }

    public void initHomePage() {
        roleDispatch.put(UsersRoles.USER, "WEB-INF/views/userpage.jsp");
        roleDispatch.put(UsersRoles.ADMIN, "WEB-INF/views/list.jsp");

    }

    public void initRedirectPage() {
        pageDispatch.put("WEB-INF/views/userpage.jsp", userPage());
        pageDispatch.put("WEB-INF/views/list.jsp", adminPage());
        pageDispatch.put("WEB-INF/views/update.jsp", userPage());
        pageDispatch.put("WEB-INF/views/adminupdate.jsp", adminUpdate());
        pageDispatch.put("WEB-INF/views/create.jsp", createPage());
        pageDispatch.put("WEB-INF/views/authentification.jsp", createPage());
    }

    public Consumer<SessionRequestContext> userPage() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                User user = store.findById((int) (context.getSessionAttribute("id")));
                context.setRequestAttribute("user", user);
            }
        };
    }

    public Consumer<SessionRequestContext> createPage() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
            }
        };
    }

    public Consumer<SessionRequestContext> adminUpdate() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                User user = store.findById(Integer.parseInt(context.getParameter("id")));
                context.setRequestAttribute("user", user);
            }
        };
    }

    public Consumer<SessionRequestContext> adminPage() {
        return new Consumer<SessionRequestContext>() {
            @Override
            public void accept(SessionRequestContext context) {
                ArrayList<User> users = store.findAll();
                context.setRequestAttribute("users", users);
                context.setRequestAttribute("size", users.size());
            }
        };
    }
}
