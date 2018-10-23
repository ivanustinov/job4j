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
                var currentContries = document.getElementById("countries");
                currentContries.innerHTML = countries;
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
                $('#cities').empty();
                var currentCities = document.getElementById("cities");
                console.log(currentCities);
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
            console.log(array);
            var object = JSON.stringify(user);
            $.ajax({
                url: "adminPage",
                type: "POST",
                data: {
                    action: "add",
                    user: object
                }
            }).done(function (data) {
                $('#result').html(data);
                $('form')[0].reset();
            });
            return false;
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
        <form method='post'>
            <legend align='center'>Create new User</legend>
            <div>
                <select name="role">
                    <option selected="selected" disabled>Select role</option>
                    <option>USER</option>
                    <option>ADMIN</option>
                </select>
            </div>
            <div>
                <select name="country" id="countries" onchange="getCities()"/>
            </div>
            <div>LOGIN: <input type='text' name='login' placeholder="Input login"></div>
            <div>PASSWORD: <input type='text' name='password' placeholder="Input password"></div>
            <p align='center'>
                <button type='button' onclick="createUser()">CREATE</button>
            </p>
            <div>
                <select name="city" id="cities"/>
            </div>
        </form>
    </fieldset>
    <p align="center" id="result"></p>
    <form>
        <div>
            <button type='submit'>BACK TO USER'S LIST</button>
        </div>
    </form>
</body>
</html>
