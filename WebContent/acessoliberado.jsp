<jsp:useBean id="calcula" class="beans.BeanCursoJsp" type="beans.BeanCursoJsp" scope="page" />

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
   <jsp:setProperty property="*" name="calcula"/> 
    
   <h3>Seja bem vindo ao sistema em JSP</h3>
      
   <a href="cadastroUsuario.jsp">Cadastro de usu�rios</a> 
</body>
</html>