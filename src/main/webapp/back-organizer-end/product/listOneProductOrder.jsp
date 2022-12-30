<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
ProductOrderVO productorderVO = (ProductOrderVO) request.getAttribute("ProductOrderVO");
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
				<h2>查詢商品訂單</h2>
				<h4>
					<a
						href="${context}/back-organizer-end/product/selectProductOrder.jsp">回到查詢商品訂單首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table class="table-2">
		<tr>
			<th>商品訂單編號</th>
			<th>會員編號</th>
			<th>總金額</th>
			<th>商品總數</th>
			<th>付款日期</th>
			<th>收件人姓名</th>
			<th>收件人電話</th>
			<th>收件人電話</th>
			<th>收件地址</th>
			<th>訂單狀態</th>
			<th>配送狀態</th>
		</tr>
		<tr>
			<td class=group1><%=productorderVO.getProdOrderNo()%></td>
			<td class=group1><%=productorderVO.getNumber()%></td>
			<td class=group1><%=productorderVO.getAmountPrice()%></td>
			<td class=group1><%=productorderVO.getProdTotal()%></td>
			<td class=group1><%=productorderVO.getPaymentDate()%></td>
			<td class=group1><%=productorderVO.getReceiverName()%></td>
			<td class=group1><%=productorderVO.getReceiverTel()%></td>
			<td class=group1><%=productorderVO.getShippingAdd()%></td>
			<td class=group1><%=productorderVO.getProdOrderStatus()%></td>
			<td class=group1><%=productorderVO.getDeliveryStatus()%></td>

			<td class=group1>
				<form method="post" action="${context}/ProductServlet">
					<input type="hidden" name="prodOrderNo" value="<%=productorderVO.getProdOrderNo()%>">
				</form>
			</td>
		</tr>
	</table>

</body>
</html>