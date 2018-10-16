<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Insert Login & Password</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        function authantification() {
            if (!validate()) {
                return;
            }
            var data = $('form').serializeArray();
            $.ajax({
                type: "POST",
                url: "auth",
                data: data
            });
            return false;
        }


        function validate() {
            var names = $('div [id]')
            for (var i = 0; i != names.length; i++) {
                var input = names[i]
                if (input.value == '') {
                    alert($(input).attr('placeholder'));
                }
            }
        }
    </script>
</head>
<body style="text-align: center">
    <fieldset>
        <form id="myForm" class="form-horizontal">
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-5" style="padding-right: 0px" for="Login">Login:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px" class="form-control" id="Login"
                           placeholder="Enter login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-5" style="padding-right: 0px" for="Password">Password:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px" class="form-control" id="Password"
                           placeholder="Enter password">
                </div>
            </div>
            <div>
                <button type="button" class="btn btn-primary" style="margin-top: 10px" onclick="authantification()">
                    Submit
                </button>
            </div>
        </form>
        <br/>
        <div align="center">
            ${result}
        </div>
    </fieldset>
</body>
</html>

