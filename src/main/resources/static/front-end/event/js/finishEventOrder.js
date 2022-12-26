    function editUserData(){
    	$('#editUserDataDiv').modal('show');
    }
    function closeUserData(modifyData){
    	if(modifyData){
    		let template = `
    			 <h4>聯絡人資料</h4>
    			 <p>名稱：${modifyData.inputName}</p>
				 <p>Email：${modifyData.inputEmail}</p>
				 <p>身分證號：${modifyData.inputRocid}</p>
				 <p>電話：${modifyData.inputPhone}</p>
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
    				let errMsg = data.msg;
    				alert(errMsg);
    				return;
    			}else{
    				alert('訂單已取消');
    				window.location.href = `${context}/main_frame/index_header.jsp`;
    			}
        	});
    	}
		
    }
    
    function confirmUserData(){
    	
//    	儲存購買者資訊
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
			url:`${context}/FrontendEventAjaxServlet`,
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