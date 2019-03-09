<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<%!
Connection con;
PreparedStatement stmt;
RequestDispatcher rd;
ResultSet rs;
int limit;


public void jspInit(){
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:33061/bankapp"
				+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
				"root", "prileptsi");		
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
}

public void jspDestroy() {
	try {
		stmt.close();
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
}
%>


<%
String data = request.getParameter("data");
stmt = con.prepareStatement("Select * from accounts where id like '%" + data + "%' or name like '%" + data + "%';");

rs = stmt.executeQuery();

while(rs.next()) {
	out.print("Name: " + rs.getString("name") + " - ID: " + rs.getInt("id") + " - Balance: " + rs.getDouble("balance") + "<br/>");
}

%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>