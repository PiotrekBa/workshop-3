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
	<h1>Szczegóły rozwiązania zadania</h1>
	<p>
		Tytuł: ${details.getExercise().title} <br> 
		Autor rozwiązania: ${details.getUser().username}<br> 
		Data dodania: ${details.created}<br><br>
		 Rozwiązanie zadania:
	</p>
	<p>${details.getDescription()}</p>
	<%@include file="footer.jspf"%>
</body>
</html>