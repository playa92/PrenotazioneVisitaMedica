<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Convalida Prenotazione</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
 	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
 	<script src="${pageContext.request.contextPath}/js/jquery/instascan.min.js"></script>

</head>
<body>

	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="../home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
			     <li class="dropdown nav-item">
			     	<a id="navbar-text" class="nav-link dropdown-toggle" style="color:yellow" href="controlloAccessi">Benvenuto <jstl:out value="${username}"></jstl:out></a>
			     </li>
	   		</ul>
		</div>	
	</nav>

	<div class="jumbotron text-center" style="background:#FFA500;color:white">
  		<h1>Convalida Prenotazione</h1>
  		<h4>Scegli una delle due modalità per convalidare una prenotazione</h4>
	</div>
	
	<div align="center">
	<p style="font-size:16px">Se disponi un lettore codice QR allora scegli la prima modalità a sinistra,<br> 
	   in alternativa la seconda modalità a destra ti permette di inserire un codice identificativo da tastiera.</p>
	 	<button id="QR" class="btn btn-lg myButton" style="background:#FFA500;color:white" title="Scansiona il codice QR">
         	<span class="glyphicon glyphicon-qrcode"></span>
        </button>
	 	<button id="HEX" class="btn btn-lg myButton" style="background:#FFA500;color:white" title="Inserisci il codice esadecimale">
	 		<span class="glyphicon glyphicon-text-size "></span> 
        </button>
	 </div><br>
	
	<div id="HEXCODE" align="center" class="container" style="display:none"><br>
	  <div class="row">
		<h2>Inserisci codice:</h2>
           <div class="input-group col-md-4">
              <input id="hexcode" type="text" class="search-query form-control" style="text-transform:uppercase" required/>
              <span class="input-group-btn">
                  <button class="btn" type="submit" onclick="validate($('#hexcode').val());" style="background:#FFA500;color:white">
                      <span class="glyphicon glyphicon-ok"></span>
                  </button>
              </span>
           </div>
	  </div>
   	</div>
   	
   	<div id="dialog">
      	<div class="modal fade" id="notice" role="dialog">
		 	<div class="modal-dialog modal-md">
		    	<div class="modal-content">
	        	<div class="modal-header" style="text-align:center; background-color:#bcc4f2">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
        			<h3 id="message" class="modal-title" style="text-align:center"></h3>
         		</div>
		    	</div>
		 	</div>
		</div>
	</div>
	
	<div id="dialog">
      	<div class="modal fade" id="receipt" role="dialog">
		 	<div class="modal-dialog modal-md">
		    	<div class="modal-content">
		    	<div class="modal-header">
				 	<button type="button" class="close" data-dismiss="modal" onclick="window.location.href='../home'">&times;</button>    
				 </div>
				 <div class="modal-body" style="text-align:center;">
				 
					 <div id="content" style="background:#FFA500; border-width:5px; border-style:double;">
	        			<h3 id="messageReceipt" style="text-align:center;font-size:37px"></h3>
	        		</div><br>
	        		  <button id='cmd' type="button" class="btn btn-sm" style="font-size:22px;background:#FFA500;">
         		 	stampa promemoria <span class="glyphicon glyphicon-print"></span></button>
				 </div>				 
		    	</div>
		 	</div>
		</div>
	</div>
	
	<!--  SCROLLING -->
  	<a href="#" class="scrollup">Scroll</a>
  
	<!-- QR-READER -->
	<div id="QRCODE"  align="center" style="display:none">
	  <h2>Tieni il codice QR ben in vista</h2>
	  <video style="width:350px;"id="preview"></video>
	</div>
	
	<script src="${pageContext.request.contextPath}/js/convalida.js"></script> 
 	<script src="${pageContext.request.contextPath}/js/jquery/jspdf.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/pdf_print.js"></script>
    <script src="${pageContext.request.contextPath}/js/scroll_up.js"> </script>   
    <script src="${pageContext.request.contextPath}/js/qr_scanner.js"></script>
    <script src="https://momentjs.com/downloads/moment.min.js"></script>
	<script src="https://momentjs.com/downloads/moment-with-locales.js"></script>
	
</body>
</html>