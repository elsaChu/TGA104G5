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
	margin: 0px auto 10px auto;
}

th, td {
	padding: 5px;
}

td.prodDetails {
	overflow: hidden;
	text-overflow: ellipsis;
}

th {
	text-align: center;
	background-color: #F0F0F0;
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
			<!-- 			<th>商品詳情</th> -->
			<!-- 			<th>商品總評價</th> -->
			<th>商品是否上架</th>
			<th>修改</th>
		</tr>
		<tr>
			<td class=group1><%=prodVo.getProdNo()%></td>
			<td class=group1><%=prodVo.getEventNumber()%></td>
			<td class=group1><%=prodVo.getOrganizerNumber()%></td>
			<td class=prodName><%=prodVo.getProdName()%></td>
			<td class=group1><%=prodVo.getProdSpec()%></td>
			<td class=group1><%=prodVo.getUnitPrice()%></td>
			<td class=group1><%=prodVo.getProdStock()%></td>
			<%-- 			<td class=prodDetails><%=prodVo.getProdDetails()%></td> --%>
			<%-- 			<td class=group1><%=prodVo.getProdScore()%></td> --%>
			<c:if test="${ProductVO.isPOn==true}">
				<td class=group1>已上架</td>
			</c:if>
			<c:if test="${ProductVO.isPOn==false}">
				<td class=group1>未上架</td>
			</c:if>
			<td class=group1>
				<form method="post" action="${context}/ProductServlet">
					<input type="hidden" name="prodNo" value="<%=prodVo.getProdNo()%>">
					<input type="hidden" name="action" value="getOne_For_Update">
					<input type="submit" value="修改">
				</form>
			</td>
		</tr>
	</table>
</body>
</html>