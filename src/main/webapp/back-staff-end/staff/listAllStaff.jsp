<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.staff.model.*"%>
<!DOCTYPE html>

<%
StaffService staffSvc = new StaffService();
List<StaffVO> list = staffSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有員工資料 - listAllStaff.jsp</title>

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

<script>
	function showAlert() {
		let c = confirm("勝敗乃兵家常事，大俠真的要重新來過嗎？");
		console.log("c", c);
		if (!c) {
			return false
		}

		document.getElementById("delForm").submit();

	}

	function searchStaffNumber() {
		document.getElementById("search").submit();

	}
</script>

<meta charset="UTF-8">

</head>
<body bgcolor='white'>

	<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>

	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllStaff.jsp</h3>
			</td>
		</tr>
	</table>

	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
<%@ include file="page1.file"%>

<div style="float: right; margin-right: 23%;">
	<li>
		<FORM METHOD="post" id="search" ACTION="<%=request.getContextPath()%>/StaffServlet" name="action">
			<b>搜尋員工編號:</b>
			<input type="text" name="searchStaffNumber" value="" style="" placeholder="員工編號">
			<input type="submit" name="action" value="search" onclick="searchStaffNumber()">
		</FORM>
	</li>
</div>
	<!-- 	<div style="float: right; margin-right: 23%;"> -->
	<!-- 		<form METHOD="post" id="search" -->
	<%-- 			ACTION="<%=request.getContextPath()%>/StaffServlet" --%>
	<!-- 			name="action"> -->
	<!-- 			<input type="text" name="searchStaffNumber" style="" -->
	<!-- 				placeholder="員工編號" /> -->
	<!-- 			<!-- 	../ 上一層 -->

	<!-- 			<!-- 	../ ../ 上一層再上一層 -->

	<!-- 			<input type= "hidden" name="action" value="search"></input> -->
	<!-- 		</form> -->
	<!-- 			<img alt="" src="../../main_frame/images/bigpic.png" -->
	<!-- 				style="width: 20px; cursor: pointer;" onclick="searchStaffNumber()"> -->
	<!-- 	</div> -->

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>修改員工</th>
			<!-- 			<th>刪除員工</th> -->
		</tr>

		<c:forEach var="staffVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${staffVO.staffNumber}</td>
				<td>${staffVO.staffName}</td>
				<td>${staffVO.staffAccount}</td>
				<td>${staffVO.staffPassword}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/StaffServlet"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="staffNumber" value="${staffVO.staffNumber}"> <input
							type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<!-- 				<td> -->
				<!-- 					<FORM METHOD="post" id="delForm" -->
				<%-- 						ACTION="<%=request.getContextPath()%>/StaffServlet" --%>
				<!-- 						style="margin-bottom: 0px;"> -->
				<!-- 						<input type="button" value="刪除" onclick="showAlert()"> <input -->
				<%-- 							type="hidden" name="staffNumber" value="${staffVO.staffNumber}"> --%>
				<!-- 						<input type="hidden" name="action" value="delete"> -->
				<!-- 					</FORM> -->
				<!-- 				</td> -->
			</tr>
		</c:forEach>
	</table>

	<%@ include file="page2.file"%>

</body>


</html>