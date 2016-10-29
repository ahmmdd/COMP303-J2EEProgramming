package Project;

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import Project.Validate;

@WebServlet("/login")
public class Login extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		if (Validate.checkUser(user, pass)) {
			RequestDispatcher rs = request.getRequestDispatcher("Welcome");
			rs.forward(request, response);
		} else {
			out.println("Username or Password incorrect");
			RequestDispatcher rs = request.getRequestDispatcher("index.html");
			rs.include(request, response);
		}
	}
}