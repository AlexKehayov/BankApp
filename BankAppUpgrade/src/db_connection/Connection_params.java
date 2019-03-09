package db_connection;

public class Connection_params {
	private static String url = "jdbc:mysql://localhost:33061/bankapp"
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String root = "root";
	private static String pass = "prileptsi";

	public static String getUrl() {
		return url;
	}

	public static String getRoot() {
		return root;
	}

	public static String getPass() {
		return pass;
	}
}
