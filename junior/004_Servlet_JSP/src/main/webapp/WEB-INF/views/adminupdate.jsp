<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update User</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            $("#role [value='${user.role}']").attr("selected", "selected");
        });

    </script>
</head>
<body>
    <h4 align='center'>Edit user ${user.login}</h4>
    <fieldset>
        <form method='post'>
            <legend align='center'>Insert new Parameters</legend>
            <input type='hidden' name='action' value='adminUpdate'>
            <input type='hidden' name='page' value='/adminUpdate'>
            <input type='hidden' name='id' value='${user.id}'>
            <div align='center'>
                <select id="role" name="role">
                    <option value="USER">USER</option>
                    <option value="ADMIN">ADMIN</option>
                </select>
            </div>
            <p align='center'>LOGIN: <input type='text' name='login' value="${user.login}" placeholder=${user.login}>
            </p>
            <p align='center'>PASSWORD: <input type='text' name='password' value="${user.password}"
                                               placeholder=${user.password}></p>
            <p align='center'>
                <button type='submit'>UPDATE</button>
            </p>
        </form>
        <form method='post'>
            <p align='center'>
                <button type='submit'>BACK TO USER'S LIST</button>
            </p>
        </form>
    </fieldset>
    <p align="center"><c:out value="${result}"/></p>
</body>
</html>
