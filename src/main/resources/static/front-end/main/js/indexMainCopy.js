
// window on load 
window.addEventListener("DOMContentLoaded", function () {
   console.log("DOM Content Loaded");
  var loggedIn = null;
//  checkLogIn();
	checkUserLogin(); 
	console.log("after checkUserLogin");
	console.log(loggedIn);
	loggedIn = $("#memid").val();
	console.log("after jquery", loggedIn);
  getBannerPic();
  getAllEvents();
  $(document).on("click", ".searchResult", function () {
	 $(this).css("border", "3px solid black"); 
	  console.log("click on div.searchResult");
	 $(this).find("form").submit();
  });
  $("#myFavorite").click(function(e){
	  console.log("click favorite");
	//  $(this).css("border","3px solid black");
  });
  
//  clicked on icon inside div.searchResult START (including favorite feature)
	//click on heart (and its outer a tag)
    $(document).on("click", "ul.featured__item__pic__hover li a", function (e) {
		if($(this).find("i.fa-heart")){
			console.log("have heart");
			if(loggedIn == ""){
				console.log("loggedIn empty string");
			}else if(loggedIn == null){
				alert("logged in ==null 請先登入");
			}else{
				let eventID = $(this).closest("div.searchResult").attr("eventNumber");
				console.log("clicked heart icon on event ", eventID);
				console.log("loggedin?: ", loggedIn);
				e.preventDefault();
				e.stopPropagation();
				addToFavorite(eventID);
			}
		}else{
			console.log("clicked icon");
			e.preventDefault();
			e.stopPropagation();
		}
	});


//  clicked on icon inside div.searchResult END


  $("div.gallery-cell").click(function(e){
    let thisEvent = $(this).attr("eventIs");
    console.log(thisEvent);
    if(thisEvent != null){
      window.location.href = "../../EventDetails?eventNumber=" + thisEvent ;
    }
  })
  $("button").click(function(e){
    e.preventDefault();
    $(".loggedIn").removeAttr("hidden");
    $(".notLoggedIn").attr("hidden", true);
  });
});


function checkLogIn() {
  fetch('../../CheckLogin', {
    method: 'get',
    headers: {
        'Content-Type': 'application/json'
    }
  })
    .then(function (resp) { return resp.json(); })
    .then(function (body) {
      if(body.successful== true){
          console.log("From CheckLogin: logged in");
          loggedIn = true;
        }else{
          console.log("From CheckLogin: not logged in");
          // alert("successful is not true")
        }
    });

 }
 
function checkUserLogin(){
	console.log("memberid from hidden input", $("#memid").val());
	if($("#memid").val() == ""){
		console.log("memid is empty string")
	}else if ($("#memid").val() == null){
		console.log("memid is null");
	}else{
		loggedIn = $("#memid").val();
		console.log("typeof: ", typeof loggedIn,": ", loggedIn);
	}
}

function getBannerPic() {
  fetch('../../GetBanner', {
    method: 'get',
    headers: {
        'Content-Type': 'application/json'
    }
  })
    .then(function (resp) { return resp.json(); })
    .then(function (body) {
      if(body.successful== true){
          // alert(body.successful);
          console.log("get　Banner success");
          let ar1 = eval(body.bannerL);
//          console.log("ar1:",ar1)
          ar1.forEach(function(e, i){
//			console.log(e,i);
			if(i < 5){
			$("div.gallery-cell").eq(i).find("img").attr("src","../../DBGifReader?eventId='" + e + "'");
			$("div.gallery-cell").eq(i).attr("eventIs", e )
      // console.log("added event jump");
			}else{
				console.log("more banner than carousel spots");
			};
			
		  })
         	
        }else{
          console.log("get banner fail");
          // alert("successful is not true")
        }
    });
}

function getAllEvents(){
  //	fetch('http://localhost:8080/TGA104G5/DBGifReader?' + new URLSearchParams({"mainSearch": keyword.value}), {
    fetch('http://localhost:8080/TGA104G5/IndexEventSearch?' + new URLSearchParams({"mainSearch": ""}), {
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        },
    })
        .then(function (resp) { return resp.json();})
        .then(function (body) {
        if(body.successful == true){
          // alert("成功取得所有活動");
        }else{
              // alert("無符合結果，請重新搜尋");
        }
        var allEvents1 = body.allEvents;
        var eventObj = JSON.parse(allEvents1);
              $("div.featured__filter").html("");
        eventObj.forEach(function(e, index){
//          console.log("index: ", index);
//          console.log("element", e);
//          console.log("element.eventNum: ", e.eventNumber);
          
          //Add New List Into ul Start
           let list_html = "";
               list_html = '<div class="col-lg-4 col-md-4 col-sm-6 searchResult" eventNumber="'+ e.eventNumber + '" organizerNumber="' + e.organizerNumber +
                        '" eventName="'+  e.eventName + '" isOn="'+ e.isOn +'">';
                        
           list_html +=    '<form action="../../EventDetails">';
               list_html +=`	<div class="featured__item">
                                <div class="featured__item__pic set-bg">  `;
              list_html +=        	'<input type="text" name="eventNumber" value="'+ e.eventNumber +'" hidden>';
                                
             list_html +=	'<img class=""  src="../../DBGifReader?eventId='+ e.eventNumber +'"'
              + 'alt="" style="width: 100%" />';
             
             list_html +=          `<ul class="featured__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>`;
//              list_html +=              '<li><a href="#"><i class="fa fa-retweet"></i></a></li>';
             list_html +=             ` <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="featured__item__text">`;
                                
           list_html +=           '<h6><a href="#">'+ e.eventName+'</a></h6>' +
                         '<h5>'+ e.eventPlace +'</h5>';
           list_html +=        `</div>
                            </div>
                           </form>
                        </div>`;
                //add new list into ul (end)
                $("div.featured__filter").append(list_html);
          })
        });
};

function addToFavorite(eventid){
//	get member id see if member logged in
//		if not send alert "go log in"
//	get event id for this gallery cell
//	send request to servlet with eventid and memberid to add to collect DB 
//	if servlet already has this then dont add send back message saying already in favorite
	fetch('../../AddFavorite?' + new URLSearchParams({"heartEvent": eventid}), {
    method: 'get',
    headers: {
        'Content-Type': 'application/json'
    }
  })
    .then(function (resp) { return resp.json(); })
    .then(function (body) {
      if(body.addNewFav == "success"){
          alert("Added to Favorite");
        }else if (body.addNewFav == "foundSame"){
          alert("already in favorite");
        }else if (body.addNewFav == "fail"){
          console.log("Add to Favorite Failed");
          // alert("successful is not true")
        }else{
			console.log("failed add to favorite");
		}
    });
	
}
 
