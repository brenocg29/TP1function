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
import siteEntities.Feed;
import siteEntities.Post;
@WebServlet("/ComradeServlet")

public class ComradeServlet extends HttpServlet{
	/**
	 * 
	 */
	public ComradeServlet() {

	}
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = (String) request.getParameter("ComradeAdd");
		if (user !=null) {
			request.getSession().setAttribute("ComradeAdd", user);
		}
		else {
			user = (String) request.getSession().getAttribute("ComradeAdd");
		}
		Comrade C = null;
		Comrade Logged = (Comrade) request.getSession().getAttribute("sharedCom");
		Feed F = null;
		try {
			C = new JsonHandler("comrade").readCFromJson(user);
			try {
				F = new JsonHandler("feed").readFFromJson(C.getUserName()+"cfeed");
				System.out.println(F.getFeedId());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DeserializationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		if(request.getParameter("Addcom")!= null && request.getParameter("Addcom").equals("y")) {
			try {
				System.out.println("loooooooookkjkkkkk" + C.getName());
				Logged.addComrade(C);
				request.getSession().setAttribute("sharedCom",Logged);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("Addcom")!= null && request.getParameter("Addcom").equals("n")) {
			try {
				request.getSession().setAttribute("sharedCom",Logged);
				System.out.println("im UNDOING THIS");
				Logged.UndoComrade(C);
				
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String post = request.getParameter("post");
		String msg = request.getParameter("msg1");
		String title = request.getParameter("msgtitle");
		String like = request.getParameter("like");
		String indexl = request.getParameter("indexl");
		if(like != null & indexl != null) {
			try {
				Post.appendlike(Logged, F.getFeedId(), Integer.parseInt(indexl));
			} catch (NumberFormatException | DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (post != null) {
			try {
				F.AppendPost(new Post(Logged.getUserName(),post,0,C.getUserName() + "cfeed"));
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		if(msg != null) {
			try {
				Logged.sendMessage(C, msg,title);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Logged.getComrades().contains(user);
		request.setAttribute("name", C.getName());
		request.setAttribute("comrades",C.getComrades());
		request.setAttribute("lastname", C.getLastName());
		request.setAttribute("comrade", C);
		request.setAttribute("pageList", C.getPages());
		request.setAttribute("grouplist", C.getGroups());
		request.setAttribute("photo", C.getPhotoPath());
		request.setAttribute("postList", F.getPosts());
		request.getRequestDispatcher("comradePage.jsp").forward(request, response);	
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comrade Logged = (Comrade) request.getSession().getAttribute("sharedCom");
		String user = (String) request.getAttribute("user");
		if (user !=null) {
			request.getSession().setAttribute("commented", user);
		}
		else {
			user = (String) request.getSession().getAttribute("commented");
		}
		String comment = request.getParameter("comment");
		String cont = request.getParameter("index");
		System.out.println(user);
		Comrade C = null;
		Feed F = null;
		try {
			C = new JsonHandler("comrade").readCFromJson(user);
			try {
				F = new JsonHandler("feed").readFFromJson(Logged.getUserName()+"cfeed");
				System.out.println(F.getFeedId());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (DeserializationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		}
		if (comment!=null && cont != null) {
			try {
				Post.makecomment(C, comment,Integer.parseInt(cont),F.getFeedId());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(Logged.getComrades().contains(user) || Logged.getPendingComrades().contains(user)) {
			request.setAttribute("iscom", "isfriend");
		}
		else {
			request.setAttribute("iscom", "isnot");
		}
		request.getSession().setAttribute("ComradeAdd", user);
		request.setAttribute("name", C.getName());
		request.setAttribute("pageList", C.getPages());
		request.setAttribute("grouplist", C.getGroups());
		request.setAttribute("Age", C.getAge());
		request.setAttribute("Birth", C.getBirthday());
		request.setAttribute("comrades",C.getComrades());
		request.setAttribute("lastname", C.getLastName());
		request.setAttribute("comrade", C);
		request.setAttribute("photo", C.getPhotoPath());
		request.setAttribute("postList", F.getPosts());
		request.getRequestDispatcher("comradePage.jsp").forward(request, response);
	}
}
