<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
</head>
<body>
    <h2>Welcome, ${user.username}</h2>
    <p>Email: ${user.email}</p>
    <form action="profile" method="post">
        <label for="email">Update Email:</label>
        <input type="email" name="email" value="${user.email}" required><br><br>
        
        <label for="password">Update Password:</label>
        <input type="password" name="password" required><br><br>
        
        <input type="submit" value="Update Profile">
    </form>
    <a href="logout">Logout</a>
</body>
</html>
