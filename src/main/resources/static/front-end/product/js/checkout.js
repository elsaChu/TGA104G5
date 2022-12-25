$(window).on("load", function () {
    var check_order = function () {
        const orderList = JSON.parse(
            sessionStorage.getItem("orderDetail")
        );
        // console.log(orderList);
        let myOrderStr = "";
        let myPayment = 0;
        const myorder = document.querySelector("#myorder");
        const payment = document.querySelector("#payment");
        for (let i = 0; i < orderList.length; i++) {
            myOrderStr += `
            <li>${orderList[i].prodName}, ${orderList[i].prodSpec} - ${orderList[i].prodQty}
                <span>$ ${orderList[i].subtotal}</span>
            </li>
            `;
            myPayment += orderList[i].subtotal;
        }
        // console.log(str);
        myorder.innerHTML = myOrderStr; 
        payment.innerHTML = `付款金額 <span>$ ${myPayment}</span>`;

    };
    check_order();
});

document.querySelector("#sitebtn").addEventListener("click", function () {
    const prodOrderVO = JSON.parse(sessionStorage.getItem("prodOrderVO"));
    console.log(prodOrderVO);

    let receiverName_el = document.querySelector("#receiverName").value;
    let receiverTel_el = document.querySelector("#receiverTel").value;
    let shippingAdd_el = document.querySelector("#shippingAdd").value;

    prodOrderVO.receiverName = receiverName_el;
    prodOrderVO.receiverTel = receiverTel_el;
    prodOrderVO.shippingAdd = shippingAdd_el;
    sessionStorage.setItem("prodOrderVO", JSON.stringify(prodOrderVO));

    const orderDetailList = JSON.parse(sessionStorage.getItem("orderDetail"));

    const insertOrder = {
        "productOrderVO" : prodOrderVO,
        "orderDetailList" : orderDetailList
    }

    $.ajax({
        url: "http://localhost:8080/TGA104G5/order/addProdOrder",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(insertOrder),
        dataType: "json",
        success: function (a) {
            console.log(a);
        },
    });
});



