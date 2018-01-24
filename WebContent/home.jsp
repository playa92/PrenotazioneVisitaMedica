<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>

<html>

<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
	<title>Prenota Visita Medica</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/loader.css">
 	<link rel="stylesheet" href="css/common.css"> 	
</head>

<body>

	<!-- Loader -->
	<div id="preloader" class="container">
		<div class="row">
			<div class="load-overlay">
				<div class="loader">
				  <i></i><i></i><i></i><i></i><i></i>
				</div>
			</div>
		</div>
	</div>
			
	<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="home.jsp"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
			    <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="prenotazione.jsp">Prenotazione</a>
			     </li>
			     <li class="dropdown nav-item ">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" >Assistenza <b class="caret"></b></a>
			        <ul class="dropdown-menu">
				       	<li><a id="list-element" href="#">1</a></li>
				       	<li><a id="list-element" href="#">2</a></li>
				       	<li><a id="list-element" href="#">3</a></li>
			        </ul>
			     </li>
			      <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Statistiche <b class="caret"></b></a>
			        <ul class="dropdown-menu">
				     	<li><a id="list-element" href="html/cerca_prenotazione.html">Cerca prenotazione</a></li>
				       	<li><a id="list-element" href="html/visualizza_prenotazioni.html">Visualizza statistiche prenotazioni</a></li>
			        </ul>
			     </li>  
	   		</ul>
		    <ul id="right-fields" class="nav navbar-nav navbar-right">
				<!--  Login -->	
				<li class="dropdown nav-item">
					<c:if test="${not loggato}">						
						  <a id="navbar-text" data-toggle="modal" data-target="#myModal" href="#"><span class="glyphicon glyphicon-log-in"></span> Accedi<b class="caret"></b></a>       		 
				       		  <div class="modal fade" id="myModal" role="dialog">
							    <div class="modal-dialog modal-sm">
							      <div class="modal-content login-form">
							          <div class="modal-header">
							            <button type="button" class="close" data-dismiss="modal">&times;</button>
							            <h3 class="modal-title">Inserisci le credenziali</h3>
							          </div>
							          <div class="modal-body">
				           			 	<form id="form-field" class="text-form" method="post" action="login">
					             		  <label>Username</label>
						             	  <input type="text" placeholder="Enter Username" name="username" required>
						             	  <label>Password</label>
						             	  <input type="password" placeholder="Enter Password" name="password" required>
						             	  <label id="checkbox"> <input id="rememberChkBox" type="checkbox" checked="checked"> Ricordami </label>
										  <input id="sign" data-toggle="modal" data-target="#edit-modal" type="submit" value="Conferma"/>	
										  <a id="forgotText" href="html/ripristino-password.html"> Password dimenticata?</a>
				           			 	</form>
							        </div>
							      </div>
							    </div>
							</div>
					</c:if>
					<c:if test="${loggato}">
					<li class="dropdown nav-item">
			       		<a id="navbar-text" type="submit" href="login?logout=true"><span class="glyphicon glyphicon-log-out"></span> Disconnetti</a>		
					</li>
					</c:if>
		
				</li> 
			   	<!-- Ricerca -->
			  	<li id="navbar-text" >
		        	<form class="navbar-form" role="search">
				        <div class="input-group">
				            <input type="text" class="form-control" placeholder="Search" name="q">
				            <div class="input-group-btn" > 
					           	<button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
				            </div>
				        </div>
			        </form>
			 	</li>
			</ul> 
			
		</div>	
	</nav>
	
	<div class="jumbotron text-center">
	  <h1>Benvenuto nel Sito di Prenotazione</h1>
  	  <h5>Prenota subito la tua visita medica!</h5> 
	</div>
			
			
	<!-- COUNTDOWN -->
<!-- 	<div id="timer"></div>			 			 -->
	 	
	<a id="list-element" href="html/prova-slideshow.html">Prova slideshow</a>
		 	 
<!-- 	<div class="slideshow-container"> -->
	
<!-- 		<div class="mySlides fade"> -->
<!-- 		  <div class="numbertext">1 / 3</div> -->
<!-- 		  <img src="images/img_nature_wide.jpg" style="width:100%"> -->
<!-- 		  <div class="text">Caption Text</div> -->
<!-- 		</div> -->
		
<!-- 		<div class="mySlides fade"> -->
<!-- 		  <div class="numbertext">2 / 3</div> -->
<!-- 		  <img src="images/img_fjords_wide.jpg" style="width:100%"> -->
<!-- 		  <div class="text">Caption Two</div> -->
<!-- 		</div> -->
		
<!-- 		<div class="mySlides fade"> -->
<!-- 		  <div class="numbertext">3 / 3</div> -->
<!-- 		  <img src="images/img_mountains_wide.jpg" style="width:100%"> -->
<!-- 		  <div class="text">Caption Three</div> -->
<!-- 		</div> -->
		
<!-- 	<a class="prev" onclick="plusSlides(-1)">&#10094;</a> -->
<!-- 	<a class="next" onclick="plusSlides(1)">&#10095;</a> -->
	
<!-- 	</div> -->
<!-- 	<br> -->
	
<!-- 	<div style="text-align:center"> -->
<!-- 	  <span class="dot" onclick="currentSlide(1)"></span>  -->
<!-- 	  <span class="dot" onclick="currentSlide(2)"></span>  -->
<!-- 	  <span class="dot" onclick="currentSlide(3)"></span>  -->
<!-- 	</div> -->
	
<!-- 	COSI DA NON RALLENTARE IL CARICAMENTO -->
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 	<script src="js/jquery.cookie.js"></script>
 	<script src="js/effects.js"></script>
 	<script src="js/login.js"></script>
	 
</body>
</html>