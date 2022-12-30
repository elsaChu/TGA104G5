<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
ProductOrderService prodOrderSvc = new ProductOrderService();
List<ProductOrderVO> list = prodOrderSvc.getAll();
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
</style>

</head>
<body>
	<table class="table-1">
		<tr>
			<td>
				<h2>全部商品訂單資料</h2>
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
		<c:forEach var="productorderVO" items="${list}">
			<tr class="productorderlist">
				<td class=group1>${productorderVO.prodOrderNo}</td>
				<td class=group1>${productorderVO.number}</td>
				<td class=group1>${productorderVO.amountPrice}</td>
				<td class=group1>${productorderVO.prodTotal}</td>
				<td class=group1>${productorderVO.paymentDate}</td>
				<td class=group1>${productorderVO.receiverName}</td>
				<td class=group1>${productorderVO.receiverTel}</td>
				<td class=group1>${productorderVO.shippingAdd}</td>
				<td class=group1>${productorderVO.prodOrderStatus}</td>
				<td class=group1>${productorderVO.deliveryStatus}</td>

				<td class=group1>
					<form method="post" action="${context}/ProductOrderServlet">
						<input type="hidden" name="prodOrderNo" value="${productorderVO.prodOrderNo}">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>