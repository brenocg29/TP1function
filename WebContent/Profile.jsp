<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.json.simple.JsonObject"
	import="java.util.List" import="siteEntities.Post" import="java.util.*" import="siteEntities.Comrade"
	import="org.json.simple.JsonArray"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" ,inital-scale=1.0">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"
	integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1"
	crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Russo+One"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<link rel="stylesheet" href="./css/styles.css" type="text/css" />
</head>
<body>
<div class="top col-md-6">
	<a href = "ProfileServlet" class="waves-effect waves-light red darken-4 btn"><i
		class="material-icons left">home</i>Home</a> <a
		href = "test.jsp" class="waves-effect waves-light btn red darken-4"><i
		class="material-icons left">exit_to_app</i>Log Out</a>
	<div class = "col s12 m7">
	<form id="pesquisa" action="SearchServlet" method="POST">
		<div class = "row">
		<div class="form-group col s12 m6 text-black">
				<label for="pesquisa">Pesquisa</label> <input type="Text"
					class="form-control" name="pesquisa" id="pesquisa"
					placeholder="Pesquisa">
					${wrong}
			</div>
			<div class = "form-group col s12 m2 text-black">
			<label for="choose"> Choose the element</label>
			<select name = "choose" id = "choose">
				<option value = "comrade">Comrade</option>
				<option value = "group">Group</option>
				<option value = "page">Page</option>
				
			</select>
			</div>
			<div class = "col s12 m2">
			<div class = "row">
			 Search for any element in the site			                      
			</div>
			<button type="submit" class="btn btn-danger">Submit</button>
			</div>
			</div>
		</form>
	</div>
</div>
<div id="modal1" class="modal bottom-sheet">
	<div class="modal-content">
		<h4>Messages from Comrades</h4>
		<% JsonArray msgs = (JsonArray)request.getAttribute("messages");
		if(msgs.size() > 0){
		for (int i = msgs.size() - 1; i >= 0; i--) {
			
			JsonObject text = (JsonObject) msgs.get(i); %>
		<ul id="collapsa" class="collapsible popout"
			data-collapsible="accordion">
			<li>
				<div class="collapsible-header">
					<i class="material-icons">message</i>Camarada <%out.println(text.get("sender").toString()); %>
				</div>
				<div class="collapsible-body">
					<span>
						<div class="card white">
							<div class="chip col m12 disclaimer" style="color: black">
								<%out.println(text.get("sender").toString());%> <img
									src="http://cdn.thinglink.me/api/image/523023357868769280/1240/10/scaletowidth">
							</div>
							<div class="card-content">
								<span class="card-title black-text disclaimer">
									<%out.println(text.getString("title")); %> </span>
								<p><%out.println(text.getString("msg").toString());%></p>
							</div>
						</div>
					</span>
				</div>
			</li>
		</ul>
		<%}%>
		<%} %>
	</div>
	<div class="modal-footer">
		<a href="#!"
			class="modal-action modal-close waves-effect waves-green btn-flat">Agree</a>
	</div>
</div>
<div id="modal2" class="modal">
	<div class="modal-content">
		Send Message
		<form id="register" action="ProfileServlet" method="POST">
			<div class="form-group  col-m12 text-black">
				<label for="receiver">Comrade</label> <input type="Text"
					class="form-control" name="receiver" id="receiver"
					placeholder="comrade">
			</div>
			<div class="form-group  col-m12 text-black">
				<label for="msgtitle">Title</label> <input type="Text"
					class="form-control" name="msgtitle" id="msgtitle"
					placeholder="title">
			</div>
			<textarea name="msg1" id="msg1" class="form-control">
  	</textarea>
			<div class="row offset-m3">
				<div class="col m6">
					<img src="http://www.filosofia.org/enc/ros/img/mao.png" width="80%"
						height="85%">
				</div>				
				<p class = "disclaimer black-text">All reactionaries are paper tigers. In appearance,
				 the reactionaries are terrifying, 
				 but in reality they are not so powerful</p>
			</div>
			<button type="submit" class="btn btn-danger">Send</button>
		</form>
	</div>
</div>
<div id="modal3" class="modal">
	<div class="modal-content">
		Create a new Page
		<form id="register" action="ProfileServlet" method="POST">
			<div class="form-group  col-m12 text-black">
				<label for="PageName">Page Name</label> <input type="Text"
					class="form-control" name="PageName" id="PageName"
					placeholder="Name">
					<button type="submit" class="btn btn-danger">Send</button>
			
			</div>
			
			<div class="row offset-m3">
				<div class="col m6">
					<img src="http://www.retrocards.co.uk/media/gbu0/prodxl/AP563-lenin-pravda-russian-poster-1969.jpg" width="80%"
						height="85%">
				</div>
			</div>
			
		</form>
	</div>
</div>
<div id="modal4" class="modal">
	<div class="modal-content">
		Create a new Group
		<form id="register" action="ProfileServlet" method="POST">
			<div class="form-group  col-m12 text-black">
				<label for="PageName">Group Name</label> <input type="Text"
					class="form-control" name="GroupName" id="GroupName"
					placeholder="Name">
					<button type="submit" class="btn btn-danger">Send</button>
			
			</div>
			
			<div class="row offset-m3">
				<div class="col m6">
					<img src="http://weakonomics.com/wp-content/uploads/2011/04/anti-capitalism-propaganda.jpg" width="80%"
						height="85%">
				</div>
			</div>
			
		</form>
	</div>
</div>
<script>
var $toastContent = $('<span>Praise Leaders Now?</span>').add($('<a class="btn-flat toast-action" href="ProfileServlet?praise=y">Praise</button>'));	
	<%System.out.println("Look at meee" + request.getSession().getAttribute("praised").toString().equals("0"));
	if (request.getSession().getAttribute("praised").toString().equals("0"))
	{
	%>
	Materialize.toast($toastContent, 4000);
	<%}%>

   $(document).ready(function(){
	   $('select').material_select();
   });
	$(document).ready(function() {
		// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
		$('#modal1').modal();
	});
	$(document).ready(function() {
		$('#collapsa').collapsible();
	});
	$(document).ready(function() {
		$('#modal2').modal();
	});
	$(document).ready(function() {
		// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
		$('#modal3').modal();
	});
	$(document).ready(function() {
		// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
		$('#modal4').modal();
	});
</script>
<div class="init-body col-md-6">
	<div class="row">
		<div class="col s12">
			<div class="card white">
				<div class="card-image">
					<img src="https://coverfiles.alphacoders.com/128/12862.jpg"
						style="height: 50vh"> <span class="card-title disclaimer">${name}
						${lastname}<a class = "waves-effect waves-light dropdown-button btn black" href = "#" data-activates='dropdown1'>
						Pending</a>
						<ul id = "dropdown1" class = 'dropdown-content'>
						<%JsonArray pending = (JsonArray)request.getAttribute("pendingList"); 
						  for(int i = 0; i< pending.size();i++){
							  String Puser = pending.getString(i);
						%>
						<li><a href="ProfileServlet?ComradeApp=<%out.println(Puser);%>"></a><%out.println(Puser); %></li>
						<%} %>
						</ul>
						
						
						</span>
				</div>
			</div>
		</div>
		<div class="col s12 m2">
			<div class="card white">
				<div class="card-image">
					<img src=<%out.println('"' + (String)request.getAttribute("photo") + '"'); %>style="height: 35vh; width: 35vh;">
				</div>
			</div>
			<div class="row">
			<div class="card white">
			<span class = "card-title">Info</span>
				<div class="card-content black-text info">
					Age:${Age}<br> Birthday:${birth}<br>
					<a href = "completeCadastro.jsp">alterar dados</a>
				</div>
			</div>
			<div class="card white">
			<span class = "card-title">Groups</span>
				<div class="card-content black-text info">
					<% JsonArray groups = (JsonArray) request.getAttribute("grouplist");
				for(int i = 0; i < groups.size();i++){
					String name = groups.getString(i);
					%> <a href="GroupServlet?GroupName=<%out.println(name);%>"><%out.println(name);%></a><br>
			<% } %>
					
				</div>
			</div>
			
			<div class="card white">
			<span class = "card-title">Pages</span>
				<div class="card-content black-text info">
					<% JsonArray pages = (JsonArray) request.getAttribute("pageList");
				for(int i = 0; i < pages.size();i++){
					String name = pages.getString(i);
					%> <a href="PageServlet?PageName=<%out.println(name);%>"><%out.println(name);%></a><br>
			<% } %>
					
				</div>
			</div>
			</div>
		
		
		<div class = "row">
				<div class = "card white">
				<span class = "card-title">Comrades</span>
					<div class = "card-content black-text info">
			<% JsonArray comrades = (JsonArray) request.getAttribute("comrades");
				for(int i = 0; i < comrades.size();i++){
					String name = comrades.getString(i);
					%> <a href="ComradeServlet?ComradeAdd=<%out.println(name);%>"><%out.println(name);%></a><br>
			<% } %>
					</div>
				</div>
			</div>
		</div>
		<div class="col s12 m8 offset-m1">
			<div class="card white">
				<div class="card-content">
					<span class="card-title black-text"> Enviar</span>
					<form class="container" action="ProfileServlet" method="POST">
						<div class="row">
							<div class="input-field col s12 m6">
								<textarea name="post" id="post" class="form-control"></textarea>
								<label for="post">Only allowed opinions</label>
							</div>
							<div class="input-field col m6">
								<button class="btn waves-effect waves-light" type="submit"
									name="action">
									Post <i class="material-icons right">send</i>
								</button>
							</div>
						</div>
					</form>
				</div>
			</div>
			<%
				JsonArray posts = (JsonArray) request.getAttribute("postList");
				if (posts.size() > 0){
				for (int i = posts.size() - 1; i >= 0; i--) {
					JsonObject post = (JsonObject) posts.get(i);
			%>
			<div class="row">
				<div class="card white">
				<%System.out.println(request.getSession().getAttribute("sharedCom"));
				if (post.get("poster").equals(((Comrade)request.getSession().getAttribute("sharedCom")).getUserName())){ %>
					<a href="ProfileServlet?deletePost=y&indexd=<%out.println(post.get("cont"));%>"><i class = "material-icons small">close</i></a>
					<%} %>
					<div class="chip col m12 disclaimer" style="color: black">
						<%
							out.println(post.getString("poster"));
						%>
						<img src=<%out.println('"' + (String)request.getAttribute("photo") + '"'); %>>
					</div>
					<div class="card-content">
						<span class="card-title black-text disclaimer"> <%
 	out.println(post.getString("msg"));
 %>
						</span> <span class="card-content date"> <%
 	out.println(post.getString("data"));
 %>
						</span>
						<a href="ProfileServlet?like=y&indexl=<%out.println(post.get("cont"));%>"><i
								class="tiny material-icons">thumb_up</i>
								<%
									out.println(((JsonArray) post.get("likes")).size());
								%></a>
				
					</div>
		<ul id = "collapsa2" class="collapsible" data-collapsible="accordion">
    	<li>
      <div class="collapsible-header"><i class="material-icons">filter_drama</i>Comment</div>
      <div class="collapsible-body">
					<div class = "card-content">
					<form class="container" action="ProfileServlet" method="POST">
					<div class="input-field col s12 m6">
								<textarea name="comment" id="comment" class="form-control"></textarea>
								<input type = "hidden" name = "index" value = <%out.println(post.get("cont")); %>/>
							<button class="btn waves-effect waves-light" type="submit"
									name="action">
									Post <i class="material-icons right">send</i>
								</button>
	
							</div>
							</form>
					</div>
					</div>
					</li>
					</ul>
					<%JsonArray comments = (JsonArray)post.get("comment");
					  for (int y = 0; y < comments.size();y++){
					  JsonObject comment = (JsonObject)comments.get(y);%>
					   <div class="card-content">	
					  <div class="card-title">
						<% out.println(comment.get("posterId"));%> 
						</div>
						 <%out.println(comment.get("msg")); %>
						</div>
					  <%}%>
			
					
				</div>
			</div>
			<%
				}}
			%>
		</div>
		<div class="fixed-action-btn horizontal">
			<a class="btn-floating btn-large red"> <i
				class="large material-icons">explore</i>
			</a>
			<ul>
				<li><a class="btn-floating red"><i
						class="material-icons modal-trigger" href="#modal1">email</i></a>
				<li><a class="btn-floating red"><i
						class="material-icons modal-trigger" href="#modal2">mode_edit</i></a>
				<li><a class="btn-floating red"><i
						class="material-icons modal-trigger" href="#modal3">library_books</i></a>
				<li><a class="btn-floating red"><i
						class="material-icons modal-trigger" href="#modal4">group_add</i></a>
			
			</ul>
		</div>
	</div>
</div>
<footer class="page-footer red accent-4">
		<div class="container red accent-4">
			<div class="row">
				<div class="col l6 s12"></div>
			</div>
		</div>
		<div class="grey-text text-lighten-4">Designed in California
			Assembled in China</div>
		<div class="footer-copyright">
			<div class="container">© 2017 Comunist Enterprises</div>
		</div>
	</footer>
	</body>
</html>