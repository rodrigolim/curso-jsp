<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Telefone</title>
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css" />

</head>
<body>
    <a href="acessoliberado.jsp">Inicío</a>
    <a href="index.jsp">Sair</a>
    
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>


	<div class="form">
		<form name="formTelefone" action="salvarTelefones" method="post"
			id="formUser" onsubmit="return validarCampos() ? true : false;">
			<ul class="form-style-1">
				<li>
				<table>
					<tr>
						<td><label>Usuário<span class="required"></span></label> </td>
						<td><input type="text" readonly="readonly" id="id" name="idnumero" value="${userEscolhido.id}"	 size="5"/>
					    <input type="text" readonly="readonly" id="nome" name="nome" value="${userEscolhido.nome}"	size="30"/></td>
					</tr>
					<tr>
						<td><label>Número<span class="required">*</span></label></td>
						<td><input type="text" id="numero" name="numero" value="" size="27%" />
						<select id="tipo" name="tipo">
						  <option>Casa</option>
						  <option>Contato</option>
						  <option>Celular</option>
						</select></td> 
					</tr>
				
					
					<tr> 
				        <td></td>
				   		<td><input type="submit" value="Salvar"> <input type="submit"  value="Cancelar" onclick="document.getElementById('formTelefone').action = 'salvarTelefones?acao=reset'"></td>
				   </tr>
				</table>
			</ul>
		</form>
	</div>

	<div class="container">
		<table class="responsive-table">
			<thead>
				<tr>
					<th scope="col">Código</th>
					<th scope="col">Número</th>
					<th scope="col">Tipo</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${telefones}" var="fone">
					<tr>
						<td style="width: 50px"><c:out value="${fone.id}"></c:out></td>
						<td style="width: 75px"><c:out value="${fone.numero}"></c:out></td>
						<td style="width: 150px"><c:out value="${fone.tipo}"></c:out></td>
						<td style="width: 40px"><a
							href="salvarTelefones?acao=delete&id=${fone.id}"><img
								src="resources/img/excluir.png" title="Excluir" alt="Excluir"
								width="20px" height="20px" /></a></td>
						<td style="width: 40px"><a
							href="salvarTelefones?acao=editar&id=${fone.id}"><img
								src="resources/img/editar.png" title="Alterar" alt="Alterar"
								width="20px" height="20px" /></a></td>
								</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				window.alert('Informe o Número');
				return false;
			} else if (document.getElementById("tipo").value == '') {
				window.alert('Informe o Tipo');
				return false;
			}

			return true;
		}
	</script>

</body>
</html>