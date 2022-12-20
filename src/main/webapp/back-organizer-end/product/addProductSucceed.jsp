<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
 <c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
	
<style>
.my_size {
display: inline-block;
        margin: 20px auto 10px auto;
        width: 970px;
        text-align: center
}

</style>

</head>
<body>
<div class="my_size">
	<h3>您已成功新增商品！</h3>
	<a href="listAllProduct.jsp">查看所有商品</a><br>
	<a href="${context}/back-organizer-end/product/selectProduct.jsp">回到查詢商品首頁</a>
</div>
</body>
</html>