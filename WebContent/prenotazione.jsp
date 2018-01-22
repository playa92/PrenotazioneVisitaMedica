<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="c" %>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Prenotazione Visita Medica</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<script src="js/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/effects.js"></script>
</head>

<body>

	<h2>Compila il seguente form per prenotare una visita medica</h2>

	<div class="col-lg-3">
		<form id="form" class="text-form" method="post" action="formPrenotazione">
				<div class="form-group"><label for="nome">Codice Fiscale *</label><input name="codice fiscale" type="text" class="form-control"  placeholder="Enter SSN" required/></div>
				<div class="form-group"><label for="nome">Nome *</label><input name="nome" type="text" class="form-control"  placeholder="Enter Name" required/></div> 
				<div class="form-group"><label for="cognome">Cognome *</label><input name="cognome" type="text" class="form-control"  placeholder="Enter Surname" required/></div>  
				<div class="form-group"><label for="matricola">Matricola</label><input name="matricola" type="text" class="form-control"  placeholder="Enter Id"></div>  
				<div class="form-group"><label for="handicap">Handicap</label><input name="handicap" type="text" class="form-control"  placeholder="Enter Invalidity"></div>		
				<div class="form-group">
					<input name="conferma" type="submit" value="Conferma"  class="btn btn-success"/>
					<input name="annulla" type="reset" value="Annulla"  class="btn btn-danger" onclick="avviso();"/>
				</div>
		</form>
		<c:if test="${submit}">
		<script type="text/javascript">
			function reset() {	
				$('#form').trigger("reset"); 
			}
			</script>
		</c:if>
	</div>	
	
	<div>
	<footer>
  		<h5><strong>* Campi Obbligatori</strong></h5>
	</footer>
	</div>
	
</body>
</html>