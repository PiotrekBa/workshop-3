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
	
	<form action="panel-users" method="post">
		Nazwa
		<input type="text" name="username" value="${user.username}"><br>
		Email
		<input type="text" name="email" value="${user.email}"><br>
		Hasło (puste - bez zmian)
		<input type="text" name="password"><br>
		Grupa
		<select name="groupId">
			<c:forEach items="${groups}" var="group" >
				<option value="${group.id}"
				<c:if test="${group.id == user.user_group_id}">
					selected
				</c:if>
				>${group.name}</option>
			
			</c:forEach>
		</select>
		<br>
		<input type="submit" value="Zapisz">
		<input type="hidden" name="id" value="${user.id}">
	</form>

<%@include file="footer.jspf" %>
</body>
</html>