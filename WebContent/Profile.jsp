<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="back.Comrade" import="Autentication.Register"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width",inital-scale=1.0">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" integrity="sha384-h0AbiXch4ZDo7tp9hKZ4TsHbi047NrKGLO3SEJAg45jXxnGIfYzk4Si90RDIqNm1" crossorigin="anonymous"></script>
	<link href="https://fonts.googleapis.com/css?family=Russo+One" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/css/materialize.min.css">
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.100.2/js/materialize.min.js"></script>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link rel="stylesheet"
      href="./css/styles.css"
      type="text/css"/>
</head>
</body>
<div class="top col-md-6">
	<a class="waves-effect waves-light red darken-4 btn"><i class="material-icons left">home</i>Home</a>
	<a class="waves-effect waves-light btn red darken-4"><i class="material-icons left">exit_to_app</i>Log Out</a>
</div>
<div class="init-body col-md-6">
	<div class="row">
        <div class="col s12">
          <div class="card white">
            <div class="card-image">
            <img src="https://coverfiles.alphacoders.com/128/12862.jpg" style = "height: 50vh">
              <span class="card-title disclaimer">Joseph Stalin</span>
      
            </div>
         </div>
        </div>
        <div class="col s12 m2">
          <div class="card white">
            <div class="card-image">
            <img src = "http://memes.ucoz.com/_nw/14/91658412.jpg" style = "height: 35vh; width: 35vh;">
            </div>
            </div>
        </div>
        <div class = "col s12 m8 offset-m1">
        <div class="card white">
            <div class="card-content">
            <span class="card-title black-text">
            Something on your mind?
            </span>
             <form class="container">
                 <div class = "row">
                   <div class="input-field col s12 m6">
                     <textarea id="post"></textarea>
                     <label for="email">Only allowed opinions</label>
                   </div>
                   <div class="input-field col m6">
                     <button class="btn waves-effect waves-light" type="submit" name="action">
                       Post <i class="material-icons right">send</i>
                     </button>
                   </div>
                 </div>
       			</form>
            </div>
         </div>
             
        </div>
        <div class = "row">
        <div class = "col s12 m8 offset-m3">
        <div class="card white">
        <div class="chip col m12 disclaimer" style = "color:black">
        	Joseph
        	<img src = "http://memes.ucoz.com/_nw/14/91658412.jpg">
            
        </div>
            <div class="card-content">
            <span class="card-title black-text disclaimer">
            	I think my buddy Kim Jong Un is doing a great job with Korea
            </span>
            </div>
            </div>
             </div>
        </div>
        <div class = "row">
       <div class = "col s12 m8 offset-m3">
        <div class="card white">
        <div class="chip col m12 disclaimer" style = "color:black">
        	Joseph
        	<img src = "http://memes.ucoz.com/_nw/14/91658412.jpg">
            
        </div>
            <div class="card-content">
            <span class="card-title black-text disclaimer">
            	The people who cast the votes dont decide an election the people who count the votes do<br/>
            </span>
            	<a href = "#" class = "secondary-content"><i class = "material-icons">exposure_plus_1</i></a>
            	<a href = "#" class = "secondary-content"><i class = "material-icons">exposure_minus_1</i></a>
            	
            </div>
            </div>
        </div>
        </div>
       </div>
       </div>
</html>