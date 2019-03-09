<%@ page import="java.sql.*"  language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Entry</title>
</head>
<body>

<%!
Connection con;
PreparedStatement stmt;
RequestDispatcher rd;
String name;
double balance;

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
try{
name = request.getParameter("name");
balance = Double.parseDouble(request.getParameter("balance"));
}catch(Exception e){
	rd=request.getRequestDispatcher("NewEntry_Data.jsp");
	rd.include(request, response);
}
stmt=con.prepareStatement("insert into accounts(name, balance) values (?,?);");
stmt.setString(1, name);
stmt.setDouble(2, balance);

stmt.executeUpdate();

ResultSet rs = stmt.executeQuery("select * from accounts order by id desc limit 1;");
while (rs.next()) {
	int id1 = rs.getInt("id");
	String name1 = rs.getString("name");
	double balance1 = rs.getDouble("balance");
	out.println("Last entry info: ID: " + id1 + " - Name: " + name1 + " - Balance: " + balance1);
}
%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>