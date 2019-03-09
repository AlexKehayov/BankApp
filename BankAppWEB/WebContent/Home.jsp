<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BankApp-Home</title>
</head>
<body>

	<br/>Menu:<br/>0.Exit<br/>1.Create new entry<br/>2.Transaction between
	accounts<br/>3.Show history of transactions<br/>4.Show
	accounts<br/>5.Delete account<br/>6.Search account<br/>7.Witheld
	Interest<br/>

	<form action="Controller.jsp" method="post">
		Option : <input type="text" name="option" /><br /> 
		<input type="submit" />
	</form>

</body>
</html>