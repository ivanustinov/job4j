package appjsp.servlets;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 28.08.2018
 */
//public class ControllerTest {
//    @Test
//    public void addUser() throws ServletException, IOException {
//        DbStore store = DbStore.getInstance();
//        ArrayList<User> usersBefore = store.findAll();
//        Controller controller = new Controller();
//        HashMap<String, String[]> par = new HashMap<>();
//        String page = "WEB-INF/views/create.jsp";
//        par.put("page", new String[]{page});
//        par.put("action", new String[]{"add"});
//        par.put("login", new String[]{"Petr"});
//        par.put("password", new String[]{"petr"});
//        par.put("role", new String[]{"USER"});
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getParameterMap()).thenReturn(par);
//        when(request.getSession()).thenReturn(session);
//        when(request.getRequestDispatcher(page)).thenReturn(requestDispatcher);
//        controller.doPost(request, response);
//        verify(requestDispatcher).forward(request, response);
//        ArrayList<User> usersAfter = store.findAll();
//        usersAfter.removeAll(usersBefore);
//        User petr = usersAfter.get(0);
//        int id = petr.getId();
//        store.delete(id);
//    }
//
//    @Test
//    public void updateAdmin() throws ServletException, IOException {
//        DbStore store = DbStore.getInstance();
//        ArrayList<User> usersBefore = store.findAll();
//        store.add(UsersRoles.USER, "Petr", "petr");
//        ArrayList<User> usersAfter = store.findAll();
//        usersAfter.removeAll(usersBefore);
//        User petr = usersAfter.get(0);
//        int id = petr.getId();
//        Controller controller = new Controller();
//        HashMap<String, String[]> par = new HashMap<>();
//        par.put("page", new String[]{"WEB-INF/views/adminUpdate.jsp"});
//        par.put("action", new String[]{"adminUpdate"});
//        par.put("login", new String[]{"Ivan"});
//        par.put("password", new String[]{"ivan"});
//        par.put("role", new String[]{"ADMIN"});
//        par.put("id", new String[]{String.valueOf(id)});
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getParameterMap()).thenReturn(par);
//        when(request.getSession()).thenReturn(session);
//        when(request.getRequestDispatcher("WEB-INF/views/adminUpdate.jsp")).thenReturn(requestDispatcher);
//        controller.doPost(request, response);
//        assertThat(store.findById(id), is(new User(id, UsersRoles.ADMIN, "Ivan", "ivan")));
//        store.delete(id);
//    }
//
//    @Test
//    public void updateUser() throws ServletException, IOException {
//        DbStore store = DbStore.getInstance();
//        Controller controller = new Controller();
//        ArrayList<User> usersBefore = store.findAll();
//        store.add(UsersRoles.USER, "Petr", "petr");
//        ArrayList<User> usersAfter = store.findAll();
//        usersAfter.removeAll(usersBefore);
//        User petr = usersAfter.get(0);
//        int id = petr.getId();
//        HashMap<String, String[]> par = new HashMap<>();
//        par.put("page", new String[]{"WEB-INF/views/userUpdate.jsp"});
//        par.put("action", new String[]{"userUpdate"});
//        par.put("login", new String[]{"Alex"});
//        par.put("password", new String[]{"alex"});
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getParameterMap()).thenReturn(par);
//        when(request.getSession()).thenReturn(session);
//        when(session.getAttribute("id")).thenReturn(id);
//        when(request.getRequestDispatcher("WEB-INF/views/userUpdate.jsp")).thenReturn(requestDispatcher);
//        controller.doPost(request, response);
//        assertThat(store.findById(id), is(new User(id, UsersRoles.USER, "Alex", "alex")));
//        store.delete(id);
//    }
//
//    @Test
//    public void delete() throws ServletException, IOException {
//        DbStore store = DbStore.getInstance();
//        Controller controller = new Controller();
//        ArrayList<User> usersBefore = store.findAll();
//        store.add(UsersRoles.USER, "Petr", "petr");
//        ArrayList<User> usersAfter = store.findAll();
//        usersAfter.removeAll(usersBefore);
//        User petr = usersAfter.get(0);
//        int id = petr.getId();
//        HashMap<String, String[]> par = new HashMap<>();
//        par.put("page", new String[]{"WEB-INF/views/list.jsp"});
//        par.put("action", new String[]{"delete"});
//        par.put("id", new String[]{String.valueOf(id)});
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getParameterMap()).thenReturn(par);
//        when(request.getSession()).thenReturn(session);
//        when(request.getRequestDispatcher("WEB-INF/views/list.jsp")).thenReturn(requestDispatcher);
//        controller.doPost(request, response);
//        User user = store.findById(id);
//        User user1 = null;
//        assertThat(user, is(user1));
//    }
//
//}