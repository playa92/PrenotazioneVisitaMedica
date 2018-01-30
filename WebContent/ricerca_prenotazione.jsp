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
	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://momentjs.com/downloads/moment-with-locales.js"></script>
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
                 <input id="input" type="text" class="search-query form-control" placeholder="Search"/>
                 <span class="input-group-btn">
                     <button class="btn btn-danger" type="button" onclick="search();">
                         <span class=" glyphicon glyphicon-search"></span>
                     </button>
                 </span>
           </div>
	  </div>
   	</div>
	
	<div align="center">
   		<label id="countdown"></label>
	</div>
	
	<button class="btn btn-success" onclick="init();">Clicca per sapere il tempo rimasto:</button>
	<p id="countdown">null</p> 

<!--46953D594425 -->
<!--CDF17D61B3BF -->

	<script>
// 	TODO
	var result = 0;
	
		function init(){
			result = document.getElementById('countdown').innerHTML;
			currentTime();
		}
		
	// 	TEMPO CORRENTE
		function currentTime() {
			
			var then =  result;
			
			var ms = moment(then,"HH:mm:ss").diff(moment(moment(),"HH:mm:ss"));
			var d = moment.duration(ms);
			var s = Math.floor(d.asHours()) + moment.utc(ms).format(":mm:ss");

		    document.getElementById('countdown').innerHTML = s;
		    t = setTimeout(function () {
		        currentTime()
		    }, 1000);
		}
		
		function checkTime(i) {
		    if (i < 10) {
		        i = "0" + i;
		    }
		    return i;
		}
				
	</script>

</body>
</html>