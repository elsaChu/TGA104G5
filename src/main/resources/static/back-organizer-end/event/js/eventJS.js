$.datetimepicker.setLocale('zh'); // kr ko ja en
$(function () {
	$('#start_date').datetimepicker({
		theme: '',
		timepicker: true,
		step: 15,
		format: 'Y-m-d H:i:s',
		value: eventStartDate,
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
        value: eventEndDate,
		onShow: function () {
			this.setOptions({
				minDate: $('#start_date').val() ? $('#start_date').val() : false
			})
		}
	});
	
//====show pic=====
window.addEventListener("pageshow", function () {
//	 console.log("in load");
//	 console.log("smallimg=" + smallimgPIC);
//	 console.log("bigimg=" + bigimgPIC);
	if(eventNumber != ""){
		 if(bigimgPIC != ""){
			document.getElementById("bigImg_img").src = bigimgPIC;
		 }else{
			document.getElementById("bigImg_img").src = context +"/back-organizer-end/event/img/defPic4.jpg";
		 }
		 if(smallimgPIC != ""){
			document.getElementById("smallImg_img").src = smallimgPIC;
		 }else{
			document.getElementById("smallImg_img").src = context +"/back-organizer-end/event/img/defPic4.jpg";
		 }
	}
//	 document.getElementsByTagName("form").reset();
//	document.getElementById("formNa").reset();
});
let getInImg = document.getElementsByClassName("inImg");
//console.log(getInImg);
for (let i = 0; i < getInImg.length; i++) {
	let get_one = getInImg[i];
	// console.log(get_one);
	get_one.addEventListener("change", function (e) {
		//   console.log("change on");
		//   console.log(e.target);

		//清除預覽圖片
		let the_ul = document.getElementsByClassName("picture_list")[i];
		the_ul.innerHTML = "";

		//read file
		let reader = new FileReader();
		//   console.log(this.files[0]);
		if (this.files.length !== 0) {
			reader.readAsDataURL(this.files[0]);
		}

		reader.addEventListener("load", function () {
			// console.log("load on");
			// console.log(this.result);

			//put img in ul
			let li_el = `
				<li>
					<img src="${this.result}" class="preview">
				</li>	
			`;
			let li_el_def = `
				<li>
					<img src="img/defPic4.jpg" class="preview">
				</li>
			`
			let getUL = document.getElementsByClassName("picture_list")[i];
			// console.log(getUL);

			getUL.insertAdjacentHTML("beforeend", li_el);


		});
	});
};

/* ===editor===*/
ClassicEditor
	.create(document.querySelector('#editor'), {
		// toolbar: [ 'heading', '|', 'bold', 'italic', 'link' ]
	})
	.then(editor => {
		window.editor = editor;
	})
	.catch(err => {
		console.error(err.stack);
	});





//check box
function totalChb(loadClass,fromwhere) {
//	console.log(fromwhere);
	let items = document.querySelectorAll(".chb");
//	 console.log(items);
	 let total = 0;
//	 console.log("load class ="+loadClass);
	if(loadClass != undefined){
		total = loadClass;
	}
	
	items.forEach(function (element) {
		element.addEventListener("click", function (e) {
			if (element.checked) {
				total++;
				console.log(total);
			} else if(total > 0){
				total--;
				console.log(total);
			}

			if (total >= 3) {
				// console.log(" >=3");
				items.forEach(function (el) {
					if (!el.checked) {
						// console.log(el);
						// el.disabled = ture;
						el.setAttribute("disabled", true);
					}
				});
			} else {
				// console.log("<3");
				items.forEach(function (el) {
					el.removeAttribute("disabled");
				});
			}
		});
	});
}

//window load
window.addEventListener("load",function(){
	 console.log("isON=" +isON + typeof(isON));
	 console.log("needSeat=" + needSeat+ typeof(needSeat));
	if(isON == "true"){
		document.getElementById("isON").checked = true;
	}
	if(needSeat == "true"){
		document.getElementById("needSeat").checked = true;
	}
	
	//event class check box
//	console.log("evclassJSON=" +evclassJSON + typeof(evclassJSON));
	if(evclassJSON != ""){
		let evclaJSON = JSON.parse(evclassJSON);	
		let loadClass = evclaJSON.length;
	//	console.log(evclaJSON);
	//	console.log(evclaJSON[0].eventClassNumber);
		let chbclass = document.getElementsByClassName("chb");
	//	console.log(chbclass);
		for(let i=0 ; i< chbclass.length ; i++){
	//		console.log(chbclass[i]);
	//		console.log(chbclass[i].value);
			for(let j=0 ; j < evclaJSON.length ; j++){
				if(chbclass[i].value == evclaJSON[j].eventClassNumber){
	//				console.log("chbclass="+chbclass[i].value + "json="+evclaJSON[j].eventClassNumber);
					chbclass[i].checked = true;
				}
			}
		}
		totalChb(loadClass,"from load");
	}
});

totalChb();

});
//===========google map============
let map;
let marker;
let geocoder;
let responseDiv;
let response;
function initMap(input_) {
	map = new google.maps.Map(document.getElementById("map"), {
		zoom: 15,
		center: { lat: 25.0474428, lng: 121.5170955 },
		mapTypeControl: false,
	});
	geocoder = new google.maps.Geocoder();

	const submitButton = document.getElementById("search");

	marker = new google.maps.Marker({
		map,
	});
	map.addListener("click", (e) => {
		geocode({ location: e.latLng });
	});
	submitButton.addEventListener("click", () => geocode({ address: input_.value }));
}
function geocode(request) {
	geocoder
		.geocode(request)
		.then((result) => {
			const { results } = result;
			map.setCenter(results[0].geometry.location);
			marker.setPosition(results[0].geometry.location);
			marker.setMap(map);

			return results;
		})
		.catch((e) => {
			alert("Geocode was not successful for the following reason: " + e);
		});
};

let autocomplete;
function initAutocomplete() {
	let input_ = document.getElementById("autocomplete");
	let options = {
		types: ['establishment'],
		componentRestrictions: { country: ['TW'] },
		fields: ["address_components", "geometry", "icon", "name", "place_id"],
	};
	autocomplete = new google.maps.places.Autocomplete(input_, options);
	initMap(input_);
	autocomplete.addListener('place_changed', onPlaceChanged);
};

function onPlaceChanged() {
	var place = autocomplete.getPlace();
	if (!place.geometry) {
		document.getElementById("autocomplete").placeholder = "Enter a place";
	} else {
		document.getElementById("autocomplete").innerHTML = place.name;
	}
};
window.initMap = initMap;
