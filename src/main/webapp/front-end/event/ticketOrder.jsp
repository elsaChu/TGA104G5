<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page import="tw.com.tibame.event.model.*"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<%@ page import="java.util.*"%>

<%
OrderService orderSvc = new OrderService();
MemberVO membervo = (MemberVO)session.getAttribute("memberVO");
List<OrderEventVO> list = null;
if(membervo != null){
	list = orderSvc.findByNumber(membervo.getNumber());
}
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
	<jsp:include page="/main_frame/index_header.jsp"></jsp:include>
	<link rel="stylesheet" href="${context}/front-end/member/css/memberCentre.css" />
	<link rel="stylesheet" href="${context}/front-end/event/css/ticketOrder.css" />

</head>

<body>

	

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
			<li><a href="${context}/front-end/member/memberCentreUpdatePwd.jsp">修改密碼</a></li>
			<li><a class="active" href="${context}/front-end/member/ticketOrder.jsp">票券訂單</a></li>
			<li><a href="${context}/front-end/member/order.html">商品訂單</a></li>
		</ul>

		<!-- 頁籤的內容區塊 -->

		<div class="tab-content-3">
			<br>
			<h4>個人帳戶清單</h4>
			
			<div class="row">
				<div class="col-md-4">訂單編號</div>
				<div class="col-md-6">活動名稱 / 描述</div>
				<div class="col-md-2"></div>
			</div>
			<hr>
				<c:forEach var="orderEventVO" items="${list}">
					<div class="row">
							<div class="col-md-4" style="display: inline-block;">
								<p>#${orderEventVO.orderID}</p>
								<img src="${orderEventVO.bigImg64}" class="img_size" />
							</div>
							<div class="col-md-6 div_font_size">
								<div style="margin-bottom:20px;">
									<a class="event_a" href="${context}/EventDetails?eventNumber=${orderEventVO.eventNumber}">${orderEventVO.eventName}</a>
								</div>
								<P>開始時間： ${orderEventVO.eventStartDate}</P>
								<p>活動地點： ${orderEventVO.eventPlace}</p>
								<p>主辦單位： ${orderEventVO.organizerName}</p>
								<p>訂單狀態： ${orderEventVO.orderType}</p>
								<p>票券數量： ${orderEventVO.totalTicket}</p>
								<p>總金額： ${orderEventVO.total}</p>
							</div>
							<div class="col-md-2">
							<c:if test="${orderEventVO.orderType != '未繳費'}">
								<form action="OrderServlet" method="POST">
								<div class="ticketOrdBtn">
									<a href="${context}/FrontendEventOrderProcessServlet?return=${orderEventVO.orderID}_tickit">
										<input type="button" value="訂單資訊" />
									</a>
								</div>
								</form>
							</c:if>
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
