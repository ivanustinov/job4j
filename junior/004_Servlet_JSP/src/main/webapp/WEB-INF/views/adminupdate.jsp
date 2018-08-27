<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<h4 align='center'>Edit user ${param.login}</h4>
<fieldset>
    <form action="/contr" method='post'>
        <legend align='center'>Insert new Parameters</legend>
        <input type='hidden' name='action' value='adminUpdate'>
        <input type='hidden' name='page' value='WEB-INF/views/adminupdate.jsp'>
        <input type='hidden' name='id' value='${param.id}'>
        <p align='center'>
            <select name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select>
        </p>
        <p align='center'>LOGIN: <input type='text' name='login' value="${param.login}" placeholder=${param.login}></p>
        <p align='center'>PASSWORD: <input type='text' name='password' value="${param.password}"
                                           placeholder=${param.password}></p>
        <p align='center'>
            <button type='submit'>UPDATE</button>
        </p>
    </form>
    <form action="/contr" method='post'>
        <p align='center'>
            <button type='submit'>BACK TO USER'S LIST</button>
        </p>
    </form>
</fieldset>
<p align="center"><c:out value="${result}"/></p>
</body>
</html>
