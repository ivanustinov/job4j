<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserStore</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(getUsers());

        function deleteUser(id) {
            console.log(id);
            $.ajax({
                url: "adminPage",
                type: "POST",
                data: {
                    action: "delete",
                    id: id
                },
                success: function (data) {
                    console.log(data)
                    $('#result').html(data);
                }
            }).done(function () {
                getUsers();
            });
            return false;
        }

        function getUsers() {
            $.ajax({
                url: "adminPage",
                method: "POST",
                data: {
                    action: "getAll"
                },
                complete: function (data) {
                    var users = JSON.parse(data.responseText);
                    var table = '';
                    var a = 1;
                    for (var i = 0; i != users.length; i++) {
                        table += '<tr>'
                            + '<td>' + a++ + '</td>'
                            + '<td>' + users[i].role + '</td>'
                            + '<td>' + users[i].country + '</td>'
                            + '<td>' + users[i].city + '</td>'
                            + '<td>' + users[i].login + '</td>'
                            + '<td><button type="button" onclick="deleteUser(' + users[i].id + ')">UPDATE</button><td>'
                            + '<td><button type="button" onclick="deleteUser(' + users[i].id + ')">DELETE</button><td>'
                            + '</tr>'
                    }
                    var tableBody = document.getElementById("tableBody");
                    tableBody.innerHTML = table;
                }
            });
        }
    </script>
</head>
<body style="text-align: center">
    <h1 align='center'>UserStore App!</h1>
    <h4 align='center'>Login: ${user.login}</h4>
    <h4 align='center'>Role: ${user.role}</h4>
    <table align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption>users in the store</caption>
        <tr>
            <th>№</th>
            <th>ROLE</th>
            <th>COUNTRY</th>
            <th>CITY</th>
            <th>LOGIN</th>
            <th colspan='2'>ACTIONS</th>
        </tr>
        <tbody id="tableBody">
        </tbody>
    </table>
    <p align="center" id="result"/>
    <div align="center">
        <form>
            <input type='hidden' name='page' value='/WEB-INF/views/create.jsp'>
            <button type='submit'>Add user</button>
        </form>
    </div>
    <div style="text-align: center">
        <form method="post">
            <input type='hidden' name='action' value='findById'>
            <div>ID: <input type='text' name='id'></div>
            <button type='submit'>Find user</button>
        </form>
        <form>
            <input type='hidden' name='action' value='logOut'>
            <button type='submit'>Exit</button>
        </form>
    </div>
</body>
</html>
