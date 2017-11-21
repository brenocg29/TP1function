<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="org.json.simple.JsonObject"
	import="java.util.List" import="siteEntities.Post" import="java.util.*"
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
			class="waves-effect waves-light btn red darken-4"><i
			class="material-icons left">exit_to_app</i>Log Out</a>
	</div>
	<div class="init-body col-md-6">
		<div class="row">
			<div class="col s12">
				<div class="card white">
					<div class="card-image">
						<img src="https://coverfiles.alphacoders.com/128/12862.jpg"
							style="height: 50vh"> <span class="card-title disclaimer">${name}
							${lastname} <%
 	if ((String) request.getAttribute("iscom") == "isnot") {
 %>
							<a href="ComradeServlet?Addcom=y"
							class="waves-effect waves-light btn black">Add</a>
						</span>
						<%
							}
						%>
						<%
							if ((String) request.getAttribute("iscom") != "isnot") {
						%>
						<a href="ComradeServlet?Addcom=n"
							class="waves-effect waves-light btn black">Remove</a></span>
						<%
							}
						%>
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
							Age:${Age}<br> Birthday:${Birth}<br>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="card white">
						<span class = "card-title">Comrades</span>
						<div class="card-content black-text info">
							<%
								JsonArray comrades = (JsonArray) request.getAttribute("comrades");
								for (int i = 0; i < comrades.size(); i++) {
									String name = comrades.getString(i);
							%>
							<a href="ComradeServlet?ComradeAdd=<%out.println(name);%>">
								<%
									out.println(name);
								%>
							</a><br>
							<%
								}
							%>
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
			
				</div>
			</div>
			<div class="col s12 m8 offset-m1">
				<div class="card white">
					<div class="card-content">
						<span class="card-title black-text"> Enviar</span>
						<form class="container" action="ComradeServlet" method="GET">
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
					if (posts.size() > 0) {
						for (int i = posts.size() - 1; i >= 0; i--) {
							JsonObject post = (JsonObject) posts.get(i);
				%>
				<div class="row">
					<div class="card white">
						<div class="chip col m12 disclaimer" style="color: black">
							<%
								out.println(post.getString("poster"));
							%>
							<img src="http://memes.ucoz.com/_nw/14/91658412.jpg">
						</div>
						<div class="card-content">
							<span class="card-title black-text disclaimer"> <%
 	out.println(post.getString("msg"));
 %>
							</span> <span class="card-content date"> <%
 	out.println(post.getString("data"));
 %>
							</span>
							<a href = "ComradeServlet?like=y&indexl=<%out.println(post.get("cont"));%>"><i class="tiny material-icons">thumb_up</i><%out.println(((JsonArray)post.get("likes")).size()); %></a> 
						</div>
						<ul id="collapsa2" class="collapsible"
							data-collapsible="accordion">
							<li>
								<div class="collapsible-header">
									<i class="material-icons">filter_drama</i>Comment
								</div>
								<div class="collapsible-body">
									<div class="card-content">
										<form class="container" action="ComradeServlet" method="POST">
											<div class="input-field col s12 m6">
												<textarea name="comment" id="comment" class="form-control"></textarea>
												<input type="hidden" name="index"
													value=<%out.println(post.get("cont"));%> />
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
						<%
							JsonArray comments = (JsonArray) post.get("comment");
									for (int y = 0; y < comments.size(); y++) {
										JsonObject comment = (JsonObject) comments.get(y);
						%>
						<div class="card-content">
							<div class="card-title">
								<%
									out.println(comment.get("posterId"));
								%>
							</div>
							<%
								out.println(comment.get("msg"));
							%>
						</div>
						<%
							}
						%>

					</div>
				</div>
				<%
					}
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>