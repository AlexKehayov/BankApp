<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History of Transactions</title>
</head>
<body>

<form action="showHistoryServlet" method="post">

	Number of transactions to be shown(starting from last one):<br/>
	 <input type="text" name="limit"/><br/>
<input type="submit" />
</form>

</body>
</html>