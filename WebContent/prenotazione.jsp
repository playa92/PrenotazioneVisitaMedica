<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Prenotazione Visita Medica</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<script src="js/jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/reservation_controls.js"></script>
	<script src="js/hex_code.js"></script>
</head>

<body>

	<h2>Compila il seguente form per prenotare una visita medica</h2>

	<div class="col-lg-3">
		<form id="form" method="post" action="formPrenotazione">
				<div class="form-group"><label for="codice fiscale">Codice Fiscale *</label><input id="cf" onblur="CFRegex();" name="codice fiscale" type="text" class="form-control"  placeholder="Enter SSN" required/></div>
				<div class="form-group"><label for="nome">Nome *</label><input id="s1" onblur="correct(id);" name="nome" type="text" class="form-control"  placeholder="Enter Name" required/></div> 
				<div class="form-group"><label for="cognome">Cognome *</label><input id="s2" onblur="correct(id);" name="cognome" type="text" class="form-control"  placeholder="Enter Surname" required/></div>  
				<div class="form-group"><label for="matricola">Matricola **</label><input id="n" onblur="correct(id);" name="matricola" type="text" class="form-control"  placeholder="Enter Id"></div>  
				<div class="form-group"><label for="invalidita">Invalidità</label>
					<select name="invalidita" class="form-control">	 
						<option selected="selected">Nessuna</option>
						<option>Malformazione</option>
						<option>Lesione Muscolare</option>
						<option>Malattia Generica</option>
					</select>		
				</div>
				<div class="form-group"><input id="hex" name="hexCode" type="hidden" class="form-control"></div>	
				<div class="form-group">
					<input id="conferma" name="conferma" type="submit" value="Conferma"  class="btn-success" onclick="generate();"/>
					<input name="annulla" type="reset" value="Annulla"  class="btn-danger" onclick="avviso();"/>
				</div>
		</form>
	</div>
	
	<div>
	<footer>
  		<h5><strong>* Campi Obbligatori</strong></h5>
		<h5><strong>** Il seguente campo deve essere completato solo se si possiede realmente una matricola</strong></h5>
	</footer>	
	</div>
	
</body>
</html>