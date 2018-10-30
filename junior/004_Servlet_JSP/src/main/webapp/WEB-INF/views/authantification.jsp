<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Insert Login & Password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<script>
    function authorisation() {
        if (!validate()) {
            return false;
        }
        $.ajax({
            type: "POST",
            url: "auth",
            data: $('form').serializeArray(),
            dataType: "json"
        }).done(function (data) {
            var response = data;
            if (response.result == "success") {
                window.location.href = "userPage";
            }
            else {
                $('#result').html(response.result);
            }
            $('#myForm')[0].reset();
        });
        return false;
    }

    function validate() {
        var names = $('div [id]');
        var isEmpty = true;
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
<body style="text-align: center">
    <fieldset>
        <form id="myForm" class="form-horizontal">
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-6" style="padding-right: 0px" for="login">Login:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px; width: auto" class="form-control" id="login"
                           name="login"
                           placeholder="Enter login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-6" style="padding-right: 0px" for="password">Password:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px; width: auto" class="form-control" id="password"
                           name="password"
                           placeholder="Enter password">
                </div>
            </div>
            <div>
                <button type="button" class="btn btn-primary" style="margin-top: 10px" onclick="authorisation()">
                    Submit
                </button>
            </div>
        </form>
        <br/>
        <div align="center" id="result"></div>
    </fieldset>
</body>
</html>

