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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>查詢商品</title>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div style="font-weight: bold;">查詢商品</div>

		<ul>
		<li><a href="listAllProduct.jsp">顯示所有商品</a><br></li>

		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>輸入商品編號：</b> <input type="text" name="prodNo" value="" size="20">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>

		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>選擇商品編號：</b> <select name="prodNo" width="185" style="width: 185px">
					<c:forEach var="productVO" items="${list}">
						<option value="${productVO.prodNo}">${productVO.prodNo}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
		
		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>輸入商品名稱：</b> <input type="text" name="prodName" value="" size="20">
				<input type="hidden" name="action" value="getOneProductName_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
		
		<li>
			<form method="post" id="formName"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<b>選擇商品名稱：</b> <select name="prodName" width="185" style="width: 185px">
					<c:forEach var="productVO" items="${list}">
						<option value="${productVO.prodName}">${productVO.prodName}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOneProductName_For_Display">
				<input type="submit" value="送出">
			</form>
		</li>
	</ul>
	
	<script
		src="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp"></script>
</body>
</html>