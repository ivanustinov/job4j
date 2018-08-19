<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
<h1 align='center'>UserStore App!</h1>
<c:if test="${size == 0}">
    <p align="center">There are no users in the store</p>
</c:if>
<c:if test="${size > 0}">
    <table align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption>users in the store</caption>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>LOGIN</th>
            <th colspan='2'>ACTIONS</th>
        </tr>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.login}</td>
                <td>
                    <form action='/edit' method='get'>
                        <input type='hidden' name='id' value='${user.id}'>
                        <input type='hidden' name='name' value='${user.name}'>
                        <input type='hidden' name='login' value='${user.login}'>
                        <button type='submit'>UPDATE</button>
                    </form>
                </td>
                <td>
                    <form method='post'>
                        <input type='hidden' name='action' value='delete'>
                        <input type='hidden' name='id' value='${user.id}'>
                        <button type='submit'>DELETE</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
<p align="center"><c:out value="${postResult}"/></p>
<p align="center">
    <form action='/create' method='get'>
<p align='center'>
    <button type='submit'>CREATE USER</button>
</p>
</form>
<form method='post'>
    <input type='hidden' name='action' value='findById'>
    <p align='center'>ID: <input type='text' name='id'></p>
    <p align='center'>
        <button type='submit'>Find user</button>
    </p>
</form>
</body>
</html>
