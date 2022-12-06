<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
<%@ page import="java.util.*"%>
<%@ page import="com.staff.model.*"%>  
<!DOCTYPE html>

<%
    StaffService staffSvc = new StaffService();
    List<staffVO> list = staffSvc.getAll();
    pageContext.setAttribute("list",list);
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

<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table id="table-1">
		<tr>
			<td>
				<h3>所有員工資料 - listAllStaff.jsp</h3>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工帳號</th>
			<th>員工密碼</th>
			<th>修改！</th>
			<th>刪除！</th>
		</tr>
		<%@ include file="page1.file" %> 
	<c:forEach var="staffVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		
				<tr>
				<td>${staffVO.staffNumber}</td>
				<td>${staffVO.staffName}</td>
				<td>${staffVO.staffAccount}</td>
				<td>${staffVO.staffPassword}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="修改"> <input type="hidden"
							name="staffNumber" value="${staffVO.staffNumber}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="刪除"> <input type="hidden"
							name="empno" value="${empVO.empno}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>