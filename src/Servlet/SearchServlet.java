package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.DeserializationException;

import dao.JsonHandler;
import siteEntities.Comrade;
import siteEntities.Group;
import siteEntities.Page;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getParameter("pesquisa");
		String type = (String) request.getParameter("choose");
		Comrade C = null;
		Group G = null;
		Page P = null;
		try {
			if(type.equals("comrade"))
				C = new JsonHandler(type).readCFromJson(user);
			if(type.equals("group"))
				G = new JsonHandler(type).readGFromJson(user + "group");
			if (type.equals("page"))
				P = new JsonHandler(type).readPFromJson(user + "page");
		} catch (DeserializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(C == null && G==null && P==null) {
			request.setAttribute("wrong", "This Entity Does not Exist");
			request.getRequestDispatcher("ProfileServlet").forward(request, response);
			return;
		}
		request.setAttribute("user", user);
		if (type.equals("comrade"))
			request.getRequestDispatcher("ComradeServlet").forward(request, response);		
		if (type.equals("group"))
			request.getRequestDispatcher("GroupServlet").forward(request, response);
		if (type.equals("page"))
			request.getRequestDispatcher("PageServlet").forward(request, response);
	
	}

}
