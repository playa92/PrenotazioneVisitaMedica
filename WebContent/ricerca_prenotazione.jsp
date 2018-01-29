<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="jstl" %>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ricerca Prenotazione</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<script src="js/jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/effects.js"></script>
	<script src="js/search.js"></script>
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
	
	<div class="jumbotron text-center">
		<h1> Cerca la tua prenotazione</h1> <br>
		<h3> semplicemente inserendo nell'apposito campo sottostante il codice generato durante la fase di prenotazione: </h3>
	</div>
		
	<div align="center" class="container">
	  <div class="row">
		<h2>Inserisci qui il tuo codice:</h2>
              <div class="input-group col-md-4">
                 <input id="input_h" type="text" class="search-query form-control" placeholder="Search" />
                 <span class="input-group-btn">
                     <button class="btn btn-danger" type="button" onclick="search()">
                         <span class=" glyphicon glyphicon-search"></span>
                     </button>
                 </span>
           </div>
	   </div>
   </div>
	
	<label id="countdown">Tempo rimasto:</label>

</body>
</html>