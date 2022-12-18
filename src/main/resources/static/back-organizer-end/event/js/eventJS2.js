$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function () {
	//date
    $('#start_date').datetimepicker({
        theme: '',
        timepicker: true,
        step: 15,
        format: 'Y-m-d H:i:s',
        onShow: function () {
            this.setOptions({
                maxDate: $('#end_date').val() ? $('#end_date').val() : maxDate
            })
        }
    });
	console.log("date"+maxDate);
    $('#end_date').datetimepicker({
        theme: '',
        timepicker: true,
        step: 15,
        format: 'Y-m-d H:i:s',
        maxDate: maxDate,
        onShow: function () {
            this.setOptions({
                minDate: $('#start_date').val() ? $('#start_date').val() : false
            })
        }
    });
    
//tickets control
    let toobj = {};
    toobj.ticket = [];
    // console.log(toobj);
    let count = 0;
	//select ticket
	function init(){
		console.log("eventNumber=",eventNumber);
		if(eventNumber != ""){
			let formData = new FormData();
			formData.append("action","selectTickets");
			formData.append("eventNumber",eventNumber);
			
			fetch(context + "/UpdateEventServlet", {
	            method: "POST",
	            body: new URLSearchParams(formData)
	        }).then(function (response) {
	//			console.log(response);
	            return response.json();
	        }).then(function (data) {
	//			let tojson = JSON.parse(data);
				console.log(data);
				if(data.success){
					console.log("success="+data.success);
					console.log("data="+JSON.stringify(data));
					console.log(typeof(data.tickets));
					let tickets =JSON.parse(data.tickets);
					let list_html = '';
					$.each(tickets, function(index,item){
	//					console.log(index,", ",item,", ",item.eventNumber);
						list_html = list_html+`
				            <li data-id="${count}">
				                <div class="item_flex">
				                    <div class="row">
				                        <div class="left_block col-md-2">
				                            <span class="star"><i class="fa-solid fa-ticket-simple fa-5x"></i></span>
				                        </div>
				                        <div class="middle_block col-md-8">
				                            <div class="line1">
				                                <div><p class="ticketname">${item.ticketName}</p></div>
				                                <div>
				                                    <p class="ticketStartTime">${item.ticketStartTime}</p>
				                                    <p>~</p>
				                                    <p class="ticketEndTime">${item.ticketEndTime}</p>
				                                </div>
				                                <div><p class="price">TWD$${item.price}</p></div>
				                                <div><p class="ticketQuantity">票數:${item.ticketQuantity}</p></div>
				                            </div>
				                            <div class="line2">
				                                <div><p>販售數量${item.ticketMIN}~${item.ticketMAX}張</p></div>
				                                <div><p>販售單位${item.ticketMIN}張</p></div>
				                            </div>
				                        </div>
				                        <div class="right_block col-md-2">
				                            <div class="btn_flex">
				                                <button type="button" class="btn_delete">移除</button>
				                            </div>
				                        </div>
				                    </div>          
				                </div>
				            </li>`;
				            
					    let obj = {
							ticket_ID: item.ticketID,
			                ticket_name: item.ticketName,
			                eventNumber: item.eventNumber,
			                ticket_price: item.price,
			                ticket_quantity: item.ticketQuantity,
			                start_date: item.ticketStartTime,
			                end_date: item.ticketEndTime,
			                ticket_min: item.ticketMIN,
			                ticket_max: item.ticketMAX,
			                record: count
		            	};
		            	toobj.ticket.push(obj);
		            	count++; 
					});
					$("ul.task_list").html(list_html);
				}else{
					console.log("success="+data.success);
					errorMessage(data);
				}
	        });
		}
	}
	init();
	
    //add ticket
    $("input.task_add").on("click", function () {
        //        console.log("in click");
        let ticket_name = ($("input.ticket_name").val()).trim();
        let ticket_price = ($("input.ticket_price").val()).trim();
        let ticket_quantity = ($("input.ticket_quantity").val()).trim();
        let start_date = ($("input#start_date").val()).trim();
        let end_date = ($("input#end_date").val()).trim();
        let ticket_min = ($("input.ticket_min").val()).trim();
        let ticket_max = ($("input.ticket_max").val()).trim();

        if (ticket_name != "" && ticket_price != "" && ticket_quantity != "" && start_date != ""
            && end_date != "" && ticket_min != "" && ticket_max != "") {

            let obj = {
				ticket_ID: -100,
                ticket_name: ticket_name,
                eventNumber: -100,
                ticket_price: ticket_price,
                ticket_quantity: ticket_quantity,
                start_date: start_date,
                end_date: end_date,
                ticket_min: ticket_min,
                ticket_max: ticket_max,
                record: count
            };
            toobj.ticket.push(obj);
            // console.log(toobj);

            // console.log(count);

            let list_html = "";
            list_html = `
            <li data-id="${count}">
                <div class="item_flex">
                    <div class="row">
                        <div class="left_block col-md-2">
                            <span class="star"><i class="fa-solid fa-ticket-simple fa-5x"></i></span>
                        </div>
                        <div class="middle_block col-md-8">
                            <div class="line1">
                                <div><p class="ticketname">${ticket_name}</p></div>
                                <div>
                                    <p class="ticketStartTime">${start_date}</p>
                                    <p>~</p>
                                    <p class="ticketEndTime">${end_date}</p>
                                </div>
                                <div><p class="price">TWD$${ticket_price}</p></div>
                                <div><p class="ticketQuantity">票數:${ticket_quantity}</p></div>
                            </div>
                            <div class="line2">
                                <div><p>販售數量${ticket_min}~${ticket_max}張</p></div>
                                <div><p>販售單位${ticket_min}張</p></div>
                            </div>
                        </div>
                        <div class="right_block col-md-2">
                            <div class="btn_flex">
                                <button type="button" class="btn_delete">移除</button>
                            </div>
                        </div>
                    </div>          
                </div>
            </li>`

            $("ul.task_list").append(list_html);
            clearAllInput();
            count++;
        }
    });

    //delete ticket
    $("ul.task_list").on("click", "button.btn_delete", function () {
        let r = confirm("確認移除?");

        if (r) {
            let that = this;
            $(this).closest("li").animate({
                "opacity": 0
            }, 1000, "swing", function () {
                let getrecord = $(that).closest("li").attr("data-id");
                console.log("record nu =" + getrecord);

                for (let i = 0; i < toobj.ticket.length; i++) {
                    let re = toobj.ticket[i].record;
                    if (re == getrecord) {
                        console.log(toobj.ticket[i]);
                        toobj.ticket.splice(i, 1);
                    }
                };
                console.log(toobj.ticket);
                // console.log($(this));
                $(this).remove();
            });
        }
    })

    //insert
    let sub = document.getElementById("sub");
    console.log("sub=",sub);
    if(sub != null){
		sub.addEventListener("click", function () {
			$('.row5 div').html("");
	//		console.log(JSON.stringify(toobj));
			let formData = new FormData();
			formData.append("action","page2");
			formData.append("tickets",JSON.stringify(toobj));
			
	        fetch(context + "/addEventServlet", {
	            method: "POST",
	            body: new URLSearchParams(formData)
	        }).then(function (response) {
	//			console.log(response);
	            return response.json();
	        }).then(function (data) {
	//			let tojson = JSON.parse(data);
				console.log(data);
				if(data.success){
					console.log("success="+data.success);
					if(data.needSeat){
						console.log("need seat="+data.needSeat);
						window.location.href=context+"/back-organizer-end/event/addEvent3.jsp";
					}else{
						console.log("need seat="+data.needSeat);
						console.log("insertOK="+ data.insertOK);
						if(data.insertOK == 1){
							confirm('儲存完畢');
						}else{
							confirm('新增失敗');
						}
	                	window.location.href=context+"/main_frame/index_manufacturer.jsp";
					}
				}else{
					console.log("success="+data.success);
					errorMessage(data);
				}
	            console.log("data="+JSON.stringify(data));
	        });
    	});
	}

    
    //update
    let up_sub = document.getElementById("up_sub");
    if(up_sub != null){
		up_sub.addEventListener("click", function () {
			$('.row5 div').html("");
		//	console.log(JSON.stringify(toobj));
			let formData = new FormData();
			formData.append("action","update_page2");
			formData.append("tickets",JSON.stringify(toobj));
				
		    fetch(context + "/UpdateEventServlet", {
		            method: "POST",
		            body: new URLSearchParams(formData)
		        }).then(function (response) {
		//			console.log(response);
		            return response.json();
		        }).then(function (data) {
		//			let tojson = JSON.parse(data);
					console.log(data);
					if(data.success){
						console.log("success="+data.success);
						if(data.needSeat){
							console.log("need seat="+data.needSeat);
		//					window.location.href=context+"/back-organizer-end/event/addEvent3.jsp";
						}else{
							console.log("need seat="+data.needSeat);
							console.log("insertOK="+ data.insertOK);
							if(data.insertOK == 1){
								confirm('更新完畢');
							}else{
								confirm('更新失敗');
							}
		                	window.location.href=context+"/main_frame/index_manufacturer.jsp";
						}
					}else{
						console.log("success="+data.success);
						errorMessage(data);
					}
		    	console.log("data="+JSON.stringify(data));
			});
		});
	}
    
    
    //clear input
    $('.del').on("click", function () {
        clearAllInput();
    });

    function clearAllInput() {
        $("input.ticket_name").val("");
        $("input.ticket_price").val("");
        $("input.ticket_quantity").val("");
        $("input#start_date").val("");
        $("input#end_date").val("");
        $("input.ticket_min").val("");
        $("input.ticket_max").val("");
    };
    
    //error message
    function errorMessage(data) {
        //		console.log("err msg ="+data.noticket);
        if (typeof data.noticket != 'undefined') {
            alert(data.noticket);
        }
        // console.log(typeof data);
        if (typeof data.errRecord != 'undefined') {
            data.errRecord.myArrayList.forEach(function (element) {
                // console.log("ele|");
                // console.log(element);
                let dataid = $('li[data-id="' + element + '"]').children();
                dataid.addClass('errbackground');
            });
        }

        if (typeof data.ticket_pricemsg != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticket_pricemsg + '</div>');
        }

        if (typeof data.ticket_quantitymsg != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticket_quantitymsg + '</div>');
        }

        if (typeof data.end_datemsgeday != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.end_datemsgeday + '</div>');
        }

        if (typeof data.ticket_minmsg != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticket_minmsg + '</div>');
        }

        if (typeof data.ticket_maxmsg != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticket_maxmsg + '</div>');
        }

        if (typeof data.datemsg != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.datemsg + '</div>');
        }
        if (typeof data.ticket_numerr != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticket_numerr + '</div>');
        }
        if (typeof data.ticketTotalOut != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.ticketTotalOut + '</div>');
        }
        if (typeof data.max_dateerr != 'undefined') {
            $('.row5').append('<div style="color:red;margin-left:5px;">' + data.max_dateerr + '</div>');
        }
    }; 
});