<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Area Segnalazioni</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
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
			</tr>
		</thead>
		<tbody>
			<jstl:forEach var="i" items="${segnalazioni}">
				<tr>
					<td>${i.codice}</td>
					<td>${i.nomeUtente}</td>
					<td>${i.cognomeUtente}</td>
					<td>${i.motivazione}</td>
					<td><button onclick="risolvi()">Risolvi</button><td>
					
				</tr>			
			</jstl:forEach>
		</tbody>
		</table>
	</div>
	
</body>
</html>