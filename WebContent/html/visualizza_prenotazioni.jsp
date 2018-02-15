<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Visualizza prenotazioni</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css">
	<script src="${pageContext.request.contextPath}/js/jquery/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body id="show_res">
	
	<jstl:if test="${not vuoto}">
		
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
	
		<div class="jumbotron text-center" style="background:#5d7396; color:white">
			<h1> Prenotazioni correnti</h1> <br>
		</div>
		
		  <!-- SEARCHBAR-->
		  <div align="center"><br>
		  	<input id="search" class="button" placeholder="Cerca..." />
		  </div><br>
		
		
		<table id="table" class="table">
		  	<thead class="thead-default">
		    	<tr>
			        <th>#</th>
			      	<th>Nome</th>
			      	<th>Cognome</th>
			      	<th>Orario Visita</th>
		    	</tr>
		  	</thead>
			<tbody id="elencoPrenotati">
			
			<jstl:set var="count" value="0" scope="page" />
		
			<jstl:forEach var="p" items="${prenotazioni}">
				<jstl:set var="count" value="${count + 1}" scope="page"/>
			
					<tr>
						<th> <jstl:out value="${count}"/></th>
						<td>${p.nomePaziente}</td>
						<td>${p.cognomePaziente}</td>
						<td>${p.orarioVisita}</td>
					</tr>			
			</jstl:forEach>
			
		  </tbody>
		</table><br><br>
	
		<script>
		$(document).ready(function(){
			var $rows = $('#table tr');
			$('#search').keyup(debounce(function() {
			    var val = $.trim($(this).val()).replace(/ +/g, ' ').toLowerCase();
			    
			    $rows.show().filter(function() {
			        var text = $(this).text().replace(/\s+/g, ' ').toLowerCase();
			        return !~text.indexOf(val);
			    }).hide();
			}, 300));
		
			function debounce(func, wait, immediate) {
				var timeout;
				return function() {
					var context = this, args = arguments;
					var later = function() {
						timeout = null;
						if (!immediate) func.apply(context, args);
					};
					var callNow = immediate && !timeout;
					clearTimeout(timeout);
					timeout = setTimeout(later, wait);
					if (callNow) func.apply(context, args);
				};
			};
		})
		</script>
	 
		<!-- SCROLLING -->
		<a href="#" class="scrollup">Scroll</a>
		
	</jstl:if>
	
	<jstl:if test="${vuoto}">
		<br>
		<div class="jumbotron text-center" style="background:#5d7396; color:white">
			<h1> Nessuna Prenotazione disponibile </h1>
		</div>
		<div align="center">
			<button type='button'class='btn btn-default btn-lg' onclick="window.location='<%=request.getContextPath()%>/home'">  
				Torna alla Home <span class='glyphicon glyphicon-home'></span>
			</button>
		</div>
	</jstl:if>
	
	<script src="${pageContext.request.contextPath}/js/scroll_up.js"> </script>
	
</body>
</html>
