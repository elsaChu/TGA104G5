function init() {
    const shoppingCart = document.querySelector("#shopping__cart");
    $.ajax({
        url: "http://localhost:8080/TGA104G5/shoppingCart/memberCart",           // 資料請求的網址
        type: "GET",                                                     // GET | POST | PUT | DELETE | PATCH
        // data: { "number": number },                                      // 將物件資料(不用雙引號) 傳送到指定的 url
        dataType: "json",                                                // 預期會接收到回傳資料的格式： json | xml | html
        success: function (data) {                                         // request 成功取得回應後執行
            console.log(data);
            shoppingCart.innerHTML =
                data.map((e) => Template(e.prodNo, e.prodName, e.prodSpec, e.unitPrice, e.shoppingQty)).join('');
            // 購買數量調整按鈕
            var proQty = $('.pro-qty');
            proQty.prepend('<span class="dec qtybtn">-</span>');
            proQty.append('<span class="inc qtybtn">+</span>');
            proQty.on('click', '.qtybtn', function () {
                var $button = $(this);
                var oldValue = $button.parent().find('input').val();
                if ($button.hasClass('inc')) {
                    var newVal = parseFloat(oldValue) + 1;
                } else {
                    // Don't allow decrementing below zero
                    if (oldValue > 0) {
                        var newVal = parseFloat(oldValue) - 1;
                    } else {
                        newVal = 0;
                    }
                }
                $button.parent().find('input').val(newVal);
            });
        },

    });



}

window.onload = init;

function Template(prodNo, prodName, prodSpec, unitPrice, shoppingQty) {
    return `
    <tr>
        <td class="shoping__cart__item">
            <img src="" alt="">
            <h5>${prodName} | ${prodSpec}</h5>
        </td>
        <td class="shoping__cart__price">
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

// function showDetail(prodOrderNo) {
//     sessionStorage.setItem('prodOrderNo', prodOrderNo);
//     // location = 'orderdetail.html#tab-4';
//     location = 'orderdetail1.html#tab-4';
// }

