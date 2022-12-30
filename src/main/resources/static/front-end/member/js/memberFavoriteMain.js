


  function checklogin() {
    $.ajax({
      url: '../member/MemberServlet',
      method: 'POST',
      dataType: 'json',
      data: { action: 'checkLogin' },
      success: function (data) {
        // console.log(data);
        if (data.check == '2') {
          $('#login_menuB').show();
          $('#login_menuA').hide();
        } else {
          $('#login_menuA').show();
          $('#login_menuB').hide();
        }
      }
    });
  }
// window on load 
window.addEventListener("DOMContentLoaded", function () {
   console.log("DOM Content Loaded");
  checklogin(); 
//   $("div.featured__filter").html('<div class="loadingIcon" ><span><img src="images/dotIcon.png"></i></span></div>');
   getFavEvent();
   
  $(document).on("click", ".searchResult", function () {
	 $(this).css("border", "3px dotted black"); 
	  console.log("click on div.searchResult");
	 $(this).find("form").submit();
  });
  
  //click delete icon START
   $(document).on("click", "ul.featured__item__pic__hover li a", function (e) {
		if($(this).find("i.fa-trash-o")){
			console.log("have delete");
			let eventID = $(this).closest("div.searchResult").attr("eventNumber");
			console.log("clicked delete icon on event ", eventID);
			e.preventDefault();
			e.stopPropagation();
			deleteFav(eventID);
		}else{
			console.log("clicked icon");
			e.preventDefault();
			e.stopPropagation();
		}
	});
  
});

	window.addEventListener("load", function () {
		// $("div.featured__filter").css("border","5px solid black");
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
				alert("成功取得所有收藏活動");
			}else{
	        	alert("無收藏活動");
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
	           list_html = '<div class="col-lg-4 col-md-4 col-sm-6 searchResult" eventNumber="'+ e.eventNumber + '" organizerNumber="' + e.organizerNumber +
	           				 '" eventName="'+  e.eventName + '" isOn="'+ e.isOn +'">';
		  	 list_html +=    '<form action="../../EventDetails">';
//	           						<input type="text" class="" name="action" value="delete" hidden />
	           list_html +=`	<div class="featured__item">
			                        <div class="featured__item__pic set-bg">  `;
		        list_html +=        	'<input type="text" name="eventNumber" value="'+ e.eventNumber +'" hidden>';
			                        
			     list_html +=	'<img class=""  src="../../DBGifReader?eventId='+ e.eventNumber +'"'
			      + 'alt="" style="width: 100%" />';
			     
			     list_html +=          `<ul class="featured__item__pic__hover">
			                                <li><a href="#"><i class="fa fa-solid fa-trash-o"></i></a></li>
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

function deleteFav(eventid){
	fetch('../../AddFavorite?' + new URLSearchParams({"choseEvent": eventid , "action": "delete"}), {
	    method: 'get',
	    headers: {
	        'Content-Type': 'application/json'
   		}
	})
    .then(function (resp) { return resp.json(); })
    .then(function (body) {
      if(body.deleteFav == "success"){
          alert("Deleted Favorite");
             getFavEvent();
        }else if (body.deleteFav == "fail"){
          console.log("Add to Favorite Failed");
          // alert("successful is not true")
        }else{
			console.log("failed add to favorite");
		}
    });
}

