$(window).on("load", function(){
    var check_order = function(){
        const orderDetail = JSON.parse(
            sessionStorage.getItem("orderDetail")
        );
        console.log(orderDetail);
       // 頁面顯示訂單明細之後再寫

    };
    check_order();
});

document.querySelector("#sitebtn").addEventListener("click", function(){
    const prodOrderVO = JSON.parse(sessionStorage.getItem("prodOrderVO"));
    console.log(prodOrderVO);

    let receiverName_el = document.querySelector("#receiverName").value;
    let receiverTel_el = document.querySelector("#receiverTel").value;
    let shippingAdd_el = document.querySelector("#shippingAdd").value;

    prodOrderVO.receiverName = receiverName_el;
    prodOrderVO.receiverTel = receiverTel_el;
    prodOrderVO.shippingAdd = shippingAdd_el;
    sessionStorage.setItem("prodOrderVO", JSON.stringify(prodOrderVO));

    

    $.ajax({
        url: "http://localhost:8080/TGA104G5/order/addProdOrder",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(prodOrderVO),
        dataType: "json",
        success: function (a) {
          console.log(a);
        },
      });
});



