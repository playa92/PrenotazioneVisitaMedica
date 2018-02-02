<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>

<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
	<title>Prenota Visita Medica</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/loader.css">
 	<link rel="stylesheet" href="css/common.css"> 
 	<script src="js/jquery/jquery-3.2.1.min.js"></script>
 	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
 	<script src="js/jquery/jquery.cookie.js"></script>
 	<script src="js/effects.js"></script>
 	<script src="js/login.js"></script>	
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
					<a id="navbar-text" class="nav-link dropdown-toggle" href="<%=request.getContextPath()%>/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
			    <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="html/prenotazione.jsp">Prenotazione</a>
			     </li>
			     <li class="dropdown nav-item ">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="html/assistenza.html">Assistenza</a>
			     </li>
			     <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Statistiche <b class="caret"></b></a>
			        <ul class="dropdown-menu">
				     	<li><a id="list-element" href="html/cerca_prenotazione.jsp">Cerca prenotazione</a></li>
				       	<li><a id="list-element" href="restituisciPrenotazioni">Prenotazioni correnti</a></li>
			        </ul>
			     </li>
			     <jstl:if test="${loggato}">
			     	<li class="dropdown nav-item">
						<a id="navbar-text" class="nav-link dropdown-toggle" href="html/segnalazioni.html">Segnalazioni 
							<span class="badge badge-notify" style="background:orange">1</span>
						</a>
			     	</li>
			     </jstl:if>   
	   		</ul>
		    <ul id="right-fields" class="nav navbar-nav navbar-right">
				<!--  Login -->	
				<li class="dropdown nav-item">
					<jstl:if test="${not loggato}">
						  <!-- login -->						
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
					<jstl:if test="${loggato}">
					<!-- logout -->
					<li id="dialog" class="dropdown nav-item">
			       			<a id="navbar-text" data-toggle="modal" data-target="#myModal" href="#"><span class="glyphicon glyphicon-log-in"></span> Disconnetti<b class="caret"></b></a>       		 
				       		<div style="color:yellow; margin:-34px -300px; position:absolute">Benvenuto <jstl:out value="${username}"/>!</div>		
				       		  <div class="modal fade" id="myModal" role="dialog">
							    <div class="modal-dialog modal-md">
							      <div class="modal-content">
							          <div class="modal-header">
										<!-- <button type="button" class="close" data-dismiss="modal">&times;</button> -->
							            <h3 class="modal-title" style="text-align:center">Sei sicuro di voler effettuare la disconnessione</h3>
							          </div>
							   		  <div class="modal-body" style="text-align:center; background-color:#bcc4f2">
							   		 		<button type="button" style="background-color: #092147; color:white" class="btn btn-default" data-dismiss="modal" onclick="window.location='login?logout=true'">Yes</button>
								    		<button type="button" style="background-color: #092147; color:white" class="btn btn-defualt" data-dismiss="modal" onclick="window.location='#'">No</button>
								      </div>
							     </div>
							   </div>
							</div>
					</li>
					</jstl:if>
				</li> 
			   	<!-- Ricerca -->
			  	<li id="navbar-text" >
		        	<form id="searchForm" class="navbar-form" role="search" action="javascript:search();">
				    <div class="input-group">
			            <input id="searchItem" type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
			            <div class="input-group-btn">
			                <button class="btn btn-default" type="submit" onclick="document.getElementById('searchForm').submit(); return false;"><i class="glyphicon glyphicon-search"></i></button>
			            </div>
			        </div>
				    </form>
			 	</li>

			</ul> 
		
		</div>	
	</nav>
		
	<div class="title" align="center">
	  <p id="primary">Benvenuto nel Sito di Prenotazione</p>
	  <p id="secondary">(Scriviamo qualcos'altro)</p>
	</div>
	
	<!-- BOOTSTRAP CAROUSEL -->
	<div class="container">
	  <div id="myCarousel" class="carousel slide" data-ride="carousel">
	    <!-- Indicators -->
	    <ol class="carousel-indicators">
	      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
	      <li data-target="#myCarousel" data-slide-to="1"></li>
	      <li data-target="#myCarousel" data-slide-to="2"></li>
	    </ol>
	
	    <!-- Wrapper for slides -->
	    <div class="carousel-inner">
	      <div class="item active">
	        <img src="images/studio_medico.jpg" alt="studio medico">
	        <div class="carousel-content">
		        <a href="html/prenotazione.jsp" >Prenota subito la tua visita</a>
		    </div>
	      </div>	
	      
	      <div class="item">
	        <img src="images/cerca_pren.jpg" alt="Ricerca">
	        <div class="carousel-content" style="margin:50px 0 0 100px">
		        <a href="html/cerca_prenotazione.jsp">Cerca prenotazione!</a>
		    </div>
	      </div>
	      
	      <div class="item">
	        <img src="images/statistiche.jpg" alt="Statistiche">
	        <div class="carousel-content">
		        <a href="restituisciPrenotazioni">Visualizza stato prenotazioni</a>
		    </div>
	      </div>
	    </div>
	
	    <!-- Left and right controls -->
	    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
	      <span class="glyphicon glyphicon-chevron-left"></span>
	      <span class="sr-only">Previous</span>
	    </a>
	    <a class="right carousel-control" href="#myCarousel" data-slide="next">
	      <span class="glyphicon glyphicon-chevron-right"></span>
	      <span class="sr-only">Next</span>
	    </a>
	 	</div>
	</div>
	
		<!-- Dialog -->
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
	   
<%-- 	   <jstl:if test="${popUp}"> --%>
<!-- 	   		<script> $("#notice").modal('show'); </script>   -->
<%-- 	   </jstl:if> --%>
					
</body>
</html>