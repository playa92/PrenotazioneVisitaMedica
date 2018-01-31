<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" 
prefix="jstl" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ricerca Prenotazione</title>
	<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="css/common.css">
	<link rel="stylesheet" href="css/loading.css">
	<script src="js/jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	<script src="js/effects.js"></script>
	<style>
	.scrollup {
	    width: 80px;
	    height: 80px;
	    position: fixed;
	    bottom: 50px;
	    right: 100px;
	    display: none;
	    text-indent: -9999px;
	    background: url('images/arrow-up.png') no-repeat;
	}
	</style>
</head>

<body style="height:1150px">
		
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
  		<h1>Cerca la tua prenotazione</h1>
	</div>
	
	<div align="center" class="container">
	  <div class="row">
		<h2>Inserisci qui il tuo codice:</h2>
           <div class="input-group col-md-4">
              <input id="input" type="text" class="search-query form-control" placeholder="Search"/>
              <span class="input-group-btn">
                  <button id="search" class="btn btn-info" type="button">
                      <span class=" glyphicon glyphicon-search"></span>
                  </button>
              </span>
           </div>
	  </div>
   	</div>
	
	<div align="center" id="info" >
	     <div id="content">	
			<h1>Riepilogo Prenotazione:</h1><hr>
			<h3>Orario visita:</h3><h4 id="orario"></h4>
		    <h3>Tempo rimasto:</h3><h4 id="countdown"></h4>	
			<input id="text" type="hidden" value="46429BA1F391">
			<h3>Codice: </h3><h4>46429BA1F391</h4>
			<h3>Codice QR: </h3>
			<div id="qrcode"></div>
	     </div>
	     <h4 align="center" style="color:#092147; margin-top:60px">stampa promemoria
			<button id="cmd" type="button" class="btn btn-default btn-sm">
			<span class="glyphicon glyphicon-print"></span> PDF</button></h4>
	</div>
		
	<div id="error" align="center" style="color:red; font-size: 20px; padding-top:20px"></div>
		
	<!-- LOADING -->
	<div id="fountainG">
		<div id="fountainG_1" class="fountainG"></div>
		<div id="fountainG_2" class="fountainG"></div>
		<div id="fountainG_3" class="fountainG"></div>
		<div id="fountainG_4" class="fountainG"></div>
		<div id="fountainG_5" class="fountainG"></div>
		<div id="fountainG_6" class="fountainG"></div>
		<div id="fountainG_7" class="fountainG"></div>
		<div id="fountainG_8" class="fountainG"></div>
	</div>
	
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	
<!--  	46429BA1F391 -->

	<script>
	$('#cmd').click(function() {
		
		  var options = {
		  };
		  var pdf = new jsPDF('l', 'pt', 'a4');		  
		  pdf.addHTML($("#content"), 15, 15, options, function() {
		  pdf.save('riepilogo.pdf'); 
		  });
	});
	</script>

	<script src="js/search.js"></script>
	<script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://momentjs.com/downloads/moment-with-locales.js"></script>
	<script src="js/jquery/jquery.qrcode.js"></script>
	<script src="js/qr_code.js"></script>
	<script src="js/jquery/jspdf.min.js"></script>
	<script src="js/jquery/html2canvas.js"></script>

</body>
</html>