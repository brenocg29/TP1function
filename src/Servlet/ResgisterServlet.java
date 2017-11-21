package Servlet;

import Autentication.AtenticationFacade;
import Autentication.Register;
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

@WebServlet("/RegisterServlet")
public class ResgisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Name = request.getParameter("FName");
		String LastName = request.getParameter("Surname");
		String UIN = request.getParameter("UIN");
		String username = request.getParameter("username");
		String password = request.getParameter("Password1");
		boolean R = false;
		AtenticationFacade L = AtenticationFacade.getInstance();
		try {
			R = L.MakeRegister(Name,UIN,password,LastName,username);
			if (R == false) {
				response.sendRedirect("register.jsp");
				return;
			}
		} catch (DeserializationException | NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.sendRedirect("test.jsp");
	}

}
