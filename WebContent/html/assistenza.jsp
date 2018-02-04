<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<%-- <jsp:useBean id="prenotato" class="model.Prenotazione" scope="request"/> --%>
<%-- <jsp:setProperty name="prenotato" property="codiceVisita" value=""/> --%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Assistenza</title>
	<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/common.css">
	<script src="../js/jquery/jquery-3.2.1.min.js"></script>
	<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="../js/report.js"></script> 	
	<script src="../js/scroll_up.js"> </script>
	
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
	
	<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="../home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
	   		</ul>
		</div>	
	</nav>
	
	<div class="jumbotron text-center" style="background:#720802;color:white">
<%-- 		<jsp:getProperty name="prenotato" property="codiceVisita"/>	 --%>
		<h1>Assistenza</h1><br>
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
					<form method="get" action="../segnalazione">
						<input type="text" name="nome" placeholder="Nome" required/>
						<input type="text" name="cognome" placeholder="Cognome" required/>
						<input type="text" name="email" placeholder="E-mail">
						<textarea id="textarea" name="commento"></textarea>
						<label>Motivazione: </label>
						<select id="select" name="motivazione">
					  		<option>Assenza Connessione</option>
					  		<option>Errore prenotazione</option>
					  		<option>Prenotazione non trovata</option>
					  		<option>Connessione scaduta</option>
						</select>
						<div class="modal-footer" style="text-align:center;">
		      		 		<input type="submit" class="btn btn-md" value="Invia"/>
		    		 		<button id="annulla" type="button" class="btn btn-md" data-dismiss="modal" onclick="window.location.href='#'">Annulla</button>
				  		</div>
			  		</form>	
				 </div>
		      </div>
	     </div>
	   </div>
	</div>
	
	<h1 style="padding: 0 0 0 1em;">Domande frequenti (FAQ) </h1><br><br>
	<div class="wrapper">  
	  <div class="half">
	    <div class="tab">
	      <input id="tab-one" type="checkbox" name="tabs">
	      <label for="tab-one">Domanda 1</label>
	      <div class="tab-content">
	        <p>Risposta 1.</p>
	      </div>
	    </div>
	    <div class="tab">
	      <input id="tab-two" type="checkbox" name="tabs">
	      <label for="tab-two">Domanda 2</label>
	      <div class="tab-content">
	        <p>Risposta 2.</p>
	      </div>
	    </div>
	    <div class="tab">
	      <input id="tab-three" type="checkbox" name="tabs">
	      <label for="tab-three">Doamnda 3</label>
	      <div class="tab-content">
	        <p>Risposta 3.</p>
	      </div>
	    </div>
<%-- 	   		<jstl:set var="count" value="0" scope="page" /> --%>
	    
<%-- 	    	<jstl:forEach var="p" items="${prenotazioni}"> --%>
<%-- 	    			<jstl:set var="count" value="${count + 1}" scope="page"/> --%>
	    	
<!-- 				<tr> -->
<%-- 					<td>${p.nomePaziente}</td> --%>
<%-- 					<td>${p.cognomePaziente}</td> --%>
<%-- 					<td>${p.orarioVisita}</td> --%>
<!-- 				</tr>			 -->
				
<!-- 				 <div class="tab"> -->
<%-- 			        <input id="tab-<jstl:out value="${count}"/>" type="checkbox" name="tabs"> --%>
			       
<!-- 			        <label for="tab-three">Doamnda 3</label> -->
<!-- 			        <div class="tab-content"><p>Risposta 3.</p></div> -->
			    
<!-- 			    </div> -->
			    
<%-- 			</jstl:forEach> --%>
	    
	    
	  </div>
	</div>
	
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	
</body>
</html>