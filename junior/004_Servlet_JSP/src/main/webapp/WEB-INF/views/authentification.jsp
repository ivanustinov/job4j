<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Login & Password</title>
</head>
<body>
<fieldset>
    <form action="auth" method='post'>
        <legend align='center'>Authentification</legend>
        <div align='center'>LOGIN: <input type='text' name='login'></div>
        <div align='center'>PASSWORD: <input type='password' name='password'>
            <br>
            <button type='submit'>ENTER</button>
        </div>
    </form>
    <br/>
    <div align="center">
        ${result}
    </div>
</fieldset>
</body>
</html>

