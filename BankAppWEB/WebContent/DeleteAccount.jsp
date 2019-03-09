<%@ page import="java.sql.*" language="java"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Account</title>
</head>
<body>

	<%!Connection con;
	PreparedStatement stmt;
	RequestDispatcher rd;
	ResultSet rs;
	int id;

	public void jspInit() {
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
	con.setAutoCommit(false);

	try {
		id = Integer.parseInt(request.getParameter("id"));
	} catch (Exception e) {
		rd = request.getRequestDispatcher("DeleteAccount_Data.jsp");
		rd.forward(request, response);
	}

	stmt = con.prepareStatement("select * from accounts where id=?;");
	stmt.setInt(1, id);
	rs = stmt.executeQuery();

	if (rs.next()) {
		String name = rs.getString("name");
		double balance = rs.getDouble("balance");

		out.print("Account information-> ID: " + id + " Name: " + name + " Balance: " + balance
				+ "<br/>");
	} else {
		rd = request.getRequestDispatcher("DeleteAccount_Data.jsp");
		rd.forward(request, response);
	}

	stmt = con.prepareStatement("delete from accounts where id=?;");
	stmt.setInt(1, id);
	int i = stmt.executeUpdate();
	if(i==1){
	con.commit();
	out.print("Operation Successful");
	}
	else{
		con.rollback();
		out.print("Operation failed. Nothing was deleted");
	}
	%>

	<br />
	<a href="Home.jsp">Main Page</a>

</body>
</html>