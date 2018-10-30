<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create User</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            getCountries();
        });

        function getCountries() {
            $.ajax({
                type: "GET",
                url: "countryCity",
                dataType: "json"
            }).done(function (data) {
                var countries = '<option selected="selected" disabled>Select country</option>';
                $.each(data, function (key, value) {
                    console.log(key + ' ' + value);
                    countries += '<option value=' + value + '>' + key + '</option>'
                });
                $('#countries').html(countries);
            });
        }

        function getCities() {
            $.ajax({
                type: "POST",
                url: "countryCity",
                data: {country: $('[name=country]').val()},
                dataType: "json"
            }).done(function (data) {
                var cities = '<option selected="selected" disabled>Select city</option>';
                $.each(data, function (key, value) {
                    cities += '<option value=' + value + '>' + key + '</option>'
                });
                var currentCities = document.getElementById("cities");
                currentCities.innerHTML = cities;
            });
        }

        function createUser() {
            if (!validate()) {
                return false;
            }
            var user = {};
            var array = $('form').serializeArray();
            for (var i = 0; i < array.length; i++) {
                user[array[i].name] = array[i].value;
            }
            var object = JSON.stringify(user);
            $.ajax({
                type: "POST",
                url: "adminPage",
                data: {
                    action: "add",
                    user: object
                }
            }).done(function (data) {
                $('#result').html(data);
                $('form')[0].reset();
            });
        }

        function validate() {
            var isEmpty = true;
            var names = $('div [name]')
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
    <fieldset>
        <form class="form-horizontal">
            <legend align='center'>Create new User</legend>
            <div>
                <select class="form-control" style="margin-left: 706px; padding-left: 3px; width: auto" name="role">
                    <option selected="selected" disabled>Select role</option>
                    <option>USER</option>
                    <option>ADMIN</option>
                </select>
            </div>
            <div style="margin-top: 10px">
                <select class="form-control" style="margin-left: 706px; padding-left: 3px; width: auto" name="country"
                        id="countries" onchange="getCities()"></select>
            </div>
            <div style="margin-top: 10px; text-align: right">
                <select class="form-control" style="margin-left: 706px; padding-left: 3px; width: auto" name="city"
                        id="cities"></select>
            </div>
            <div style="margin-left: 706px; margin-top: 10px">
                <input type='text' style="padding-left: 3px; width: auto" class="form-control"
                       name='login' id="login" placeholder="Input login">
            </div>
            <div style="margin-left: 706px; margin-top: 10px">
                <input type='text' style="padding-left: 3px; width: auto" class="form-control"
                       name='password' id="password" placeholder="Input password">
            </div>
            <div align='center' style="margin-top: 10px">
                <button type='button' class="btn btn-primary" onclick="createUser()">CREATE</button>
            </div>
        </form>
    </fieldset>
    <form>
        <div>
            <button class="btn btn-primary" type='submit'>BACK TO USER'S LIST</button>
        </div>
    </form>
    <p align="center" id="result"></p>
</body>
</html>
