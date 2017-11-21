<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width",inital-scale=1.0">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>

</body>
<div class="top">
<img src = 
			"http://4.bp.blogspot.com/-ZDXIfnQD2nI/UgEQO63k-
			4I/AAAAAAAAAq4/xLIAeT8jx1I/s1600/foice.jpg" id = "logo">
</div>
<div class="init-body col-md-6" >
<p class="disclaimer">Input your data to get access to everyone network</p>
	<div class="data-input">
	<div class="quoteL">
	<p>"The bourgeoisie incites the
	 workers of one nation against those of another in the 
	 endeavor to keep them disunited"</p><br>
	 <p></p> Vladimir Lennin
	</div>
	<form id = "register" action="RegisterServlet" method = "POST">
	<div class="form-group  col-md-6">
    <label for="inputname">Name</label>
    <input type="Text" class="form-control" name = "FName" id="FName"  placeholder="Comrade name" required>
  </div>
  <div class="form-group  col-md-6">
    <label for="inputSurname">Surname</label>
    <input type="Text" class="form-control" name = "Surname" id="Surname" placeholder="Surname" required>
  </div>
  <div class="form-group  col-md-6">
    <label for="InputUIN">UIN</label>
    <input type="Text" class="form-control" name = "UIN" id="UIN" placeholder="Unified international num" required>
  </div>
  <div class="form-group  col-md-6">
    <label for="Inputusername">Username</label>
    <input type="Text" class="form-control" name = "username" id="username" placeholder="Username" required>
  </div>
  <div class="form-group  col-md-6">
    <label for="InputPassword1">Password</label>
    <input type="password" class="form-control" name = "Password1"id="Password1" placeholder = "Password" required>
</div>
<div class = "col-md-12">
  <button type="submit" class="btn btn-danger">Submit</button>
</div>
</form>
	</div>
	<div id = "lenninBW"> 
		<img src="http://pngimg.com/uploads/lenin/lenin_PNG1.png?i=1">
	</div>
</div>
</html>