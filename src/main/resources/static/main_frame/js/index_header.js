$(function(){
	
	function checklogin(){
		$.ajax({
        url: context_header+'/front-end/member/MemberServlet',
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
	
	
	
	
})