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

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private PreparedStatement stmt;
	private RequestDispatcher reqDis;
       
    
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("Login Error 1");
		} catch (SQLException e) {
			System.out.println("Login Error 2");
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			stmt=con.prepareStatement("select * from access where name=? and password=?;");
			stmt.setString(1, request.getParameter("name"));
			stmt.setString(2, request.getParameter("password"));
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				reqDis = request.getRequestDispatcher("Home.jsp");
				reqDis.forward(request, response);
			}else {
				reqDis = request.getRequestDispatcher("Login.jsp");
				reqDis.include(request, response);
			}
		} catch (SQLException e) {
			System.out.println("Login Error 3");
		}
		
		
	}
	
	
	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Login Error 4");
		}
	}

}
