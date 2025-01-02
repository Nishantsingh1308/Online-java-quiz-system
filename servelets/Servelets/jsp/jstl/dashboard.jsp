<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, ${user.name}!</h2>

    <p>Your profile information:</p>
    <ul>
        <li>Email: ${user.email}</li>
        <li>Username: ${user.username}</li>
    </ul>

    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</body>
</html>
