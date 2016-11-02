package Project;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginServlet1")
public class Validate extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String n = request.getParameter("USERNAME");
		String p = request.getParameter("PASSWORD");

		if (Login.validate(n, p)) {
			//RequestDispatcher rd = request.getRequestDispatcher("/Welcome");
			//rd.forward(request, response);
			response.sendRedirect("HuffmanCoddingDisplay.html");
		} else {
			out.print("Sorry username or password error");
			RequestDispatcher rd = request.getRequestDispatcher("index.html");
			rd.include(request, response);
		}

		out.close();
	}

}
