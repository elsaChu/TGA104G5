<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>
<%@ page import="java.util.*"%>

<%
ProductOrderService prodOrderSvc = new ProductOrderService();
List<ProductOrderVO> list = prodOrderSvc.getAll();
pageContext.setAttribute("list", list);
%>

<c:set var="context" value="${pageContext.request.contextPath}" />
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

<style>
.my_size {
        margin: 0 auto;
        width: 970px;
}

h2 {
        margin: 20px 10px 10px 0px;
        display: inline-block;
        text-align: center;
      }

.searchitems{
  list-style: none;
}

.error {
	color: red;
}

.inputbox {
	height: 23px;
	width: 200px;
	margin: 8px auto;
	text-align: center;
}

select {
	text-align-last: center;
		height: 23px;
	width: 200px;
	margin: 8px auto;
}
</style>

</head>
<body>
<div class="my_size">
	<c:if test="${not empty errorMsgs}">
		<font class="error">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li class="error">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<h2>查詢商品訂單</h2>
	<ul class="searchitems">
		<li><a href="listAllProductOrder.jsp">查看所有商品訂單</a></li>
		<li>
			<form method="post" action="${context}/ProductOrderServlet">
				輸入商品訂單編號： <input type="text" name="prodOrderNo" value="" class="inputbox">
				<input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="查詢">
			</form>
		</li>

		<li>
			<form method="post" action="${context}/ProductServlet">
				選擇商品訂單編號： <select name="prodOrderNo">
					<option selected="selected">請選擇</option>
					<c:forEach var="ProductOrderVO" items="${list}">
						<option value="${productorderVO.prodOrderNo}">${productorderVO.prodOrderNo}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<input type="submit" value="查詢">
			</form>
		</li>

	</ul>
	</div>

</body>
</html>