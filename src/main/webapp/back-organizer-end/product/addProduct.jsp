<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}"/>
<%
ProductVO prodvo = (ProductVO) request.getAttribute("prodvo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
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

	<form method="post" id="formName"
		action="<%=request.getContextPath()%>/AddProductServlet" name=""
		enctype="multipart/form-data">
		<span>活動編號：</span> <input type="text" name="eventNumber"><br>
		<span>商品名稱：</span> <input type="text" name="prodName"
			value="<%=(prodvo == null) ? "" : prodvo.getProdName()%>"><br>
		<span>商品圖片：</span> <input type="file" id="prodIMG" name="prodIMG"
			class="prodIMG">
		<ul class="picture_list"></ul>
		<span>商品規格：</span> <input type="text" name="prodSpec"
			value="<%=(prodvo == null) ? "" : prodvo.getProdSpec()%>"><br>
		<span>商品單價：</span> <input type="number" name="unitPrice"
			value="<%=(prodvo == null) ? "" : prodvo.getUnitPrice()%>">元<br>
		<span>庫存數量：</span> <input type="number" name="prodStock"
			value="<%=(prodvo == null) ? "" : prodvo.getProdStock()%>"><br>
		<span>商品詳情：</span>
		<textarea rows="6" cols="15" name="prodDetails"
			value="<%=(prodvo == null) ? "" : prodvo.getProdDetails()%>"></textarea>
		<br> <span>商品是否上架：</span> <input type="radio" name="isPOn"
			value="1" checked>是 <input type="radio" name="isPOn"
			value="0">否<br>
		<button type="submit">新增商品</button>
	</form>

	<script
		src="<%=request.getContextPath()%>/back-organizer-end/product/js/addProduct.js"></script>
</body>
</html>