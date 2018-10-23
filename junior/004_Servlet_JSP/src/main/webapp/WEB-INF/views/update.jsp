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
            loadCountryCity()
        });

        function loadCountryCity() {
            $.ajax({
                type: "GET",
                url: "countryCity",
                dataType: "json"
            }).done(function (data) {
                var countries = '';
                $.each(data, function (key, value) {
                    console.log(key + ' ' + value);
                    countries += '<option value=' + value + '>' + key + '</option>'
                });
                $('#countries').html(countries);
                $('#countries [value=${sessionScope.user.country}]').attr("selected", "selected");
            }).done(function () {
                getCities();
                $('#cities [value=${sessionScope.user.city}]').attr("selected", "selected");
            })
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
                    console.log(key + ' ' + value);
                    cities += '<option value=' + value + '>' + key + '</option>';
                });
                $('#cities').html(cities);
            });
        }
    </script>
</head>
<body style="text-align: center">
    <h4 align='center'>Edit User: ${sessionScope.user.login}</h4>
    <legend>Insert new Parameters</legend>
    <fieldset>
        <c:set var="user" scope="page" value="${sessionScope.user}"/>
        <form method='post'>
            <div>
                <select id="countries" onchange="getCities()"/>
            </div>
            <div>
                <select id="cities"/>
            </div>
            <div>
                LOGIN: <input type='text' name='login' placeholder="${user.login}">
            </div>
            <div>
                PASSWORD: <input type='text' name='password' placeholder="${user.password}">
            </div>
            <div>
                <button style="text-align: center" type='submit'>UPDATE</button>
            </div>

        </form>
        <form>
            <div>
                <button type='submit'>BACK TO HOME PAGE</button>
            </div>
        </form>
    </fieldset>
    <div align="center">${result}</div>
</body>
</html>
























