<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>Gerenciamento de Usuário</h1>
		<h2>
			<a href="new">Adicionar Novo Usuário</a> &nbsp;&nbsp;&nbsp; <a
				href="list">Listar Todos Usuários</a>

		</h2>
	</center>
	<div align="center">
		<c:if test="${usuario != null}">
			<form action="update" method="post">
		</c:if>
		<c:if test="${usuario == null}">
			<form action="insert" method="post">
		</c:if>
		<table border="1" cellpadding="5">
			<caption>
				<h2>
					<c:if test="${usuario != null}">
               Editar Usuário
              </c:if>
					<c:if test="${usuario == null}">
               Adicionar Novo Usuário
              </c:if>
				</h2>
			</caption>
			<c:if test="${usuario != null}">
				<input type="hidden" name="id"
					value="<c:out value='${usuario.id}' />" />
			</c:if>
			<tr>
				<th>Nome*:</th>
				<td><input type="text" name="nome" size="45" 
					value="<c:out value='${usuario.nome}' />" /></td>
			</tr>
			<tr>
				<th>Email:</th>
				<td><input type="email" name="email" size="45"
					value="<c:out value='${usuario.email}' />" /></td>
			</tr>
			<tr>
				<th>Endereço*:</th>
				<td><input type="text" name="endereco" size="15" 
					value="<c:out value='${usuario.endereco}' />" />
				</td>
			</tr>
			<tr>
			
				<th>Data De Nascimento:</th>
				<td><input type="text" name="dataNascimento" size="15" required="required"
					value="<c:out value='${usuario.dataNascimento}' />" />
				</td>
			</tr>

			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Salvar" /></td>
			</tr>
		</table>
		</form>
        <br>
        <div align="center">
        <c:out value="${mensagem}" />
        </div>
        
       <!-- <b>Ops!! ERRO:</b>${mensagemErro} --> 
     
		
	</div>
</body>
</html>