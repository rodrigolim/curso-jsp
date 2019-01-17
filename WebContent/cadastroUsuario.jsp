<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css" />
</head>
<body>
	<center><h1>Cadastro de usuário</h1></center>


	<form action="salvarUsuario" method="post">
		<ul class="form-style-1">
		    <li>
		      <label>Código<span class="required"></span></label>
		      <input type="text" id="id" name="id" value="${user.id}" class="field-divided"/>
            </li>
			<li>
			  <label>Login <span class="required">*</span></label>
			  <input type="text" id="login" name="login" value="${user.login}" class="field-divided" placeholder="informe o login do usário" />
            </li>
			<li>
			  <label>Senha <span class="required">*</span></label>
			  <input type="password" id="senha" name="senha" value="${user.senha}" class="field-divided" placeholder="informe a senha do usário" />
			</li>
			<li>
			  <input type="submit" value="Salvar" />
			</li>
		</ul>
	</form>

  <div class="container">
   <table class="responsive-table">
	    <thead>
	      <tr>
	        <th scope="col">Código</th>
	        <th scope="col">Login</th>
	        <th scope="col">Senha</th>
	        <th scope="col"></th>
	        <th scope="col"></th>
	      </tr>
	    </thead>
	    <tbody>
			<c:forEach items="${usuarios}" var="user">
				<tr>
					<td style="width: 50px"><c:out value="${user.id}"></c:out></td>
					<td style="width: 150px"><c:out value="${user.login}"></c:out>
					</td>
					<td style="width: 100px"><c:out value="${user.senha}"></c:out>
					</td>
					<td style="width: 70px"><a
						href="salvarUsuario?acao=delete&id=${user.id}"><img src="resources/img/excluir.png" title="Excluir" alt="Excluir" width="20px" height="20px"/></a></td>
					<td style="width: 70px"><a
						href="salvarUsuario?acao=editar&id=${user.id}"><img src="resources/img/editar.png" title="Alterar" alt="Alterar" width="20px" height="20px"/></a></td>
				</tr>
			</c:forEach>
		 </tbody>
  </table>
</div>

</body>
</html>