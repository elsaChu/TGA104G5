<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="tw.com.tibame.staff.model.*"
%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
System.out.println(request.getAttribute("staffVO"));
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
</head>
<link rel="stylesheet" type="text/css"
	href="${context}/main_frame/css/button.css" />
	<link rel="stylesheet" type="text/css"
	href="${context}/back-organizer-end/staff/css/insertStaff.css" />改
<body>

<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>

<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<form method="post" action="<%=request.getContextPath()%>/StaffServlet" name="action" value="insert">

<input type= "hidden" name="action" value="insert"></input>
	<div class="my_size">
		<span>員工姓名：</span><input type="text" name="staffName"></input><br>
		<span>員工帳號：</span><input type="text" name="staffAccount"></input><br>
		<span>員工密碼：</span><input type="text" name="staffPassword"></input><br>
		<span>管理權限：</span><br>
		<span>會員管理</span><input type="checkbox" name="permissionNumber" value="1"></input><br>
		<span>員工管理</span><input type="checkbox" name="permissionNumber" value="2"></input><br>
		<span>活動管理</span><input type="checkbox" name="permissionNumber" value="3"></input><br>
		<span>網站管理</span><input type="checkbox" name="permissionNumber" value="4"></input><br>
		<span>廠商管理</span><input type="checkbox" name="permissionNumber" value="5"></input><br>
		<span>商品管理</span><input type="checkbox" name="permissionNumber" value="6"></input><br>
		<button type="submit">新增員工</button><br>
	</div>
		</form>
</body>
</html>