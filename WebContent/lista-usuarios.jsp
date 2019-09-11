<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.2/css/all.css">
<title>gerenciamento de usuários</title>
</head>
<body>
  <center>
  <h1>Bem Vindo ao Gerenciador de Usuário</h1>
        <h3>
         <a href="new">Adicionar novo Usuário</a>
         &nbsp;&nbsp;&nbsp;
         <a href="list">Lista De Todos Usuários</a>
         
        </h3>
 </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>Lista De Usuários</h2></caption>
            <tr>
                <th>ID</th>
                <th>NOME</th>
                <th>EMAIL</th>
                <th>ENDERECO</th>
                <th>DATA DE NASCIMENTO</th>
                <th>AÇÃO</th>
            </tr>
            <c:forEach var="usuario" items="${listarUsuarios}">
                <tr>
                    <td><c:out value="${usuario.id}" /></td>
                    <td><c:out value="${usuario.nome}" /></td>
                    <td><c:out value="${usuario.email}" /></td>
                    <td><c:out value="${usuario.endereco}" /></td>
                    <td><c:out value="${usuario.dataNascimento}"/></td>
                    <td>
                     <a href="edit?id=<c:out value='${usuario.id}' />"><i class="fas fa-user-edit"></i></a> <!-- Editar Usuario -->
                     &nbsp;&nbsp;&nbsp;&nbsp;
                     <a href="delete?id=<c:out value='${usuario.id}' />"><i class="fas fa-user-times"></i></i></a> <!-- Excluir Usuario -->                    
                    </td>                                                <!-- <i class="fas fa-trash"> -->
                </tr>
            </c:forEach>
        </table>
    </div> 
</body>
</html>