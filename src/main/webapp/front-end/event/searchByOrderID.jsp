<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

<link rel="stylesheet" href="${context}/back-organizer-end/event/css/listOneOrganizerEvent.css">
</head>

<body>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
	<div>
		<h3>活動訂單</h3>
		<br>
		<a href="${context}/front-end/event/listOneOrganizerEvent.jsp"><iconify-icon icon="heroicons:arrow-uturn-left-20-solid"></iconify-icon>返回列表</a>
	</div>
	
<!-- 	search -->
		<div class="row"> 
			<div class="row div_search">
				<div class="col-md-6">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<div style="margin-left:40px;">
							<font style="color: red">請修正以下錯誤:</font>
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li style="color: red">${message}</li>
								</c:forEach>
							</ul>
						</div>
						
					</c:if>
				</div>
				<div class="col-md-6 div_search_right">
					<FORM METHOD="post" id="search" ACTION="<%=request.getContextPath()%>/OrderServlet">
						<b>訂單編號查詢</b>
						<input type="number" name="searchByOrderID" value="" placeholder="訂單編號" >
						<input type="hidden" name="action" value="searchByOrderID">
						<input type="submit" value="搜尋訂單編號">
					</FORM>
				
<%-- 					<FORM METHOD="post" id="search" ACTION="<%=request.getContextPath()%>/OrderServlet" name="action"> --%>
<!-- 						<b>會員編號查詢</b> -->
<!-- 						<input type="text" name="searchByNumber" value="" placeholder="會員編號"> -->
<!-- 						<input type="submit" name="action" value="searchByNumber" onclick="selectByNumber()"> -->
<!-- 					</FORM> -->
				
<%-- 					<FORM METHOD="post" id="search" ACTION="<%=request.getContextPath()%>/OrderServlet" name="action"> --%>
<!-- 						<b>訂單狀態查詢</b>  -->
<!-- 						<SELECT> -->
<!-- 							<option disabled selected>請選擇訂單狀態</option> -->
<!-- 							<option value="notyet">未開賣</option> -->
<!-- 							<option value="onsale">販售中</option> -->
<!-- 							<option value="soldout">已售罄</option> -->
<!-- 							<option value="ending">已結束</option> -->
<!-- 						</SELECT> -->
<!-- 									<input type="text" name="searchEventType" value="" style="" placeholder="訂單狀態"> -->
<!-- 									<input type="submit" name="action" value="search" onclick="searchEventType()"> -->
<!-- 					</FORM> -->
				
<%-- 					<FORM METHOD="post" id="search" ACTION="<%=request.getContextPath()%>/OrderServlet" name="action"> --%>
<!-- 						<b>訂單日期查詢</b>  -->
<!-- 						<input type="datetime-local" name="startTime" style="" placeholder="訂單日期"> -->
<!-- 					</FORM> -->
				</div>
			</div>
	<!-- search end -->
	<!-- order list -->
			<div class="row">
				<table class="box">
					<tr>
						<th>訂單編號</th>
						<th>會員編號</th>
						<th>活動編號</th>
	<!-- 					<th>活動名稱</th> -->
						<th>訂單日期</th>
						<th>總票數</th>
						<th>金額</th>
						<th>訂單狀態</th>
					</tr>	
					<c:forEach var="orderVO" items="${orderVO_list}">
						<tr>
							<td>${orderVO.orderID}</td>
							<td>${orderVO.number}</td>
							<td>${orderVO.eventNumber}</td>
	<%-- 						<td>${orderVO.eventName}</td> --%>
							<td>${orderVO.orderDate}</td>
							<td>${orderVO.totalTicket}</td>
							<td>${orderVO.total}</td>
							<td>${orderVO.orderType}</td>
		<!-- 					<td><a href="#">查詢</a></td> -->
						</tr>
					</c:forEach>
				</table>
			</div>
		<!-- order list -->
		</div>
</body>
</html>