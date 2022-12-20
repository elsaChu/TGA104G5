/*
 * 要寫一個算評價星星數的方法
 * 分頁?? 
 * 加入購物車
 * 顯示查詢筆數
*/
function init() {

  const prodarea = document.querySelector("#prodarea");
  const prodQty = document.querySelector("#prodQty");

  // 載入頁面時先從後端取得已上架商品
  $.ajax({
    url: "http://localhost:8080/TGA104G5/product/launch", 
    type: "GET",
    data: { "isPOn": true },
    dataType: "json",
    success: function (data) {

      prodarea.innerHTML =
        data.map((e) => Template(e.prodNo, e.prodName, e.prodSpec, e.unitPrice, e.eventName, e.isPOn, e.eventType, e.commentQty, e.totalComment)).join('');
      console.log(data);
      console.log(data.length);
      prodQty.innerHTML = `<h6><span>Products ${data.length} found</span></h6>`;
    },
    error: function (xhr) {
      console.log(xhr);
    },
//    complete: function (data) {
//      let Qty = data.length;
//      console.log(Qty);
//      // prodQty.innerHTML = Qty;
//
//    },
  });





}

window.onload = init;

function Template(prodNo, prodName, prodSpec, unitPrice) {
  return `
        <div class="col-lg-4 col-md-6 col-sm-6"  id="categories">
          <div class="product__item" id="product__item">
              <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg"> <!-- 商品圖片 -->

                  <ul class="product__item__pic__hover">
                      <li class="cart_add" data-prodNo="${prodNo}" id=prodno><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                  </ul>
              </div>
              <div class="product__item__text">
                  <h6><a href="#">${prodName}</a></h6>
                  <h6><a >${prodSpec}</a></h6>
                  <h5>$ ${unitPrice}</h5>
              </div>
          </div>
        </div>
        `;
}


function showDetail(prodNo) {
    sessionStorage.setItem('prodNo', prodNo);
    window.location.href = `./shopdetail.html?prodNo=${prodNo}`;
}

$(function () {
  // 點圖片進入商品明細頁面
  $("div.product__item__pic").on("click", function (e) {
    console.log(this);
     console.log($("#prodno").attr("data-prodNo"));
    showDetail($("#prodno").attr("data-prodNo"));
  });

  // 點商品名稱進入商品明細頁面
  $("#product__item").on("click", function (e) {
    e.preventDefault();
    console.log("aa");
    showDetail($("#prodno").attr("data-prodNo"));
     console.log($("#prodno").attr("data-prodNo"));
  }
  );
});
