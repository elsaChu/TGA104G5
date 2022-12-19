function init() {

    const prodarea = document.querySelector("#prodarea");


    // 載入頁面時先從後端取得資料
    $.ajax({
        url: "http://localhost:8080/TGA104G5/product/detail",
        type: "GET",
        data: { "prodNo": prodNo },
        dataType: "json",
        success: function (data) {

            prodarea.innerHTML =
                data.map((e) => Template(e.prodNo, e.prodName, e.unitPrice, e.eventName, e.eventType, e.commentQty, e.totalComment)).join('');
            console.log(data);
            console.log(data.length);
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
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