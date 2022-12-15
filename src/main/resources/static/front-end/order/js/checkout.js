// submit 收件資訊
$("#btn_checkout").on("click", function(e){
    e.preventDefault();
    var send_data = {};

    
    let name = $("#receiverName").val();
    let phone = $("#receiverTel").val();
    let address = $("#shippingAdd").val();

    if(name != ""){
        send_data.name = name;
    }else{
        alert("請輸入收件人姓名");
        return;
    }
    
       if(phone != ""){
        send_data.phone = phone;
    }else{
        alert("請輸入正確的手機號碼");
        return;
    }

    if(address != ""){
       send_data.address = address;
    }else{
        alert("請輸入收件地址");
        return;
    }

    send_data.name = $("#receiverName").val();
    send_data.phone = $("#receiverTel").val();
    send_data.address = $("#shippingAdd").val();

    console.log(send_data);

});