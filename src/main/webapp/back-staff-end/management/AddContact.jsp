<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>add contact</title>
<link   rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" /> 
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

</head>

<body>
<h1>Create New Contact-Us </h1>
<form method="post" action="<%=request.getContextPath()%>/ContactServlet">
	<input type="hidden" name="doTask" value="insert">
	<label>Contact Title </label>
	<input type="text" name="contactTitle" id="cTitle">
	<br>
	<label>Contact Content</label>
	<br>
	<textarea id="cContent" name="contactContent" rows="4" cols="50"></textarea>
	<br>
	<input type="button" value="Create" id="submit">
	
</form>

</body>
<script>
	const contactTitle = document.querySelector('#cTitle');
	const contactContent = document.querySelector('#cContent');
	
 	document.querySelector('#submit').addEventListener('click', function () {
 		console.log("clicked");
		fetch('../ContactServlet', {
		    method: 'POST',
		    headers: {
		        'Content-Type': 'application/json'
		    },
		    body: JSON.stringify({
		        "contactTitle": contactTitle.value,
		        "contactContent": contactContent.value
		    })
		})
		    .then(function (resp) { return resp.json(); })
		    .then(function (body) {
		        alert(body.successful);
		    });
	 });

	$("#submit").click(function(e){
		console.log(contactTitle.value);
		console.log(contactContent.value);
	});
</script>

</html>