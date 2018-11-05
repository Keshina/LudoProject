<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign In</title>
</head>
<body>

<form id="loginForm" action="login" method="POST">
	<h1>Sign In</h1>
	<input type="text" name="username" placeholder="Username" required><br>
	<input type="password" name="password" placeholder="Password" required><br>
	<input type="submit" value="login" placeholder="Login" required><br>
	${errorMsg}
</form>
</body>
</html>