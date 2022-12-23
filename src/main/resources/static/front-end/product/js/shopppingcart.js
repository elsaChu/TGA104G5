function init() {
    const shoppingCart = document.querySelector("#shopping__cart");
    // 計算金額
    function calculateCart() {
  
      let totalAmount = 0;
      let totalQty = 0;
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
  console.log($("td.shoping__cart__total").last().attr("subtotal"));
        //   let itemName = $(this)
        //     .siblings("td.shoping__cart__item")
        //     .find("h6")
        //     .text();
        //   // console.log(itemName);
          // let li_subtotal = `<li class="cart__subtotal"><span>${subtotal}</span></li>`;
          // if (subtotal != 0) {
          //   $("li.cart__final__amount").before(li_subtotal);
          // }
        });
  
      $("li.cart__final__amount > span").text(` ${totalAmount}`);
      $("li.cart__final__amount").attr("data-amount", totalAmount);
    //   $("div.header__top__right__cart div.cart__price span").text(
    //     `${totalQty}筆`
    //   );
  
    //   if ($("li.cart__final__amount").is(":nth-child(2)")) {
    //     $("li.cart__final__amount").removeClass("-on");
    //   } else {
    //     $("li.cart__final__amount").addClass("-on");
    //   }
    }
    
    
    $.ajax({
        url: "http://localhost:8080/TGA104G5/cart/memberCart",           // 資料請求的網址
        type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
        // data: { "number": number },                                      // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {                                         // request 成功取得回應後執行
            console.log(data);
            shoppingCart.innerHTML =
                data.map((e) => Template(e.prodNo, e.prodName, e.prodSpec, e.unitPrice, e.shoppingQty)).join('');
            // 顯示購買數量調整按鈕
            var proQty = $('.pro-qty');
            proQty.prepend('<span class="dec qtybtn">-</span>');
            proQty.append('<span class="inc qtybtn">+</span>');
            proQty.on('click', '.qtybtn', function () {
                var $button = $(this);
                var oldValue = $button.parent().find('input').val();
                if ($button.hasClass('inc')) {
                    var newVal = parseFloat(oldValue) ;
                } else {
                    // Don't allow decrementing below zero
                    if (oldValue > 0) {
                        var newVal = parseFloat(oldValue) ;
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
    


}

window.onload = init;

function Template(prodNo, prodName, prodSpec, unitPrice, shoppingQty) {
    return `
    <tr data-prodNo="${prodNo}">
        <td class="shoping__cart__item">
            <img src="../../product/mainPic?prodNo=${prodNo}" id="mainPic" alt="">
            <h5 id="name">${prodName} <br> ${prodSpec}</h5>
        </td>
        <td class="shoping__cart__price" price="${unitPrice}">
            $ ${unitPrice}
        </td>
        <td class="shoping__cart__quantity">
            <div class="quantity">
                <div class="pro-qty">
                    <input type="text" value="${shoppingQty}">
                </div>
            </div>
        </td>
        <td class="shoping__cart__total">
            $${unitPrice * shoppingQty}
        </td>
        <td class="shoping__cart__item__close">
            <span class="icon_close"></span>
        </td>
    </tr>
    `;
}

$(function () {

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
        prodNo: parseInt($(this).closest("tr").attr("data-prodNo")),
        prodQty: parseInt($(this).siblings("input").val()),
      };
      
      $.ajax({
        url: "../../cart/addToCart",
        type: "POST",
        contentType: "application/json",
        data: data,
        dataType: "json",
        success: function (data) {
          console.log(data);
        },
        error: function (xhr) {
            console.log(xhr);
        }
    });

    //   fetch("../../cart/addToCart", {
    //     method: "POST",
    //     body: JSON.stringify(data),
    //     headers: {'content-type': 'application/json'}
    //   })
    // .then((r) => r.json())
    //   .then((data) => {
    //     // console.log(data);

    //   });
      


  });


})


  

// function showDetail(prodOrderNo) {
//     sessionStorage.setItem('prodOrderNo', prodOrderNo);
//     // location = 'orderdetail.html#tab-4';
//     location = 'orderdetail1.html#tab-4';
// }

