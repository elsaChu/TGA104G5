function init() {

    const prodNo = sessionStorage.getItem('prodNo');
    // sessionStorage.removeItem('prodNo');
    const prodarea = document.querySelector("#prodarea");
	const mainPic = document.querySelector("#mainPic");
	const picture = document.querySelector("#picture");

    $.ajax({
        url: "../../product/detail",
        type: "GET",
        data: { "prodNo": prodNo },
        dataType: "json",
        success: function (data) {

            prodarea.innerHTML = Template1(data);
            console.log(data.length);
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
    });
	

	// 顯示下方小圖(未完成)
	$.ajax({
        url: "../../product/findImageId",
        type: "GET",
        data: { "prodNo": prodNo },
        dataType: "json",
        success: function (data) {
			
            let imageId = data;
            console.log(data);
			console.log(data[0]);
			picture.innerHTML = "";
			// 小圖區域不見了O_O
			for(i = 0; i < imageId.length; i++) {
				picture.innerHTML = Template2(imageId[i]);
			};
			
           
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
    });

	// 顯示主要圖片
	mainPic.innerHTML = `
			<img class="product__details__pic__item--large"
			src="../../product/mainPic?prodNo=${prodNo}" alt="">
			  `;
	

}

// 顯示商品資訊
function Template1({prodName, totalcomment, eventName, unitPrice, prodDetails, commentQty}) {
	console.log(totalcomment);
	let avgComment = (totalcomment / commentQty) * 20;
	// console.log(avgComment);
    return `
		<div class="product__details__text">
			<h3>${prodName}</h3>
			<p id="eventName">${eventName}</p>
			<div class="product__details__rating">
				<div class="product__rating">
					<div class="fill-ratings" style="width: ${avgComment}%">
					<span>★★★★★</span>
					</div>
					<div class="empty-ratings">
					<span>★★★★★</span>
					</div>
				</div>
				<span style="display:block" class="commit">${commentQty}則評論</span>
			</div>
			<div class="product__details__price" id="product__details__price">$ ${unitPrice}</h5>
            </div>
			
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
					<p id="prodDetails">${prodDetails}</p>
				</div>
			</div>	
		</div>
      `;
}


function Template2({prodIMGID}) {
	
    return `
		<img data-imgbigurl=""../../product/findPictureById?prodIMGID=${prodIMGID}"
		src="../../product/findPictureById?prodIMGID=${prodIMGID}" alt="">
      `;
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
	console.log(aaa);
}, 1000)

window.onload = init;
