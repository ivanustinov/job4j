<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
    <h4 align='center'>Edit User: ${user.login}</h4>
    <fieldset>
        <c:set var="user" scope="page" value="${sessionScope.user}"/>
        <form method='post'>
            <div style="text-align: center">
                <input type='hidden' name='action' value="userUpdate">
                <input type='hidden' name='id' value="${user.id}">
                <input type='hidden' name='page' value="WEB-INF/views/update.jsp">
                <legend>Insert new Parameters</legend>
                <div>LOGIN: <input type='text' name='login' value="${user.login}"
                                   placeholder=${user.login}>
                </div>
                <div>PASSWORD:
                    <input type='text' name='password' value="${user.password}" placeholder=${user.password}>
                </div>
                <div>
                    <button style="text-align: center" type='submit'>UPDATE</button>
                </div>
            </div>
        </form>
        <form method='post'>
            <div align='center'>
                <button type='submit'>BACK TO HOME PAGE</button>
            </div>
        </form>
    </fieldset>
    <div align="center">${result}</div>
</body>
</html>
























