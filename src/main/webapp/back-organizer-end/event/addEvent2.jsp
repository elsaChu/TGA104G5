<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.event.model.*" %>
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
    <jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
    <link   rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-organizer-end/event/css/eventStyle2.css" />
</head>
<body>
    <div id="my_head" class="my_size col-md-12">
<!--         <h2>活動上架</h2> -->
        <div class="row stepTop">
            <div class="col-md-3 ">設定活動資料</div><div class="col-md-3 col-md-offset-1 step">設定票種</div><div class="col-md-3 col-md-offset-1">設定座位</div>
        </div>
    </div>
    <div class="task_list_parent my_size col-md-12">
        <ul class="task_list">
        </ul>
    </div>
    <div class="my_cont my_size col-md-12">
        <label style="color:red">${errorMsg}</label>
            <div>
                <div class="row row1">
                    <div class="col-md-3">票種名稱:<br><input type="text" name="ticketName" class="ticket_name form-control" ></div>
                    <div class="col-md-2">價格:<br><input type="number" name="price" class="ticket_price form-control"></div>
                    <div class="col-md-2">數量:<br><input type="number" name="ticketQuantity" class="ticket_quantity form-control"></div>
                    <!-- <div class="col-sm-2"><br><input type="radio" name="limitTicket">不限定數量</div> -->
                </div>
                <div class="row row2">
                    <div class="col-md-3">販售時間:<br><input type="text" name="ticketStartTime" id="start_date" class="form-control"></div>
                    <div class="col-md-1 bu"><br>~</div>
                    <div class="col-md-3 getbr"><br><input type="text" name="ticketEndTime form-control" id="end_date" class="form-control"></div>
                </div>
                <div class="row row3">
                    <div class="col-md-3">限制販售數量<br><input type="number" name="ticketMIN" class="ticket_min form-control"></div>
                    <div class="col-md-1 bu"><br>~</div>
                    <div class="col-md-3 getbr"><br><input type="number" name="ticketMAX" class="ticket_max form-control"></div>
                </div>
                <div class="row4">
                    <div><input type="button" value="新增" class="task_add"></div>
                    <div><input type="button" value="取消" class="del"></div>
                </div>
                <div class="row5">
                </div>
            </div>
            <div class="myButton">
<%--             	<form method="post" action="<%=request.getContextPath()%>/addEventServlet" name="eventForm1" enctype="multipart/form-data" id="formNa"> --%>
<!--             		<input type="hidden" name="action" value="page2"> -->
	                <input type="button" value="上一步">
	                <input type="button" value="下一步" id="sub">
<!--             	</form> -->
            </div>
    </div>
    <script>
		var context = '${context}';
	</script>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/datetimepicker/jquery.datetimepicker.full.js"></script>
    <script src="<%=request.getContextPath()%>/main_frame/js/bootstrap.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"></script>
    <script src="<%=request.getContextPath()%>/back-organizer-end/event/js/eventJS2.js"></script>
</body>
</html>