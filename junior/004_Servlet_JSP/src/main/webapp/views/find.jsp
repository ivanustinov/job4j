<%--
  Created by IntelliJ IDEA.
  User: Ivan
  Date: 15.08.2018
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Find User!</title>
</head>
<h1 align='center'>Find user by id</h1>
<body>
<form action='/servlet/list' method='post'>
    <input type='hidden' name='action' value='findById'>
    <p align='center'>ID: <input type='text' name='id'></p>
    <p align='center'>
        <button type='submit'>Find</button>
    </p>
</form>
</div>
</body>
</html>
