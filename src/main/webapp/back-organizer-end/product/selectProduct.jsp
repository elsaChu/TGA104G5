<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
	ProductVO prodVo = (ProductVO) request.getAttribute("prodVo");
%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢商品</title>
</head>
<body bgcolor='white'>

	<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<ul>
		<li><a href='selectProduct.jsp'>List</a> all products. <br></li>

		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>輸入商品編號：</b> <input type="text" name="prodNo"> <input
					type="hidden" name="action" value="getOne_For_Display"> 
					<input type="submit" value="送出">
			</form>
		</li>

		<jsp:useBean id="prodSvc" scope="page"
			class="tw.com.tibame.product.model.ProductService" />

		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>選擇商品編號：</b> <select size="1" name="prodNo">
					<c:forEach var="prodVo" items="${prodSvc.all}">
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>

		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>選擇商品名稱：</b> <select size="1" name="prodNo">
					<c:forEach var="prodVo" items="${prodSvc.all}">
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</FORM>
		</li>
	</ul>

	<script
		src="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp"></script>
</body>
</html>