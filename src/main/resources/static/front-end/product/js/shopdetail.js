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
				sessionStorage.setItem("URL_before_login", window.location.href);
			}else{
				$('#login_menuA').show();
				$('#login_menuB').hide();
			}
        }
    });
	}
	checklogin();


	
    let prodNo = sessionStorage.getItem('prodNo');
	// console.log(prodNo);
	// 從網址抓取prodNo
    if(!prodNo){
		let getUrlString = location.href;
		let url = new URL(getUrlString);
		prodNo = parseInt(url.searchParams.get('prodNo'));
		// console.log(prodNo);
	} else {
		prodNo = sessionStorage.getItem('prodNo');
		sessionStorage.removeItem('prodNo');
	}
	
    const prodarea = document.querySelector("#prodarea");
	const mainPic = document.querySelector("#mainPic");
	const picture = document.querySelector("#picture");

	fetch(`../../product/detail?prodNo=${prodNo}`)
		.then((resp) => resp.json())
		.then(function(data){
			prodarea.innerHTML = Template1(data);
		});


	// 顯示主要圖片
	mainPic.innerHTML = `
		<img class="product__details__pic__item--large"
			src="../../product/mainPic?prodNo=${prodNo}" alt="">
		`;	

	// 取得下方小圖編號
	let imageList = [];
	fetch(`../../product/findImageId?prodNo=${prodNo}`)
		.then((resp) => resp.json())
		.then(function(data){
			imageList = data;
            // console.log(data);
			// 顯示圖片
			let imageStr = "";
			for(let i = 0; i < imageList.length; i++){
				imageStr += `
					<img data-imgbigurl="../../product/findPictureById?prodIMGID=${imageList[i]}"
						src="../../product/findPictureById?prodIMGID=${imageList[i]}" alt="">
				`;
			};
			picture.innerHTML = imageStr;

			$('.pic__slider img').on('click', function () {

				var imgurl = $(this).data('imgbigurl');
				var bigImg = $('.product__details__pic__item--large').attr('src');
				if (imgurl != bigImg) {
					$('.product__details__pic__item--large').attr({
						src: imgurl
					});
				}
			});
		});


	// 加入購物車
	$("#prodarea").on("click", "a.primary-btn", function(e){
		e.preventDefault();
		if($(this).hasClass("added")) {
		return;
		}

		let data = {
		//   "number": number,
		"prodNo": parseInt(prodNo),
		"shoppingQty": parseInt($("div.pro-qty input").val()) 
		};
		// console.log(data);

		fetch("../../cart/addToCart", {
		method: "POST",
		body: JSON.stringify(data),
		headers: {'content-type': 'application/json'}
		})
		.then((r) => {
			if(r.redirected) {
			Swal.fire({
				position: "center",
				icon: "warning",
				title: "請先登入",
				showConfirmButton: false,
				timer: 1000,
			}).then(()=>{
				$(this).closest("div").removeClass("added");
				sessionStorage.setItem("URL_before_login", window.location.href);
				window.location.href = r.url;
			});
			} else {
			return r.json();
			}
		})
		
		?.then((data) => {
		// console.log(data);
		$(this).closest("div").addClass("added");
		$(this).text("已加入購物車");
		});
	});


	// 顯示商品資訊
	function Template1({prodName, totalcomment, eventName, prodSpec, unitPrice, prodStock, prodDetails, commentQty}) {
		// console.log(totalcomment); // 沒有被買過的商品會顯示null 先取消此功能
		let avgComment = (totalcomment / commentQty) * 20;
		// console.log(avgComment);
		return `
			<div class="product__details__text">
				<h3>${prodName}</h3>
				<p id="eventName">${eventName}</p>
				
				<div class="product__details__price" id="product__details__price">
					<h5>$ ${unitPrice}</h5>
					<h5>商品規格： ${prodSpec}</h5>
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


