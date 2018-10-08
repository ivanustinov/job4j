<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    <h4 align='center'>Hello ${user.login}</h4>
    <h4 align='center'>Role: ${sessionScope.user.role}</h4>
<table align='center' border='2' cellspacing='0' cellpadding='2'>
    <caption>User's information</caption>
    <tr>
        <th>ID</th>
        <th>ROLE</th>
        <th>LOGIN</th>
        <th>ACTIONS</th>
    </tr>
    <tr>
        <td>${user.id}</td>
        <td>${user.role}</td>
        <td>${user.login}</td>
        <td>
            <form action="contr" method='post'>
                <input type='hidden' name='page' value='WEB-INF/views/update.jsp'>
                <button type='submit'>Edit</button>
            </form>
        </td>
    </tr>
</table>
    <form method='post'>
    <input type='hidden' name='action' value='logOut'>
    <input type='hidden' name='page' value='WEB-INF/views/authentification.jsp'>
    <p align="center">
        <button type='submit'>Exit</button>
    </p>
</form>
</body>
</html>
