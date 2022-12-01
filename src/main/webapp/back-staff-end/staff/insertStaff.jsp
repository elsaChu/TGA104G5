<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="" name="insertStaff">
		<span>員工姓名：</span><input type="text" name="staffName"></input><br>
		<span>員工帳號：</span><input type="text" name="staffAccount"></input><br>
		<span>員工密碼：</span><input typr="text" name="staffPassword"></input><br>
		<span>管理權限</span><br>
		<span>會員管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<span>員工管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<span>活動管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<span>網站管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<span>廠商管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<span>商品管理</span><input type="checkbox" name="permissionNumber"></input><br>
		<button type="submit">送出！！！</button><br>
</body>
</html>