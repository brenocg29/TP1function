<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="back.Comrade" import="back.Register"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",inital-scale=1.0">
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
</head>

</body>
<div class="top">
<img src = 
			"http://4.bp.blogspot.com/-ZDXIfnQD2nI/UgEQO63k-
			4I/AAAAAAAAAq4/xLIAeT8jx1I/s1600/foice.jpg" id = "logo">
</div>
<div class="init-body">
<p class="disclaimer">Input your data to get access to everyone network</p>
	<div class="data-input">
	<div class="quoteL">
	<p>"The bourgeoisie incites the
	 workers of one nation against those of another in the 
	 endeavor to keep them disunited"</p><br>
	 <p></p> Vladimir Lennin
	</div>
	<form action=register.jsp method = "POST">
		Name:<input type="text" name="Fname" id = "Fname"><br>
		Surname:<input type="text" name ="Sname" id = "Sname"><br>
		UIN: <input type="text" name="UIN" id = "UIN"><br>
		Username:<input type="text" name="username" id = "username"><br>
		Password:<input type="password" name="Password" id = "Password"><br>
		<input type = "submit" class="btn btn-danger" value=SendData>
		</form>	
	</div>
	<div id = "lenninBW"> 
		<img src="http://pngimg.com/uploads/lenin/lenin_PNG1.png?i=1">
	</div>
	<%
	%>
</div>


</html>
