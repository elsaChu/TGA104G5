<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%--     <link   rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.css" /> --%>
    <jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
    <link   rel="stylesheet" type="text/css" href="${context}/back-organizer-end/event/css/eventStyle3.css" />
</head>
<body>
	<div id="my_head" class="container my_size">
    <!-- <h2>活動上架</h2> -->
    <div class="row stepTop">
        <div class="col-md-4">設定活動資料</div><div class="col-md-4">設定票種</div><div class="col-md-4 step">設定座位</div>
    </div>
    </div>
    <div class="container my_size">
        <div class="row">
            <div id="xdiv" class="col-md-3">
                <label for="xVal" class="form-label">X軸</label>
                <label class="control-label" id="xerrmsg" for="xVal"></label>
                <input type="number" min="1" class="form-control" id="xVal">
            </div>
            <div id="ydiv" class="col-md-3">
                <label for="yVal" class="form-label">Y軸</label>
                <label class="control-label" id="yerrmsg" for="yVal"></label>
                <input type="number" min="1" class="form-control" id="yVal">
            </div>
            <div class="col-md-4">
                <br>
                <button type="button" class="btn btn-primary row1btn" onclick="genSeatTemplate();" >產生座位表</button>
                <!-- <button type="button" class="btn btn-primary row1btn" onclick="getSeat();" >重新讀取座位表</button> -->
            </div>
        </div>
        <div class="row">
            <div class="col-md-12 tabdiv">
                <table id="seatSettingTable"></table>
            </div>
            
        </div>
        <div class="row">
            <div class="col-md-3">
             <button type="button" class="btn btn-primary row3btn" onclick="saveSeatTemplate();" >儲存座位表</button>
            </div>
        </div>
        <input type="hidden" id="eventNumber" value="${eventNumber}">
        <input type="hidden" id="curX" >
        <input type="hidden" id="curY" >
        <form action="${context}/main_frame/index_manufacturer.jsp"></form>
   </div>
	<script>
		var context = '${context}';
	</script>

    <script src="${context}/back-organizer-end/event/datetimepicker/jquery.js"></script>
<%--     <script src="${context}/main_frame/js/bootstrap.js"></script> --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <script src="${context}/back-organizer-end/event/js/eventJS3.js"></script>
</body>
</html>