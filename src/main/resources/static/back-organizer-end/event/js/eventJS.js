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
//window.addEventListener("pageshow", function () {
	// console.log("in load");
	// document.getElementsByTagName("form").reset();
//	document.getElementById("formNa").reset();
//});
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
function totalChb(e) {
	let items = document.querySelectorAll(".chb");
	// console.log(items);
	let total = 0;
	items.forEach(function (element) {
		element.addEventListener("click", function (e) {
			if (element.checked) {
				total++;
				console.log(total);
			} else {
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
