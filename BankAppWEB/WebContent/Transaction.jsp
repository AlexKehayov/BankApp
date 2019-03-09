<%@ page import="java.sql.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction</title>
</head>
<body>

<%!
Connection con;
PreparedStatement stmt;
RequestDispatcher rd;
int sender_id;
int receiver_id;
String sender_name;
String receiver_name;
double balance;
double sender_balance;
double receiver_balance;

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
con.setAutoCommit(false);
try{
sender_id=Integer.parseInt(request.getParameter("sender_id"));
receiver_id=Integer.parseInt(request.getParameter("receiver_id"));
balance = Double.parseDouble(request.getParameter("balance"));
}catch(Exception e){
	rd=request.getRequestDispatcher("Transaction_Data.jsp");
	rd.forward(request, response);
}
stmt=con.prepareStatement("Select * from accounts where id=?;");
stmt.setInt(1, sender_id);
ResultSet rs = stmt.executeQuery();
if(rs.next()){
	sender_balance = rs.getDouble("balance");
	sender_name = rs.getString("name");
}else{
	rd=request.getRequestDispatcher("Transaction_Data.jsp");
	rd.forward(request, response);
}

stmt=con.prepareStatement("Select * from accounts where id=?;");
stmt.setInt(1, receiver_id);
rs = stmt.executeQuery();
if(rs.next()){
	receiver_name = rs.getString("name");
}else{
	rd=request.getRequestDispatcher("Transaction_Data.jsp");
	rd.forward(request, response);
}

if(sender_balance<balance){
	rd=request.getRequestDispatcher("Transaction_Data.jsp");
	rd.forward(request, response);
}else{
	stmt=con.prepareStatement("update accounts set balance = balance-? where id = ?;");
	stmt.setDouble(1, balance);
	stmt.setInt(2, sender_id);
	stmt.executeUpdate();
	stmt=con.prepareStatement("update accounts set balance = balance+? where id = ?;");
	stmt.setDouble(1, balance);
	stmt.setInt(2, receiver_id);
	stmt.executeUpdate();
	
	stmt = con.prepareStatement("insert into trhistory(sender, receiver, value) values(?, ?, ?);");
	stmt.setString(1, sender_name);
	stmt.setString(2, receiver_name);
	stmt.setDouble(3, balance);
	stmt.executeUpdate();
}
con.commit();
con.setAutoCommit(true);

stmt=con.prepareStatement("select * from accounts where id=?;");
stmt.setInt(1, sender_id);
rs=stmt.executeQuery();
if(rs.next()){
	sender_balance=rs.getDouble("balance");
	out.print("Sender-> ID: " + sender_id + " Name: " + sender_name + " Balance: " + sender_balance + "<br/>");
}

stmt.setInt(1, receiver_id);
rs=stmt.executeQuery();
if(rs.next()){
	receiver_balance=rs.getDouble("balance");
	out.print("Receiver-> ID: " + receiver_id + " Name: " + receiver_name + " Balance: " + receiver_balance + "<br/>");
}
%>

<br/><a href = "Home.jsp">Main Page</a>

</body>
</html>