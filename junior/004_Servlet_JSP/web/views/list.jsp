<%@ page import="appjsp.logic.ValidateService" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
<h1 align='center'>UserStore App!</h1>
<%
    ValidateService service = ValidateService.getInstance();
    String users = service.doAction("GET", "findAll", null);
    out.println(users);%>
<form action='/create' method='get'>
    <p align='center'>
        <button type='submit'>CREATE USER</button>
    </p>
</form>
</body>
</html>
