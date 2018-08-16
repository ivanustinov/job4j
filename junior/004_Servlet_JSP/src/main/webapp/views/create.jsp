<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 12.08.2018
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
</head>
<body>
<%
    String resultparameter = (String) request.getAttribute("result");
    if (resultparameter == null) {
        resultparameter = "<p align='center'>Insert name and login and press create button</p>";
    }
%>
<form method='post'>
    <fieldset>
        <legend align='center'>Create User</legend>
        <input type='hidden' name='action' value='add'>
        <p align='center'>NAME: <input type='text' name='name'></p>
        <p align='center'>LOGIN: <input type='text' name='login'></p>
        <p align='center'>
            <button type='submit'>CREATE</button>
        </p>
        <%=resultparameter%>
    </fieldset>
</form>
<form action='/servlet/list' method='get'>
    <p align='center'>
        <button align='center' type='submit'>VIEW LIST</button>
    </p>
</form>
</body>
</html>
