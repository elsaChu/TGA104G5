<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-TW">
<head>
<meta charset="UTF-8">
<!-- <meta name="description" content=""> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>TICK IT</title>
<link rel="icon" href="${context}/main_frame/images/logo.ico" />
<!-- <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous"> -->
<jsp:include page="/main_frame/index_header.jsp"></jsp:include>
<link rel="stylesheet" href="${context}/front-end/event/css/finishEventOrder.css">
</head>
<body>
<c:if test="${not empty orderCancel}">
<div class="container">
	<div class="card">
		  <div class="card-body">
		    此訂單已過期或取消！
		  </div>
		</div>
</div>
</c:if>
<c:if test="${empty orderCancel}" >
	<div class="container">
		<div class="card">
		  <div class="card-body">
		    恭喜！您已經完成訂單
		  </div>
		</div>
		<div class="card" >
				<div class="card-body">
					<h3 class="card-title">${event.eventName}</h3>
					<p class="card-text eventDesc">活動時間：${eventStart} ~
						${eventEnd}</p>
					<p class="card-text eventDesc">演出地點：${event.eventPlace}</p>
					<p class="card-text eventDesc">演出地址：${event.eventP2}</p>
					<p class="card-text eventDesc">主辦單位：${orgName}</p>
					<p class="card-text eventDesc">付款方式：信用卡</p>
					<p class="card-text eventDesc">付款金額：${order.total}</p>
				</div>
		</div>
		<div class="card" >
				<div class="card-body">
					<h3 class="card-title">訂單編號：${orderId}</h3>
					<p class="card-text">取票方式：電子票卷</p>
<!-- 					<p class="card-text">容我們提醒：</p> -->
				</div>
		</div>
		<div class="row">
			<div class="middle_block col-md-6">
				<button type="button" class="btn myButton_finEvnOrder btn-lg"
								onclick="editUserData();">修改基本資料</button>
			</div>
			<div class="middle_block col-md-6">
				<button type="button" class="btn myButton_finEvnOrder btn-lg"
								onclick="cancelOrder('${orderId}');">取消訂單</button>
			</div>
		</div>
		<div id="userDataDiv" class="card" >
			<div class="card-body">
				<div class="row">
					<div  class="col-md-1 left_div icon_div">
						<span style="color:white;margin: 0px auto;"><i class="fa-regular fa-user fa-4x"></i></span>
					</div>
					<div id="userDataShowArea" class="col-md-11">
						<h4>聯絡人資料</h4>
						<p>名稱：${inputName}</p>
						<p>Email：${inputEmail}</p>
						<p>身分證號：${inputRocid}</p>
						<p>電話：${inputPhone}</p>
					</div>
				</div>
			</div>
		</div>
		<div class="modal fade" id="editUserDataDiv" tabindex="-1"
		  	 role="dialog" aria-labelledby="exampleModalCenterTitle"
		  	 aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLongTitle">修改資料</h5>
						<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-row">
							<input type="hidden" id="hidOrderId" name="hidOrderId" value="${orderId}"  />
							<div class="form-group col-md-6">
								<label for="inputName"><font class="redDot">*</font><span class="tickMenuSpan">姓名</span></label> 
								<input type="text" class="form-control" id="inputName" value="${inputName}" required> 
								<div id="inputName_errMsg" class="invalid-feedback">請輸入姓名</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputRocid"><font class="redDot">*</font><span class="tickMenuSpan">身分證號</span></label> 
								<input type="text" class="form-control" id="inputRocid" value="${inputRocid}" required> 
								<div id="inputRocid_errMsg" class="invalid-feedback">請輸入身分證號</div>
							</div>
						</div>	
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputEmail"><font class="redDot">*</font><span class="tickMenuSpan">Email</span></label> 
								<input type="email" class="form-control" id="inputEmail" value="${inputEmail}" aria-describedby="emailHelp"> 
								<div id="inputEmail_errMsg" class="invalid-feedback">Email 格式異常</div>
							</div>
						</div>
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="inputPhone"><font class="redDot">*</font><span class="tickMenuSpan">手機</span></label> 
								<input type="text" class="form-control" id="inputPhone" value="${inputPhone}" >
								<div id="inputPhone_errMsg" class="invalid-feedback">請輸入手機號碼</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
								data-dismiss="modal">關閉</button>
						<button type="button" class="btn myButton_finEvnOrder btn-lg"
						      onclick="confirmUserData();">確認</button>
					</div>
				</div>
			</div>
		</div>
		<div class="card" >
			<c:forEach items="${qrList}" var="qrData">
			<div class="card-body ticket_div">
					<div class="row">
						<div  class="col-md-1 left_div"></div>
						<div class="col-md-5">
							<p>${qrData.ticketName}</p>    
							<p>${qrData.eventName}</p>
						</div>
		                <div  class="col-md-6" style="text-align:right;">
							<img src="data:image/png;base64, ${qrData.base64Data}" width="40%">
						</div>
					</div>
			</div>
			</c:forEach>
		</div>
	</div>
</c:if>
	<script>
		var context = '${context}';
    </script>
<!-- 	<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script> -->
<!-- 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script> -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"></script>
	<script src="${context}/front-end/event/js/finishEventOrder.js"></script>
</body>
<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
</html>
