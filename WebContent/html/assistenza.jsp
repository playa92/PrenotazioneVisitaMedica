<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Assistenza</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>

<body id="Assistenza">
	<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
	   		</ul>
		</div>	
	</nav>

	<div class="jumbotron text-center" style="background:#720802;color:white">
		<h1>Assistenza</h1>
		<h2>Contatta il nostro centro assistenza per qualsiasi evenienza</h2>
	</div>
	
	<div style="text-align:center">
		<a id="contact" class="btn" data-toggle="modal" data-target="#contattaci" href="#">Contattaci</a>       		 
	</div>
			       		
     <div class="modal fade" id="contattaci" role="dialog">
	    <div class="modal-dialog modal-md">
	      <div class="modal-content" style="box-shadow: 0 0 2px 2px white; background:#720802; text-align:center;">
	          <div class="modal-header" style="color:white">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
	            <h3 class="modal-title">Contattaci</h3>
	          </div>
	   		  <div class="modal-body"> 		
				 <div class="form-assistenza">
						<input type="text" name="nome" placeholder="Nome" required/>
						<input type="text" name="cognome" placeholder="Cognome" required/>
						<input type="email" name="email" placeholder="E-mail">
						<label>Motivazione: </label>
						<select id="select" name="motivazione">
					  		<option onclick="hideTextField();">Errore prenotazione</option>
					  		<option onclick="hideTextField();">Prenotazione non trovata</option>
					  		<option onclick="hideTextField();">Connessione scaduta</option>
					  		<option onclick="toggleTextField();">Altro</option>
						</select>
						<textarea id="textarea" name="commento" required></textarea><hr>
		      		 		<input id="submitSegnalazione" type="submit" class="btn btn-md" value="Invia" onclick="send();"/>
		    		 		<button id="annulla" type="button" class="btn btn-md" data-dismiss="modal" onclick="window.location.href='#'">Annulla</button>
				 </div>
		      </div>
	     </div>
	   </div>
	</div>
	<jstl:if test="${not vuoto}">
		<h1 style="padding: 0 0 0 1em;">Domande frequenti (FAQ)</h1><br><br>
	
		<div class="wrapper">  
		  <div class="half">
		    <div class="tab"></div>
		      
				<jstl:set var="count" value="0" scope="page" /> 
				 <jstl:forEach var="i" items="${segnalazioni}">
					<jstl:set var="count" value="${count + 1}" scope="page"/> 
					 <div class="tab">
				        <input id="tab-${count}" type="checkbox" name="tabs">    
				        <label for="tab-${count}">${i.commento} ( ${i.motivazione} )</label>
				        <div class="tab-content"><p>${i.risposta}.</p></div>
				    </div>			
				</jstl:forEach>
				
		     </div>
		</div>
	</jstl:if>
	
	<jstl:if test="${vuoto}">
		<div style="text-align:center; margin-top:100px;">
			<h1> Nessuna FAQ disponibile </h1>
		</div>
	</jstl:if>
	
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	
	<script src="${pageContext.request.contextPath}/js/scroll_up.js"> </script>
	<script src="${pageContext.request.contextPath}/js/assistenza.js"> </script>

</body>
</html>