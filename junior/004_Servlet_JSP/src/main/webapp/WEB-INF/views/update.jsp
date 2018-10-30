<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "countryCity",
                dataType: "json"
            }).done(function (data) {
                var countries = '';
                $.each(data, function (key, value) {
                    countries += '<option value=' + value + '>' + key + '</option>'
                });
                $('#countries').html(countries);
                $('#countries [value=${user.country}]').attr("selected", "selected");
                setCity()
            });
        });

        function setCity() {
            $.ajax({
                type: "POST",
                url: "countryCity",
                data: {country: $('#countries').val()},
                dataType: "json"
            }).done(function (data) {
                var cities = '';
                $.each(data, function (key, value) {
                    console.log(key + ' ' + value);
                    cities += '<option value=' + value + '>' + key + '</option>';
                });
                $('#cities').html(cities);
                $('#cities [value=${user.city}]').attr("selected", "selected");
            });

        }

        function getCities() {
            $.ajax({
                type: "POST",
                url: "countryCity",
                data: {country: $('#countries').val()},
                dataType: "json"
            }).done(function (data) {
                var cities = '';
                $.each(data, function (key, value) {
                    cities += '<option value=' + value + '>' + key + '</option>';
                });
                $('#cities').html(cities);
            });
        }

        function updateUser() {
            if (!validate()) {
                return;
            }
            var user = {};
            var array = $('#user').serializeArray();
            for (var i = 0; i < array.length; i++) {
                user[array[i].name] = array[i].value;
            }
            var us = JSON.stringify(user);
            $.ajax({
                type: "POST",
                url: "userPage",
                data: {
                    action: "userUpdate",
                    user: us
                }
            }).done(function (data) {
                $('#result').html(data);
            });
        }

        function validate() {
            var isEmpty = true;
            var names = $('div [name]');
            for (var i = 0; i != names.length; i++) {
                var input = names[i]
                if (input.value == '') {
                    alert($(input).attr('placeholder'));
                    isEmpty = false;
                }
            }
            return isEmpty;
        }
    </script>
</head>
<body style="text-align: center">
    <h4 align='center'>Edit User: ${sessionScope.user.login}</h4>
    <legend>Insert new Parameters</legend>
    <fieldset>
        <c:set var="user" scope="page" value="${sessionScope.user}"/>
        <form id="user" class="form-horizontal">
            <input type="hidden" name="id" value='${user.id}'>
            <input type="hidden" name="role" value='${user.role}'>
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-6" style="padding-right: 5px" for="countries">Select country:</label>
                <div>
                    <select class="form-control" style="padding-left: 3px; width: auto"
                            name="country" id="countries" onchange="getCities()"></select>
                </div>
            </div>
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-6" style="padding-right: 5px" for="cities">Select city:</label>
                <div>
                    <select class="form-control" style="padding-left: 3px; width: auto"
                            name="city" id="cities"></select>
                </div>
            </div>
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-6" style="padding-right: 5px" for="login">Login:</label>
                <div>
                    <input type='text' style="padding-left: 3px; width: auto" class="form-control"
                           name='login' id="login" placeholder="${user.login}" value="${user.login}">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-6" style="padding-right: 5px"
                       for="password">Password:</label>
                <div>
                    <input type='text' style="padding-left: 3px; width: auto" class="form-control"
                           name='password' id="password" placeholder="${user.password}" value="${user.password}">
                </div>
            </div>
            <div>
                <button type="button" class="btn btn-primary" onclick="updateUser()">UPDATE</button>
            </div>
        </form>
        <form>
            <div>
                <button type='submit' class="btn btn-primary">BACK TO HOME PAGE</button>
            </div>
        </form>
    </fieldset>
    <p align="center" id="result"></p>
</body>
</html>
























