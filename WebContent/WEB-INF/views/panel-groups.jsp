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
	
	<a href="panel-groups?editId=0">Dodaj grupę</a>
	<table border="1">
		<thead>
			<tr>
				<td>Nazwa grupy</td>
				<td>Akcja</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${allGroups}" var="groups">
				<tr>
					<td>${groups.name}</td>
					<td><a href="panel-groups?delId=${groups.id}">Usuń</a> 
					<a href="panel-groups?editId=${groups.id}">Edycja</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<%@include file="footer.jspf"%>
</body>
</html>