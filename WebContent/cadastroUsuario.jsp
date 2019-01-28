<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cadastro de Usuário</title>
<link rel="stylesheet" type="text/css" href="resources/css/cadastro.css" />

 <!-- Adicionando JQuery -->
 <script src="https://code.jquery.com/jquery-3.2.1.min.js"
         integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
         crossorigin="anonymous"></script>


</head>
<body>

	<!-- https://bootsnipp.com/snippets/0BDPG -->

    <a href="acessoliberado.jsp">Inicío</a>
    <a href="index.jsp">Sair</a>
    
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 style="color: orange;">${msg}</h3>
	</center>


	<div class="form">
		<form name="formUser" action="salvarUsuario" method="post"
			id="formUser" onsubmit="return validarCampos() ? true : false;">
			<ul class="form-style-1">
				<li>
				<table>
					<tr>
						<td><label>Código<span class="required"></span></label> </td>
						<td><input type="text" readonly="readonly" id="id" name="id" value="${user.id}"	class="field-divided" /></td>
					</tr>
					<tr>
						<td><label>Login<span class="required">*</span></label></td>
						<td><input type="text" id="login" name="login" value="${user.login}" size="47%"	placeholder="informe o login do usário" /></td> 
					</tr>
					<tr>
						<td><label>Senha<span class="required">*</span></label> </td> 
						<td><input type="password" id="senha" name="senha" value="${user.senha}" size="47%"	placeholder="informe a senha do usário" /></td>  
					</tr>
					<tr>
						<td><label>Nome<span class="required">*</span></label> </td> 
						<td><input type="text" id="nome" name="nome" value="${user.nome}"  size="47%" placeholder="informe o nome do usário" /></td>  
					</tr>
					<tr>
						<td><label>Telefone<span class="required">*</span></label> </td>
						<td><input type="text" id="telefone" name="telefone"value="${user.telefone}" size="47%" placeholder="informe o telefone do usário" /></td>  
					</tr>
					<tr>
						<td><label>Email<span class="required">*</span></label> </td>	 
						<td><input type="text" id="email" name="email" value="${user.email}" size="51%" placeholder="informe o email do usário" /></td> 
                	</tr>
                	<tr>
                		<td><label>Cep<span class="required">*</span></label> </td>	
                		<td><input type="text" id="cep" name="cep" onblur="consultaCep();" value="${user.cep}" size="10" maxlength="9" /></td> 
					</tr>					
					<tr>
                		<td><label>Rua<span class="required">*</span></label> </td>	
                		<td><input type="text" id="rua" name="rua" value="${user.rua}" size="47%" /></td> 
					</tr>
					<tr>
                		<td><label>Bairro<span class="required">*</span></label> </td>	
                		<td><input type="text" id="bairro" name="bairro" value="${user.bairro}" size="47%" /></td> 
					</tr>
					<tr>
                		<td><label>Cidade<span class="required">*</span></label> </td>	
                		<td><input type="text" id="cidade" name="cidade" value="${user.cidade}" size="47%"  /></td> 
					</tr>
					<tr>
                		<td><label>Estado<span class="required">*</span></label> </td>	
                		<td><input type="text" id="uf" name="uf" value="${user.uf}" size="2"  /></td> 
					</tr>
					<tr>
                		<td><label>IBGE<span class="required">*</span></label> </td>	
                		<td><input type="text" id="ibge" name="ibge" value="${user.ibge}" size="8" /></td> 
					</tr>					
					
					<tr> 
				        <td></td>
				   		<td><input type="submit" value="Salvar"> <input type="submit"  value="Cancelar" onclick="document.getElementById('formUser').action = 'salvarUsuario?acao=reset'"></td>
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
					<th scope="col">Login</th>
					<th scope="col">Nome</th>
					<th scope="col">Telefone</th>
					<th scope="col"></th>
					<th scope="col"></th>
					<th scope="col"></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios}" var="user">
					<tr>
						<td style="width: 50px"><c:out value="${user.id}"></c:out></td>
						<td style="width: 75px"><c:out value="${user.login}"></c:out></td>
						<td style="width: 150px"><c:out value="${user.nome}"></c:out></td>
						<td style="width: 150px"><c:out value="${user.telefone}"></c:out></td>
						<td style="width: 40px"><a
							href="salvarUsuario?acao=delete&id=${user.id}"><img
								src="resources/img/excluir.png" title="Excluir" alt="Excluir"
								width="20px" height="20px" /></a></td>
						<td style="width: 40px"><a
							href="salvarUsuario?acao=editar&id=${user.id}"><img
								src="resources/img/editar.png" title="Alterar" alt="Alterar"
								width="20px" height="20px" /></a></td>
						<td style="width: 40px"><a
							href="salvarTelefones?user=${user.id}"><img
								src="resources/img/telefone.png" title="Telefones" alt="Telefones"
								width="20px" height="20px" /></a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				window.alert('Informe o login');
				return false;
			} else if (document.getElementById("senha").value == '') {
				window.alert('Informe a senha');
				return false;
			} else if (document.getElementById("nome").value == '') {
				window.alert('Informe o nome');
				return false;
			} else if (document.getElementById("telefone").value == '') {
				window.alert('Informe o telefone');
				return false;
			} else if (document.getElementById("email").value == '') {
				window.alert('Informe o email');
				return false;
			}

			return true;
		}
		
		function limpa_formulário_cep() {
            // Limpa valores do formulário de cep.
            $("#rua").val("");
            $("#bairro").val("");
            $("#cidade").val("");
            $("#uf").val("");
            $("#ibge").val("");
        }
		
		function consultaCep(){
			var cep = $("#cep").val().replace(/\D/g, '');
			
						
			//Verifica se campo cep possui valor informado.
            if (cep != "") {
            	
                //Expressão regular para validar o CEP.
                var validacep = /^[0-9]{8}$/;

                //Valida o formato do CEP.
                if(validacep.test(cep)) {

                    //Preenche os campos com "..." enquanto consulta webservice.
                    $("#rua").val("...");
                    $("#bairro").val("...");
                    $("#cidade").val("...");
                    $("#uf").val("...");
                    $("#ibge").val("...");

                    //Consulta o webservice viacep.com.br/
                    $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                        if (!("erro" in dados)) {
                            //Atualiza os campos com os valores da consulta.
                            $("#rua").val(dados.logradouro);
                            $("#bairro").val(dados.bairro);
                            $("#cidade").val(dados.localidade);
                            $("#uf").val(dados.uf);
                            $("#ibge").val(dados.ibge);
                        } //end if.
                        else {
                            //CEP pesquisado não foi encontrado.
                            limpa_formulário_cep();
                            alert("CEP não encontrado.");
                        }
                    });
                } //end if.
                else {
                    //cep é inválido.
                    limpa_formulário_cep();
                    alert("Formato de CEP inválido.");
                }
            } //end if.
            else {
                //cep sem valor, limpa formulário.
                limpa_formulário_cep();
            }			
		}
	</script>

</body>
</html>