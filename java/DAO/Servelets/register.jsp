<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
</head>
<body>
    <h2>Registration</h2>

    <form action="register" method="post">
        <label for="username">Username: </label>
        <input type="text" id="username" name="username" value="${param.username}" required><br>

        <label for="password">Password: </label>
        <input type="password" id="password" name="password" required><br>

        <label for="email">Email: </label>
        <input type="email" id="email" name="email" value="${param.email}" required><br>

        <input type="submit" value="Register">
    </form>

    <!-- Display registration status message using JSTL -->
    <c:if test="${not empty param.successMessage}">
        <div style="color: green;">${param.successMessage}</div>
    </c:if>

    <c:if test="${not empty param.errorMessage}">
        <div style="color: red;">${param.errorMessage}</div>
    </c:if>
</body>
</html>
