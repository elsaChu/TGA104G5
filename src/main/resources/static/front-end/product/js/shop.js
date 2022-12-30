/*
 * 要寫一個算評價星星數的方法
 * 分頁
 * 商品分類 
*/
function init() {

  function checklogin(){
		$.ajax({
        url: '../member/MemberServlet',
        method: 'POST',
        dataType: 'json',
        data: { action: 'checkLogin'},
        success: function (data) {
			// console.log(data);
			if(data.check == '2'){
				$('#login_menuB').show();
				$('#login_menuA').hide();
			}else{
				$('#login_menuA').show();
				$('#login_menuB').hide();
			}
        }
    });
	}
	checklogin();


  const prodarea = document.querySelector("#prodarea");
  const prodQty = document.querySelector("#prodQty");
  const categories = document.querySelector("#categories");

  // 載入頁面時先從後端取得已上架商品
  $.ajax({
    url: "../../product/launch", 
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

  // 載入頁面時先從後端取得商品分類
  fetch(`../../product/categories`)
		.then((resp) => resp.json())
		.then(function(data){
			console.log(data);
        let categoriesStr = "";
        for(let i = 0; i < data.length; i++){
          categoriesStr += `<li><a href="#">${data[i]}</a></li>`;
        }
        categories.innerHTML = categoriesStr;
		});

}

window.onload = init;

function Template(prodNo, prodName, prodSpec, unitPrice) {
  return `
        <div class="col-lg-4 col-md-6 col-sm-6">
                          <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="https://fujifilm-x.com/wp-content/uploads/2021/01/gfx100s_sample_01_thum.jpg" 
                                style="background-image: url('../../product/mainPic?prodNo=${prodNo}');" data-prodNo="${prodNo}">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6 data-prodNo="${prodNo}"><a href="#">${prodName}</a></h6>
                                    <h6><a href="#">${prodSpec}</a></h6>
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
  $("#prodarea").on("click",".product__item > div.product__item__pic", function (e) {
     console.log($(this).attr("data-prodNo"));
     $(this).attr("data-prodNo");
   showDetail($(this).attr("data-prodNo"));
  });
  // 點商品名稱進入商品明細頁面
  $("#prodarea").on("click",".product__item > div.product__item__text > h6", function (e) {
    e.preventDefault();
    console.log($(this).attr("data-prodNo"));
    $(this).attr("data-prodNo");
    showDetail($(this).attr("data-prodNo"));
  }
  );
});



// 點活動分類時更新顯示商品
$("#categories").on("click", "li > a", function(){
  let data = {
    "eventType": document.querySelector("#categories li a").innerText
    };
    console.log(this);
  fetch("../../product/eventType", {
    method: "POST",
    body: JSON.stringify(data),
    headers: { 'content-type': 'application/json' }
  })
		.then((resp) => resp.json())
		.then(function(data){
			prodarea.innerHTML =
        data.map((e) => Template(e.prodNo, e.prodName, e.prodSpec, e.unitPrice, e.eventName, e.isPOn, e.eventType, e.commentQty, e.totalComment)).join('');
      console.log(data);
      console.log(data.length);
      prodQty.innerHTML = `<h6><span>Products ${data.length} found</span></h6>`;
		});
});
