<jsp:useBean id="calcula" class="beans.BeanUsuario" type="beans.BeanUsuario" scope="page" />

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
      
   <a href="salvarUsuario?acao=listartodos">
       <img src="resources/img/cadastro_usuario.png" title="Cadastro de usuários" alt="Cadastro de usuários"
								width="120px" height="120px" /></a></td>
   <a href="salvarProduto?acao=listartodos">
       <img src="resources/img/cadastro_produto.png" title="Cadastro de produtos" alt="Cadastro de produtos"
								width="140px" height="140px" /></a></td>
</body>
</html>