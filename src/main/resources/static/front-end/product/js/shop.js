/*
 * 要寫一個算評價星星數的方法
 * 分頁?? 
 * 加入購物車
 * 顯示查詢筆數
*/
function init() {

  const prodarea = document.querySelector("#prodarea");
  const prodQty = document.querySelector("#prodQty");

  // 載入頁面時先從後端取得資料
  $.ajax({
    url: "http://localhost:8080/TGA104G5/product/all", //要換api
    type: "GET",
    data: "",
    dataType: "json",
    success: function (data) {

      prodarea.innerHTML =
        data.map((e) => Template(e.prodNo, e.prodName, e.unitPrice, e.eventName, e.eventType, e.commentQty, e.totalComment)).join('');
      console.log(data);
      console.log(data.length);
      prodQty.innerHTML = data.length;
    },
    error: function (xhr) {
      console.log(xhr);
    },
    complete: function (data) {
      let Qty = data.length;
      console.log(Qty);
      // prodQty.innerHTML = Qty;

    },
  });

}

window.onload = init;

function Template(prodNo, prodName, unitPrice) {
  return `
        <div class="col-lg-4 col-md-6 col-sm-6"  id="categories">
          <div class="product__item">
              <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg"> <!-- 商品圖片 -->

                  <ul class="product__item__pic__hover">
                      <li class="cart_add" data-prodNo="${prodNo}"><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                  </ul>
              </div>
              <div class="product__item__text">
                  <h6><a href="#">${prodName}</a></h6>
                  <h5>$ ${unitPrice}</h5>
              </div>
          </div>
        </div>
        `;
}

function shopDeatil(prodNo) {
  window.location.href = `./shopdetail.html?prodNo=${prodNo}`;
}

$(function () {
  // 點圖片進入商品明細頁面
  $("#prodarea").on("click", "div.product__item__pic", function (e) {
    e.preventDefault();
    // console.log($(this).attr("data-prodNo"));
    shopDeatil($(this).attr("data-prodNo"));
  });

  // 點商品名稱進入商品明細頁面
  $("#prodarea").on("click", "div.product__item__text > h6 > a", function (e) {
    e.preventDefault();
    shopDeatil($(this).attr("data-prodNo"));
    // console.log($(this).attr("data-prodNo"));
  }
  );
});
