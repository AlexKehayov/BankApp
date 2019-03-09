<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction</title>
</head>
<body>

<form action="transactionServlet" method="post">
	ID of sender : <input type="text" name="sender_id"/><br/>
	ID of receiver : <input type="text" name="receiver_id"/><br/>
	Balance : <input type="text" name="balance"/><br/>
<input type="submit" />
</form>

</body>
</html>