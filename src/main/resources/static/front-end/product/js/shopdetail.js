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
          
            
        },
        error: function (xhr) {
            console.log(xhr);
        }
  
    });
	

	// 顯示下方小圖(未完成)
	// $.ajax({
    //     url: "../../product/findImageId",
    //     type: "GET",
    //     data: { "prodNo": prodNo },
    //     dataType: "json",
    //     success: function (data) {
			
    //         let imageId = data;
    //         console.log(data);
	// 		console.log(data[0]);
	// 		picture.innerHTML = "";
			
	// 		for(i = 0; i < imageId.length; i++) {
	// 			picture.innerHTML = Template2(imageId[i]);
	// 		};
			
           
            
    //     },
    //     error: function (xhr) {
    //         console.log(xhr);
    //     }
  
    // });

	// 顯示主要圖片
	mainPic.innerHTML = `
			<img class="product__details__pic__item--large"
			src="../../product/mainPic?prodNo=${prodNo}" alt="">
			  `;


// 加入購物車
$("#prodarea").on("click", "a.primary-btn", function(e){
    e.preventDefault();
    if($(this).hasClass("added")) {
      return;
    }

// console.log("ddd");

    let data = {
	//   "number": number,
      "prodNo": parseInt(prodNo),
      "shoppingQty": parseInt($("div.pro-qty input").val()) 
    };

console.log(data);
	// $.ajax({
    //     url: "http://localhost:8080/TGA104G5/cart/addToCart",
    //     type: "POST",
	// 	contentType: "application/json",
    //     data: data,
    //     dataType: "json",
    //     success: function (data) {	
	// 		console.log(data);
    //     },
    //     // error: function (xhr) {
    //     //     console.log(xhr);
    //     // }

    // });


	fetch("../../cart/addToCart", {
      method: "POST",
      body: JSON.stringify(data),
      headers: {'content-type': 'application/json'}
    })
	.then((r) => r.json())
    .then((data) => {
      console.log(data);
      $(this).closest("div").addClass("added");
      $(this).text("已加入購物車");
	});
  });
	
  


// 顯示商品資訊
function Template1({prodName, totalcomment, eventName, unitPrice, prodStock, prodDetails, commentQty}) {
	// console.log(totalcomment); // 沒有被買過的商品會顯示null 先取消此功能
	let avgComment = (totalcomment / commentQty) * 20;
	// console.log(avgComment);
    return `
		<div class="product__details__text">
			<h3>${prodName}</h3>
			<p id="eventName">${eventName}</p>
			
			<div class="product__details__price" id="product__details__price">
				<h5>$ ${unitPrice}</h5>
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
				<a href="#" class="primary-btn" data-prodStock="${prodStock}">加入購物車</a>
				
				<div>
					<p id="prodDetails">${prodDetails}</p>
				</div>
			</div>	
		</div>
      `;

	// 有評論星星的版本
	// return `
	// 	<div class="product__details__text">
	// 		<h3>${prodName}</h3>
	// 		<p id="eventName">${eventName}</p>
	// 		<div class="product__details__rating">
	// 			<div class="product__rating">
	// 				<div class="fill-ratings" style="width: ${avgComment}%">
	// 				<span>★★★★★</span>
	// 				</div>
	// 				<div class="empty-ratings">
	// 				<span>★★★★★</span>
	// 				</div>
	// 			</div>
	// 			<span style="display:block" class="commit">${commentQty}則評論</span>
	// 		</div>
	// 		<div class="product__details__price" id="product__details__price">$ ${unitPrice}</h5>
	// 		</div>
			
	// 		<div class="product__details__quantity">
	// 			<div class="quantity">
	// 				<div class="pro-qty">
	// 					<span class="dec qtybtn" id="dec">-</span>
	// 					<input id="qtyVal" type="text" value="1">
	// 					<span class="inc qtybtn" id="inc">+</span>
	// 				</div>
	// 			</div>
	// 		</div>
	// 			<a href="#" class="primary-btn">加入購物車</a>
	// 		<div>
	// 			<p id="prodStock">剩餘數量：${prodStock}</p>
	// 		</div>
	// 			<div>
	// 				<p id="prodDetails">${prodDetails}</p>
	// 			</div>
	// 		</div>	
	// 	</div>
	// `;	
	  
}

// 這個小圖要跑迴圈
// function Template2({prodIMGID}) {
	
//     return `
// 		<img data-imgbigurl=""../../product/findPictureById?prodIMGID=${prodIMGID}"
// 		src="../../product/findPictureById?prodIMGID=${prodIMGID}" alt="">
//       `;
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
	// console.log(aaa);
}, 1000)

window.onload = init;


