package appjsp.servlets;

/**
 * //TODO add comments.
 *
 * @author Ivan Ustinov(ivanustinov1985@yandex.ru)
 * @version 1.0
 * @since 31.08.2018
 */
public class SignInTest {

//    @Test
//    public void loginUser() throws ServletException, IOException {
//        DbStore store = DbStore.getInstance();
//        ArrayList<User> usersBefore = store.findAll();
//        User user = new User(UsersRoles.USER, "Petr", "petr");
//        store.add(user);
//        ArrayList<User> usersAfter = store.findAll();
//        usersAfter.removeAll(usersBefore);
//        User petr = usersAfter.get(0);
//        String id = petr.getId();
//        SignIn signIn = new SignIn();
//        HttpServletRequest request = mock(HttpServletRequest.class);
//        when(request.getParameter("login")).thenReturn("Petr");
//        when(request.getParameter("password")).thenReturn("petr");
//        HttpServletResponse response = mock(HttpServletResponse.class);
//        HttpSession session = mock(HttpSession.class);
//        RequestDispatcher requestDispatcher = mock(RequestDispatcher.class);
//        when(request.getSession()).thenReturn(session);
//        when(request.getRequestDispatcher("/contr")).thenReturn(requestDispatcher);
//        signIn.doPost(request, response);
//        verify(session, times(1)).setAttribute("login", "Petr");
//        store.delete(id);
//    }

}