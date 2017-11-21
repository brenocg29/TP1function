package Servlet;

import Autentication.AtenticationFacade;
import siteEntities.Comrade;

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
		Comrade test = null;
		AtenticationFacade L = AtenticationFacade.getInstance();
		try {
			test = L.MakeLogin(username, password);
		} catch (DeserializationException | NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (test != null) {
		request.setAttribute("sharedCom", test);
		request.getSession().setAttribute("sharedCom", test);
		request.getSession().setAttribute("praised", "0");
		this.getServletConfig().getServletContext().setAttribute("sharedCom", test);
		request.getRequestDispatcher("/ProfileServlet").forward(request, response);
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
