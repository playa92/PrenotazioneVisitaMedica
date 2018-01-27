<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="jstl" %>

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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.3.2/jspdf.debug.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.js"></script>
    <script src="js/qrcode.js"></script>	
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
				           			 		
				           			      <span id="avviso" class="label label-danger">Nothing to show yet</span><br> 
				           			 	
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
		
	<div class="jumbotron text-center">
	  <h1>Benvenuto nel Sito di Prenotazione</h1>
  	  <h5>Prenota subito la tua visita medica!</h5> 
	</div>
		
		
	<script>
				
		$(document).ready(function(){
		    $("#form-field").submit(function(e){
		    	  $.ajax({
					  type: 'post',
			          url: 'login', 
			          
			          // CON SERIALIZABLE APRE UNA NUOVA PAGINA
			          // SENZA INVECE FUNZIONA (prova a decommentare l'altro XD)
			          
 			          data: this.serializable(), 
// 			          data: {
// 							prova:'ciao',
// 							prova2:'ciao2'
// 					  },
					  success: function(data) {
					       $('#avviso').text(data);
					  }
				  });
		        e.preventDefault();
		    });
		});
		
	</script>

	
	
</body>
</html>