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

@WebServlet("/newAccServlet")
public class NewAccServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement stmt;
	private RequestDispatcher rd;

	String name;
	double balance;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("New Account Error 1");
		} catch (SQLException e) {
			System.out.println("New Account Error 2");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			name = request.getParameter("name");
			balance = Double.parseDouble(request.getParameter("balance"));

			stmt = con.prepareStatement("insert into accounts(name, balance) values (?,?);");
			stmt.setString(1, name);
			stmt.setDouble(2, balance);

			stmt.executeUpdate();

			ResultSet rs = stmt.executeQuery("select * from accounts order by id desc limit 1;");
			while (rs.next()) {
				int id1 = rs.getInt("id");
				request.setAttribute("id", id1);

				String name1 = rs.getString("name");
				request.setAttribute("name", name1);

				double balance1 = rs.getDouble("balance");
				request.setAttribute("balance", balance1);

				request.getRequestDispatcher("NewEntry_Result.jsp").forward(request, response);
			}

		} catch (Exception e) {
			rd = request.getRequestDispatcher("NewEntry_Data.jsp");
			rd.include(request, response);
		}
	}

	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("New Account Error 3");
		}

	}
}
