<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.event.model.*"%>

<%
OrderService orderSvc = new OrderService();
List<EventVO> list = orderSvc.organizerNumber();
pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>廠商活動列表 - LlistAllEvent.jsp</title>

</head>
<body bgcolor='white'>

	<%-- 	<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include> --%>

	
	<table id="table-1">
		<tr>
			<td>
				<h3>廠商活動列表 - LlistAllEvent.jsp</h3>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>活動編號</th>
			<th>活動名稱</th>
			<th>活動狀態</th>
			<th>開始時間</th>
			<th>結束時間</th>
			<th>查看詳情</th>
		</tr>

		<%@ include file="page1.file"%>

		<c:forEach var="eventVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>

								<td>${eventVO.eventNumber}</td>
								<td>${eventVO.eventName}</td>
								<td>${eventVO.eventType}</td>
								<td>${eventVO.eventStartDate}</td>
								<td>${eventVO.eventEndDate}</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" -->
<%-- 						ACTION="<%=request.getContextPath()%>/StaffServlet" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<input type="submit" value="修改"> -->
<%-- 						<input type="hidden" name="staffNumber" value="${eventVO.Number}">  --%>
<!-- 						<input type="hidden" name="action" value="getOne_For_Update"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
					<td>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderServlet" style ="margin-bottom: 0px;">
					<input type ="submit" value="查看詳情">
					<input type ="hidden" name="eventNumber" value="${eventVO.eventNumber}">
					<input type ="hidden" name="action" value="show_all_event_order">
					
											
					</FORM>
					</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>