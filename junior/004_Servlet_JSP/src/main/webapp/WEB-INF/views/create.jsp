<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<fieldset>
    <form action="/contr" method='post'>
        <legend align='center'>Create new User</legend>
        <input type='hidden' name='action' value='add'>
        <input type='hidden' name='page' value='WEB-INF/views/create.jsp'>
        <p align='center'>
            <select name="role">
                <option value="ADMIN">ADMIN</option>
                <option value="USER">USER</option>
            </select>
        </p>
        <p align='center'>LOGIN: <input type='text' name='login'></p>
        <p align='center'>PASSWORD: <input type='text' name='password'></p>
        <p align='center'>
            <button type='submit'>CREATE</button>
        </p>
    </form>
</fieldset>
<p align="center">${result}</p>
<form action="/contr" method='post'>
    <p align='center'>
        <button type='submit'>BACK TO USER'S LIST</button>
    </p>
</form>
</body>
</html>
