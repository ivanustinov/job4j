<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
    <h1 align='center'>UserStore App!</h1>
    <h4 align='center'>Login: ${user.login}</h4>
    <h4 align='center'>Role: ${user.role}</h4>
    <c:if test="${users.size() == 0}">
        <p align="center">There are no users in the store</p>
    </c:if>
    <c:if test="${users.size() > 0}">
        <table align='center' border='2' cellspacing='0' cellpadding='2'>
            <caption>users in the store</caption>
            <tr>
                <th>№</th>
                <th>ROLE</th>
                <th>LOGIN</th>
                <th colspan='2'>ACTIONS</th>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="counter">
                <tr>
                    <td>${counter.count}</td>
                    <td>${user.role}</td>
                    <td>${user.login}</td>
                    <td>
                        <form method='post'>
                            <input type='hidden' name='id' value='${user.id}'>
                            <input type='hidden' name='page' value='/adminUpdate'>
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
    <p align="center"><c:out value="${result}"/>
    <div align="center">
        <form method='post'>
            <input type='hidden' name='page' value='WEB-INF/views/create.jsp'>
            <button type='submit'>Add user</button>
        </form>
    </div>
    <div style="text-align: center">
        <form method="post">
            <input type='hidden' name='action' value='findById'>
            <div>ID: <input type='text' name='id'></div>
            <button type='submit'>Find user</button>
        </form>
        <form method="post">
            <input type='hidden' name='action' value='logOut'>
            <button type='submit'>Exit</button>
        </form>
    </div>
</body>
</html>
