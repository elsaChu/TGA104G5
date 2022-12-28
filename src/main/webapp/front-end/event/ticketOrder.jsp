<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page import="tw.com.tibame.event.model.*"%>
<%@ page import="java.util.*"%>

<%
OrderService orderSvc = new OrderService();
List<OrderEventVO> list = orderSvc.findByNumber(1);
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta name="description" content="" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport"
		content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->
	
	<!-- Title -->
	<title>TICK IT</title>
	
	<!-- Favicon -->
<%-- 	<link rel="icon" href="${context}/images/logo.ico" /> --%>
	
	<!-- Style CSS -->
	<link rel="stylesheet" href="${context}/front-end/member/css/memberCentre.css" />

</head>

<body>

	<jsp:include page="/main_frame/index_header.jsp"></jsp:include>

	<!-- 空行 -->
	<div class="row">
		<div class="col-12"></div>
	</div>


	<span id="tab-1">帳號設定</span>
	<span id="tab-2">修改密碼</span>
	<span id="tab-3">票券訂單</span>
	<span id="tab-4">商品訂單</span>
	<!-- 頁籤按鈕 -->
	<div id="tab">
		<ul>
			<li><a href="${context}/front-end/member/memberCentre.jsp">帳號設定</a></li>
			<li><a
				href="${context}/front-end/member/memberCentreUpdatePwd.jsp">修改密碼</a></li>
			<li><a class="active" href="${context}/front-end/member/ticketOrder.jsp">票券訂單</a></li>
			<li><a href="${context}/front-end/member/order.html">商品訂單</a></li>
		</ul>

		<!-- 頁籤的內容區塊 -->

		<div class="tab-content-3">
			<h4>個人帳戶清單</h4>
			<a>訂單編號：</a>${orderEventVO.orderID}
			
			<div class="row">
				<div class="col-md-4">圖片</div>
				<div class="col-md-4">活動名稱 / 描述</div>
				<div class="col-md-4">button區塊</div>
			</div>
			<hr>
				<c:forEach var="orderEventVO" items="${list}">
					<div class="row">
							<div class="col-md-4">圖片</div>
							<div class="col-md-4">
								<p>活動名稱：<br>
								${orderEventVO.eventName}</p>
								<P>開始時間：<br>
								${orderEventVO.eventStartDate}</P>
								<p>活動地點：<br>
								${orderEventVO.eventPlace}</p>
								<p>主辦單位：<br>
								${orderEventVO.organizerName}</p>
								<p>訂單狀態：<br>
								${orderEventVO.orderType}</p>
								<p>票券數量：<br>
								${orderEventVO.totalTicket}</p>
								<p>總金額：<br>
								${orderEventVO.total}</p>
							</div>
							<div class="col-md-4">
								<form action="OrderServlet" method="POST">
								<div class="submit">
								<br>
									<input type="submit" value="修改訂單資訊" />
									<br>
									<br> 
									<input type="submit" value="取消訂單">
								</div>
								</form>
							</div>
					</div>
					<hr>
				</c:forEach>
		</div>
	</div>



	<!-- ##### Footer 開始 ##### -->
	<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
	<!-- ##### Footer 結束 ##### -->
</body>
</html>
