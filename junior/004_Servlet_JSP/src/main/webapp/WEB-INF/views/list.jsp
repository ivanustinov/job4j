<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>UserStore</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        .table-bordered > tbody > tr > th {
            border: 2px solid black;
            background: #ccc; /* Цвет фона */
            text-align: center;
        }

        .table-bordered > tbody > tr > td {
            border: 2px solid black;
        }
    </style>
    <script>
        $(document).ready(function () {
            getUsers();
            $("#myInput").on("keyup", function () {
                var value = $(this).val().toLowerCase();
                $("#table tr").filter(function () {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });

        function deleteUser(id) {
            $.ajax({
                url: "userPage",
                type: "POST",
                data: {
                    action: "delete",
                    id: id
                },
                success: function (data) {
                    $('#result').html(data);
                }
            }).done(function () {
                getUsers();
            });
        }

        function userUpdatePage(id) {
            $.ajax({
                url: "userPage",
                type: "POST",
                data: {
                    id: id,
                    action: 'findById',
                    page: "WEB-INF/views/adminUpdate.jsp"
                }
            }).done(function (response) {
                document.write(response);
            });
        }

        function getUsers() {
            $.ajax({
                url: "userPage",
                method: "POST",
                data: {
                    action: "getAll"
                },
                complete: function (data) {
                    var users = JSON.parse(data.responseText);
                    var table = '';
                    if (users.length == 0) {
                        table = 'There are no users in the store';
                    }
                    else {
                        var a = 1;
                        for (var i = 0; i != users.length; i++) {
                            table += '<tr>'
                                + '<td>' + a + '</td>'
                                + '<td>' + users[i].role + '</td>'
                                + '<td>' + users[i].country + '</td>'
                                + '<td>' + users[i].city + '</td>'
                                + '<td>' + users[i].login + '</td>'
                                + '<td>'
                                + '<form style="margin-bottom: 0em" method="post">'
                                + '<input type=\'hidden\' name=\'id\' value=\'' + users[i].id + '\'>'
                                + '<input type=\'hidden\' name=\'page\' value=\'/WEB-INF/views/adminUpdate.jsp\'>'
                                + '<input type=\'hidden\' name=\'action\' value=\'findById\'>'
                                + '<button type="submit">UPDATE</button>'
                                + '</form>'
                                + '</td>'
                                + '<td><button type="button" onclick="deleteUser(\'' + users[i].id + '\')">DELETE</button></td>'
                                + '</tr>';
                            a++;
                        }
                    }
                    $('#tableBody').html(table);
                }
            });
        }

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
    <h1 align='center'>UserStore App!</h1>
    <h4 align='center'>Login: ${sessionScope.user.login}</h4>
    <h4 align='center'>Role: ${sessionScope.user.role}</h4>
    <table style="width: 45%; margin-top: 10px; text-align: center" class="container table table-bordered"
           id="table" align='center' border='2' cellspacing='0' cellpadding='2'>
        <caption style="text-align: center; color: black">users in the store</caption>
        <tr>
            <th>№</th>
            <th>ROLE</th>
            <th style="width: 150px">COUNTRY</th>
            <th style="width: 150px">CITY</th>
            <th style="width: 150px">LOGIN</th>
            <th colspan='2'>ACTIONS</th>
        </tr>
        <tbody id="tableBody">
        </tbody>
    </table>
    <p align="center" id="result"/>
    <div align="center">
        <form>
            <input type='hidden' name='page' value='/WEB-INF/views/create.jsp'>
            <button class="btn btn-primary" type='submit'>Add user</button>
        </form>
    </div>
    <div>
        <p>Type something in the input field to search the table for role, country, city or login:</p>
        <input id="myInput" type="text" placeholder="Search..">
    </div>
    <button style="margin-top: 15px" type='button' class="btn btn-primary" onclick="exit()">Exit</button>
</body>
</html>
