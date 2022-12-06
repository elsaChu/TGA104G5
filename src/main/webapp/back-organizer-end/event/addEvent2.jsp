<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.event.model.*" %>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
	String str = (String)request.getAttribute("adddata");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.css" />
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main_frame/css/bootstrap.css" />
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main_frame/css/button.css" />
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/css/eventStyle2.css" />
</head>
<body>
    <div id="my_head" class="container my_size">
        <h2>活動上架</h2>
        <div class="row stepTop">
            <div class="col-md-2">設定活動資料</div><div class="col-md-2 step">設定票種</div><div class="col-md-2">設定座位</div><div class="col-md-2">設定表單</div>
        </div>
    </div>
    <div class="container task_list_parent my_size">
        <ul class="task_list">
        </ul>
    </div>
    <div class="container my_cont my_size">
        
            <div>
                <div class="row row1">
                    <div class="col-md-2">票種名稱:<br><input type="text" name="ticketName" class="ticket_name"></div>
                    <div class="col-md-2">價格:<br><input type="text" name="price" class="ticket_price"></div>
                    <div class="col-md-2">數量:<br><input type="text" name="ticketQuantity" class="ticket_quantity"></div>
                    <!-- <div class="col-sm-2"><br><input type="radio" name="limitTicket">不限定數量</div> -->
                </div>
                <div class="row row2">
                    <div class="">販售時間:<br><input type="text" name="ticketStartTime" id="start_date"></div>
                    <div class=""><br>~</div>
                    <div class=""><br><input type="text" name="ticketEndTime" id="end_date"></div>
                </div>
                <div class="row row3">
                    <div class="">限制販售數量<br><input type="text" name="ticketMIN" class="ticket_min"></div>
                    <div class=""><br>~</div>
                    <div class=""><br><input type="text" name="ticketMAX" class="ticket_max"></div>
                </div>
                <div class="row4">
                    <div><input type="button" value="新增" class="task_add"></div>
                    <div><input type="button" value="取消" class="del"></div>
                </div>
            </div>
            <div class="myButton">
            	<form method="post" action="<%=request.getContextPath()%>/addEventServlet" name="eventForm1" enctype="multipart/form-data" id="formNa">
            		<input type="hidden" name="action" value="page2">
	            	<input type="hidden" value='${adddata}' id="hiddData" name="alldata">
	                <input type="button" value="上一步">
	                <input type="button" value="下一步" id="sub">
            	</form>
            </div>
        
    </div>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.full.js"></script>
    <script src="<%=request.getContextPath()%>/main_frame/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/js/eventJS2.js"></script>
</body>
</html>