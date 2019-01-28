<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Produto</title>
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css" />
</head>
<body>

	<!-- https://bootsnipp.com/snippets/0BDPG -->
	
	<a href="acessoliberado.jsp">Inicío</a>
	<a href="index.jsp">Sair</a>

	<center>
		<h1>Cadastro de Produto</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>


	<div class="form">
		<form action="salvarProduto" method="post" id="formProduct"
			onsubmit="return validarCampos() ? true : false;">
			<ul class="form-style-1">
				<li><label>Código<span class="required"></span></label> <input
					type="text" id="id" name="id" value="${product.id}"
					class="field-divided" /> <label>Descrição <span
						class="required">*</span></label> <input type="text" id="descricao"
					name="descricao" value="${product.descricao}" class="field-long"
					placeholder="informe a descrfição do produto" /> <label>Quantidade
						<span class="required">*</span>
				</label> <input type="text" id="quantidade" name="quantidade"
					value="${product.quantidade}" class="field-divided" placeholder="" />
					<label>Valor <span class="required">*</span></label> <input
					type="text" id="valor" name="valor" value="${product.valor}"
					class="field-divided" placeholder="" /></li>

				<li><input type="submit" value="Salvar" /> <input
					type="submit" value="Cancelar"
					onclick="document.getElementById('formProduct').action = 'salvarProduto?acao=reset'" />
				</li>
			</ul>
		</form>
	</div>

	<div class="container">
		<table class="responsive-table">
			<thead>
				<tr>
					<th scope="col">Código</th>
					<th scope="col">Descrição</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Valor</th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${produtos}" var="product">
					<tr>
						<td style="width: 50px"><c:out value="${product.id}"></c:out></td>
						<td style="width: 75px"><c:out value="${product.descricao}"></c:out></td>
						<td style="width: 150px"><c:out value="${product.quantidade}"></c:out></td>
						<td style="width: 150px"><c:out value="${product.valor}"></c:out></td>
						<td style="width: 70px"><a
							href="salvarProduto?acao=delete&id=${product.id}"><img
								src="resources/img/excluir.png" title="Excluir" alt="Excluir"
								width="20px" height="20px" /></a></td>
						<td style="width: 70px"><a
							href="salvarProduto?acao=editar&id=${product.id}"><img
								src="resources/img/editar.png" title="Alterar" alt="Alterar"
								width="20px" height="20px" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("descricao").value == '') {
				window.alert('Informe a descricao');
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				window.alert('Informe a quantidade');
				return false;
			} else if (document.getElementById("valor").value == '') {
				window.alert('Informe o valor');
				return false;
			}

			return true;
		}
	</script>

</body>
</html>