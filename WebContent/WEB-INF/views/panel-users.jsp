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
	<h1>Aministrator - Użytkownicy</h1>
	
	<a href="panel-users?editId=0">Dodaj użytkownika</a>
	<table border="1">
		<thead>
			<tr>
				<td>Nazwa użytkownika</td>
				<td>Akcja</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
				<tr>
					<td>${user.username}</td>
					<td><a href="panel-users?delId=${user.id}">Usuń</a> 
					<a href="panel-users?editId=${user.id}">Edycja</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%@include file="footer.jspf"%>
</body>
</html>