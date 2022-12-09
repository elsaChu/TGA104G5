$.datetimepicker.setLocale('zh'); // kr ko ja en

//let hdata = document.getElementById("hiddData").value;
let hidd = document.getElementById("hiddData");

let hdata = hidd.value;
//console.log(hdata);
// console.log(typeof hdata);
let toobj = JSON.parse(hdata);
console.log(toobj);
toobj.ticket = [];
// console.log(toobj);
let count = 0;
$(function () {
    $('#start_date').datetimepicker({
        theme: '',
        timepicker: true,
        step: 15,
        format: 'Y-m-d H:i:s',
        //	  value: new Date(),
        onShow: function () {
            this.setOptions({
                maxDate: $('#end_date').val() ? $('#end_date').val() : false
            })
        }
    });

    $('#end_date').datetimepicker({
        theme: '',
        timepicker: true,
        step: 15,
        format: 'Y-m-d H:i:s',
        //	  value: new Date(),
        onShow: function () {
            this.setOptions({
                minDate: $('#start_date').val() ? $('#start_date').val() : false
            })
        }
    });

    //add data
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
                ticket_name: ticket_name,
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
            $("input.ticket_name").val("");
            $("input.ticket_price").val("");
            $("input.ticket_quantity").val("");
            $("input#start_date").val("");
            $("input#end_date").val("");
            $("input.ticket_min").val("");
            $("input.ticket_max").val("");
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
    
//submit form
    let sub = document.getElementById("sub");
    sub.addEventListener("click", function () {
        let hid = document.getElementById("hiddData");
        hidd.value = JSON.stringify(toobj);
        //        console.log(hid);
        console.log(hidd.value);
        let forName = document.getElementById("formNa");
        forName.submit();
    });
});