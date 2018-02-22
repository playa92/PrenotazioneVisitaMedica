<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/loader.css">
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
 	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
 	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script> 
	<title>Gestione Accessi</title>
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

	<div class="jumbotron text-center" style="background:#1db5d3; color:#092147">
		<h1>Controllo Accessi</h1>
	</div>
	
	<jstl:if test="${not vuoto}">
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
							<td><button type="button" onclick="" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-remove"></span> Rimuovi</button></td>
						</tr>
					</jstl:forEach>
				</tbody>
			</table>
		</div>
	</jstl:if>
	
</body>
</html>