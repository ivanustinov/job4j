<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
    </style>
    <script>
        $(document).ready(function () {
            $('#role [value=${user.role}]').attr("selected", "selected");
        });
    </script>
</head>
<body style="text-align: center">
    <h4 align='center'>Edit user ${user.login}</h4>
    <fieldset>
        <form method='post' class="form-horizontal">
            <legend align='center'>Insert new Parameters:</legend>
            <input type='hidden' name='action' value='adminUpdate'>
            <input type='hidden' name='page' value='WEB-INF/views/adminUpdate.jsp'>
            <input type='hidden' name='id' value='${user.id}'>
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-5" style="padding-right: 0px" for="role">Select role:</label>
                <div class="col-sm-1" style="padding-left: 3px">
                    <select class="form-control" style="padding-left: 0px; width: auto" id="role" name="role">
                        <option value="USER">USER</option>
                        <option value="ADMIN">ADMIN</option>
                    </select>
                </div>
            </div>
            <div class="form-group" style="margin-top: 10px">
                <label class="control-label col-sm-5" style="padding-right: 0px" for="login">Login:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px" class="form-control" value="${user.login}"
                           id="login" name="login" placeholder=${user.login}>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-5" style="padding-right: 0px" for="password">Password:</label>
                <div class="col-sm-2" style="padding-left: 3px">
                    <input type="text" style="padding-left: 3px" class="form-control" value="${user.password}"
                           id="password" name="password" placeholder=${user.login}>
                </div>
            </div>
            <div>
                <button type="submit" class="btn btn-primary" style="margin-top: 10px">
                    UPDATE
                </button>
            </div>
        </form>
        <form action="adminPage" method='get'>
            <div>
                <button type="submit" class="btn btn-primary" style="margin-top: 10px">
                    BACK TO USER'S LIST
                </button>
            </div>
        </form>
    </fieldset>
    <p align="center"><c:out value="${result}"/></p>
</body>
</html>
