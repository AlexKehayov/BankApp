package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_connection.Connection_params;


@WebServlet("/SearchAccountServlet")
public class SearchAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	PreparedStatement stmt;
	RequestDispatcher rd;
	ResultSet rs;
	int limit;
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("Search Account Error 1");
		} catch (SQLException e) {
			System.out.println("Search Account Error 2");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		String data = request.getParameter("data");
		stmt = con.prepareStatement("Select * from accounts where id like '%" + data + "%' or name like '%" + data + "%';");

		rs = stmt.executeQuery();

		LinkedList <String> results = new LinkedList<>();
		
		while(rs.next()) {
			String str = "Name: " + rs.getString("name") + " - ID: " + rs.getInt("id") + " - Balance: " + rs.getDouble("balance");
			results.add(str);
		}
		
		request.setAttribute("results",results);
		request.getRequestDispatcher("SearchAccount_Result.jsp").forward(request, response); 
		
		}catch(Exception e){
			rd=request.getRequestDispatcher("SearchAccount_Data.jsp");
			rd.include(request, response);
		}
	}
	
	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Search Account Error 3");
		}
	}

}
