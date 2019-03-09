<%@ page import = "java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Controller</title>
</head>
<body>

<%!
RequestDispatcher rd;

public void jspInit(){
}

public void jspDestroy() {	
}
%>

<%
String operation = request.getParameter("option");

switch (operation) {

		case "0":
			rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
			break;
		case "1":
			rd=request.getRequestDispatcher("NewEntry_Data.jsp");
			rd.forward(request, response);
			break;
		case "2":
			rd=request.getRequestDispatcher("Transaction_Data.jsp");
			rd.forward(request, response);
			break;
		case "3":
			rd=request.getRequestDispatcher("ShowHistory_Data.jsp");
			rd.forward(request, response);
			break;
		case "4":
			rd=request.getRequestDispatcher("ShowAccounts_Data.jsp");
			rd.forward(request, response);
			break;
		case "5":
			rd=request.getRequestDispatcher("DeleteAccount_Data.jsp");
			rd.forward(request, response);
			break;
		case "6":
			rd=request.getRequestDispatcher("SearchAccount_Data.jsp");
			rd.forward(request, response);
			break;
		case "7":
			rd=request.getRequestDispatcher("/InterestWitheld");
			rd.forward(request, response);
			break;
		default:
			rd=request.getRequestDispatcher("Home.jsp");
			rd.forward(request, response);
		}
%>

</body>
</html>