<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%-- <% ProductService prodSvc = new ProductService(); --%>
// List<ProductVO> list = prodSvc.findByProductName(String pdname);
<%-- List<ProductVO> list = (List<ProductVO>)request.getAttribute("ProductVO"); %> --%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

<style>
h3 {
        font-weight: bold;
      }

table#table-1 {
	width: 100%;
	background-color: #C3DFD4;
	border: 1.5px solid #8E8E8E;
	text-align: center;
}

table#table-2 {
	width: 100%;
	background-color: white;
	margin-bottom: 20px;
}


th, td {
	padding: 5px;
	text-align: center;
	border: 1px solid #8E8E8E;
}

td.prodDetails {
	text-align: left;
	white-space: normal;
	
}

th, td.group1{
	white-space: nowrap;
}

td.prodName{
	white-space: normal;
}

</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h2>查詢商品</h2>
				<h4>
					<a
						href="${context}/back-organizer-end/product/selectProduct.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table id="table-2">
		<tr>
			<th>商品編號</th>
			<th>活動編號</th>
			<th>廠商編號</th>
			<th>商品名稱</th>
			<th>商品規格</th>
			<th>商品單價</th>
			<th>庫存數量</th>
			<th>商品詳情</th>
			<th>商品總評價</th>
			<th>商品是否上架</th>
			<th>修改</th>
		</tr>
		<c:forEach var="productVO" items="${ProductVO}">
			<tr>
				<td class=group1>${productVO.prodNo}</td>
				<td class=group1>${productVO.eventNumber}</td>
				<td class=group1>${productVO.organizerNumber}</td>
				<td class=prodName>${productVO.prodName}</td>
				<td class=group1>${productVO.prodSpec}</td>
				<td class=group1>${productVO.unitPrice}</td>
				<td class=group1>${productVO.prodStock}</td>
				<td class=prodDetails>${productVO.prodDetails}</td>
				<td class=group1>${productVO.prodScore}</td>
				<td class=group1>${productVO.isPOn}</td>

				<td>
					<form method="post" action="${context}/ProductServlet">
						<input type="submit" value="修改">
						<input type="hidden" name="prodName" value="${productVO.prodName}">
						<input type="hidden" name="action" value="getOneProductName_For_Display">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>