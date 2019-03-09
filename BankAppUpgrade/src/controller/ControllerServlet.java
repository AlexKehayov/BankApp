package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db_connection.Connection_params;


@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Connection con;
	private PreparedStatement stmt;
	private RequestDispatcher rd;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("Controller Error 1");
		} catch (SQLException e) {
			System.out.println("Controller Error 2");
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					rd=request.getRequestDispatcher("SearchAccount_Data.jsp");
					rd.forward(request, response);
					break;
				case "5":
					rd=request.getRequestDispatcher("/InterestWitheld");
					rd.forward(request, response);
					break;
				default:
					rd=request.getRequestDispatcher("Home.jsp");
					rd.forward(request, response);
				}
	}
	
	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Controller Error 3");
		}
	}

}
