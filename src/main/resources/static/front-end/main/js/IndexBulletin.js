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
const bulletinRow = document.querySelector('#bulletinRow');

// window on load 
window.addEventListener("DOMContentLoaded", function () {
   	console.log("DOM Content Loaded");
//	checkLogIn();
	getBulletin();
	checklogin();

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

function getBulletin(){
	 fetch('../../GetBulletin', {
    method: 'get',
    headers: {
        'Content-Type': 'application/json'
    }
  })
    .then(function (resp) { return resp.json(); })
    .then(function (body) {
      if(body.successful== true){
//          alert(body.successful);
          console.log("get　Bulletin success");
          	var allBulletin = body.listB;
			var listB = JSON.parse(allBulletin);
            $("div.featured__filter").html("");
            if(listB.length == 0){
				console.log("listB empty");
			}else{
				console.log("bulletinContentfrom listB[0]",listB[0].bulletinContent);
			};
			let bulletinHtml = "";
			listB.forEach(function(e, index){
				console.log(e,index);
				
				bulletinHtml += `
				<div class="col-lg-6 col-md-12 col-sm-12">
                    <div class="memo">
                        <div class="pinBlock">
                            <div class="pin"></div>
                        </div>
                        <div class="memoContent">`;
    		bulletinHtml += '<h5>' + e.bulletinName + `</h5>
                           	 <div class="bulletinContent">`;
              	bulletinHtml += '<p>' + e.bulletinContent + '</p>';
   		      bulletinHtml += `</div>
                        </div>
                    </div>
                </div> `;
                
                
			});
			$("#bulletinRow").html("");
			$("#bulletinRow").append(bulletinHtml);
			
         	
        }else{
          console.log("get Bulletin fail");
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
          console.log(body.successful);
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

