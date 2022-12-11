<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>


<%
	ProductService prodSvc = new ProductService();
	List<ProductVO> list = prodSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>全部商品資料</title>
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>

</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>全部商品資料</h3>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th width="200">商品編號</th>
			<th width="200">活動編號</th>
			<th width="200">廠商編號</th>
			<th width="200">商品名稱</th>
			<th width="200">商品規格</th>
			<th width="200">商品單價</th>
			<th width="200">庫存數量</th>
			<th width="200">商品詳情</th>
			<th width="200">商品總評價</th>
			<th width="200">商品是否上架</th>
		</tr>
		<c:forEach var="ProductVO" items="${list}">
			<tr>
				<td width="200"><div align="center">${prodVo.getProdNo}></div></td>
				<td width="200"><div align="center">${prodVo.getEventNumber}></div></td>
				<td width="200"><div align="center">${prodVo.getOrganizerNumber}></div></td>
				<td width="200"><div align="center">${prodVo.getProdName}></div></td>
				<td width="200"><div align="center">${prodVo.getProdSpec}></div></td>
				<td width="200"><div align="center">${prodVo.getUnitPrice}></div></td>
				<td width="200"><div align="center">${prodVo.getProdStock}></div></td>
				<td width="200"><div align="center">${prodVo.getProdDetails}></div></td>
				<td width="200"><div align="center">${prodVo.getProdScore}></div></td>
				<td width="200"><div align="center">${prodVo.getIsPOn}></div></td>

				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="prodNo" value="${empVO.empno}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</form>
				</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/ProductServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="prodNo" value="${empVO.empno}"> <input
							type="hidden" name="action" value="delete">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>