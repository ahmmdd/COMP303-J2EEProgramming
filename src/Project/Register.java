package Project;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;

@WebServlet("/register")
public class Register extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("USERNAME");
		String pass = request.getParameter("PASSWORD");
		try {

			// loading drivers for mysql
			Class.forName("com.mysql.jdbc.Driver");

			// creating connection with the database
			Connection con = DriverManager.getConnection("jdbc:mysql:/ /localhost:3306/subcomp.user", "root", "3415");

			PreparedStatement ps = con.prepareStatement("insert into Student values(?,?)");

			ps.setString(1, email);
			ps.setString(2, pass);
			int i = ps.executeUpdate();

			if (i > 0) {
				out.println("You are sucessfully registered");
			}

		} catch (Exception se) {
			se.printStackTrace();
		}

	}
}
