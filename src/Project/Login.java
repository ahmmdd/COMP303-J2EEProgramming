package Project;

import java.sql.*;


public class Login {

	public static boolean validate(String name, String pass) {
		boolean status = false;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/subcomp", "root", "3415");

			PreparedStatement ps = con.prepareStatement("select * from user where USERNAME=? and PASSWORD=?");
			ps.setString(1, name);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			status = rs.next();

		} catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
}
