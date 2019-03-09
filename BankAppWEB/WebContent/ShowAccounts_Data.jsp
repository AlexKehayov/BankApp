<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Accounts</title>
</head>
<body>

<form action="ShowAccounts.jsp" method="post">

	Number of accounts to be shown(starting from last one):<br/>
	 <input type="text" name="limit"/><br/>
<input type="submit" />
</form>

</body>
</html>