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
	
	<form action="panel-exercises" method="post">
		Tytył
		<input type="text" name="title" value="${exercise.title}"><br>
		Opis
		<input type="text" name="description" value="${exercise.description}"><br>
		<input type="submit" value="Zapisz">
		<input type="hidden" name="id" value="${exercise.id}">
	</form>

<%@include file="footer.jspf" %>
</body>
</html>