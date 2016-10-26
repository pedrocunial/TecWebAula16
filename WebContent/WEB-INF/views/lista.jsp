<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<title>Lista de Tarefas</title>
</head>
<body>
<style>
.header {
	background: black;
	color: white;
}

th {
	padding: 1em;
}
</style>
<table>
	<tr class="header">
		<th>Id</th>
		<th>Descrição</th>
		<th>Finalizado</th>
		<th>Data de Finalização</th>
		<th></th>
		<th></th>
	</tr>
	<c:forEach items="${ tarefas }" var="tarefa">
		<tr style="background:${ tarefa.id % 2 == 0 ? 'yellow' : 'red' }">
			<td>${ tarefa.id }</td>
			<td>${ tarefa.descricao }</td>
			<c:if test="${ tarefa.finalizado eq false }">
				<td>Não finalizada</td>
			</c:if>
			<c:if test="${ tarefa.finalizado eq true }">
				<td>Finalizado</td>
			</c:if>
			<td>
				<fmt:formatDate 
					value="${ tarefa.dataFinalizacao.time }"
					pattern="dd/MM/yyyy"/>
			</td>
			<td style="background: white">
				<a class="btn btn-warning"
					href="mostraTarefa?id=${ tarefa.id }"
					role="button">
					Alterar
				</a>
			</td>
			<td style="background: white">
				<a class="btn btn-danger" 
					href="removeTarefa?id=${ tarefa.id }"
					role="button">
					Remover
				</a>
			</td>
		</tr>
	</c:forEach>
</table>


<a href="criaTarefa" class="btn btn-primary" role="button">Nova Tarefa</a>

</body>
</html>