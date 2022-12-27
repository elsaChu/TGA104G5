<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
ProductVO prodVo = (ProductVO) request.getAttribute("ProductVO");
%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
<%-- <link rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.css" /> --%>
<link rel="stylesheet" type="text/css"
	href="${context}/main_frame/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="${context}/back-staff-end/staff/css/insertStaff.css" />

</head>
<body>
<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
	<div class="my_size">
		<h2>新增員工</h2><br>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<form method="post" action="<%=request.getContextPath()%>/StaffServlet" name="action" value="insert">
		<input type= "hidden" name="action" value="insert"></input>
		
			<div class="div_text col-md-5">
				<table>
					<tr>
						<td>員工姓名：</td>
						<td>
						<input type="text" name="staffName" class="form-control"></input>
						</td>
					</tr>
					<tr>
						<td>員工帳號：</td>
						<td>
						<input type="text" name="staffAccount" class="form-control"></input>
					</tr>
					<tr>
						<td>員工密碼：</td>
						<td>
						<input type="text" name="staffPassword" class="form-control"></input>
						</td>
					</tr>
				</table>
				<button type="submit">新增員工</button>
			</div>
<!-- 			<div class="div_img col-md-5"> -->
<!-- 				<span>管理權限：</span><br> -->
<!-- 		<span>會員管理</span><input type="checkbox" name="permissionNumber" value="1"></input><br> -->
<!-- 		<span>員工管理</span><input type="checkbox" name="permissionNumber" value="2"></input><br> -->
<!-- 		<span>活動管理</span><input type="checkbox" name="permissionNumber" value="3"></input><br> -->
<!-- 		<span>網站管理</span><input type="checkbox" name="permissionNumber" value="4"></input><br> -->
<!-- 		<span>廠商管理</span><input type="checkbox" name="permissionNumber" value="5"></input><br> -->
<!-- 		<span>商品管理</span><input type="checkbox" name="permissionNumber" value="6"></input><br> -->
<!-- 				<div class="myButton"> -->
<!-- 				</div> -->
<!-- 			</div> -->
		</form>
	</div>

	<script src="${context}/back-organizer-end/product/js/addProduct.js"></script>
	<%-- 	<script src="${context}/mainframe/js/bootstrap.js"></script> --%>
	<%-- 	<script src="${context}/mainframe/js/jquery-1.10.2.js"></script> --%>

</body>
</html>