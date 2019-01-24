<jsp:useBean id="calcula" class="beans.BeanUsuario"
	type="beans.BeanUsuario" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>

<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina Principal</title>
<link rel="stylesheet" type="text/css" href="resources/css/estilo.css" />
</head>
<body>

 
   <div class="login-page"> 
		<div class="form">
			<form action="LoginServlet" method="post">
				<input type="text" id="login" name="login" placeholder="usuário" />
				<input type="password" id="senha" name="senha" placeholder="senha" />
				<button type="submit">Logar</button>
			</form>
		</div>
	</div>  

</body>
</html>