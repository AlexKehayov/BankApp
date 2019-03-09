package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_connection.Connection_params;

@WebServlet("/transactionServlet")
public class TransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement stmt;
	private RequestDispatcher rd;
	
	int sender_id;
	int receiver_id;
	String sender_name;
	String receiver_name;
	double balance;
	double sender_balance;
	double receiver_balance;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("Transaction Error 1");
		} catch (SQLException e) {
			System.out.println("Transaction Error 2");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try{
		con.setAutoCommit(false);
		sender_id=Integer.parseInt(request.getParameter("sender_id"));
		receiver_id=Integer.parseInt(request.getParameter("receiver_id"));
		balance = Double.parseDouble(request.getParameter("balance"));
		
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
			request.setAttribute("sender_id",sender_id);
			request.setAttribute("sender_name",sender_name);
			request.setAttribute("sender_balance",sender_balance);
		}

		stmt.setInt(1, receiver_id);
		rs=stmt.executeQuery();
		if(rs.next()){
			receiver_balance=rs.getDouble("balance");
			request.setAttribute("receiver_id",receiver_id);
			request.setAttribute("receiver_name",receiver_name);
			request.setAttribute("receiver_balance",receiver_balance);
		}
		request.getRequestDispatcher("welcome.jsp").forward(request, response); 
		
		}catch(Exception e){
			rd=request.getRequestDispatcher("Transaction_Data.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Transaction Error 3");
		}

	}
}
