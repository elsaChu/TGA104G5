
// window on load 
window.addEventListener("DOMContentLoaded", function () {
   console.log("DOM Content Loaded");
//   $("div.featured__filter").html('<div class="loadingIcon" ><span><img src="images/dotIcon.png"></i></span></div>');
   getFavEvent();
   
  $(document).on("click", ".searchResult", function () {
	 $(this).css("border", "3px solid black"); 
	  console.log("click on div.searchResult");
	 $(this).find("form").submit();
  });
  
});

	window.addEventListener("load", function () {
		$("div.featured__filter").css("border","5px solid black");
//		$("div.featured__filter").find("div.loadingIcon").remove();

	});
	
		$(document).on('load', 'img', () => {
			alert(123);
		})

document.querySelector('#submit').addEventListener('click', function () {
	console.log("getFav from js");
		
//	getFavEvent();
 });

function getFavEvent(){
	fetch('http://localhost:8080/TGA104G5/FindFavorite', {
	    method: 'get',
	    headers: {
	        'Content-Type': 'application/json'
	    },
	})
	    .then(function (resp) { return resp.json();})
	    .then(function (body) {
			if(body.successful == true){
				alert("成功取得所有活動");
			}else{
	        	alert("無符合結果，請重新搜尋");
			}
			var favEvents1 = body.favEvents;
			var eventObjArr = JSON.parse(favEvents1);
//            $("div.featured__filter").html("");
			eventObjArr.forEach(function(e, index){
				console.log("index: ", index);
				console.log("element", e);
				console.log("element.eventNum: ", e.eventNumber);
				
				//Add New List Into ul Start
			   let list_html = "";
	           list_html = '<div class="col-lg-3 col-md-4 col-sm-6 searchResult" eventNumber="'+ e.eventNumber + '" organizerNumber="' + e.organizerNumber +
	           				 '" eventName="'+  e.eventName + '" isOn="'+ e.isOn +'">';
	           				 
		  	 list_html +=    '<form action="../../EventDetails">';
	           list_html +=`	<div class="featured__item">
			                        <div class="featured__item__pic set-bg">  `;
		        list_html +=        	'<input type="text" name="eventNumber" value="'+ e.eventNumber +'" hidden>';
			                        
			     list_html +=	'<img class=""  src="../../DBGifReader?eventId='+ e.eventNumber +'"'
			      + 'alt="" style="width: 100%" />';
			     
			     list_html +=          `<ul class="featured__item__pic__hover">
			                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
			                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
			                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
			                            </ul>
			                        </div>
			                        <div class="featured__item__text">`;
			                        
			   list_html +=           '<h6><a href="#">'+ e.eventName+'</a></h6>' +
			   							'<h5>'+ e.eventPlace +'</h5>';
			   list_html +=        `</div>
			                    </div>
		                     </form>
			                </div>`;
						
	            $("div.featured__filter").append(list_html);
					//add new list into ul (end)
					
				})
			
	    });
}


