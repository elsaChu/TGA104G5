<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<table>
		<tr>
			<th>訂單編號</th>
			<th>總金額</th>
			<th>付款日期</th>
			<th>訂單狀態</th>
			<th>配送狀態</th>
			
		</tr>
		<c:forEach var="order" items="${orderlist}">
			<tr>
				<th>${order.prodOrderNo}</th>
				<th>${order.amountPrice}</th>
				<th>${order.paymentDate}</th>
				<th>${order.prodOrderStatus}</th>
				<th>${order.deliveryStatus}</th>
				
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>