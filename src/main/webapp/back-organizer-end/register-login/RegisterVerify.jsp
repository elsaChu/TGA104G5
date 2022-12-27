<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Verify</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
</head>
<body>
	<h1>帳號啟用驗證碼已經寄送到您的信箱，請輸入您收到的驗證碼</h1>
	<br>
 	<!-- <form action="${pageContext.request.contextPath}/">  -->
	<!-- <form action="<%=request.getContextPath()%>/CheckRegister" method="post"> -->
		<input name="action" value = "verify" hidden>
		<input name="veriCode" id="veriCode">
		<button type="submit" id="submit">送出</button>
	<!-- </form> -->
</body>
<script>
	const veriCode = document.querySelector('#veriCode');
	console.log(veriCode.value);
 	document.querySelector('#submit').addEventListener('click', function () {
 		console.log("clicked");
		fetch('./OrganizerVerification', {
			//get 就塞QUERY STRING
		    method: 'POST',
		    headers: {
		        'Content-Type': 'application/json'
		    },
			//cant have a body if ur using get method
		    body: JSON.stringify({
		         veriCode: veriCode.value
		    })
		})
		    .then(function (resp) { return resp.json(); })
		    .then(function (body) {
				if(body.successful == false){
					alert("驗證碼錯誤，請重新輸入");
				}else{
		        alert("驗證完成，登入已啟用");
		        window.location.replace("${pageContext.request.contextPath}/back-organizer-end/register-login/OrganizerLogin1.jsp");
				}
		    });
	 });

	$("#submit").click(function(e){
		e.preventDefault();
		console.log(veriCode.value);
	});
</script>
</html>