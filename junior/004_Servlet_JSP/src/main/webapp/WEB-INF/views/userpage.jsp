<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
</head>
<body style="text-align: center">
    <h4 align='center'>Hellow ${user.login}</h4>
    <table align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption>User's information</caption>
        <tr>
            <th>ID</th>
            <th>ROLE</th>
            <th>COUNTRY</th>
            <th>CITY</th>
            <th>LOGIN</th>
            <th>PASSWORD</th>
            <th>ACTIONS</th>
        </tr>
        <tr>
            <td>${sessionScope.user.id}</td>
            <td>${sessionScope.user.role}</td>
            <td>${sessionScope.user.country}</td>
            <td>${sessionScope.user.city}</td>
            <td>${sessionScope.user.login}</td>
            <td>${sessionScope.user.password}</td>
            <td>
                <form>
                    <%--<input type='hidden' name='page' value='countryCity'>--%>
                    <input type='hidden' name='page' value='/WEB-INF/views/update.jsp'>
                    <button type='submit'>EDIT</button>
                </form>
            </td>
        </tr>
    </table>
    <form>
        <input type='hidden' name='action' value='logOut'>
        <div>
            <button type='submit'>Exit</button>
        </div>
    </form>
</body>
</html>
