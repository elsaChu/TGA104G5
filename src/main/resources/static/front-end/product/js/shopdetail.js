function init() {

    const prodNo = sessionStorage.getItem('prodNo');
    sessionStorage.removeItem('prodNo');
    const prodarea = document.querySelector("#prodarea");


    // 載入頁面時先從後端取得資料
    $.ajax({
        url: "http://localhost:8080/TGA104G5/product/detail",
        type: "GET",
        data: { "prodNo": prodNo },
        dataType: "json",
        success: function (data) {

            prodarea.innerHTML =
                data.map((e) => Template(e.prodNo, e.prodName, e.unitPrice, e.eventName, e.eventType, e.prodDetails, e.commentQty, e.totalComment)).join('');
            console.log(data);
            console.log(data.length);
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
    });

}

window.onload = init;

function Template(prodName, totalComment, eventName, unitPrice, prodDetails) {
    return `
      
		<div class="product__details__text">
			<h3>${prodName}</h3>
			<div class="product__details__rating">
				<i class="fa fa-star"></i>
				<i class="fa fa-star"></i>
				<i class="fa fa-star"></i>
				<i class="fa fa-star"></i>
				<i class="fa fa-star-half-o"></i>
				<span>(${totalComment}則評論)</span>
			</div>
			<div class="product__details__price">$ ${unitPrice}</h5>
            </div></div>
			<p>${eventName}</p>
			<div class="product__details__quantity">-
				<div class="quantity">
					<div class="pro-qty">
						<input type="text" value="1">
					</div>
				</div>
			</div>
				<a href="#" class="primary-btn">加入購物車</a>
				<a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
			<div>
				<span>${prodDetails}</span>
			</div>
		</div>      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
//      
//        <div class="product__item">
//            <div class="product__item__pic set-bg" data-setbg="img/product/product-1.jpg"> <!-- 商品圖片 -->
//
//                <ul class="product__item__pic__hover">
//                    <li class="cart_add" data-prodNo="${prodNo}"><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
//                </ul>
//            </div>
//            <div class="product__item__text">
//                <h6><a href="#">${prodName}</a></h6>
//                <h5>$ ${unitPrice}</h5>
//            </div>
//        </div>
//      	</div>
//	      <a href="#" class="primary-btn">加入購物車</a>
//	      <a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
//	      <div>
//		      <span>${prodDetails}</span>                  
//	      </div>
//	    </div>
	      
      `;
}