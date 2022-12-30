<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.staff.model.*"%>

<%
StaffVO staffVO = (StaffVO) request.getAttribute("staffVO");
//StaffServlet.java (Concroller) 存入req的staffVO物件 (包括幫忙取出的staffVO, 也包括輸入資料錯誤時的staffVO物件)
%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>
<%-- <link rel="stylesheet" type="text/css" href="${context}/main_frame/css/bootstrap.css" /> --%>
<link rel="stylesheet" type="text/css"
	href="${context}/main_frame/css/button.css" />
<link rel="stylesheet" type="text/css"
	href="${context}/back-staff-end/staff/css/insertStaff.css" />

</head>
<body>
<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
	<div class="my_size">
	<br>
		<h3>員工資料修改</h3>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<FORM METHOD="post" ACTION="StaffServlet" name="form1">
		
			<div class="div_text col-md-5">
				<table>
					<tr>
						<td style="width:80px;">員工編號:<font color=red><b>*</b></font></td>
					<td><%=staffVO.getStaffNumber()%></td>
					</tr>
					<tr>
						<td>員工姓名:</td>
					<td>
						<input type="TEXT" name="staffName" size="45"
						value="<%=staffVO.getStaffName()%>" class="form-control"/>
					</td>
					</tr>
					<tr>
						<td>員工帳號:</td>
					<td>
						<input type="TEXT" name="staffAccount" size="45"
						value="<%=staffVO.getStaffAccount()%>" class="form-control"/>
					</td>
					</tr>
					<tr>
						<td>員工密碼:</td>
					<td>
						<input type="TEXT" name="staffPassword" size="45"
						value="<%=staffVO.getStaffPassword()%>" class="form-control"/>
					</td>
					</tr>
				</table>
		
			</div>
			<br><br><br><br><br><br><br><br><br><br>
			<input type="hidden" name="action" value="update">
<input type="hidden" name="staffNumber" value="<%=staffVO.getStaffNumber()%>">
<input type="submit" value="送出修改" style="color: white; font-size: 14px;width: 20%;  background: #000000;padding: 5px 5px; 
   					margin: 25px 0px 0px 0px;">
</FORM>
	</div>

</body>
</html>