<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.event.model.*" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
	EventVO eventvo = (EventVO)session.getAttribute("up_eventvo");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
<%-- <link rel="icon" href="${context}/main_frame/images/a0svr-jih0d-001.ico" /> --%>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.css" />
<%-- <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main_frame/css/bootstrap.css" /> --%>
<!-- <link type="text/css" href="sample/css/sample.css" rel="stylesheet" media="screen" /> -->    
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main_frame/css/button.css" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
<link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/css/eventStyle.css" />

</head>
<body>

<div id="my_head" class="my_size col-md-12">
        <div class="row stepTop">
            <div class="col-md-3 step">設定活動資料</div><div class="col-md-3 col-md-offset-1">設定票種</div><div class="col-md-3 col-md-offset-1">設定座位</div>
        </div>
</div>
<div class="my_cont my_size col-md-12">
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li><br>
		</c:forEach>
	</ul>
</c:if>
	<form method="post" action="<%=request.getContextPath()%>/UpdateEventServlet" name="eventForm1" enctype="multipart/form-data" id="formNa">
		<div class="row">
			<div class="col-md-6 left">
				<div>活動名稱：<input type="text" name="eventName" value="<%= (eventvo==null)? "" : eventvo.getEventName()%>" class="form-control"><br></div>
				<div>活動舉辦時間<br>開始日期：<input name="start_date" id="start_date" type="text" class="form-control"><br>
                </div>
				<div>結束日期：<input name="end_date"   id="end_date"   type="text" class="form-control"><br></div>
		
				<div>活動人數：<input type="text" name="peopleNumber" value="${up_eventvo.peopleNumber}" class="form-control"><br></div>
		
				<div>活動地點：<input type="text" name="eventPlace" value="<%= (eventvo==null)? "" : eventvo.getEventPlace()%>" class="form-control"><br></div>
		
				<div>活動地址：<input type="text" id="autocomplete" placeholder="Enter a place" name="eventP2" value="<%= (eventvo==null)? "" : eventvo.getEventP2()%>"><input type="button" id="search" value="更新地圖">
                     <div id="map"></div>
                </div>
			</div>
        	<div class="col-md-6 right">
				<div>上傳封面：<input type="file" id="bigImg" name="bigImg" class="inImg" accept="image/*" value='${up_eventvo.bigImg}'></div>
                    <ul class="picture_list">
                        <li>
                            <img src="" class="preview" id="bigImg_img">
                        </li>
                    </ul>
				<div>上傳輪播圖：<input type="file" name="smallImg" class="inImg" accept="image/*"></div>
                    <ul class="picture_list">
                        <li>
                            <img src="" class="preview" id="smallImg_img">
                        </li>
                    </ul>
                <jsp:useBean id="eventTypeSvc" scope="page" class="tw.com.tibame.event.model.EventTypeService" />
                <div class="chebox">
                        活動分類：(可以選擇最多三個分類)<br>
                        <c:forEach var="eventTypeVO" items="${eventTypeSvc.typeIsON}" >
							<label><input type="CheckBox" name="eventClassNumber" class="chb al_text" value="${eventTypeVO.eventClassNumber}"><span class="al_text">${eventTypeVO.eventClassName}</span></label><br>
						</c:forEach>
                </div>
             	
             	<div>馬上上架：<input type="checkbox" name="isON" id="isON"><br></div>
                <div>座位設定：<input type="checkbox" name="needSeat" id="needSeat"><br></div>
            </div>
        </div>
        <div class="lower">
                <div>活動簡介(限100字以內描述)：<br>
                	<textarea class="textarea" name="eventSummary">${up_eventvo.eventSummary}</textarea>
<%--                     <input type="text" class="textarea" name="eventSummary" value="<%= (eventvo==null)? "" : eventvo.getEventSummary()%>"><br> --%>
                </div>
                
            
                <div>描述：
                    <textarea id="editor" name="eventDescribe">${up_eventvo.eventDescribe}</textarea>
                </div>
        </div>
		<div class="myButton">
				<input type="hidden" name="action" value="update_page1">
<%-- 				<input type="hidden" name="eventNumber" value='${eventvo.eventNumber}'> --%>
                <input type="submit" value="下一步">	
        </div>
	</form>
</div>
	<script>
		var eventNumber = '${up_eventvo.eventNumber}';
		var eventStartDate = '${up_eventvo.eventStartDate}';
		var eventEndDate = '${up_eventvo.eventEndDate}';
		var peopleNumber = '${up_eventvo.peopleNumber}';
		var bigimgPIC = '${bigImg64}';
		var smallimgPIC = '${smallImg64}';
		var isON = '${up_eventvo.isON}';
		var needSeat = '${up_eventvo.needSeat}';
		var context = '${context}';
		var evclassJSON = '${evclassJSON}';
	</script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.full.js"></script>
<%-- 	<script src="<%=request.getContextPath()%>/main_frame/js/bootstrap.min.js"></script> --%>
	<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> -->
	<script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script
  src="https://maps.googleapis.com/maps/api/js?key=keyvalue&libraries=places&callback=initAutocomplete&v=weekly"
  defer
></script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/js/ckeditor.js"></script>
	<script src="<%=request.getContextPath()%>/back-organizer-end/event/js/eventJS.js"></script>
</body>
</html>