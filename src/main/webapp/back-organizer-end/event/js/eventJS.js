$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function () {
	$('#start_date').datetimepicker({
		theme: '',
		timepicker: true,
		step: 15,
		format: 'Y-m-d H:i:s',
		//	  value: new Date(),
		onShow: function () {
			this.setOptions({
				maxDate: $('#end_date').val() ? $('#end_date').val() : false
			})
		}
	});

	$('#end_date').datetimepicker({
		theme: '',
		timepicker: true,
		step: 15,
		format: 'Y-m-d H:i:s',
		//	  value: new Date(),
		onShow: function () {
			this.setOptions({
				minDate: $('#start_date').val() ? $('#start_date').val() : false
			})
		}
	});
});

//upload file
// let file_el = document.getElementById("bigImg");

// file_el.addEventListener("change", function () {
// 	//後來的東西與原來的不一樣就會觸發change事件
// 	//觸發change時清空ul裡面的預覽圖
// 	let the_ul = document.getElementsByClassName("picture_list")[0]; //[ul] 加上[0]直接取得ul
// 	the_ul.innerHTML = "";

// 	// console.log(this);
// 	// console.log(this.files); //取得file list
// 	// console.log(this.files[0]); //單獨取得file物件

// 	// console.log(this.files[0].name); //取得物件內屬性內容方法一
// 	// console.log(this.files[0]["name"]); //取得物件內屬性內容方法二

// 	//練習 2：透過 FileReader 將圖片於網頁上預覽(單選)
// 	let reader = new FileReader(); // 用來讀取檔案的物件
// 	// console.log(this.files.length);
// 	if (this.files.length !== 0) {
// 		reader.readAsDataURL(this.files[0]); // 讀取檔案
// 		fetch("",{

// 		});
// 	}

// 	// "檔案"讀取完畢時觸發
// 	// reader.addEventListener("load",() => {
// 	//     console.log(this);                  //箭頭函式 綁定的是外層的file_el物件
// 	// });

// 	reader.addEventListener("load", function () {
// 		// console.log(this); //取得圖片(reader)物件
// 		console.log(this.result); //取得圖片內容

// 		// 可以透過 reader.result 取得圖片讀取完成時的 Base64 編碼格式↓
// 		/*
// 			<li>
// 				<img src="" class="preview">
// 			</li>
// 			希望呈現上述註解↓*/
// 		//寫法一
// 		// let li_el = "<li>";
// 		// li_el += '<img src="' + this.result + '" class="preview">';
// 		// li_el += "</li>";

// 		//寫法二
// 		let li_el = `
// 			  <li>
// 				  <img src="${this.result}" class="preview">
// 			  </li>
// 		  `;
// 		//抓取想要放入的HTML標籤 並將內容放入標籤內
// 		let ul_el = document.getElementsByClassName("picture_list")[0]; //[ul] 加上[0]直接取得ul
// 		ul_el.insertAdjacentHTML("beforeend", li_el); //位置,想放的內容
// 	});
// });

