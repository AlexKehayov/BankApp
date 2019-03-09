<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<form action="loginServlet" method="post">
Welcome to BankApp!<br/>This Application is meant to be used only by bank employees!<br/>
Please enter your credentials:<br/>
	Name : <input type="text" name="name"/><br/>
	Password: <input type="password" name="password"/><br/>
<input type="submit" />
</form>

</body>
</html>