<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/AddProductServlet" name="">
	<span>活動編號：</span>
		<input type="text" name="eventNumber" value=""><br>
	<span>商品名稱：</span>
		<input type="text" name="prodName" value=""><br>
	<span>商品規格：</span>
		<input type="text" name="prodSpec" value=""><br>
	<span>商品單價：</span>
		<input type="number" name="unitPrice" value="">元<br>
	<span>庫存數量：</span>
		<input type="number" name="prodStock" value=""><br>
	<span>商品詳情：</span>
		<textarea rows="6" cols="15" name="prodDetails" value=""></textarea><br>
	<span>商品是否上架：</span>
		<input type="radio" name="isPOn" value="1" checked>是
		<input type="radio" name="isPOn" value="0">否<br>
	<button type="submit">新增商品</button>
	</form>

</body>
</html>