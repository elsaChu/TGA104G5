$(function(){
	
	function checklogin(){
		$.ajax({
        url: '../../../member/MemberServlet',
        method: 'POST',
        dataType: 'json',
        data: { action: 'checkLogin'},
        success: function (data) {
			console.log(data);
			if(data.check == '2'){
				$('#login_menuB').show();
				$('#login_menuA').hide();
			}else{
				$('#login_menuA').show();
				$('#login_menuB').hide();
			}
        }
    });
	}
	checklogin();
	
	// http://localhost:8080/TGA104G5/member/MemberServlet
	// ../../../member//MemberServlet




	// http://localhost:8080/TGA104G5/front-end/product/js/xxx.js
	
})