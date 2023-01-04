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
	href="${context}/back-organizer-end/product/css/addProduct.css" />

</head>
<body>
	<div class="my_size">
		<h2>新增商品</h2><br>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<form method="post" action="${context}/ProductServlet"
			enctype="multipart/form-data">
			<div class="div_text col-md-5">
				<table>
					<tr>
						<td>活動編號：</td>
						<td><input type="text" name="eventNumber"
							class="form-control"
							value="<%=(prodVo == null) ? "" : prodVo.getEventNumber()%>"></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>廠商編號：</td> -->
<!-- 						<td><input type="text" name="organizerNumber" -->
<!-- 							class="form-control" -->
<%-- 							value="<%=(prodVo == null) ? "" : prodVo.getOrganizerNumber()%>"></td> --%>
<!-- 					</tr> -->
					<tr>
						<td>商品名稱：</td>
						<td><input type="text" name="prodName" class="form-control"
							value="<%=(prodVo == null) ? "" : prodVo.getProdName()%>"></td>
					</tr>
					<tr>
						<td>商品規格：</td>
						<td><input type="text" name="prodSpec" class="form-control"
							value="<%=(prodVo == null) ? "" : prodVo.getProdSpec()%>"></td>
					</tr>
					<tr>
						<td>商品單價：</td>
						<td><input type="number" name="unitPrice"
							class="form-control"
							value="<%=(prodVo == null) ? "" : prodVo.getUnitPrice()%>"></td>
					</tr>
					<tr>
						<td>庫存數量：</td>
						<td><input type="number" name="prodStock"
							class="form-control"
							value="<%=(prodVo == null) ? "" : prodVo.getProdStock()%>"></td>
					</tr>
					<tr>
						<td>商品詳情：</td>
						<td><textarea rows="6" cols="20" name="prodDetails"
								class="form-control">${setProdDetails}</textarea></td>
					</tr>
					<tr>
						<td>商品是否上架：</td>
						<td><input type="radio" name="isPOn" value="true" checked>是
							<input type="radio" name="isPOn" value="false">否</td>
					</tr>
				</table>
			</div>
			<div class="div_img col-md-5">
				<span>商品圖片：</span>
				<ul class="picture_list">
					<li><img
						src="${context}/back-organizer-end/product/img/Product_Image_Default.jpg"
						class="preview"></li>
				</ul>
				<input type="file" id="prodIMG" name="prodIMG" class="prodIMG" multiple
					accept="image/*">
				<div class="myButton">
					<input type="submit" value="新增商品">
				</div>
				<input type="hidden" name="action" value="insert" />
			</div>
		</form>
	</div>

	<script src="${context}/back-organizer-end/product/js/addProduct.js"></script>
	<%-- 	<script src="${context}/mainframe/js/bootstrap.js"></script> --%>
	<%-- 	<script src="${context}/mainframe/js/jquery-1.10.2.js"></script> --%>

</body>
</html>