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
	<h1>Strona główna</h1>

	<table border="1">
		<thead>
			<tr>
				<td>Tytuł</td>
				<td>Autor rozwiązania</td>					
				<td>Data dodania</td>
				<td>Akcja</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${solutions}" var="solution">
				<tr>
					<td>${solution.getExercise().title}</td>
					<td>${solution.getUser().username}</td>
					<td>${solution.created}</td>
					<td><a href="details?id=${solution.id}">Szczegóły</a></td>
				</tr>
					
			</c:forEach>
		
		</tbody>
			
	
	</table>
<%@include file="footer.jspf" %>
</body>
</html>