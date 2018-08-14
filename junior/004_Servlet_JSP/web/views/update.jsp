<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 12.08.2018
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update User</title>
</head>
<body>
<%
    String resultparameter = (String) request.getAttribute("result");
    if (resultparameter == null) {
        resultparameter = "<p align='center'>result of updating</p>";
    }
    String name = request.getParameter("name");
    String login = request.getParameter("login");
    String id = request.getParameter("id");
%>
<form method='post'>
    <fieldset>
        <legend align='center'>Insert new Parameters</legend>
        <input type='hidden' name='action' value='update'>
        <input type='hidden' name='id' value='<%=id%>'>
        <p align='center'>NAME: <input type='text' name='name' value='<%=name%>'></p>
        <p align='center'>LOGIN: <input type='text' name='login' value='<%=login%>'></p>
        <p align='center'>
            <button type='submit'>UPDATE</button>
        </p>
        <%=resultparameter%>
    </fieldset>
</form>
<form action='/list' method='get'>
    <p align='center'>
        <button type='submit'>VIEW LIST</button>
    </p>
</form>
</body>
</html>
