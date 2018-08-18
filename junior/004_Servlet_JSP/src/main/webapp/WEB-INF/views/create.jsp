<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<fieldset>
    <form method='post'>
        <legend align='center'>Create User</legend>
        <input type='hidden' name='action' value='add'>
        <p align='center'>NAME: <input type='text' name='name'></p>
        <p align='center'>LOGIN: <input type='text' name='login'></p>
        <p align='center'>
            <button type='submit'>CREATE</button>
        </p>
    </form>
</fieldset>
<p align="center"><c:out value="${result}"/></p>
<form action='/list' method='get'>
    <p align='center'>
        <button align='center' type='submit'>VIEW LIST</button>
    </p>
</form>
</body>
</html>
