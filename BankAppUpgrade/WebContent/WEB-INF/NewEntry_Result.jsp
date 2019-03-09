<%@ page import="java.sql.*"  language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Entry</title>
</head>
<body>

ID: <%= request.getAttribute("id") %><br/>
Name: <%= request.getAttribute("name") %><br/>
Balance: <%= request.getAttribute("balance") %><br/>

<br/><a href = "Home.jsp">Main Page</a>
</body>
</html>