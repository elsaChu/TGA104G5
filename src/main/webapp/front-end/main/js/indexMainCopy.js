
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
          alert(body.successful);
          console.log("logged in");
          loggedIn = true;
        }else{
          console.log("not logged in");
          // alert("successful is not true")
        }
    });

  };

// window on load 
window.addEventListener("DOMContentLoaded", function () {
  // console.log("DOM Content Loaded");
  let loggedIn = false;
  checkLogIn();
  $("button").click(function(e){
    e.preventDefault();
    $(".loggedIn").removeAttr("hidden");
    $(".notLoggedIn").attr("hidden", true);
  });
});
