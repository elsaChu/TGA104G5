<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>
<%@ page import="java.util.*"%>

<%
ProductService prodSvc = new ProductService();
List<ProductVO> list = prodSvc.getAll();
pageContext.setAttribute("list", list);
%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
<style>
.error {
	color: red;
}

.inputbox {
	height: 23px;
	width: 200px;
	margin:8px auto;
}

select {
	text-align-last: center;
}
</style>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font class="error">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li class="error">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<h2>查詢商品</h2>
	<ul>
		<li><a href="listAllProduct.jsp">顯示所有商品</a></li>
		<li>
			<form method="post" action="${context}/ProductServlet">
				輸入商品編號： <input type="text" name="prodNo" value="" class="inputbox">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="查詢">
			</form>
		</li>

		<li>
			<form method="post" action="${context}/ProductServlet">
				選擇商品編號： <select name="prodNo" class="inputbox">
					<option selected="selected">請選擇</option>
					<c:forEach var="productVO" items="${list}">
						<option value="${productVO.prodNo}">${productVO.prodNo}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="查詢">
			</form>
		</li>

		<li>
			<form method="post" action="${context}/ProductServlet">
				輸入商品名稱： <input type="text" name="prodName" value="" class="inputbox">
				<input type="hidden" name="action"
					value="getOneProductName_For_Display"> <input type="submit"
					value="查詢">
			</form>
		</li>

		<li>
			<form method="post" action="${context}/ProductServlet">
				選擇商品名稱： <select name="prodName" class="inputbox">
					<option selected="selected">請選擇</option>
					<c:forEach var="productVO" items="${list}">
						<option value="${productVO.prodName}">${productVO.prodName}
					</c:forEach>
				</select> <input type="hidden" name="action"
					value="getOneProductName_For_Display"> <input type="submit"
					value="查詢">
			</form>
		</li>
	</ul>
</body>
</html>