package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import classes.UserAcc;
import db_connection.Connection_params;

@WebServlet("/InterestWitheld")
public class InterestWitheld extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	PreparedStatement stmt;
	PreparedStatement stmt2;
	PreparedStatement stmt3;
	RequestDispatcher rd;
	ResultSet rs;
	ExecutorService executor;
	private static final double interest = 0.114567;
	private volatile static double profit1 = 0;

	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(Connection_params.getUrl(), Connection_params.getRoot(),
					Connection_params.getPass());
		} catch (SQLException e) {
			System.out.println("Witheld Error 1");
		} catch (ClassNotFoundException e) {
			System.out.println("Witheld Error 2");
		}
	}

	private static UserAcc GetData(ResultSet rs) throws SQLException {
		UserAcc acc = new UserAcc();
		acc.setName(rs.getString("name"));
		acc.setId(rs.getLong("id"));
		acc.setBalance(rs.getDouble("balance"));
		return acc;
	}

	private synchronized static void profit(UserAcc acc, PreparedStatement stmt2, PreparedStatement stmt3)
			throws SQLException {
		double curr = acc.getBalance();
		profit1 += curr * (interest / 100.);
		double curr1 = curr - curr * (interest / 100.);

		stmt2.setDouble(1, curr1);
		stmt2.setLong(2, acc.getId());
		stmt3.setString(1, acc.getName());
		stmt3.setString(2, "Bank");
		stmt3.setDouble(3, (curr - curr1));
		stmt2.executeUpdate();
		stmt3.executeUpdate();

		System.out.println(profit1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		executor = Executors.newFixedThreadPool(10);
		try {
			con.setAutoCommit(false);
			stmt = con.prepareStatement("select * from accounts;");
			stmt2 = con.prepareStatement("update accounts set balance = ? where id = ?;");
			stmt3 = con.prepareStatement("insert into trhistory(sender, receiver, value) values(?,?,?);");
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserAcc acc = GetData(rs);
				executor.submit(new Runnable() {

					@Override
					public void run() {
						try {
							profit(acc, stmt2, stmt3);
						} catch (SQLException e) {
							System.out.println(e.getMessage());
						}
					}

				});
			}

			executor.shutdown();

			executor.awaitTermination(1, TimeUnit.DAYS);

			stmt = con.prepareStatement("select balance from accounts where name = 'Bank';");
			rs = stmt.executeQuery();

			double balance = 0;

			while (rs.next()) {
				balance = rs.getDouble("balance");
			}
			balance += profit1;
			stmt = con.prepareStatement("update accounts set balance = ? where name = 'Bank';");
			stmt.setDouble(1, balance);
			stmt.executeUpdate();

			con.commit();

			rd = request.getRequestDispatcher("WitheldInterest.jsp");
			request.setAttribute("profit1", profit1);
			rd.forward(request, response);
			profit1 = 0;

		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
			System.out.println("Witheld Error 3");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			System.out.println("Witheld Error 4");
		}
	}

	@Override
	public void destroy() {
		try {
			stmt.close();
			stmt2.close();
			stmt3.close();
			rs.close();
			executor.shutdown();
			con.close();
		} catch (SQLException e) {
			System.out.println("Witheld Error 5");
		}
	}

}
