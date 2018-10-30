<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .table-bordered > tbody > tr > th {
            border: 2px solid black;
            background: #ccc;
            text-align: center;
        }

        .table-bordered > tbody > tr > td {
            border: 2px solid black;
        }

    </style>
    <script>
        function exit() {
            $.ajax({
                url: "userPage",
                type: "POST",
                data: {
                    action: "logOut",
                },
                success: function () {
                    window.location.href = '/storage';
                }
            });
        }
    </script>
</head>
<body style="text-align: center">
    <h4>Hellow ${user.login}</h4>
    <c:set var="user" scope="page" value="${sessionScope.user}"/>
    <table style="width: 45%; margin-top: 10px; text-align: center" class="container table table-bordered"
           id="table" align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption style="text-align: center; color: black">User's information</caption>
        <tr>
            <th>ROLE</th>
            <th>COUNTRY</th>
            <th style="width: 150px">CITY</th>
            <th style="width: 150px">LOGIN</th>
            <th>PASSWORD</th>
            <th>ACTIONS</th>
        </tr>
        <tr>
            <td>${user.role}</td>
            <td>${user.country}</td>
            <td>${user.city}</td>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>
                <form style="margin-bottom: 0em" method="post">
                    <input type='hidden' name='page' value='/WEB-INF/views/update.jsp'>
                    <button type='submit'>EDIT</button>
                </form>
            </td>
        </tr>
    </table>
    <form>
        <input type='hidden' name='action' value='logOut'>
        <div>
            <button type='button' class="btn btn-primary" onclick="exit()">Exit</button>
        </div>
    </form>
</body>
</html>
