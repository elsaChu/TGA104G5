function init() {

    const prodNo = sessionStorage.getItem('prodNo');
    // sessionStorage.removeItem('prodNo');
    const prodarea = document.querySelector("#prodarea");


    $.ajax({
        url: "../../product/detail",
        type: "GET",
        data: { "prodNo": prodNo },
        dataType: "json",
        success: function (data) {

            prodarea.innerHTML = Template1(data);
            console.log(data);
            console.log(data.length);
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
    });

}


function Template1({prodName, totalcomment, eventName, unitPrice, prodDetails, commentQty}) {
	console.log(totalcomment);
	let avgComment = (totalcomment / commentQty) * 20;
	// console.log(avgComment);
    return `
		<div class="product__details__text">
			<h3>${prodName}</h3>
			<div class="product__details__rating">
				<div class="product__rating">
					<div class="fill-ratings" style="width: ${avgComment}%">
					<span>★★★★★</span>
					</div>
					<div class="empty-ratings">
					<span>★★★★★</span>
					</div>
				</div>
				<span style="display:block" class="commit">(${totalcomment}則評論)</span>
			</div>
			<div class="product__details__price">$ ${unitPrice}</h5>
            </div>
			<p>${eventName}</p>
			<div class="product__details__quantity">
				<div class="quantity">
					<div class="pro-qty">
						<span class="dec qtybtn" id="dec">-</span>
						<input id="qtyVal" type="text" value="1">
						<span class="inc qtybtn" id="inc">+</span>
					</div>
				</div>
			</div>
				<a href="#" class="primary-btn">加入購物車</a>
				<a href="#" class="heart-icon"><span class="icon_heart_alt"></span></a>
			<div>
				<span>${prodDetails}</span>
			</div>
		</div>
      `;

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
}

setTimeout(() => {
	let aaa = document.getElementById('dec')
	let qtyVal = document.getElementById('qtyVal')
	aaa.addEventListener('click', () => {
		qtyVal.value--
	})

	let bbb = document.getElementById('inc')
	bbb.addEventListener('click', () => {
		qtyVal.value++
	})
	
}, 1000)

window.onload = init;
