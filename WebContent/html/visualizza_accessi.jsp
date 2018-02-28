<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
	<title>Gestione Accessi</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loader.css">
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
 	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
 	<script src="${pageContext.request.contextPath}/js/scroll_up.js"> </script> 
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
				     	<li><a id="list-element" href="${pageContext.request.contextPath}/eliminaAccessi">Pulisci Accessi</a></li>
				       	<li><a id="list-element" href="#">Altro</a></li>
			        </ul>
			     </li>
	   		</ul>
		</div>	
	</nav>

	<div class="jumbotron text-center" style="background:#1db5d3; color:#092147">
		<h1>Controllo Accessi</h1>
	</div>
	
	<jstl:if test="${not nessun_accesso}">
		<div class="table-accessi">
			<table style="color:#092147; width:100%;">
				<thead>
					<tr>
						<th>Azione</th>
						<th>Data</th>
						<th>Ora</th>
						<th>Utente</th>
					</tr>
				</thead>
				<tbody>
					<jstl:forEach var="it" items="${accessi}">
						<tr>
							<jstl:choose>
								<jstl:when test="${it.azione == 'login'}">
									<td style="color:green; font-weight:bold;">${it.azione}</td>
								</jstl:when>
								<jstl:otherwise> 
									<td style="color:red; font-weight:bold;">${it.azione}</td>	
								</jstl:otherwise>
							</jstl:choose>
							<td>${it.data}</td>
							<td>${it.orario}</td>
							<td>${it.nomeUtente}</td>   
						</tr>
					</jstl:forEach>
				</tbody>
			</table>
		</div>
	</jstl:if>
	<jstl:if test="${nessun_accesso}">
		<div style="text-align:center; margin-top:100px;">
			<h1> Nessun accesso disponibile </h1>
		</div>
	</jstl:if>
	 
	<!-- SCROLLING -->
	<a href="#" class="scrollup">Scroll</a>
	
</body>
</html>