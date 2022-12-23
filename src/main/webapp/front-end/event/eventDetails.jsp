<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
<!--     <link   rel="stylesheet" type="text/css" href="css/bootstrap.min.css" /> -->
    <jsp:include page="/main_frame/index_header.jsp"></jsp:include>
    <link   rel="stylesheet" type="text/css" href="${context}/front-end/event/css/details.css" />
</head>
<body>
    <div class="container">
        <div class="card ">
            <div class="card-header">
                <h3 class="card-title">${event.eventName}</h3>
            </div>
			<img id="eventImg" src="${bigImg64}" class="card-img-top eventDesc">
            <div class="card-body">
                <p>主辦單位：${orgName}</p>
                <p>活動時間：${eventStart} ~ ${eventEnd}</p>
                <p>演出地點：${event.eventPlace}</p>
                <p>演出地址：${event.eventP2}</p>
                <p>${event.eventSummary}</p>
                <p>${event.eventDescribe}</p>
				<c:forEach items="${eventClassName}" var="qrData">
                	<span class="badge badge-info">${qrData}</span>
                </c:forEach>
            </div>
            <h3 class="card-title">活動票劵</h3>
            <div class="card-footer">
            		<div class="row">
            			<div class="col-sm">
            				<p>票種名稱</p>
            			</div>
            			<div class="col-sm-6">
            				<p>販售時間</p>
            			</div>
            			<div class="col-sm price_right">
            				<p>價格</p>
            			</div>
            		</div>
            	<c:forEach items="${ticketlist}" var="ticketData">
            		<div class="row">
            			<div class="col-sm">
            				<p>${ticketData.ticketName}</p>
            			</div>
            			<div class="col-sm-6">
            				<p>${ticketData.ticketStartTime} ~ ${ticketData.ticketEndTime}</p>
            			</div>
            			<div class="col-sm price_right">
            				<p>TWD$${ticketData.price}</p>
            			</div>
            		</div>
                </c:forEach>
            </div>
		</div>

			<div class="btn_div">
				<a href="${context}/FrontendEventServlet?eventNumber=${event.eventNumber}" class="card-link">
        			<input type="button" class="myButton_evnDetails" value="下一步"> 
        		</a>
			</div>
		
    </div>

<!--   <script src="js/jquery-2.2.4.min.js"></script> -->
<!--   <script src="js/bootstrap.min.js"></script> -->
</body>
<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
</html>