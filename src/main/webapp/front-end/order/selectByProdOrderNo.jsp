<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>單筆訂單查詢</title>
</head>
<body>
	<table>
		<tr>
			<th>項目編號</th>
			<th>商品編號</th>
			<th>購買數量</th>
			<th>小計金額</th>
			<th>評價</th>
			<th>評價內容</th>
			<th>評論日期</th>
			<th>退貨原因</th>
			<th>退款狀態</th>
			<th>退款申請日期</th>
			<th>退款完成日期</th>
			
		</tr>
		<c:forEach var="detail" items="${detaillist}">
			<tr>
				<th>${detail.itemNo}</th>
				<th>${detail.prodNo}</th>
				<th>${detail.prodQty}</th>
				<th>${detail.subtotal}</th>
				<th>${detail.commentRanking}</th>
				<th>${detail.commentContent}</th>
				<th>${detail.commentDate}</th>
				<th>${detail.returnReason}</th>
				<th>${detail.refundStatus}</th>
				<th>${detail.refundSDate}</th>
				<th>${detail.refundEDate}</th>
			</tr>
		</c:forEach>
		
	</table>
	
</body>
</html>