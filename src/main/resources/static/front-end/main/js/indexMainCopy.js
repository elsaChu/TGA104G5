
// window on load 
window.addEventListener("DOMContentLoaded", function () {
   console.log("DOM Content Loaded");
  let loggedIn = false;
  checkLogIn();
  getBannerPic();
  console.log("did banner activate");
  $(document).on("click", ".searchResult", function () {
	 $(this).css("border", "3px solid black"); 
	  console.log("click on div.searchResult");
	 $(this).find("form").submit();
  });
  $("#myFavorite").click(function(e){
	  console.log("click favorite");
	 $(this).css("border","3px solid black");
	
  });
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

 };

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
          alert(body.successful);
          console.log("get　Banner success");
          let ar1 = eval(body.bannerL);
          console.log("ar1:",ar1)
          ar1.forEach(function(e, i){
			console.log(e,i);
			if(i < 5){
			$("div.gallery-cell").eq(i).find("img").attr("src","../../DBGifReader?eventId='" + e + "'");
			}else{
				console.log("more banner than carousel spots");
			};
			
		  })
         	
        }else{
          console.log("get banner fail");
          // alert("successful is not true")
        }
    });
};

