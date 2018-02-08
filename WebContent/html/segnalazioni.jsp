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
	<script src="${pageContext.request.contextPath}/js/scroll_up.js"></script>
	<script src="${pageContext.request.contextPath}/js/segnalazione.js"></script>
	<style>
		table, th, td {
    		border: 1px solid black;
    		text-align:center;
		}
		th, td {
			color:#92147;
		}
	</style>
</head>
<body>
	<!-- Navbar -->
	<nav role="navigation" role="navigation" class="navbar">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="dropdown nav-item">
					<a id="navbar-text" class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/home"><span class="glyphicon glyphicon-home"></span> Home</a>
			     </li>
	   		</ul>
		</div>	
	</nav>
	
	<div class="jumbotron text-center" style="background-color:#ffa500; color:#ffffff">
	  <h1>Area Segnalazioni</h1>
	</div>
	
	<div>
		<table style="color:#092147;width:100%;">
		<thead>
			<tr>
				<th>Codice</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Segnalazione</th>
				<th>Stato</th>
			</tr>
		</thead>
		<tbody>
				<jstl:set var="count" value="0" scope="page" />
			<jstl:forEach var="i" items="${segnalazioni}">
				<jstl:set var="count" value="${count + 1}" scope="page"/>
				<tr>
					<td>${i.codice}</td>
					<td>${i.nomeUtente}</td>
					<td>${i.cognomeUtente}</td>
					<td id="ris${count}">${i.motivazione}</td>
					<jstl:if test="${i.risolto}">
						<td><a data-toggle="modal" href="#" style="color:green; pointer-events:none; text-decoration: none;"> Risolto</a> <td>
					</jstl:if>
					<jstl:if test="${not i.risolto}">
						<td><a id="risolvi${count}" data-toggle="modal" data-target="#risolvi" href="#" 
						onclick="tmp('ris${count}', 'risolvi${count}');" style="color:red; text-decoration: none;"> Da risolvere</a> <td>
					</jstl:if>
				</tr>			
			</jstl:forEach>
		</tbody>
		</table>
	</div>
	
	<!-- Dialog -->
		<div id="dialog">
      	  <div class="modal fade" id="risolvi" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		         <div class="modal-header" style="text-align:center; background-color:#ffa500">
				<button type="button" class="close" data-dismiss="modal" onclick="dismiss();">&times;</button>
	        	<textarea id="risp" placeholder="Rispondi.." style="width:400px; height:300px"></textarea><br><br>
	      		<button data-dismiss="modal" class="btn btn-default" onclick="risp();">Conferma</button>  	
	          </div>
		     </div>
		   </div>
		 </div>
	   </div>
	
		<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
			
</body>
</html>