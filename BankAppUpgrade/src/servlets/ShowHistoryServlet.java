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

@WebServlet("/showHistoryServlet")
public class ShowHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Connection con;
	private PreparedStatement stmt;
	private RequestDispatcher rd;
	ResultSet rs;
	int limit;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(), Connection_params.getPass());
		} catch (ClassNotFoundException e) {
			System.out.println("Show History Error 1");
		} catch (SQLException e) {
			System.out.println("Show History Error 2");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try{
			limit = Integer.parseInt(request.getParameter("limit"));
			

			stmt = con.prepareStatement("select * from trhistory order by dt desc limit ?;");
			stmt.setInt(1, limit);
			rs = stmt.executeQuery();
			
			LinkedList <String> results = new LinkedList<>();

			while(rs.next()){
				String sender = rs.getString("sender");
				String receiver = rs.getString("receiver");
				double value = rs.getDouble("value");
				String dt3 = rs.getString("dt");
				
				String str = "Sender: " + sender + " - Receiver: " + receiver + " - Value: " + value + " - DT: " + dt3;
				results.add(str);
			}
			
			request.setAttribute("results",results);
			request.getRequestDispatcher("ShowHistory_Result.jsp").forward(request, response); 
			
		}catch(Exception e){
			rd=request.getRequestDispatcher("ShowHistory_Data.jsp");
			rd.include(request, response);
		}
	}

	@Override
	public void destroy() {
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {
			System.out.println("Show History Error 3");
		}

	}
}
