<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>Adicionar Tarefas</h3>
	<form action="adicionaTarefa" method="post">
		Descricao: </br>
		<textarea name="descricao" rows="6" cols="80"></textarea><br>
<!--  	Finalizado<input type="radio" name="finalizado" value="true" checked><br>
		Pendente<input type="radio" name="finalizado" value="false"><br>
		<br>
		Data Finalização<br>
		<fmt:formatDate var="dateString" pattern="dd/MM/yyyy" />
		<input value="${dateString}" name="dataFinalizacao"/> -->
		<form:errors path="tarefa.descricao" cssStyle="color:red"/><br>
		<input type="submit" value="Adicionar">
	</form>
</body>
</html>