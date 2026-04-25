<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div align="center">
    <h1>Gerenciamento de Editoras</h1>
    <h2>
        <%-- O contextPath correto é buscado do pageContext --%>
        <a href="${pageContext.request.contextPath}/">Menu Principal</a>
        &nbsp;&nbsp;&nbsp;
        <a href="${pageContext.request.contextPath}/editoras/cadastro">Adicionar Nova Editora</a>
    </h2>
</div>

<div align="center">
    <table border="1">
        <caption>Lista de Editoras</caption>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>CNPJ</th>
            <th>Ações</th>
        </tr>


        <c:forEach var="editora" items="${listaEditoras}">
            <tr>
                <td>${editora.id}</td>
                <td>${editora.nome}</td>
                <td>${editora.cnpj}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/editoras/edicao?id=${editora.id}">Edição</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="${pageContext.request.contextPath}/editoras/remocao?id=${editora.id}"
                       onclick="return confirm('Tem certeza que deseja excluir?');">
                        Remoção
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>