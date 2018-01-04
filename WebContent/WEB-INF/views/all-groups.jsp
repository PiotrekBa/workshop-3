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
	<h1>Grupy użytkowników</h1>

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
					<td><a href="group-users?id=${groups.id}">Użytkownicy</a></td>
				</tr>
					
			</c:forEach>
		
		</tbody>
			
	
	</table>
<%@include file="footer.jspf" %>
</body>
</html>