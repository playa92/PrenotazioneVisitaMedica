<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Segnalazioni</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<body>
		<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
			     <li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="#" data-toggle="dropdown">Update <b class="caret"></b></a>
			        <ul class="dropdown-menu">
				     	<li><a id="list-element" href="#">Pulisci Risolti</a></li>
				       	<li><a id="list-element" href="#">Altro</a>
				       	
<!-- 				       		<form method="get" action="risolviSegnalazione"> -->
<!-- 				      			<input id="motivazione" name="motivazione" type="hidden"> -->
<!-- 			          		</form> -->
				       	
				       	</li>
			        </ul>
			     </li>
	   		</ul>
		</div>	
	</nav>
	
	<div class="jumbotron text-center" style="background-color:#ffa500; color:#ffffff">
	  <h1>Area Segnalazioni</h1>
	</div>
	
	<div class="Form-segnalazione">
		<table style="color:#092147;width:100%;">
		<thead>
			<tr>
				<th>Codice</th>
				<th>E-mail</th>
				<th>Nome</th>
				<th>Segnalazione</th>
				<th>Stato</th>
			</tr>
		</thead>
		<tbody>
			
			<jstl:forEach var="i" items="${segnalazioni}">
				<tr>
					<td>${i.id}</td>
					<td><a data-toggle="modal" data-target="#send" href="#" onclick="getMail(text);">${i.email}</a></td>
					<td>${i.nomeUtente}</td>
					<td id="ris${i.id}">${i.motivazione}</td>
					<jstl:if test="${i.risolto}">
						<td><a data-toggle="modal" href="#" style="color:green; pointer-events:none; text-decoration: none;"> Risolto</a> </td>
					</jstl:if>
					<jstl:if test="${not i.risolto}">
						<td><a id="risolvi${i.id}" data-toggle="modal" data-target="#risolvi" href="#" 
						onclick="tmp('ris${i.id}', 'risolvi${i.id}');" style="color:red; text-decoration: none;"> Da risolvere</a> </td>
					</jstl:if>
				</tr>			
			</jstl:forEach>
			
		</tbody>
		</table>
	</div>
	
<!-- EMAIL -->
		<div id="dialog">
      	  <div class="modal fade" id="send" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		         <div class="modal-header" style="text-align:center; background-color:#ffa500">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
	        		<form method="post" action="">
			    		<input type="hidden" name="status" value="SOLVED"/>
			    		<textarea placeholder="Scrivi..." rows="10" cols="40" style="width:400px; height:200px;font-size:25px;" name="message" required="required"></textarea><br><br>
			    		<button type="submit" class="btn-success btn-lg">Invia 
         					<span class="glyphicon glyphicon-envelope"></span> 
       					</button>
					</form>
	          	</div>
		     </div>
		   </div>
		 </div>
	   </div>
		
<!-- RISOLVI -->
		<div id="dialog">
      	  <div class="modal fade" id="risolvi" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		         <div class="modal-header" style="text-align:center; background-color:#ffa500">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
	        		<form method="get" action="risolviSegnalazione">
		        		<textarea id="risposta" name="risposta" placeholder="Rispondi.." style="width:400px; height:200px; font-size:25px;"></textarea><br><br>
		      			<input id="motivazione" name="motivazione" type="hidden">
		      			<button class="btn-success btn-lg" type="submit">Conferma <span class="glyphicons glyphicons-ok"></span> </button>  	
	          		</form>
	          	</div>
		     </div>
		   </div>
		 </div>
	   </div>
	
		<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	
	<script src="${pageContext.request.contextPath}/js/scroll_up.js"></script>
	<script src="${pageContext.request.contextPath}/js/segnalazione.js"></script>
			
</body>
</html>