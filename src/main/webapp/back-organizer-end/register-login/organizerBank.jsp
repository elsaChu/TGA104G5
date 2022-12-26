<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.organizer.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<% OrganizerVO organizerVO = (OrganizerVO) request.getAttribute("organizerVO");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT 廠商後台</title>
</head>
<body>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

</body>
</html>