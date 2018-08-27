<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Login & Password</title>
</head>
<body>
<fieldset>
    <form action="/auth" method='post'>
        <legend align='center'>Authentification</legend>
        <p align='center'>LOGIN: <input type='text' name='login'></p>
        <p align='center'>PASSWORD: <input type='password' name='password'></p>
        <p align='center'>
            <button type='submit'>ENTER</button>
        </p>
    </form>
    <br/>
    ${result}
</fieldset>
</body>
</html>

