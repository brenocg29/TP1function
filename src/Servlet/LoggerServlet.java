package Servlet;

import Autentication.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.DeserializationException;

@WebServlet("/LoggerServlet")
public class LoggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("login");
		String password = request.getParameter("pass");
		boolean test = false;
		Logger L = new Logger(username, password);
		try {
			test = L.CheckUserPass();
		} catch (DeserializationException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (test == true) {
		response.sendRedirect("Profile.jsp");
		}
		else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username incorreto GO TO GULAG');");
			out.println("location='test.jsp';");
			out.println("</script>");
		}
	}

}
