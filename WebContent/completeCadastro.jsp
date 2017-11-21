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
<p class="disclaimer">Complete your information, so the great marxists can recognize you</p>
	<div class="data-input">
	<div class="quoteL">
	<p>"It’s up to you whether
	 the nation called the United States exists on this planet or not"</p><br>
	 <p></p> Kim Jong Un
	</div>
	<form id = "register" action="ProfileServlet" method = "POST">
	<div class="form-group  col-md-6">
    <label for="inputname">Date of Birth</label>
    <input type="Date" class="form-control" name = "Birth" id="Birth"  placeholder="Birthday">
  </div>
  <div class="form-group  col-md-6">
    <label for="inputname">Photo Link</label>
    <input type="Text" class="form-control" name = "photo" id="photo"  placeholder="Photo">
  </div>
  <div class = "col-md-12">
  <button type="submit" class="btn btn-danger">Submit</button>
</div>
</form>
	</div>
	<div id = "lenninBW"> 
		<img src="https://vignette.wikia.nocookie.net/lapis/images/6/6d/Kim_Jong_Un.png/revision/latest?cb=20141220162929" style="height: 70vh">
	</div>
</div>
</html>