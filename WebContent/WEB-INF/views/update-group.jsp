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
	
	<form action="panel-groups" method="post">
		<input type="text" name="name" value="${group.name}">
		<input type="submit" value="Zapisz">
		<input type="hidden" name="id" value="${group.id}">
	</form>

<%@include file="footer.jspf" %>
</body>
</html>