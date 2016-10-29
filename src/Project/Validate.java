package Project;

import java.sql.*;

public class Validate {
	public static boolean checkUser(String user, String pass) {
		boolean st = false;
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager
					.getConnection(
							"jdbc:mysql://localhost:3306/SUBCOMP",
							"root", "3415");
			String queryStr = "select * from USER where USERNAME=? and PASSWORD=?";
			PreparedStatement ps = con.prepareStatement(queryStr);
			ps.setString(1, user);
			ps.setString(2, pass);
			ResultSet rs = ps.executeQuery();
			st = rs.next();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return st;
	}

}
