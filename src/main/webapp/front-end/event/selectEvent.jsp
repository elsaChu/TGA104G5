<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<meta charset="UTF-8">
<meta name="description" content="">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>TICK IT</title>
<!-- Favicon -->
<link rel="icon" href="${context}/main_frame/images/a0svr-jih0d-001.ico" />
<!-- Style CSS -->
<link rel="stylesheet" href="${context}/main_frame/style.css">

<style type="text/css">
.midType {
	margin-top: 15px;
}

.container {
	margin-bottom: 100px;
}

.ticketInput {
	width: 50px;
}

.card-text {
	font-size: 18px;
}

.agrrement {
	margin-top: 30px;
	margin-bottom: 30px;
}

.stepMenu {
	font-size: 24px;
	font-weight: bold;
}

#seatSet {
	display: none;
}

.stepDiv {
	display: none;
}

.seatIcon {
	width: 20px;
}

.seat {
	margin: 1px;
}
.redDot{
	color:red;
	font-weight: bold;
}
.tickMenuSpan{
	font-size: 18px;
}
</style>
</head>

<body>
	<div class="container">
		<input type="hidden" name="eventNumber" id="eventNumber"
			value="${event.eventNumber}" />
			<input type="hidden" name="needSeat" id="needSeat"
			value="${event.needSeat}" />

		<c:if test="${not empty event }">
			<div class="card" style="width: 100%;">
				<img height="100px"
					src="https://d3vhc53cl8e8km.cloudfront.net/hello-staging/wp-content/uploads/2017/12/22223742/Events-1200x630.jpg"
					class="card-img-top eventDesc" alt="${event.eventName}">
				<div class="card-body">
					<h3 class="card-title">${event.eventName}</h3>
					<p class="card-text eventDesc">活動時間：${event.eventStartDate} ~
						${event.eventEndDate}</p>
					<p class="card-text eventDesc">活動地點：${event.eventPlace} ,
						${event.eventP2}</p>
					<p class="card-text eventDesc">主辦單位：${event.organizerNumber}</p>
					<p class="card-text eventDesc">付款方式：信用卡</p>
				</div>
			</div>
			<ul class="nav nav-pills nav-fill midType">
				<li class="nav-item"><a id="stepMenuA1"
					class="nav-link stepMenuA" href="javascript:void(0);"><font
						class="stepMenu">選擇票種</font></a></li>
				<li class="nav-item"><a id="stepMenuA2"
					class="nav-link stepMenuA" href="javascript:void(0);"><font
						class="stepMenu">劃位</font></a></li>
				<li class="nav-item"><a id="stepMenuA3"
					class="nav-link stepMenuA" href="javascript:void(0);"><font
						class="stepMenu">填寫表單</font></a></li>
				<li class="nav-item"><a id="stepMenuA4"
					class="nav-link stepMenuA" href="javascript:void(0);"><font
						class="stepMenu">取票繳費</font></a></li>
			</ul>

		</c:if>

		<div class="stepDiv" id="step01">
			<ul class="list-group" id="tickMenu">

			</ul>
			<div class="row agrrement">
				<div class="col-sm">
					<input type="checkbox" id="agrrement" name="agrrement"> <font>我已閱讀與同意<a>服務條款</a>與<a>隱私權政策</a>。若後續辦理退票作業，TICK
						IT 得待您處理法律要求之相關單據(如發票折讓單等等)
					</font>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div class="d-flex justify-content-center">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="confirmTicket();">下一步</button>
					</div>
				</div>
			</div>
		</div>

		<div class="stepDiv" id="step02">
			<div class="row agrrement">
				<div class="col-sm">
					<table id="seatSettingTable"></table>
				</div>
			</div>
			<div class="row">
				<div class="col-sm">
					<div class="d-flex justify-content-center">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="switchStep(1,2);">上一步</button>
					</div>
				</div>
				<div class="col-sm">
					<div class="d-flex justify-content-center">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="confirmSeat();">下一步</button>
					</div>
				</div>
			</div>
		</div>
		<div class="stepDiv" id="step03">
			<ul class="list-group" id="tickMenuReadOnly">

			</ul>
			<div class="card" style="margin-top: 15px; width: 100%;">
				<div class="card-body">
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputName"><font class="redDot">*</font><span class="tickMenuSpan">姓名</span></label> 
						<input type="text" class="form-control" id="inputName" required> 
						<div id="inputName_errMsg" class="invalid-feedback">請輸入姓名</div>
					</div>
				</div>
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputRocid"><font class="redDot">*</font><span class="tickMenuSpan">身分證號</span></label> 
						<input type="text" class="form-control" id="inputRocid" required> 
						<div id="inputRocid_errMsg" class="invalid-feedback">請輸入身分證號</div>
					</div>
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputEmail"><font class="redDot">*</font><span class="tickMenuSpan">Email</span></label> 
						<input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp"> 
						<div id="inputEmail_errMsg" class="invalid-feedback">Email 格式異常</div>
					</div>
				</div>
				
				<div class="form-row">
					<div class="form-group col-md-4">
						<label for="inputPhone"><font class="redDot">*</font><span class="tickMenuSpan">手機</span></label> 
						<input type="text" class="form-control" id="inputPhone">
						<div id="inputPhone_errMsg" class="invalid-feedback">請輸入手機號碼</div>
					</div>
				</div>
					

				</div>

			</div>


			<div class="row" style="margin-top: 15px;">
				<div class="col-sm">
					<div class="d-flex justify-content-center">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="switchStep(2,3);">上一步</button>
					</div>
				</div>
				<div class="col-sm">
					<div class="d-flex justify-content-center">
						<button type="button" class="btn btn-primary btn-lg"
							onclick="confirmUserData();">準備付款</button>
					</div>
				</div>
			</div>
		</div>
		<div class="stepDiv" id="step04">
			<form action="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5" method="POST" id="paymetForm">
				<div class="form-group">
					<label for="MerchantID">MerchantID</label> 
					<input type="text"  class="form-control" id="MerchantID" name="MerchantID" value="3002607">
				</div>
				<div class="form-group">
					<label for="MerchantTradeNo">MerchantTradeNo</label> 
					<input type="text"  class="form-control" id="MerchantTradeNo" name="MerchantTradeNo" value="">
				</div>
				<div class="form-group">
					<label for="MerchantTradeDate">MerchantTradeDate</label> 
					<input type="text"  class="form-control" id="MerchantTradeDate" name="MerchantTradeDate" value="">
				</div>
				<div class="form-group">
					<label for="PaymentType">PaymentType</label> 
					<input type="text"  class="form-control" id="PaymentType" name="PaymentType" value="aio">
				</div>
				<div class="form-group">
					<label for="TotalAmount">TotalAmount</label> 
					<input type="text"  class="form-control" id="TotalAmount" name="TotalAmount" value="">
				</div>
				<div class="form-group">
					<label for="TradeDesc">TradeDesc</label> 
					<input type="text"  class="form-control" id="TradeDesc" name="TradeDesc" value="">
				</div>
				<div class="form-group">
					<label for="ItemName">ItemName</label> 
					<input type="text"  class="form-control" id="ItemName" name="ItemName" value="">
				</div>
				<div class="form-group">
					<label for="ReturnURL">ReturnURL</label> 
					<input type="text"  class="form-control" id="ReturnURL" name="ReturnURL" value="">
				</div>
				<div class="form-group">
					<label for="ChoosePayment">ChoosePayment</label> 
					<input type="text"  class="form-control" id="ChoosePayment" name="ChoosePayment" value="ALL">
				</div>
				<div class="form-group">
					<label for="CheckMacValue">CheckMacValue</label> 
					<input type="text"  class="form-control" id="CheckMacValue" name="CheckMacValue" value="">
				</div>
				<div class="form-group">
					<label for="EncryptType">EncryptType</label> 
					<input type="text"  class="form-control" id="EncryptType" name="EncryptType" value="1">
				</div>
				<div class="form-group">
					<label for="ClientBackURL">ClientBackURL</label> 
					<input type="text"  class="form-control" id="ClientBackURL" name="ClientBackURL" value="">
				</div>
				
<!-- 				<div class="form-group"> -->
<!-- 					<label for="ItemURL">ItemURL</label>  -->
<!-- 					<input type="text"  class="form-control" id="ItemURL" name="ItemURL" value=""> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="Remark">Remark</label>  -->
<!-- 					<input type="text"  class="form-control" id="Remark" name="Remark" value=""> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="ChooseSubPayment">ChooseSubPayment</label>  -->
<!-- 					<input type="text"  class="form-control" id="ChooseSubPayment" name="ChooseSubPayment" value=""> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="OrderResultURL">OrderResultURL</label>  -->
<!-- 					<input type="text"  class="form-control" id="OrderResultURL" name="OrderResultURL" value=""> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="IgnorePayment">IgnorePayment</label>  -->
<!-- 					<input type="text"  class="form-control" id="IgnorePayment" name="IgnorePayment" value=""> -->
<!-- 				</div> -->
<!-- 				<div class="form-group"> -->
<!-- 					<label for="Language">Language</label>  -->
<!-- 					<input type="text"  class="form-control" id="Language" name="Language" value=""> -->
<!-- 				</div> -->
				
				<button type="submit" class="btn btn-primary">金流測試送出</button>
			</form>
		</div>




	</div>

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="${context}/main_frame/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- Popper js -->
	<script src="${context}/main_frame/js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script src="${context}/main_frame/js/bootstrap.min.js"></script>

	<script>
	var context = '${context}';
    </script>

    <script src="${context}/front-end/event/js/selectEvent.js"></script>

	<!-- Active js -->
	<script src="${context}/main_frame/js/active.js"></script>

	<script src="${context}/main_frame/js/model.js"></script>

	<script
		src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
	<!-- Plugins js -->
	<script src="${context}/main_frame/js/plugins.js"></script>


</body>

</html>
