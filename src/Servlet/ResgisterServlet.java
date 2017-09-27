package Servlet;

import Autentication.Register;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/RegisterServlet")
public class ResgisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Name = request.getParameter("FName");
		System.out.println(Name);
		String LastName = request.getParameter("Surname");
		String UIN = request.getParameter("UIN");
		String username = request.getParameter("username");
		String password = request.getParameter("Password1");
		Register R = new Register(Name,UIN,password,LastName,username);
		R.SaveNewUser();
		response.sendRedirect("Profile.jsp");
	}

}
