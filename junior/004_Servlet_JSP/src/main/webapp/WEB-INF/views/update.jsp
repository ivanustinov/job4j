<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<fieldset>
    <form action="/edit" method='post'>
        <legend align='center'>Insert new Parameters</legend>
        <input type='hidden' name='action' value='update'>
        <input type='hidden' name='id' value='${param.id}'>
        <p align='center'>NAME: <input type='text' name='name' value="${param.name}" placeholder=${param.name}></p>
        <p align='center'>LOGIN: <input type='text' name='login' value="${param.login}" placeholder=${param.login}></p>
        <p align='center'>
            <button type='submit'>UPDATE</button>
        </p>
    </form>
</fieldset>
<p align="center"><c:out value="${result}"/></p>
<form action='/list' method='get'>
    <p align='center'>
        <button type='submit'>VIEW LIST</button>
    </p>
</form>
</body>
</html>
























