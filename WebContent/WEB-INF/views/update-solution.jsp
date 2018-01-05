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
	
	<h1>${head}</h1>
	
	<form action="details" method="POST">
		Zadanie
		<select name="exerciseId">
			<c:forEach items="${exercises}" var="exercise" >
				<option value="${exercise.id}"
				<c:if test="${exercise.id == solution.exercise_id}">
					selected
				</c:if>
				>${exercise.title}</option>
			</c:forEach>
		</select>
		<br>
		Użytkownik
		<select name="userId">
			<c:forEach items="${users}" var="user" >
				<option value="${user.id}"
				<c:if test="${user.id == solution.user_id}">
					selected
				</c:if>
				>${user.username}</option>
			</c:forEach>
		</select>
		<br>
		Rozwiązanie
		<input type="text" name="sol"><br>
		<input type="submit" value="Zapisz">
		<input type="hidden" name="id" value="${solution.id}">
	</form>

<%@include file="footer.jspf" %>
</body>
</html>