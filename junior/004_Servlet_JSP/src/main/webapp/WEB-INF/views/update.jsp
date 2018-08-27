<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<h4 align='center'>Edit User: ${user.login}</h4>
<fieldset>
    <form action="/contr" method='post'>
        <legend align='center'>Insert new Parameters</legend>
        <input type='hidden' name='action' value='update'>
        <input type='hidden' name='page' value='WEB-INF/views/update.jsp'>
        <p align='center'>LOGIN: <input type='text' name='login' value="${user.login}" placeholder=${user.login}></p>
        <p align='center'>PASSWORD: <input type='text' name='password' value="${user.password}"
                                           placeholder=${user.password}></p>
        <p align='center'>
            <button type='submit'>UPDATE</button>
        </p>
    </form>
    <form action="/contr" method='post'>
        <p align='center'>
            <button type='submit'>BACK TO HOME PAGE</button>
        </p>
    </form>
</fieldset>
<p align="center"><c:out value="${result}"/></p>
</body>
</html>
























