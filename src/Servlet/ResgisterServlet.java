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

@WebServlet("/LoginServlet")
public class ResgisterServlet extends HttpServlet {
	@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String Name = request.getParameter("FName");
	String LastName = request.getParameter("Surname");
	String UIN = request.getParameter("UIN");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	Register R = new Register(Name,LastName,UIN,username,password);
	response.sendRedirect("Profile.jsp");
}

}
