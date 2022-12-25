window.onload = init;
function init() {
  const shoppingCart = document.querySelector("#shopping__cart");
  // 計算金額
  function calculateCart() {

    let totalAmount = 0;
    let totalQty = 0;
    let prodQty = 0;
    $("#shopping__cart td.shoping__cart__total")
      .each(function (index, item) {
        let price = parseInt(
          $(item).siblings("td.shoping__cart__price").attr("price")
        );
        let quantity = parseInt(
          $(item).siblings("td.shoping__cart__quantity").find("input").val()
        );
        let subtotal = price * quantity;

        $(item).html(`$ ${subtotal}`);
        $(item).attr("subtotal", subtotal);
        totalAmount += subtotal;
        totalQty += quantity;
        prodQty += quantity;

      });

    $("li.cart__final__amount > span").text(`$ ${totalAmount}`);
    $("li.cart__final__amount").attr("data-amount", totalAmount);
    $("li.cart__final__amount").attr("data-quantity", prodQty);

  }


  $.ajax({
    url: "http://localhost:8080/TGA104G5/cart/memberCart",           // 資料請求的網址
    type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
    // data: { "number": number },                                      // 將物件資料(不用雙引號) 傳送到指定的 url
    dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
    success: function (data) {                                         // request 成功取得回應後執行
      console.log(data);
      shoppingCart.innerHTML =
        data.map((e) => Template(e.shoppingCartNo, e.prodNo, e.prodName, e.prodSpec, e.unitPrice, e.shoppingQty)).join('');
      // 顯示購買數量調整按鈕
      var proQty = $('.pro-qty');
      proQty.prepend('<span class="dec qtybtn">-</span>');
      proQty.append('<span class="inc qtybtn">+</span>');
      proQty.on('click', '.qtybtn', function () {
        var $button = $(this);
        var oldValue = $button.parent().find('input').val();
        if ($button.hasClass('inc')) {
          var newVal = parseFloat(oldValue);
        } else {
          // Don't allow decrementing below zero
          if (oldValue > 0) {
            var newVal = parseFloat(oldValue);
          } else {
            newVal = 0;
          }
        }
        $button.parent().find('input').val(newVal);
      });


      console.log($("#shopping__cart td.shoping__cart__total"));
      calculateCart();

    },

  });

  const price = document.querySelector("td.shoping__cart__price");
  console.log(price);
  console.log()







  function Template(shoppingCartNo, prodNo, prodName, prodSpec, unitPrice, shoppingQty) {
    return `
    <tr data-prodNo="${prodNo}">
        <td class="shoping__cart__item">
            <img src="../../product/mainPic?prodNo=${prodNo}" id="mainPic" alt="">
            <h5><div id="prodName">${prodName}</div><div id="prodSpec">${prodSpec}</div></h5>
        </td>
        <td class="shoping__cart__price" price="${unitPrice}">
            $ ${unitPrice}
        </td>
        <td class="shoping__cart__quantity">
            <div class="quantity">
                <div class="pro-qty">
                    <input type="text" id="shoppingQty" value="${shoppingQty}">
                </div>
            </div>
        </td>
        <td class="shoping__cart__total" subtotal = "${unitPrice * shoppingQty}" >
            $${unitPrice * shoppingQty}
        </td>
        <td class="shoping__cart__item__close">
            <span id="remove" data-shoppingCartNo="${shoppingCartNo}" class="icon_close"></span>
        </td>
    </tr>
    `;
  }



  // 更改數量
  $("div.shoping__cart__table").on("click", ".qtybtn", function () {
    var $button = $(this);
    var oldValue = $button.parent().find("input").val();
    if ($button.hasClass("inc")) {
      var newVal = parseFloat(oldValue) + 1;
      // console.log('xxx');
    } else {
      // Don't allow decrementing below zero
      if (oldValue > 0) {
        var newVal = parseFloat(oldValue) - 1;
      } else {
        newVal = 0;
      }
    }
    $button.parent().find("input").val(newVal);
    calculateCart();
    let data = {
      "prodNo": parseInt($(this).closest("tr").attr("data-prodNo")),
      "shoppingQty": parseInt($(this).siblings("input").val())
    };
    sessionStorage.setItem("cart", data);
    console.log(data);
    $.ajax({
      url: "../../cart/addToCart",
      type: "POST",
      contentType: "application/json",
      data: JSON.stringify(data),
      dataType: "json",
      success: function (data) {
        console.log(data);
      },
      error: function (xhr) {
        console.log(xhr);
      }
    });
  });


  // 立即結帳
  document.querySelector("#primary-btn").addEventListener("click", function(){
    console.log("...");
    // e.preventDefault();
    console.log("!!!");
    // 購物車商品存入sessionStorage
    const order_list = [];
    const result = document.querySelectorAll("tr[data-prodno]");
    
    for(let i = 0; i < result.length; i++){
      let prodNo = parseInt(result[i].getAttribute("data-prodNo"));
      let prodName = result[i].querySelector("#prodName").innerText;
      let prodSpec = result[i].querySelector("#prodSpec").innerText;
      let prodQty = parseInt(result[i].querySelector("#shoppingQty").value);
      let subtotal = parseInt(result[i].querySelector("td.shoping__cart__total[subtotal]").getAttribute("subtotal"));

      const data = {};
      data["prodNo"] = prodNo;
      data["prodName"] = prodName;
      data["prodSpec"] = prodSpec;
      data["prodQty"] = prodQty;
      data["subtotal"] = subtotal;

      order_list.push(data);
    }
    console.log("list result: ", order_list);

    // const prodOrderVO = [];
    let prodTotal = parseInt(document.querySelector("li.cart__final__amount[data-quantity]").getAttribute("data-quantity"));
    let amountPrice = parseInt(document.querySelector("li.cart__final__amount[data-amount]").getAttribute("data-amount"));
    let receiverName = null;
    let receiverTel = null;
    let shippingAdd = null;

    const data1 = {};
    data1["prodTotal"] = prodTotal;
    data1["amountPrice"] = amountPrice;
    data1["receiverName"] = receiverName;
    data1["receiverTel"] = receiverTel;
    data1["shippingAdd"] = shippingAdd;

    // prodOrderVO.push(data1);
    // console.log(prodOrderVO);
    
    



    

      sessionStorage.setItem("prodOrderVO", JSON.stringify(data1));
      sessionStorage.setItem("orderDetail", JSON.stringify(order_list));
      window.location.href = "./checkout.html";

  });

  // 移除購物車商品(未完成)
  document.querySelector("#remove").addEventListener("click", function(){
    let shoppingCartNo = parseInt(
      document.querySelector("#remove").getAttribute("data-shoppingCartNo"));

    $.ajax({
      url: "http://localhost:8080/TGA104G5/cart/remove?shoppingCartNo",
      type: "GET",
      data: {"shoppingCartNo": shoppingCartNo},
      dataType: "json",
      success: function (data) {
        console.log(data);
      },
      error: function (xhr) {
        console.log(xhr);
      }
    });

  });




  // 結帳jQuery
  // $("a.primary-btn").on("click", function () {
  //   e.preventDefault();

  //   let order_list = new Array();
  //   $.each($("table tbody tr"), (index, item) => {
  //     let prod_qty = parseInt($(item).find("input").val());
  //     if (prod_qty) {
  //       let order_detail = {
  //       prodNo: parseInt($(item).attr("data-prodNo")),
  //       prodName: $(item).find("h6").text(),
  //       prodQty: prod_qty,
  //       prodPrice: parseInt($(item).find("td.cart__price").attr("subtotal"))
  //       };
  //       order_list[index] = order_detail;
  //     }
  //   });
  //   let data = {
  //     prodOrderVO: {
  //       restaurantNo: parseInt(
  //         $("table.-on > .prod__cart__heads").attr("data-restaurantNo")
  //       ),
  //       restaurantName: $("table.-on .prod__cart__head1").attr("data-restaurantName"),
  //       deliverFee: null,
  //       couponNo: null,
  //       amountBeforeCoupon: parseInt($("li.cart__final__amount").attr("data-amount")),
  //       amountAfterCoupon: parseInt($("li.cart__final__amount").attr("data-amount")),
  //       prodOrderPoint: parseInt($("li.cart__final__amount").attr("data-amount")),
  //       prodOrderReceiverName: null,
  //       prodOrderReceiverTel: null,
  //       prodOrderReceiverMail: null,
  //       prodOrderReceiverAddress: null,
  //     },
  //     orderDetailList: order_list,
  //   };
  //   console.log(data);
  //   sessionStorage.setItem("tempOrder", JSON.stringify(data));
    // window.location.href = "./checkout.html";








    // sessionStorage.setItem("cart", data);
  // });






  
}
