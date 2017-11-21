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
import siteEntities.Page;
import siteEntities.Post;

/**
 * Servlet implementation class PageServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Comrade Logged = (Comrade) request.getSession().getAttribute("sharedCom");
		String indexd = request.getParameter("indexd");
		String delete = request.getParameter("deletePost");
		String pagename = request.getParameter("PageName");
		String indexl = request.getParameter("indexl");
		String post = request.getParameter("post");
		String comment = request.getParameter("comment");
		String like = request.getParameter("like");
		String indexc = request.getParameter("index");
		if (pagename == null) {
			pagename = (String) request.getAttribute("PageName");
		}
		if(pagename == null) {
			pagename = (String) request.getSession().getAttribute("PageName");
		}
		Page P = null;
		Feed F = null;
		try {
			P = new JsonHandler("page").readPFromJson(pagename + "page");
			F = new JsonHandler("feed").readFFromJson(P.getName()+"pfeed");
			request.getSession().setAttribute("owner", P.getOwner());
		}
		
			catch (DeserializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		if(like !=null && indexl!=null) {
			try {
				Post.appendlike(Logged, F.getFeedId(), Integer.parseInt(indexl));
				F = new JsonHandler("feed").readFFromJson(P.getName()+"pfeed");
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Logged.getUserName().equals(P.getOwner())) {
			request.setAttribute("notMember", "n");
		}
		if(post!=null) {
			if(Logged.getUserName().equals(P.getOwner())) {
				try {
					F.AppendPost(new Post(P.getName(),post,0,P.getName() + "pfeed"));
				} catch (DeserializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if(request.getParameter("joincom")!=null && request.getParameter("joincom").equals("y")) {
			try {
				Logged.followPage(P);
				System.out.println("reforming " + P.getFollowers().size() );
				P.Reform();
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(request.getParameter("joincom")!=null && request.getParameter("joincom").equals("n")) {
			try {
				P.ExpelComrade(Logged);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(Logged.getPages().contains(P.getName())) {
			request.setAttribute("iscom", "ismember");
		}
		else {
			request.setAttribute("iscom", "isnot");
		}
		if (Logged.getUserName().equals(P.getOwner())) {
			request.setAttribute("expel", "Expel Comrade");
		}
		if(request.getParameter("Expel") != null) {
			try {
				P.ExpelComrade(new JsonHandler("comrade").readCFromJson(request.getParameter("Expel")));
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.getSession().setAttribute("PageName", pagename);
		request.setAttribute("postList",F.getPosts());
		request.setAttribute("name", P.getName());
		request.setAttribute("owner", P.getOwner());
		request.setAttribute("members", P.getFollowers());
		request.getRequestDispatcher("Pagepage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = (String) request.getAttribute("user");
		if(name == null) {
			name = (String) request.getSession().getAttribute("user");
		}
		else {
			request.setAttribute("PageName",name);
			request.getSession().setAttribute("PageName", name);	
		}
		doGet(request,response);
	}

}
