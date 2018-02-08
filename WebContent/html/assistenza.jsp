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
	<script src="${pageContext.request.contextPath}/js/scroll_up.js"> </script>
	
	<script>
		$(document).ready(function() {
			$("input[name=nome]").val('');
			$("input[name=cognome]").val('');
			$("input[name=email]").val('');
			$("#textarea").val('');
		});
			
		function reset() {
			$("input[name=nome]").val('');
			$("input[name=cognome]").val('');
			$("input[name=email]").val('');
			$("#textarea").val('');
		}
	</script>

</head>

<body id="Assistenza">
	<jstl:if test="${not vuoto}">
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
	</jstl:if>

	<div class="jumbotron text-center" style="background:#720802;color:white">
		<h1>Assistenza</h1>
		<h2>Contatta il nostro centro assistenza per qualsiasi evenienza</h2>
	</div>
	
	<div style="text-align:center">
		<a id="contact" class="btn" data-toggle="modal" data-target="#contattaci" href="#">Contattaci</a>       		 
	</div>
			       		
     <div class="modal fade" id="contattaci" role="dialog">
	    <div class="modal-dialog modal-md">
	      <div class="modal-content" style="box-shadow: 0 0 2px 2px white;background: #720802;text-align:center;">
	          <div class="modal-header" style="color:white">
				<button type="button" class="close" data-dismiss="modal" onclick="reset();">&times;</button>
	            <h3 class="modal-title">Contattaci</h3>
	          </div>
	   		  <div class="modal-body"> 		
				 <div class="form-assistenza">
					<form method="get" action="${pageContext.request.contextPath}/segnalazione">
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
						<textarea id="textarea" name="commento" required></textarea>
						<div class="modal-footer" style="text-align:center;">
		      		 		<input id="submitSegnalazione" type="submit" class="btn btn-md" value="Invia"/>
		    		 		<button id="annulla" type="button" class="btn btn-md" data-dismiss="modal" onclick="window.location.href='#'">Annulla</button>
				  		</div>
			  		</form>	
				 </div>
		      </div>
	     </div>
	   </div>
	</div>
	<jstl:if test="${not vuoto}">
	<h1 style="padding: 0 0 0 1em;">Domande frequenti (FAQ) </h1><br><br>
	</jstl:if>
	<div class="wrapper">  
	  <div class="half">
	    <div class="tab">
	      </div>
	      
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
	
	<jstl:if test="${vuoto}">
			<h1> Nessuna FAQ disponibile </h1>
		<div style="margin-left:100px">
			<button type='button'class='btn btn-default btn-lg' onclick="window.location='${pageContext.request.contextPath}/home'">  
				Torna alla Home <span class='glyphicon glyphicon-home'></span>
			</button>
		</div>
	</jstl:if>
	
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
		
</body>
</html>