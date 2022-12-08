<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>


<%
ProductVO prodVo = (ProductVO) request.getAttribute("ProductVO"); //prodctServlet.java(Concroller), 存入req的productVO物件
%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<html>
<head>
<title>查詢單筆商品資料</title>

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
	width: 600px;
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>查詢單筆商品資料</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>商品編號</th>
		<th>活動編號</th>
		<th>廠商編號</th>
		<th>商品名稱</th>
		<th>商品規格</th>
		<th>商品單價</th>
		<th>庫存數量</th>
		<th>商品詳情</th>
		<th>商品總評價</th>
		<th>商品是否上架</th>
	</tr>
	<tr>
		<td><%=prodVo.getProdNo()%></td>
		<td><%=prodVo.getEventNumber()%></td>
		<td><%=prodVo.getOrganizerNumber()%></td>
		<td><%=prodVo.getProdName()%></td>
		<td><%=prodVo.getProdSpec()%></td>
		<td><%=prodVo.getUnitPrice()%></td>
		<td><%=prodVo.getProdStock()%></td>
		<td><%=prodVo.getProdDetails()%></td>
		<td><%=prodVo.getProdScore()%></td>
		<td><%=prodVo.getIsPOn()%></td>

	</tr>
</table>

</body>
</html>