<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>History of Transactions</title>
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
try{
limit = Integer.parseInt(request.getParameter("limit"));
}catch(Exception e){
	rd=request.getRequestDispatcher("ShowHistory_Data.jsp");
	rd.include(request, response);
}

stmt = con.prepareStatement("select * from trhistory order by dt desc limit ?;");
stmt.setInt(1, limit);
rs = stmt.executeQuery();

while(rs.next()){
	String sender = rs.getString("sender");
	String receiver = rs.getString("receiver");
	double value = rs.getDouble("value");
	String dt3 = rs.getString("dt");

	out.println(sender + " -> " + receiver + " - " + value + " - " + dt3 + "<br/>");
}

%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>