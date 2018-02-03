<%-- <%@page contentType="text/html" pageEncoding="UTF-8"%> --%>
<%-- <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%> --%>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Prenotazione Visita Medica</title>
	<link rel="stylesheet" href="../bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="../css/common.css">
	<script src="../js/jquery/jquery-3.2.1.min.js"></script>
	<script src="../bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="../js/reservation_controls.js"></script>
	<script src="../js/scroll_up.js"> </script>		
</head>

<body style="height:950px">

	<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="<%=request.getContextPath()%>/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
	   		</ul>
		</div>	
	</nav>
	
	<div class="jumbotron text-center">
		<h1>Prenotati</h1>
		<h4>Compila il seguente form per assicurarti una visita medica</h4>
	</div>

	<div class="form-prenotazione">
		<label for="codiceFiscale">Codice Fiscale *</label><input id="cf" onblur="CFRegex();" name="codiceFiscale" type="text" placeholder="codice fiscale" required/>
		<label for="nome">Nome *</label><input id="s1" onblur="correct(id);" name="nome" type="text"  placeholder="nome" required/>
		<label for="cognome">Cognome *</label><input id="s2" onblur="correct(id);" name="cognome" type="text" placeholder="cognome" required/>
		<label for="matricola">Matricola **</label><input id="n" onblur="correct(id);" name="matricola" type="text" placeholder="matricola">
		
		<label for="invalidita">Invalidit&aacute;</label>
			<select id="select" name="invalidita" class="form-control">	 
				<option selected="selected">Nessuna</option>
				<option>Malformazione</option>
				<option>Lesione Muscolare</option>
				<option>Malattia Generica</option>
			</select>
	
			<input id="conferma" name="conferma" type="submit" value="Conferma"  class="btn-success" onclick='sendForm();'/>
			<input name="annulla" type="reset" value="Annulla"  class="btn-danger" onclick="avviso();"/>
	</div>

	<div class="footer-prenotazione">
		<footer>
	  		<h5><strong>* Campi Obbligatori</strong></h5>
			<h5><strong>** Se sei universitario e vuoi usufruire della promozione specifica una matricola valida</strong></h5>
		</footer>	
	</div>
	
	<div id="dialog">
      	<div class="modal fade" id="notice" role="dialog">
		 	<div class="modal-dialog modal-md">
		    	<div class="modal-content">
	        	<div class="modal-header" style="text-align:center; background-color:#bcc4f2">
					<button type="button" class="close" data-dismiss="modal" onclick="window.location.href='../home'">&times;</button>
        			<h3 id="message" class="modal-title" style="text-align:center"></h3>
         		</div>
		    	</div>
		 	</div>
		</div>
	</div>
	
	<div id="dialog">
      	<div class="modal fade" id="notice2" role="dialog">
		 	<div class="modal-dialog modal-md">
		    	<div class="modal-content">
	        	<div class="modal-header" style="text-align:center; background-color:#bcc4f2">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h3 id="message2" class="modal-title" style="text-align:center"></h3>
        			<div class="modal-body" style="text-align:center; background-color:#bcc4f2">
        				<button type="button" class="btn btn-default" data-dismiss="modal" onclick="success();" style="background-color:#092147; color:#ffffff">Si</button>
        				<button type="button" class="btn btn-default" onclick="window.location.href='../home'" data-dismiss="modal" style="background-color:#092147; color:#ffffff">No</button>
        			</div>
         		</div>
		    	</div>
		 	</div>
		</div>
	</div>
	
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	

</body>
</html>