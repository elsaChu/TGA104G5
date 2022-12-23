function getRandomInt(max) {
		  return Math.floor(Math.random() * max) + 10000000 ;
}
	async function initPayment(returnUrl ,callbackUrl){
		
		let ranDom = 'TKIT'+ selectEventInfo.orderId + getRandomInt(99999999);
		$('#MerchantTradeNo').val(ranDom);
		//yyyy/MM/dd HH:mm:ss
		let now = new Date();
		let year = now.getFullYear();
		let month = now.getMonth() + 1;
		if(month < 10){
			month = '0' + month;
		}
		let days = now.getDate();
		if(days < 10){
			days = '0' + days;
		}
		let hours = now.getHours();
		if(hours < 10){
			hours = '0' + hours;
		}
		let mins = now.getMinutes();
		if(mins < 10){
			mins = '0' + mins;
		}
		let sec = now.getSeconds();
		if(sec < 10){
			sec = '0' + sec;
		}
		
		let MerchantTradeDate = `${year}/${month}/${days} ${hours}:${mins}:${sec}`;
		$('#MerchantTradeDate').val(MerchantTradeDate);
		
		$('#TotalAmount').val(selectEventInfo.totalPrice);
		let TradeDesc = '專題測試金流介接商品';
		$('#TradeDesc').val(TradeDesc);
		let ItemName = '測試商品'
		$('#ItemName').val(ItemName);
		$('#ReturnURL').val(returnUrl);
		//let ClientBackURL = 'http://127.0.0.1:8080${context}/frontend/event?return='+selectEventInfo.orderId+'_0';
		$('#ClientBackURL').val(callbackUrl);
		
		let checkVis = `HashKey=pwFHCqoQZGmho4w6&ChoosePayment=ALL&ClientBackURL=${callbackUrl}&EncryptType=1&ItemName=${ItemName}&MerchantID=3002607&MerchantTradeDate=${MerchantTradeDate}&MerchantTradeNo=${ranDom}&PaymentType=aio&ReturnURL=${returnUrl}&TotalAmount=${selectEventInfo.totalPrice}&TradeDesc=${TradeDesc}&HashIV=EkRm7iFT261dpevs`;
		console.log('checkVis1:',checkVis);
		let encodeVis = encodeURIComponent(checkVis);
		console.log('checkVis2:',encodeVis);
		encodeVis = encodeVis.toLowerCase();
		console.log('checkVis3:',encodeVis);
		
		//轉換 %20
		encodeVis = encodeVis.replace('%20','+');
		//SHA256
		let textAsBuffer = new TextEncoder().encode(encodeVis);
		let hashBuffer = await window.crypto.subtle.digest('SHA-256', textAsBuffer);
		let hashArray = Array.from(new Uint8Array(hashBuffer))
		let digest = hashArray.map(b => b.toString(16).padStart(2, '0')).join('');
		digest = digest.toUpperCase();
		
		console.log('final checkVis:',digest);
		$('#CheckMacValue').val(digest);
		$('#paymetForm').submit();
	}
	
    let selectEventInfo = {};
    $(function() {
    	
        //確認當前訂購步驟
    	$.ajax({
			url:`${context}/FrontendEventAjaxServlet`,
			dataType: 'json',
			method:'POST',
			data:{
				action:'checkStep' ,
				eventNumber:$('#eventNumber').val()},
			success:function(data){
				
				if(typeof data.selectEventInfo !== 'undefined' && data.selectEventInfo != null){
					selectEventInfo = data.selectEventInfo;
				}
				
				
				console.log(data);
				if(! data.success){
					
					if (data.needLogin) {
						window.location.href = `${context}/front-end/member/memberLogin.jsp`;
						return false;
					} else {
						let errMsg = 'error';
					if(data.msgList){
						data.msgList.forEach(msg=> errMsg += `${msg}\n`);
					}
					
					alert(errMsg);
					return;
					}
					
				}else{
					//跳至步驟
					switchStep(data.step);
				}
				
				
			}
		});
    	
	});
    
    function buildStep1(){
    	$('.eventDesc').show('1000');
    	$('.stepMenuA').removeClass('active');
    	$('#stepMenuA1').addClass('active');
    	changediv('.stepDiv','#step01');
    	getTicket($('#eventNumber').val());
    }
    function buildStep2(){
    	$('.eventDesc').hide('1000');
    	$('.stepMenuA').removeClass('active');
    	$('#stepMenuA2').addClass('active');
    	changediv('.stepDiv','#step02');
    	getSeat();
    	
    }
    function buildStep3(){
    	$('.eventDesc').hide('1000');
    	$('.stepMenuA').removeClass('active');
    	$('#stepMenuA3').addClass('active');
    	changediv('.stepDiv','#step03');
    	getSelectTicket();
    	
    	getUserData();
    }
    
    function getUserData(){
		let params = {
				action:'getUserData' ,
				};
		callAjax(params,function(data){
	    		
	    		console.log(data);
				if(! data.success){
					if(data.needLogin){
						window.location.href = `${context}/front-end/member/memberLogin.jsp`;
					}else{
						alert('error:',data.msg);
					}
					return;
				}else{
					$('#inputName').val(data.inputName);
    	        	$('#inputEmail').val(data.inputEmail);
    	        	$('#inputPhone').val(data.inputPhone);
    	        	$('#inputRocid').val(data.inputRocid);
				}
				
				
	    });
	}
    
    function buildStep4(){
    	/*
    	$('.eventDesc').hide('1000');
    	$('.stepMenuA').removeClass('active');
    	$('#stepMenuA4').addClass('active');
    	changediv('.stepDiv','#step04');
    	*/
    }
    function switchStep(step,curStep){
    	console.log('switch to ',step,curStep);
    	if(step == 1){
    		buildStep1();
    	}else if(step == 2){
    		if($('#needSeat').val() == 'true'){
    			buildStep2();
    		}else{
    			
    			if(curStep == 1){
    				buildStep3();
    			}else if(curStep == 3){
    				buildStep1();
    			}
    		}
    		
    	}else if(step == 3){
    		buildStep3();
    	}
    }
    
    
    function confirmUserData(){
    	
    	//儲存購買者資訊
    	let userData = {
    			inputName:$('#inputName').val(),
    	        inputEmail:$('#inputEmail').val(),
    	        inputPhone:$('#inputPhone').val(),
    	        inputRocid:$('#inputRocid').val(),
    	        action: 'confirmUserData',
    	};
    	
    	$('.invalid-feedback').html('');
    	$('#inputEmail').removeClass('is-invalid');
    	$('#inputName').removeClass('is-invalid');
    	$('#inputRocid').removeClass('is-invalid');
    	$('#inputPhone').removeClass('is-invalid');
    	
    	let validFlag = true;
    	
    	var nameReg = /[0-9!@^*#&\_\+<>\"~;$^%,.{}?]/;
    	if(!$('#inputName').val()){
    		$('#inputName').addClass('is-invalid');
    		$('#inputName_errMsg').html('請輸入姓名');
    		validFlag = false;
    	}else if($('#inputName').val().match(nameReg)){
    		$('#inputName').addClass('is-invalid');
    		$('#inputName_errMsg').html('姓名格式異常');
    		validFlag = false;
    	}
    	
    	var emailReg = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    	if(!$('#inputEmail').val()){
    		$('#inputEmail').addClass('is-invalid');
    		$('#inputEmail_errMsg').html('請輸入 Email');
    		validFlag = false;
    	}else if(!$('#inputEmail').val().match(emailReg)){
    		$('#inputEmail').addClass('is-invalid');
    		$('#inputEmail_errMsg').html('Email 格式異常');
    		validFlag = false;
    	}
    	
    	
    	if(!$('#inputRocid').val()){
    		$('#inputRocid').addClass('is-invalid');
    		$('#inputRocid_errMsg').html('請輸入身分證號');
    		validFlag = false;
    	}else if(! verifyId($('#inputRocid').val())){
    		$('#inputRocid').addClass('is-invalid');
    		$('#inputRocid_errMsg').html('身分證號格式異常');
    		validFlag = false;
    	}
    	
    	if(!$('#inputPhone').val()){
    		$('#inputPhone').addClass('is-invalid');
    		$('#inputPhone_errMsg').html('請輸入手機號碼');
    		validFlag = false;
    	}else if(!$('#inputPhone').val().match(/^(09)[0-9]{8}$/)){
    		$('#inputPhone').addClass('is-invalid');
    		$('#inputPhone_errMsg').html('手機號碼格式異常');
    		validFlag = false;
    	}
    	if(!validFlag){
    		return false;
    	}
    	
		callAjax(userData, function(data) {
			console.log(data);
			if (!data.success) {
				if (data.needLogin) {
					window.location.href = `${context}/front-end/member/memberLogin.jsp`;
					return false;
				} else {
					alert('error:', data);
				}
				return;
			} else {
				//跳至下一步驟
				selectEventInfo = data.selectEventInfo;
				//switchStep(4);
				initPayment(data.returnUrl, data.callbackUrl);
			}
		});
    }
    
    //確認票種資訊
    function confirmTicket(){
    	//檢查同意條款是否被勾選
    	if(!$('#agrrement').prop('checked')){
    		alert('請勾選同意條款');
    		return false;
    	}
    	//票數檢核
    	let ticketSelect = [];
    	let totalPrice = 0;
    	$.each($('.ticketInput'),function(){
    		if($(this).val() != '0'){
    			let price = $(this).attr('price');
    			let ticketCount = $(this).val();
    			let ticketObj = {
        				id:$(this).attr('id').replace('count_' , ''),
        				price:$(this).attr('price'),
        				ticketName:price,
        				val:ticketCount,
        				totalPrice: ( price * ticketCount )
        		};
        		ticketSelect.push(ticketObj);
        		totalPrice += ( price * ticketCount );
    		}
    	});
    	console.log('ticketSelect:',ticketSelect);
    	
    	if(ticketSelect.length == 0){
    		alert('請選擇票種');
    		return false;
    	}
    	
    	let params = {
				action:'confirmTicket' ,
				eventNumber:$('#eventNumber').val(),
				ticketSelect: JSON.stringify(ticketSelect),
				totalPrice:totalPrice
				};
    	
    	callAjax(params,function(data){
			console.log(data);
			if(! data.success){
				if (data.needLogin) {
					window.location.href = `${context}/front-end/member/memberLogin.jsp`;
					return false;
				} else {
					let errMsg = 'error';
					if(data.msgList){
						data.msgList.forEach(msg=> errMsg += `${msg}\n`);
					}
					alert(errMsg);
				return;
				}
			}else{
				//跳至下一步驟
				selectEventInfo.ticketSelect = ticketSelect;
				switchStep(2,1);
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
    
    function getSeat(){
    	let params = {
    			action:'getSeat' ,
    			eventNumber: $('#eventNumber').val()
    			};
    	callAjax(params,function(data){
    		
    		console.log(data);
			if(!data.success){
				if (data.needLogin) {
					window.location.href = `${context}/front-end/member/memberLogin.jsp`;
					return false;
				} else {
					alert('error');
				}
				return;
			}
			
			let eventInfo = data.eventInfo;
			let seatData = data.seatData;
			
			selectEventInfo = data.selectEventInfo;
			
			genSeatTemplate(seatData , eventInfo);
			
    	});
    	
		
	}
	function genSeatTemplate(seatData , eventInfo){
		
		console.log('seatData:',seatData);
		let xVal,yVal;
		let seatNumber = [];
		
		xVal = eventInfo.seatX;
		yVal = eventInfo.seatY;
		
		$('#seatSettingTable').html('');
		let seatTrData = '';
		let seatIdCount = 1;
		for(y = 0 ; y < yVal ; y ++){
			let xTd;
			for(x = 0 ; x < xVal ; x ++){
				
				let match = false;
				let matchSeatSet =0;
				for ( k = 0 ; k < seatData.length ; k++ ){
					
					let seatSet = seatData[k];
					if(seatSet['seatSet'] == seatIdCount){
						match = true;
						matchSeatSet = seatSet['seatType'];
						break;
					}
					
				}
				if(match){
					let selfSelect = false;
					if(selectEventInfo.selectSeat && selectEventInfo.selectSeat.length > 0){
						selectEventInfo.selectSeat.forEach((s)=>{
							if(s == seatIdCount ){
								selfSelect = true;
								return;
							}
						});
					}
					if(selfSelect){
						xTd += '<td><button id="seatId_'+seatIdCount+'" type="button" onclick="triggerSeatStatus(this);" class="btn btn-success seat seatSelect ">'+seatImg_select+'</button></td>';
					}else{
						xTd += '<td><button id="seatId_'+seatIdCount+'" type="button" onclick="triggerSeatStatus(this);" class="btn btn-'+( matchSeatSet ? 'secondary' : 'primary' )+' seat '+( matchSeatSet ? 'seatOccupy' : 'seatActive' )+' "><img class="seatIcon" src="'+context+'/front-end/event/images/seat-2-128.png" /></button></td>';
					}
					
				}else{
					xTd += '<td><button id="seatId_'+seatIdCount+'" type="button" onclick="triggerSeatStatus(this);" class="btn btn-light seat"><img class="seatIcon" src="'+context+'/front-end/event/images/x.png" /></button></td>';
				}
				
				seatIdCount++;
			}
			seatTrData += '<tr>'+xTd+'</tr>';
		}
		$('#seatSettingTable').html(seatTrData);
		
		//計算總票數
		let ticketSelect = selectEventInfo.ticketSelect;
		selectEventInfo.totalTicket = 0;
		ticketSelect.forEach((t)=>{
			let val = 0;
			selectEventInfo.totalTicket += parseInt(t.val);
		});
	}
	
	let seatImg_select = '<img class="seatIcon" src="'+context+'/front-end/event/images/o.png" />';
	let seatImg_active = '<img class="seatIcon" src="'+context+'/front-end/event/images/seat-2-128.png" />';
	let seatImg_x = '<img class="seatIcon" src="'+context+'/front-end/event/images/x.png" />';
	function triggerSeatStatus(seat){
		
		let selectedCount = $('.seatSelect').length;
		
		if(selectEventInfo.totalTicket <= selectedCount){
			//座位已滿就只有取消選擇一種
			if($(seat).hasClass('seatSelect')){
				//取消選擇
				$(seat).removeClass('btn-success')
				  .removeClass('seatSelect')
				  .addClass('btn-primary')
				  .addClass('seatActive');
				$(seat).html(seatImg_active);
			}
		}else{
			if($(seat).hasClass('seatActive') && ! $(seat).hasClass('seatOccupy')){
				//選擇此座位
				$(seat).removeClass('seatActive')
				  .addClass('seatSelect')
				  .removeClass('btn-primary')
				  .addClass('btn-success');
				$(seat).html(seatImg_select);
			}else if($(seat).hasClass('seatSelect')){
				//取消選擇
				$(seat).removeClass('btn-success')
				  .removeClass('seatSelect')
				  .addClass('btn-primary')
				  .addClass('seatActive');
				$(seat).html(seatImg_active);
			}
		}
		
		
		
	}
	
	function confirmSeat(seat){
		
		let selectedCount = $('.seatSelect').length;
		
		if(selectEventInfo.totalTicket > selectedCount){
			alert('尚未選滿座位(剩餘'+(selectEventInfo.totalTicket - selectedCount)+')');
			return false;
		}
		
		let seatIdList = [];
		$.each($('.seat.seatSelect'),function(){
			let seatId = $(this).attr('id').replace('seatId_' , '');
			seatIdList.push(seatId);
		});
		
		let params = {
				action:'confirmSeat',
				selectSeat :  JSON.stringify(seatIdList),
				eventNumber: $('#eventNumber').val()
		};
		
		callAjax(params,function(data){
    		
    		console.log(data);

			if(data && data.success){
				buildStep3();
			}else{
				if(data.occupy && data.occupy != ''){
					alert(`座位${data.occupy}已被占用`);
				}else{
					if (data.needLogin) {
						window.location.href = `${context}/front-end/member/memberLogin.jsp`;
						return false;
					} else {
						alert('error');
					}
					return;
				}
				
			}
    	});
		
	}
    
	function getSelectTicket(){
		let params = {
				action:'getSelectEventInfo',
		};
		
		callAjax(params,function(data){
    		
			console.log(data);
			if( ! data.success){
				if (data.needLogin) {
						window.location.href = `${context}/front-end/member/memberLogin.jsp`;
						return false;
					} else {
						alert('error');
					}
					return;
			}
			
			selectEventInfo = data.selectEventInfo;
			
			let ticketSelect = selectEventInfo.ticketSelect;
			
			if(ticketSelect.length > 0){
				let html =  `
			    	<li class="list-group-item">
					<div class="row">
						<div class="col-sm">票種</div>
						<div class="col-sm"></div>
						<div class="col-sm">金額</div>
					</div>
			    </li>
		    `;
				ticketSelect.forEach((vo)=>{
					let p = parseInt(vo.price);
					let v = parseInt(vo.val);
					let totalPrice = p * v;
					html += `
				    	<li class="list-group-item">
							<div class="row">
								<div class="col-sm">${vo.ticketName}</div>
								<div class="col-sm">\$${vo.price} X ${vo.val}</div>
								<div class="col-sm">
								\$${totalPrice}
								</div>
							</div>
					    </li>
				    `;
				});
				console.log('html:' ,html);
				$('#tickMenuReadOnly').html(html);
			}
			
    	});
	}
    
    function getTicket(eventNumber){
		$.ajax({
			url:`${context}/FrontendEventAjaxServlet`,
			dataType: 'json',
			method:'POST',
			data:{action:'getEventTickets' , eventNumber: eventNumber},
			success:function(data){
				console.log(data);
				if(!data || ! data.success){
					if (data.needLogin) {
						window.location.href = `${context}/front-end/member/memberLogin.jsp`;
						return false;
					} else {
						alert('error');
					}
					return;
				}
				
				let voList = data.voList;
				console.log('voList:',voList);
				
				
				
				if(voList.length > 0){
					let html =  `
				    	<li class="list-group-item">
						<div class="row">
							<div class="col-sm">票種</div>
							<div class="col-sm">票價</div>
							<div class="col-sm"></div>
						</div>
				    </li>
			    `;
					voList.forEach((vo)=>{
						//既有資料讀取
						vo.val = 0;
						if(selectEventInfo && selectEventInfo.ticketSelect){
							let ticketSelect = selectEventInfo.ticketSelect;
							ticketSelect.forEach((v)=>{
								if(v.id == vo.ticketID){
									vo.val = v.val;
								}
							});
						}
						
						
						
						if(vo.ticketType != '販售中'){
							html += `
						    	<li class="list-group-item">
									<div class="row">
										<div class="col-sm">${vo.ticketName}</div>
										<div class="col-sm">\$${vo.price}</div>
										<div class="col-sm">
										<p>${vo.ticketType}</p>
										<input type="hidden" id="count_${vo.ticketID}" price="${vo.price}" ticketName="${vo.ticketName}" value="0" >
										</div>
									</div>
							    </li>
						    `;
						}else{
							html += `
						    	<li class="list-group-item">
									<div class="row">
										<div class="col-sm">${vo.ticketName}</div>
										<div class="col-sm">\$${vo.price}</div>
										<div class="col-sm">
										<input class="ticketInput" type="number" id="count_${vo.ticketID}" price="${vo.price}" ticketName="${vo.ticketName}" ${vo.ticketMAX != 0 ? `max="${vo.ticketMAX}"` : ``} min="0" value="${vo.val}" >
										</div>
									</div>
							    </li>
						    `;
						}
						
					});
					
					$('#tickMenu').html(html);
				}
				
			}
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