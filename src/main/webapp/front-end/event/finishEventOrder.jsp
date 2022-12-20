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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css" integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N" crossorigin="anonymous">
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
		<div class="card" style="width: 100%;">
				<div class="card-body">
					<h3 class="card-title">${event.eventName}</h3>
					<p class="card-text eventDesc">活動時間：${event.eventStartDate} ~
						${event.eventEndDate}</p>
					<p class="card-text eventDesc">活動地點：${event.eventPlace} ,
						${event.eventP2}</p>
					<p class="card-text eventDesc">主辦單位：${orgName}</p>
					<p class="card-text eventDesc">付款方式：信用卡</p>
					<p class="card-text eventDesc">付款金額：${order.total}</p>
				</div>
		</div>
		<div class="card" style="width: 100%;">
				<div class="card-body">
					<h3 class="card-title">訂單編號：${orderId}</h3>
					<p class="card-text">取票方式：電子票卷</p>
					<p class="card-text">容我們提醒：</p>
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


			<div id="userDataDiv" class="task_list_parent my_size col-md-12">
			<div class="item_flex">
				<div class="row">
					<div  class="left_block col-md-1" style="background-color: #01A1D6;">
						<i class="fa-regular fa-user fa-4x" style="margin-top: 50px;"></i>
					</div>
					<div id="userDataShowArea" class="middle_block col-md-11">
					 <p>名稱：</p>
					 <p>${inputName}</p>
					 <p>Email：</p>
					 <p>${inputEmail}</p>
					 <p>身分證號：</p>
					 <p>${inputRocid}</p>
					 <p>電話：</p>
					 <p>${inputPhone}</p>
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
								<button type="button" class="btn btn-primary btn-lg"
							      onclick="confirmUserData();">確認</button>
						</div>
					</div>
				</div>
			</div>
		
		<c:forEach items="${qrList}" var="qrData">
		<div class="task_list_parent my_size col-md-12">
			<div class="item_flex">
				<div class="row">
					<div  class="left_block col-md-1" style="background-color: #01A1D6;">
					</div>
					<div class="middle_block col-md-5">
					 <p>${qrData.ticketName}</p>
					 <p>活動名稱：</p>    
					 <p>${qrData.eventName}</p>
					</div>
                    <div  class="left_block col-md-6" >
						<img src="data:image/png;base64, ${qrData.base64Data}" width="60%">
					</div>
				</div>
			</div>
		</div>
		</c:forEach>

	</div>
	</div>
</c:if>

	<script src="https://code.jquery.com/jquery-3.6.1.js" integrity="sha256-3zlB5s2uwoUzrXK3BT7AX3FyvojsraNFxCc2vC/7pNI=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/js/all.min.js"></script>
	
	<script>
	
    $(function() {
    	
	});
    
    function editUserData(){
    	$('#editUserDataDiv').modal('show');
    }
    function closeUserData(modifyData){
    	if(modifyData){
    		let template = `
    			  <p>名稱：</p>
				 <p>\${modifyData.inputName}</p>
				 <p>Email：</p>
				 <p>\${modifyData.inputEmail}</p>
				 <p>身分證號：</p>
				 <p>\${modifyData.inputRocid}</p>
				 <p>電話：</p>
				 <p>\${modifyData.inputPhone}</p>
    		`;
    		$('#userDataShowArea').html(template);
    	}
    	
    	$('#editUserDataDiv').modal('hide');
    }
    
    function cancelOrder(orderId){
    	
    	if(confirm('是否確定取消訂單？')){
    		let data = {
    				action: 'cancelOrder',
    				orderId: orderId
    		};
    		
        	callAjax(data,function(data){
    			console.log(data);
    			if(!data || ! data.success){
    				let errMsg = 'error';
    				alert(errMsg);
    				return;
    			}else{
    				alert('訂單已取消');
    				window.location.href = '${context}';
    			}
        	});
    	}
		
    }
    
    function confirmUserData(){
    	
    	<%-- 儲存購買者資訊 --%>
    	let userData = {
    			inputName:$('#inputName').val(),
    	        inputEmail:$('#inputEmail').val(),
    	        inputPhone:$('#inputPhone').val(),
    	        inputRocid:$('#inputRocid').val(),
    	};
    	
    	$('.invalid-feedback').html('');
    	$('#inputEmail').removeClass('is-invalid');
    	$('#inputName').removeClass('is-invalid');
    	$('#inputRocid').removeClass('is-invalid');
    	$('#inputPhone').removeClass('is-invalid');
    	
    	let validFlag = true;
    	var emailReg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    	if(!$('#inputEmail').val()){
    		$('#inputEmail').addClass('is-invalid');
    		$('#inputEmail_errMsg').html('請輸入 Email');
    		console.log('mail1');
    		validFlag = false;
    	}else if(!$('#inputEmail').val().match(emailReg)){
    		$('#inputEmail').addClass('is-invalid');
    		$('#inputEmail_errMsg').html('Email 格式異常');
    		console.log('mail2');
    		validFlag = false;
    	}
    	if(!$('#inputName').val()){
    		$('#inputName').addClass('is-invalid');
    		$('#inputName_errMsg').html('請輸入姓名');
    		console.log('name1');
    		validFlag = false;
    	}
    	
    	if(!$('#inputRocid').val()){
    		$('#inputRocid').addClass('is-invalid');
    		$('#inputRocid_errMsg').html('請輸入身分證號');
    		console.log('rocid1');
    		validFlag = false;
    	}else if(! verifyId($('#inputRocid').val())){
    		$('#inputRocid').addClass('is-invalid');
    		$('#inputRocid_errMsg').html('身分證號格式異常');
    		console.log('rocid2');
    		validFlag = false;
    	}
    	
    	if(!$('#inputPhone').val()){
    		$('#inputPhone').addClass('is-invalid');
    		$('#inputPhone_errMsg').html('請輸入手機號碼');
    		console.log('phone1');
    		validFlag = false;
    	}else if(!$('#inputPhone').val().match(/^(09)[0-9]{8}$/)){
    		$('#inputPhone').addClass('is-invalid');
    		$('#inputPhone_errMsg').html('手機號碼格式異常');
    		console.log('phone2');
    		validFlag = false;
    	}
    	if(!validFlag){
    		return false;
    	}
    	
		userData.action = 'confirmUserDataForFinish';
		userData.orderId = $('#hidOrderId').val();
		
    	callAjax(userData,function(data){
			console.log(data);
			if(!data || ! data.success){
				let errMsg = 'error';
				alert(errMsg);
				return;
			}else{
				alert('變更完成');
				closeUserData(data.userData);
			}
    	});
    	
    }
    
    function callAjax(param,callbackFunc){
    	$.ajax({
			url:'${context}/FrontendEventAjaxServlet',
			dataType: 'json',
			method:'POST',
			data:param,
			success:callbackFunc
		});
    }
    
    function changediv( begin , to ){

        $(begin ).fadeOut(200,function(){
            $(to ).fadeIn(200);
        });
        
    }
    
    function verifyId(id) {
        id = id.trim();

        if (id.length != 10) {
            return false
        }

        let countyCode = id.charCodeAt(0);
        if (countyCode < 65 | countyCode > 90) {
            return false
        }

        let genderCode = id.charCodeAt(1);
        if (genderCode != 49 && genderCode != 50) {
            return false
        }

        let serialCode = id.slice(2)
        for (let i in serialCode) {
            let c = serialCode.charCodeAt(i);
            if (c < 48 | c > 57) {
                return false
            }
        }

        let conver = "ABCDEFGHJKLMNPQRSTUVXYWZIO"
        let weights = [1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1]

        id = String(conver.indexOf(id[0]) + 10) + id.slice(1);

        checkSum = 0
        for (let i = 0; i < id.length; i++) {
            c = parseInt(id[i])
            w = weights[i]
            checkSum += c * w
        }

        verification = checkSum % 10 == 0

        return verification
    }
    </script>


</body>

</html>
