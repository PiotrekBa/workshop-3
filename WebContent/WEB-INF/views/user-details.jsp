<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Szkoła programowania</title>
</head>
<body>
	<%@include file="header.jspf" %>
	<h1>Szczegóły użytkownika</h1>
	<p>
		Nazwa użytkownika: ${user.username}<br> 
		Email: ${user.email}<br> 
		Grupa: ${user.getUserGroup().name}
	</p>
	<a href="details?editId=0">Dodaj rozwiązanie</a>
	<table border="1">
		<thead>
			<tr>
				<td>Tytuł</td>				
				<td>Data dodania</td>
				<td>Akcja</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${solutions}" var="solution">
				<tr>
					<td>${solution.getExercise().title}</td>
					<td>${solution.created}</td>
					<td>
						<a href="details?id=${solution.id}">Szczegóły</a>
						<a href="details?editId=${solution.id}">Edycja</a>
						<a href="details?delId=${solution.id}">Usuń</a>
					</td>
				</tr>
					
			</c:forEach>
		
		</tbody>
			
	
	</table>
<%@include file="footer.jspf" %>
</body>
</html>