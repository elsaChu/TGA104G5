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
		<h2>修改商品資料</h2><br>
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
						<td>商品編號:</td>
						<td><input type="text" name="ProdNo" class="form-control"
							value="<%=prodVo.getProdNo()%>" readonly /></td>
					</tr>
					<tr>
						<td>活動編號:</td>
						<td><input type="text" name="eventNumber"
							class="form-control" value="<%=prodVo.getEventNumber()%>"></td>
					</tr>
<!-- 					<tr> -->
<!-- 						<td>廠商編號:</td> -->
<!-- 						<td><input type="text" name="organizerNumber" -->
<%-- 							class="form-control" value="<%=prodVo.getOrganizerNumber()%>" --%>
<!-- 							readonly></td> -->
<!-- 					</tr> -->
					<tr>
						<td>商品名稱:</td>
						<td><input type="text" name="prodName" class="form-control"
							value="<%=prodVo.getProdName()%>"></td>
					</tr>
					<tr>
						<td>商品規格:</td>
						<td><input type="text" name="prodSpec" class="form-control"
							value="<%=prodVo.getProdSpec()%>"></td>
					</tr>
					<tr>
						<td>商品單價:</td>
						<td><input type="number" name="unitPrice"
							class="form-control" value="<%=prodVo.getUnitPrice()%>"></td>
					</tr>
					<tr>
						<td>庫存數量:</td>
						<td><input type="number" name="prodStock"
							class="form-control" value="<%=prodVo.getProdStock()%>"></td>
					</tr>
					<tr>
						<td>商品詳情:</td>
						<td><textarea rows="6" cols="20" name="prodDetails"
								class="form-control"><%=prodVo.getProdDetails()%></textarea></td>
					</tr>
					<tr>
						<td>商品是否上架：</td>
						<td><input type="radio" name="isPOn" value="true" checked>是
							<input type="radio" name="isPOn" value="false">否</td>

						<!-- 						<td>商品是否上架:</td> -->
						<!-- 						<td><input type="radio" name="isPOn" -->
						<%-- 							value="<%=prodVo.getIsPOn()%>"> --%>
						<!-- 							是 <input type="radio" -->
						<%-- 							name="isPOn" value="<%=prodVo.getIsPOn()%>"> --%>
						<!-- 							否</td> -->
					</tr>
				</table>
			</div>
			<div class="div_img col-md-5">
				<div>
					商品圖片：
					<ul class="picture_list" id="ul_id">
						<li><img src="" /></li>
					</ul>

					<input type="file" multiple class="prodIMG" accept="image/*"
						name="prodIMG">
				</div>

				<div class="myButton">
					<input type="submit" value="確認修改">
				</div>

				<input type="hidden" name="action" value="update" />
			</div>
		</form>
	</div>
	<script>
		var prodimglist = '${prodimglist}';
	</script>
	<script src="${context}/back-organizer-end/product/js/updateProduct.js"></script>
	<%-- 	<script src="${context}/mainframe/js/bootstrap.js"></script> --%>
	<%-- 	<script src="${context}/mainframe/js/jquery-1.10.2.js"></script> --%>
</body>
</html>