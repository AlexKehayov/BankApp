<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accounts</title>
</head>
<body>

<%
@SuppressWarnings("unchecked")
LinkedList<String> results = (LinkedList<String>)request.getAttribute("results");
for(int i = 0; i<results.size(); i++){
	out.println(results.get(i));
}
%>

<br/><a href = "Home.jsp">Main Page</a>
</body>
</html>