<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Szkoła programowania</title>
</head>
<body>
	<%@include file="header.jspf"%>
	<h1>Aministrator - Grupy użytkowników</h1>
	
	<a href="panel-exercises?editId=0">Dodaj zadanie</a>
	<table border="1">
		<thead>
			<tr>
				<td>Nazwa zadania</td>
				<td>Opis</td>
				<td>Akcja</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${exercises}" var="exercise">
				<tr>
					<td>${exercise.title}</td>
					<td>${exercise.description}</td>
					<td><a href="panel-exercises?delId=${exercise.id}">Usuń</a> 
					<a href="panel-exercises?editId=${exercise.id}">Edycja</a>
					<a href="panel-exercises?showId=${exercise.id}">Podgląd</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%@include file="footer.jspf"%>
</body>
</html>