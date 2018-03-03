<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
	<title>Prenota Visita Medica</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/loader.css">
 	<link rel="stylesheet" href="css/common.css"> 
 	<link rel="stylesheet" href="css/footer-with-map.css">
 	<link rel="stylesheet" href="css/map.css">
 	<link rel="stylesheet" href="css/success.css">
 	<script src="js/jquery/jquery-3.2.1.min.js"></script>
 	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 	<script src="js/jquery/jquery.cookie.js"></script>
 	<script src="js/jquery/jquery.bubble.text.js"></script>
</head>

<body style="background-image: url('images/studio_medico.jpg')">

    <nav class="navbar navbar-default">

        <div class="navbar-header">
            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="#" class="navbar-brand" style="color:yellow">CUP Unical</a>
        </div>

        <div id="navbarCollapse" class="collapse navbar-collapse">

          	<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
			     
			    <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="html/prenotazione.html">Prenotazione</a>
			     </li>
		
			     <jstl:if test="${not loggato}">
			     <li class="dropdown nav-item ">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="restituisciSegnalazioni">Assistenza</a>
			     </li>
			     </jstl:if>
			     <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Statistiche <b class="caret"></b></a>
			        <ul class="dropdown-menu">
				     	<li><a id="list-element" href="html/cerca_prenotazione.html">Cerca prenotazione</a></li>
				       	<li><a id="list-element" href="restituisciPrenotazioni">Prenotazioni correnti</a></li>
			        </ul>
			     </li>
			      <jstl:if test="${loggatoEmployee}">
			     	<li class="dropdown nav-item">
						<a id="navbar-text" class="nav-link dropdown-toggle" href="html/convalida.jsp">Convalida
						</a>
			     	</li>
			     	<li class="dropdown nav-item">
						<a id="navbar-text" class="nav-link dropdown-toggle" href="risolviSegnalazione">Segnalazioni 
						 <jstl:if test="${numSegnalazioni > 0}">
							<span class="badge badge-notify" style="background:orange"> <jstl:out value="${numSegnalazioni}"/> </span>
						</jstl:if>
						</a>
			     	</li>
			     </jstl:if> 
			     <jstl:if test="${loggatoAdmin}">
			     	<li class="dropdown nav-item">
						<a id="navbar-text" class="nav-link dropdown-toggle" href="risolviSegnalazione">Segnalazioni 
						 <jstl:if test="${numSegnalazioni > 0}">
							<span class="badge badge-notify" style="background:orange"> <jstl:out value="${numSegnalazioni}"/> </span>
						</jstl:if>
						</a>
			     	</li>
			     	<li class="dropdown nav-item">
			     		<a id="navbar-text" class="nav-link dropdown-toggle" href="controlloAccessi">Logging</a>
			     	</li>
			     </jstl:if>   
	   		</ul>
		    <ul class="nav navbar-nav navbar-right">
				<!--  Login -->	
				<li class="dropdown nav-item">
					<jstl:if test="${not loggato}">
						  <!-- login -->						
						  <a id="navbar-text" data-toggle="modal" data-target="#myModal" href="#"><span class="glyphicon glyphicon-user"></span> Accedi<b class="caret"></b></a>       		 
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
						             	  <input id="signinId" type="text" placeholder="Enter Username" name="username" required>
						             	  <label>Password</label>
						             	  <input id="signinPwd" type="password" placeholder="Enter Password" name="password" required>
						             	  <label id="checkbox"> <input id="rememberChkBox" type="checkbox" checked="checked"> Ricordami </label>
										  <input id="sign" type="submit" value="Conferma"/>	
										  <a id="forgotText" href="html/ripristino_password.html"> Password dimenticata?</a>
				           			 	</form>
							        </div>
							      </div>
							    </div>
							</div>
					</jstl:if>
					<!--  WELCOME -->
            		<jstl:if test="${loggato}">
						<li class="dropdown nav-item">
				     		<a id="navbar-text" class="nav-link dropdown-toggle" style="color:yellow" href="controlloAccessi">Benvenuto <jstl:out value="${username}"></jstl:out></a>
				     	</li>
				     	<li class="dropdown nav-item">
				     	<a id="navbar-text" data-toggle="modal" data-target="#logout" href="#"><span class="glyphicon glyphicon-user"></span> Disconnetti<b class="caret"></b></a>       		 
				     	</li>
					</jstl:if>
				<li class="dropdown nav-item">
				  <a id="navbar-text" data-toggle="modal" data-target="#settings" href="#"><span class="glyphicon glyphicon-cog"></span> </a>       		 
				</li>
			</ul> 
        </div>
    </nav>
		
		<!-- TITLE -->
		<div id="jumboHome" class="jumbotron text-center">
	  		<h1>Centro Unico Prenotazione Unical</h1>
	  		<h2>Benvenuto</h2>
		</div>
 
		<!--  CALENDARIO -->
		<div class="calendario">
		    <div id="day"></div>
		    <div id="date"></div>
		</div>
		
		<!--  BUBBLE -->
		<div id="bubble"></div>	
		
		<!--  PRENOTATI -->
		<div id="prenotati" title="Clicca qui per prenotarti">
			<img src="images/prenotati.png" style="width:100%" onclick="window.location='html/prenotazione.html'"/>
		</div>
		<div>
			<button id="prenotati2" onclick="window.location='html/prenotazione.html'">Clicca e prenotati subito!</button>
		</div>
		
		<!-- 	COLUMNS	 -->
		<div class="row">
		  <div class="column col-sm-4">
		    <img src="images/icona_help.png" class="zoom" onclick="window.location='restituisciSegnalazioni'" style="width:110px" alt="Assistenza" title="Assistenza">
		    <h3>Centro assistenza</h3>
		    <p>Hai bisogno di assistenza?<br> Siamo a completa disposizione per aiutarti a risolvere i tuoi problemi sulle prenotazioni</p>
		  </div>
		  <div class="column col-sm-4">
		    <img src="images/icona_statistica.png" class="zoom" onclick="window.location='restituisciPrenotazioni'" style="width:90px" alt="Prenotazioni correnti" title="Prenotazioni">
		    <h3>Tutte le prenotazioni</h3>
		    <p>Dai un'occhiata a tutte le prenotazioni correnti. </p>
		  </div>
		  <div class="column col-sm-4">
		     <img src="images/icona_search.png" class="zoom" onclick="window.location='html/cerca_prenotazione.html'" style="width:90px" alt="Search" title="Cerca prenotazione">
		    <h3>Cerca la tua prenotazione</h3>
		    <p>Qui potrai velocemente cercare la tua prenotazione e stamparti il riepilogo di avvenuta registrazione.</p>
		  </div>
		</div>

	    <!-- SETTINGS -->
		<div id="dialog">
      	  <div class="modal fade" id="settings" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		       <div class="modal-header">
		            <button type="button" class="close" data-dismiss="modal">&times;</button>
		            <h3 class="modal-title">Impostazioni</h3>
		          </div>
		          <div class="modal-body">
					
					<!-- RESETCOOKIES -->
         			 <a onclick="successSetting('cookies');" href="#">Ripristina Cookies</a>
	         			<div class="check_mark">
						  <div class="sa-icon sa-success animate">
						    <span class="sa-line sa-tip animateSuccessTip"></span>
						    <span class="sa-line sa-long animateSuccessLong"></span>
						    <div class="sa-placeholder"></div>
						    <div class="sa-fix"></div>
						  </div>
						</div>
	
						<!-- ALTRO -->
         			 <br>
		        </div>
		     </div>
		   </div>
		 </div>
	   </div>
	   
		<!--  LOGOUT -->
   		<div id="dialog" >
       	    <div class="modal fade" id="logout" role="dialog">
			    <div class="modal-dialog modal-md">
			      <div class="modal-content">
			          <div class="modal-header">
						<!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
			            <h3 class="modal-title" style="text-align:center">Sei sicuro di voler effettuare la disconnessione?</h3>
			          </div>
			   		  <div class="modal-body" style="text-align:center; background-color:#bcc4f2">
			   		 		<button type="button" style="background-color: #092147; color:white" class="btn btn-default" data-dismiss="modal" onclick="window.location='logout?${username}'">Si</button>
				    		<button type="button" style="background-color: #092147; color:white" class="btn btn-defualt" data-dismiss="modal" onclick="window.location='#'">No</button>
				      </div>
			     </div>
			   </div>
			</div>
		</div>
	    
		<!-- TUTORIAL -->
		<div id="dialog">
	      	<div class="modal fade" id="tutorialDialog" role="dialog">
			 	<div class="modal-dialog modal-md">
			    	<div class="modal-content">
		        	   <div class="modal-header" style="text-align:center; background-color:#bcc4f2">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<div id="tutorial">
							<p>Guarda ora il tutorial, su come prenotarsi!</p>
							<img src="images/youtube.png"><br>
								<iframe  src="https://www.youtube.com/embed/H5AVv30PM-M" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>
							<label><input id="rememberTutorial" type="checkbox" checked="checked"> Ricordami </label>
					    </div>
	         		   </div>
			    	</div>
			 	</div>
			</div>
		</div>
	    
		<!-- NOTICE(JSTL) -->
		<div id="dialog">
      	  <div class="modal fade" id="notice" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		         <div class="modal-header" style="text-align:center; background-color: #bcc4f2">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
	        	<h3 class="modal-title" style="text-align:center"> 
	        		<jstl:out value="${popUpMessage}"/>
	        	</h3>
	          </div>
		     </div>
		   </div>
		 </div>
	   </div>
	   	   
	   <jstl:if test="${popUp}">
	   		<script> $("#notice").modal('show'); </script>  
	   </jstl:if>
	

	<!-- FOOTER -->
	    <footer id="myFooter">
	        <div class="container">
	       		<div class="row">
                <div class="col-sm-3">
                      <h5>Get started</h5>
                      <ul>
                          <li><a href="home">Home</a></li>
                      </ul>
                  </div>
                  <div class="col-sm-3">
                      <h5>About us</h5>
                      <ul>
                          <li><a href="restituisciSegnalazioni">Contact us</a></li>
                      </ul>
                  </div>
                  <div class="col-sm-3">
                      <h5>Support</h5>
                      <ul>
                          <li><a href="html/assistenza.jsp">FAQ</a></li>
                          <li><a href="html/info.html">Info</a></li>
                      </ul>
                  </div>
                    <div class="col-sm-3">
                      <h5>Social</h5>
                      <ul>
                          <li> 
                            <a href="https://www.youtube.com/channel/UCCzP0HYi5dfhi4gDy8z6u2g?view_as=subscriber"><img class="social" width="50" height="50" src="images/youtube2.png"></a>
                          </li>
                          <li>   
                            <a href="https://www.facebook.com/CUP-Unical-803705986627639/"><img class="social" width="50" height="50" src="images/facebook.png"></a>
                          </li>
                     
                      </ul>
                  </div>
                  <div class="col-sm-2">
                  		<div id="fb-root"></div>
    			  		<div class="fb-like" data-href="https://www.facebook.com/CUP-Unical-803705986627639/" data-layout="standard" data-action="like" data-size="small" data-show-faces="false" data-share="true"></div>
           		  </div>  
                </div><br/>
	        	 <div id="floating-panel">
			    	<b style="color: #092147;">Partenza: </b>
			    	<input id="start" type="text" style="color:black;">
			    	<b style="color: #092147;">Arrivo: </b>
			    	<input id="end" type="text" value="Università della Calabria" style="text-align:center; color:black;">
			    </div>
			    <div id="map"></div>
	        </div>
	        <div class="footer-copyright">
	            <p>© 2018 Copyright Text</p>
	        </div>
	    </footer>
	       
	    <!-- SCROLLING -->
		<a href="#" class="scrollup">Scroll</a>
			
		<script src="js/home.js"></script>
	 	<script src="js/login.js"></script>	
	 	<script src="js/scroll_up.js"></script>
	 	<script src="js/calendar.js"></script>
	 	<script src="js/map.js"></script>
		<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxb_Bnm8d3aWl9wWiQUdk5P0lm00YkB04&callback=initMap"></script>
</body>
</html>