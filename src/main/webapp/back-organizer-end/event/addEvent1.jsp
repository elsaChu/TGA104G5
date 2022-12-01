<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
	EventVO eventvo = (EventVO)request.getAttribute("eventvo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>addEvent1</title>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.css" />
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/css/eventStyle.css" />
</head>
<body>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
<div class="container">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<!-- 	<form method="post" action="../../addEmpServlet" name="eventForm1"> -->
	<form method="post" action="<%=request.getContextPath()%>/addEventServlet" name="eventForm1" enctype="multipart/form-data">
		<span>活動名稱：</span><input type="text" name="eventName" value="<%= (eventvo==null)? "" : eventvo.getEventName()%>" ><br>
		<span>活動舉辦時間</span><br>
		<span>開始日期：</span><input name="start_date" id="start_date" type="text" value="<%= (eventvo==null)? "" : eventvo.getEventStartDate()%>"><br>
		
		<span>結束日期：</span><input name="end_date"   id="end_date"   type="text" value="<%= (eventvo==null)? "" : eventvo.getEventEndDate()%>"><br>
		
		<span>活動人數：</span><input type="text" name="peopleNumber" value="<%= (eventvo==null)? "" : eventvo.getPeopleNumber()%>"><br>
		
		<span>活動地點：</span><input type="text" name="eventPlace" value="<%= (eventvo==null)? "" : eventvo.getEventPlace()%>"><br>
		
		<span>活動地址：</span><input type="text" name="eventP2" value="<%= (eventvo==null)? "" : eventvo.getEventP2()%>"><br>
		
		<span>活動簡介(限100字以內描述)：</span><input type="textArea" name="eventSummary" value="<%= (eventvo==null)? "" : eventvo.getEventSummary()%>"><br>
		
		<span>描述：</span><input type="textArea" name="eventDescribe" value="<%= (eventvo==null)? "" : eventvo.getEventDescribe()%>"><br>
		<span>上傳封面：</span><input type="file" id="bigImg" name="bigImg">
  		<ul class="picture_list">
    	</ul>
    	
    	<span>上傳輪播圖：</span><input type="file" id="smallImg" name="smallImg">
  		<ul class="picture_list2">
    	</ul>
		
		<jsp:useBean id="eventTypeSvc" scope="page" class="com.event.model.EventTypeService" />
		<span>活動分類：</span>
<!-- 		<div> -->
<%-- 			<c:forEach var="eventTypeVO" items="${eventTypeSvc.selectAll}" > --%>
<%-- 				<input type="CheckBox" name="eventClassNumber" value="${eventTypeVO.eventClassNumber}">${eventTypeVO.eventClassName}<br> --%>
<%-- 			</c:forEach> --%>
<!-- 		</div> -->
		<input type="CheckBox" name="eventClassNumber" value="演唱會">演唱會<br>

		<span>馬上上架：</span><input type="radio" name="isON"><br>
		<span>座位設定：</span><input type="radio" name="needSeat"><br>
		<span>橫：</span><input type="text" name="seatX" value="<%= (eventvo==null)? "" : eventvo.getSeatX()%>"><br>
		<span>直：</span><input type="text" name="seatY" value="<%= (eventvo==null)? "" : eventvo.getSeatY()%>"><br>
		
		
		
		<input type="submit" value="送出" name="bigImg">	
	</form>
</div>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/js/eventJS.js"></script>
</body>
</html>