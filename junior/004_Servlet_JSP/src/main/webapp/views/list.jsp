<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserStore</title>
</head>
<body>
<h1 align='center'>UserStore App!</h1>
<%
    String postResult = (String) request.getAttribute("postResult");
    if (postResult != null) {
        out.println(postResult);
    } else {
        String list = (String) request.getAttribute("getResult");
        out.println(list);
    }
%>
<form action='/servlet/create' method='get'>
    <p align='center'>
        <button type='submit'>CREATE USER</button>
    </p>
</form>
<form action='/servlet/index.html' method='get'>
    <p align='center'>
        <button type='submit'>Back</button>
    </p>
</form>
</body>
</html>
