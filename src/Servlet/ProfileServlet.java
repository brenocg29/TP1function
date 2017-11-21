package Servlet;
import Autentication.AtenticationFacade;
import dao.JsonHandler;
import siteEntities.Comrade;
import siteEntities.Feed;
import siteEntities.Group;
import siteEntities.Page;
import siteEntities.Post;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.DeserializationException;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;

@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comrade C = (Comrade) request.getSession().getAttribute("sharedCom");
		JsonArray allposts = new JsonArray();
		Feed F = null;
		try {
			F = new JsonHandler("feed").readFFromJson(C.getUserName()+"cfeed");
		} catch (DeserializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JsonArray com = C.getComrades();
		
		String CreatePage = request.getParameter("PageName");
		String CreateGroup = request.getParameter("GroupName");
		String error = request.getParameter("wrong");
		String like = request.getParameter("like");
		String indexl = request.getParameter("indexl");
		String post = request.getParameter("post");
		String praise = request.getParameter("praise");
		String msg = request.getParameter("msg1");
		String title = request.getParameter("msgtitle");
		String rcv = request.getParameter("receiver");
		String comment = request.getParameter("comment");
		String cont =request.getParameter("index");
		String delete = request.getParameter("deletePost");
		String indexd = request.getParameter("indexd");
		if(like != null & indexl != null) {
			try {
				Post.appendlike(C, F.getFeedId(), Integer.parseInt(indexl));
				F = new JsonHandler("feed").readFFromJson(C.getUserName()+"cfeed");
			} catch (NumberFormatException | DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(indexd != null && delete != null) {
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
		if (CreatePage != null) {
			try {
				C.createPage(CreatePage);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (comment!=null && cont != null) {
			try {
				Post.makecomment(C, comment,Integer.parseInt(cont),F.getFeedId());
				F = new JsonHandler("feed").readFFromJson(F.getFeedId());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(CreateGroup != null) {
			try {
				C.createGroup(CreateGroup);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (CreatePage !=null) {
			try {
				Page G = new Page(CreatePage,C.getUserName());
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (post != null) {
			try {
				F.AppendPost(new Post(C.getUserName(),post,0,C.getUserName() + "cfeed"));
				
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		if(praise!=null) {
			request.getSession().setAttribute("praised", 1);
			try {
				F.AppendPost(new Post(C.getUserName(),"I PRAISE ALL MY GLORIOUS SOVIETIC LEADERS",0,F.getFeedId()));
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for(int i = 0;i<C.getComrades().size();i++) {
			try {
				Feed comFeed = 
						new JsonHandler("feed").
						readFFromJson(com.getString(i) + "cfeed");
				JsonArray comPosts = comFeed.getPosts();
				for (int j = 0; j< comPosts.size();j++) {
					allposts.add(comPosts.get(j));
				}
				
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JsonArray Cposts = F.getPosts();
		for(int h = 0; h < F.getPosts().size();h++) {
			allposts.add(Cposts.get(h));
		}
		if(rcv != null) {
			try {
				Comrade Recv = new JsonHandler("comrade").readCFromJson(rcv);
				C.sendMessage(Recv, msg,title);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (error != null) {
			request.setAttribute("wrong", error);
		}
		String birth  = request.getParameter("Birth");
		String photo = request.getParameter("photo");
			if(birth != null) {
				String l[] = new String[3]; 
				l = birth.split("-");
				int year = Calendar.getInstance().get(Calendar.YEAR);
				C.setBirthday(birth);
				int age = year - Integer.parseInt(l[0]);
				C.setAge(Integer.toString(age));
			}
			if(photo!=null) {
				C.setPhotoPath(photo);
				}
				try {
					new JsonHandler("comrade").ReplaceComrade(C, C.getUserName());
				} catch (DeserializationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		request.setAttribute("birth", C.getBirthday());
		request.setAttribute("Age", C.getAge());		
		request.setAttribute("grouplist", C.getGroups());
		request.setAttribute("pageList", C.getPages());
		request.setAttribute("photo", C.getPhotoPath());
		request.setAttribute("name", C.getName());
		request.setAttribute("comrades",C.getComrades());
		request.setAttribute("lastname", C.getLastName());
		request.setAttribute("comrade", C);
		request.getSession().setAttribute("comrade", C);
		request.setAttribute("postList", allposts);
		request.setAttribute("pendingList", C.getPendingComrades());
		request.setAttribute("messages", C.getMessages());
		request.setAttribute("img", C.getPhotoPath());
		request.getRequestDispatcher("Profile.jsp").forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Comrade C = (Comrade) request.getSession().getAttribute("sharedCom");
		try {
			request.getSession().setAttribute("sharedCom",new JsonHandler("comrade").readCFromJson(C.getUserName()));
		} catch (DeserializationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 C = (Comrade) request.getSession().getAttribute("sharedCom");
		String approvedC = request.getParameter("ComradeApp");
		String index = request.getParameter("indexd");
		String delete = request.getParameter("deletePost");
		if(approvedC != null) {
		try {
			C.AcceptPending(approvedC);
			request.getSession().setAttribute("sharedCom",new JsonHandler("comrade").readCFromJson(C.getUserName()));
		} catch (DeserializationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		doPost(request,response);
		
	}	
	}
