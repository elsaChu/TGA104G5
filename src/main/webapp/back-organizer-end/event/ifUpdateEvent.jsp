<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="${context}/UpdateEventServlet">
		<input type="text" name="eventNumber">
		<input type="hidden" name="action" value="getOne_updateEvent">
		<input type="submit" value="修改">
	</form>
</body>
</html>