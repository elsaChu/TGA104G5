<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
ProductVO prodVo = (ProductVO) request.getAttribute("ProductVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改商品資料</title>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body>

	<table id="table-1">
		<tr>
			<td>
				<h3>修改商品資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form method="post"
		action="<%=request.getContextPath()%>/ProductServlet" name="form1">
		<table>
			<tr>
				<td>商品編號:<font color=red><b>*</b></font></td>
				<td><%=prodVo.getProdNo()%></td>
			</tr>
			<tr>
				<td>活動編號:</td>
				<td><input type="text" name="prodNo" size="45"
					value="<%=prodVo.getEventNumber()%>" /></td>
			</tr>
			<tr>
				<td>廠商編號:</td>
				<td><input type="text" name="organizerNumber" size="45"
					value="<%=prodVo.getOrganizerNumber()%>" /></td>
			</tr>
			<tr>
				<td>商品名稱:</td>
				<td><input type="text" name="prodName" size="45"
					value="<%=prodVo.getProdName()%>" /></td>
			</tr>
			<tr>
				<td>商品規格:</td>
				<td><input type="text" name="prodSpec" size="45"
					value="<%=prodVo.getProdSpec()%>" /></td>
			</tr>
			<tr>
				<td>商品單價:</td>
				<td><input type="number" name="unitPrice" size="45"
					value="<%=prodVo.getUnitPrice()%>" /></td>
			</tr>
			<tr>
				<td>庫存數量:</td>
				<td><input type="number" name="prodStock" size="45"
					value="<%=prodVo.getProdStock()%>" /></td>
			</tr>
			<tr>
				<td>商品詳情:</td>
				<td><input type="text" name="prodDetails" size="45"
					value="<%=prodVo.getProdDetails()%>" /></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>商品是否上架:</td> -->
<!-- 				<td><input type="radio" name="isPOn" size="45" -->
<%-- 					value="<%=prodVo.getIsPOn()%>" /></td> --%>
<!-- 			</tr> -->


<%-- 
<jsp:useBean id="prodSvc" scope="page"
			class="tw.com.tibame.product.model.ProductService"/>

	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptno">
			<c:forEach var="ProductVO" items="${prodSvc.all}">
				<option value="${ProductVO.deptno}" ${(ProductVO.prodno==deptVO.deptno)?'selected':'' } >${ProductVO.dname}
			</c:forEach>
		</select></td>
	</tr>


<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="prodNo" value="<%=productVO.get()%>">
<input type="submit" value="送出修改"> --%>

		</table>
	</form>
</body>
</html>