<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<h4 align='center'>Edit User: ${user.login}</h4>
<fieldset>
    <form method='post'>
        <legend align='center'>Insert new Parameters</legend>
        <input type='hidden' name='action' value='update'>
        <input type='hidden' name='page' value='WEB-INF/views/update.jsp'>
        <div align='center'>LOGIN: <input type='text' name='login' value="${user.login}" placeholder=${user.login}>
        </div>
        <div align='center'>PASSWORD:
            <input type='text' name='password' value="${user.password}" placeholder=${user.password}>
            <button type='submit'>UPDATE</button>
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
























