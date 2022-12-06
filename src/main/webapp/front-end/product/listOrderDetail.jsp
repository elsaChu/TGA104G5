<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>

<%
OrderDetailServiceImpl odService = new OrderDetailServiceImpl();
    List<OrderDetailVO> list = odService.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>listAllOrderDetail.jsp</title>
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

<table>
	<tr>
		<th>明細編號</th>
		<th>商品訂單編號</th>
		<th>商品編號</th>
		<th>各商品數量</th>
		<th>商品小計金額</th>
		<th>評價分數</th>
		<th>評價內容</th>
		<th>評價日期</th>
		<th>退訂原因</th>
		<th>退款狀態</th>
		<th>退款申請日期</th>
		<th>退款完成日期</th>
		
	</tr>
	<c:forEach var="orderDetailVO" items="${list}">
		<tr>
			<td>${orderDetailVO.itemNo}</td>
			<td>${orderDetailVO.prodOrderNo}</td>
			<td>${orderDetailVO.prodNo}</td>
			<td>${orderDetailVO.prodQty}</td>
			<td>${orderDetailVO.subtotal}</td>
			<td>${orderDetailVO.commentRanking}</td> 
			<td>${orderDetailVO.commentContent}</td>
			<td>${orderDetailVO.commentDate}</td>
			<td>${orderDetailVO.returnReason}</td>
			<td>${orderDetailVO.refundStatus}</td>
			<td>${orderDetailVO.refundSDate}</td>
			<td>${orderDetailVO.refundEDate}</td>
			
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/OrderDetailServlet" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="prodOrderNo"  value="${orderDetailVO.prodOrderNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/emp/emp.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="刪除"> -->
<%-- 			     <input type="hidden" name="empno"  value="${empVO.empno}"> --%>
<!-- 			     <input type="hidden" name="action" value="delete"></FORM> -->
<!-- 			</td> -->
		</tr>
	</c:forEach>
</table>

</body>
</html>