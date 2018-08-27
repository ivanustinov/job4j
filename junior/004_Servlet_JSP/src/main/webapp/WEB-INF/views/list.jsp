<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
<h1 align='center'>UserStore App!</h1>
<h4 align='center'>Login: ${sessionScope.login}</h4>
<h4 align='center'>Role: ${sessionScope.role}</h4>
<c:if test="${size == 0}">
    <p align="center">There are no users in the store</p>
</c:if>
<c:if test="${size > 0}">
    <table align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption>users in the store</caption>
        <tr>
            <th>ID</th>
            <th>ROLE</th>
            <th>LOGIN</th>
            <th colspan='2'>ACTIONS</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.role}</td>
                <td>${user.login}</td>
                <td>
                    <form action="/contr" method='post'>
                        <input type='hidden' name='page' value='WEB-INF/views/adminupdate.jsp'>
                        <input type='hidden' name='id' value='${user.id}'>
                        <input type='hidden' name='role' value='${user.role}'>
                        <input type='hidden' name='login' value='${user.login}'>
                        <input type='hidden' name='password' value='${user.password}'>
                        <button type='submit'>UPDATE</button>
                    </form>
                </td>
                <td>
                    <form action="/contr" method='post'>
                        <input type='hidden' name='page' value='WEB-INF/views/list.jsp'>
                        <input type='hidden' name='action' value='delete'>
                        <input type='hidden' name='id' value='${user.id}'>
                        <button type='submit'>DELETE</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<p align="center"><c:out value="${result}"/></p>
<p align="center">
    <form action="/contr" method='post'>
        <input type='hidden' name='page' value='WEB-INF/views/create.jsp'>
<p align='center'>
    <button type='submit'>Add user</button>
</p>
</form>
</p>
<form action="/contr" method='post'>
    <input type='hidden' name='action' value='findById'>
    <input type='hidden' name='page' value='WEB-INF/views/list.jsp'>
    <p align='center'>ID: <input type='text' name='id'></p>
    <p align='center'>
        <button type='submit'>Find user</button>
    </p>
</form>
<form action="/contr" method='post'>
    <input type='hidden' name='action' value='logOut'>
    <input type='hidden' name='page' value='WEB-INF/views/authentification.jsp'>
    <p align="center">
        <button type='submit'>Exit</button>
    </p>
</body>
</html>
