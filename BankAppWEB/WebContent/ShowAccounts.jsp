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
try{
limit = Integer.parseInt(request.getParameter("limit"));
}catch(Exception e){
	rd=request.getRequestDispatcher("ShowAccounts_Data.jsp");
	rd.include(request, response);
}

stmt = con.prepareStatement("select * from accounts order by id desc limit ?;");
stmt.setInt(1, limit);
rs = stmt.executeQuery();

while(rs.next()){
	String name = rs.getString("name");
	Long id = rs.getLong("id");
	double balance = rs.getDouble("balance");

	out.println("ID: " + id + " -  Name: " + name + " -  Balance: " + balance + "<br/>");
}

%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>