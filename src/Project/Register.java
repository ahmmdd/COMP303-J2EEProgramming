package Project;

import java.io.*;
import java.sql.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/Register")
public class Register extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		this.doPost(request, response);
	}// End doGet

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		ServletContext sc = getServletContext();
		
		String n = request.getParameter("USERNAME");
		//System.out.println(n);
		String p = request.getParameter("PASSWORD");
		//System.out.println(p);

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/SUBCOMP", "root", "3415");

			PreparedStatement ps = con.prepareStatement("insert into user values(?,?)");

			ps.setString(1, n);
			ps.setString(2, p);

			int i = ps.executeUpdate();
			if (i > 0) { 
				out.print("You are successfully registered...");
				sc.getRequestDispatcher("/HuffmanCoddingDisplay.html").forward(request, response);
				
			}

		} catch (Exception e) { System.out.println(e); }// End try/catch block
		out.close();
	}// End doPost
}//End class