<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>訂單查詢：</h3>
	<form method="post" action="">
		<b>請輸入會員編號:</b>
		<input type="text" name="number">
		<input type="hidden" name="action" >
		<input type="submit" value="送出">
	</form>
	
	<form method="post" action="">
		<b>請輸入訂單編號:</b>
		<input type="text" name="prodNo">
		<input type="hidden" name="action" >
		<input type="submit" value="送出">
	</form>
</body>
</html>