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
	<style>
	#form {
		width: 600px;
		height: 450px;
	/* 	border: 1px solid blue;   */
		padding: 25px 25px; 
		background-color: #bcc4f2; 
		margin: 25px auto 75px auto;
	}
	</style>
</head>

<body>

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
		<h2>Compila il seguente form per prenotare una visita medica</h2>
	</div>

	<div id="form">
		<div class="form-group"><label for="codiceFiscale">Codice Fiscale *</label><input id="cf" onblur="CFRegex();" name="codiceFiscale" type="text" class="form-control"  placeholder="Enter SSN" required/></div>
		<div class="form-group"><label for="nome">Nome *</label><input id="s1" onblur="correct(id);" name="nome" type="text" class="form-control"  placeholder="Enter Name" required/></div> 
		<div class="form-group"><label for="cognome">Cognome *</label><input id="s2" onblur="correct(id);" name="cognome" type="text" class="form-control"  placeholder="Enter Surname" required/></div>  
		<div class="form-group"><label for="matricola">Matricola **</label><input id="n" onblur="correct(id);" name="matricola" type="text" class="form-control"  placeholder="Enter Id"></div>  
		<div class="form-group"><label for="invalidita">Invalidità</label>
			<select id="select" name="invalidita" class="form-control">	 
				<option selected="selected">Nessuna</option>
				<option>Malformazione</option>
				<option>Lesione Muscolare</option>
				<option>Malattia Generica</option>
			</select>
		</div>
		<div class="form-group">			
			<input id="conferma" name="conferma" type="submit" value="Conferma"  class="btn-success" onclick='sendForm();'/>
			<input name="annulla" type="reset" value="Annulla"  class="btn-danger" onclick="avviso();"/>
		</div>
	</div>
	
	<div>
	<footer>
  		<h5><strong>* Campi Obbligatori</strong></h5>
		<h5><strong>** Il seguente campo deve essere completato solo se si possiede realmente una matricola</strong></h5>
	</footer>	
	</div>
	
	<div id="dialog">
      	<div class="modal fade" id="notice" role="dialog">
		 	<div class="modal-dialog modal-md">
		    	<div class="modal-content">
		        	<div class="modal-header" style="text-align:center; background-color: #bcc4f2">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h3 id="message" class="modal-title" style="text-align:center"></h3>
	         		</div>
		    	</div>
		 	</div>
		</div>
	</div>
	
</body>
</html>