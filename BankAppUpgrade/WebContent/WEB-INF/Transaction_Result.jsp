<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction</title>
</head>
<body>

Sender ID: <%= request.getAttribute("sender_id") %><br/>
Sender Name: <%= request.getAttribute("sender_name") %><br/>
Sender Balance: <%= request.getAttribute("sender_balance") %><br/>
Receiver ID: <%= request.getAttribute("receiver_id") %><br/>
Receiver Name: <%= request.getAttribute("receiver_name") %><br/>
Receiver Balance: <%= request.getAttribute("receiver_balance") %><br/>

<br/><a href = "Home.jsp">Main Page</a>
</body>
</html>