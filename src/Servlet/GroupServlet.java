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
import siteEntities.Group;
import siteEntities.Post;

/**
 * Servlet implementation class GroupServlet
 */
@WebServlet("/GroupServlet")
public class GroupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Expel = (String) request.getParameter("Expel");
		Comrade Logged = (Comrade) request.getSession().getAttribute("sharedCom");
		String groupname = request.getParameter("GroupName");
		String indexd = request.getParameter("indexd");
		String delete = request.getParameter("deletePost");
		String comment = request.getParameter("comment");
		String indexc = request.getParameter("index");
		String post = request.getParameter("post");
		String like = request.getParameter("like");
		String indexl = request.getParameter("indexl");
		if (groupname == null) {
			groupname = (String) request.getAttribute("GroupName");
		}
		if(groupname == null) {
			groupname = (String) request.getSession().getAttribute("GroupName");
		}
		Group G = null;
		Feed F = null;
		try {
			System.out.println(groupname);
			G = new JsonHandler("group").readGFromJson(groupname + "group");
			request.getSession().setAttribute("admin", G.getAdmin());
			F = new JsonHandler("feed").readFFromJson(G.getName()+"gfeed");
		} 
			catch (DeserializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(delete !=null && indexd!=null) {
			try {
				F.deletePost(Integer.parseInt(indexd));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(like!=null && indexl!=null) {
			try {
				Post.appendlike(Logged, F.getFeedId(), Integer.parseInt(indexl));
				F = new JsonHandler("feed").readFFromJson(G.getName()+"gfeed");
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(comment!=null && indexc!=null) {
			try {
				Post.makecomment(Logged, comment,Integer.parseInt(indexc),F.getFeedId());
				F = new JsonHandler("feed").readFFromJson(F.getFeedId());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		if(request.getParameter("joincom")!=null && request.getParameter("joincom").equals("y")) {
			try {
				Logged.joinGroup(G);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("joincom")!=null && request.getParameter("joincom").equals("n")) {
			try {
				G.expelMember(Logged);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Logged.getGroups().contains(G.getName())) {
			request.setAttribute("iscom", "ismember");
		}
		else {
			request.setAttribute("iscom", "isnot");
		}
		
		if (post != null) {
			try {
				System.out.println(G.getMembers().toJson());
				
				if(G.getMembers().contains(Logged.getUserName())) {
					F.AppendPost(new Post(Logged.getUserName(),post,0,G.getName() + "gfeed"));
				}
				else {
					request.setAttribute("notMember", "you are not a member");
					
				}
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		if (Logged.getUserName().equals(G.getAdmin())) {
			request.setAttribute("expel", "Expel Member");
		}
		if(Expel != null) {
			Comrade Member;
			try {
				if(Logged.getUserName().equals(G.getAdmin())) {
					Member = new JsonHandler("comrade").readCFromJson(Expel);
					G.expelMember(Member);
				}
				
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("GroupName", groupname);
		request.setAttribute("postList",F.getPosts());
		request.setAttribute("name", G.getName());
		request.setAttribute("Admin", G.getAdmin());
		request.setAttribute("members", G.getMembers());
		request.getRequestDispatcher("GroupPage.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getAttribute("user");
		if (name == null) {
			name = (String) request.getSession().getAttribute("GroupName");
		}
		else {
			request.getSession().setAttribute("GroupName", name);	
			request.setAttribute("GroupName",name);
		}
		doGet(request,response);
	}

}
