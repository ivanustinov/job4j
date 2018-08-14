<%@ page import="appjsp.entities.User" %>
<%@ page import="appjsp.persistent.DbStore" %>
<%@ page import="appjsp.persistent.Store" %>
<%@ page import="java.util.Collection" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
<h1 align='center'>UserStore App!</h1>
<%
    final Store store = DbStore.getInstance();
    Collection<User> users = store.findAll();
    if (users.size() == 0) {
        out.println("<p align = 'center'> there are no users in the store</p>");
    } else {
        out.println("<table align='center' border='2' cellspacing='0' cellpadding='2'>" +
                "<caption>users in the store</caption>" +
                "<tr><th>ID</th><th>NAME</th><th colspan='2'>ACTIONS</th></tr>");
        for (User user : users) {
            String name = user.getName();
            String login = user.getLogin();
            int id = user.getId();
            out.println("<tr><td>" + id + "</td><td>" + name + "</td><td>" +
                    "<form action='/edit' method='post'>" +
                    "<input type='hidden' name='login' value ='" + login + "'>" +
                    "<input type='hidden' name='name' value = '" + name + "'>" +
                    "<input type='hidden' name='id' value = '" + id + "'>" +
                    "<button type='submit'>UPDATE</button></td><td>" +
                    "</form>" +
                    "<form method='post'>" +
                    "<input type='hidden' name='action' value = 'delete'>" +
                    "<input type='hidden' name='id' value = '" + id + "'>" +
                    "<button type='submit'>DELETE</button>" +
                    "</form>" +
                    "</td></tr>");
        }
        out.println("</table>");
    }%>
<form action='/create' method='get'>
    <p align='center'>
        <button type='submit'>CREATE USER</button>
    </p>
</form>
</body>
</html>
