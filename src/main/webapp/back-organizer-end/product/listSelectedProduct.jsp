<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

<style>
.table-1 {
	width: 95%;
	background-color: #415A77;
	color: white;
	text-align: center;
	margin: 10px auto 0px auto;
}

a {
	display: block;
	text-align: right;
	color: white;
	margin: 0px 10px 0px 0px;
}

.table-2 {
	width: 95%;
	background-color: white;
	margin-bottom: 20px;
	margin: 0px auto 10px auto;
}

th, td {
	padding: 5px;
}

.table-2 tr:nth-child(odd){
background-color: #F0F0F0};

td.prodDetails {
	overflow: hidden;
	text-overflow: ellipsis;
}

th {
	text-align: center;
white-space: nowrap;
}


td.group1 {
	text-align: center;
	white-space: nowrap;
}

td.prodName {
	white-space: normal;
}
</style>

</head>
<body>
	<table class="table-1">
		<tr>
			<td>
				<h2>查詢商品</h2>
				<h4>
					<a href="${context}/back-organizer-end/product/selectProduct.jsp">回到查詢商品首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table class="table-2">
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
						<input type="hidden" name="prodNo" value="${productVO.prodNo}">
						<input type="hidden" name="action" value="getOne_For_Update">
						<input type="submit" value="修改">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>