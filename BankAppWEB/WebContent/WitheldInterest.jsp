<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Witheld Interest</title>
</head>
<body>

<%!
double profit;

public void jspInit(){
	
}

public void jspDestroy() {
	
}
%>

<%
profit = (double)request.getAttribute("profit1");
out.print("Profit for the bank: " + profit);
%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>