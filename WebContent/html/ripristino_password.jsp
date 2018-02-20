<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1" name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Ripristino Credenziali</title>
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
	   		</ul>
		</div>	
	</nav>
	
	<div class="jumbotron text-center">
  		<h1>Ripristina la tua password</h1>
	</div>

   <div class="form-password">
   <form method="post" action="../ripristinoPassword">
        <label>Username *</label><input type="text" class="form-control" placeholder="Inserisci il tuo username" name="username" required/>
        <label>Password *</label><input type="password" class="form-control" placeholder="Inserisci la tua nuova password" name="newPassword" required/>
	   <div align="center">
	      <input type="submit" value="Salva" class="btn-success">
	      <input type="reset" value="Annulla" class="btn-danger">
	   </div>
   </form>
   </div>
   
   <div id="dialog">
      	<div class="modal fade" id="notice" role="dialog">
		    <div class="modal-dialog modal-md">
		      <div class="modal-content">
		        <div class="modal-header" style="text-align:center; background-color: #bcc4f2">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			<h3 class="modal-title" style="text-align:center"> 
	        				<jstl:out value="${message}"/>

	        			</h3>
          			</div>
	     		</div>
	   		</div>
	 	</div>
   </div>
  
  
  <jstl:if test="${not empty message}">
	   	<script> $("#notice").modal('show'); </script>  
  </jstl:if>
  
</body>
</html>