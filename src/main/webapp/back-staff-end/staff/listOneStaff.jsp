<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@
page import="tw.com.tibame.staff.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
 StaffVO staffvo = (StaffVO) request.getAttribute("staffVO");
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT</title>

<link rel="stylesheet"
	href="${context}/back-staff-end/staff/css/listAllStaff.css">
<script>
// 	function showAlert() {
// 		let c = confirm("勝敗乃兵家常事，大俠真的要重新來過嗎？");
// 		console.log("c", c);
// 		if (!c) {
// 			return false
// 		}

// 		document.getElementById("delForm").submit();

// 	}

	function searchStaffNumber() {
		document.getElementById("search").submit();

	}
</script>

</head>

<body>
	<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
	<div>
		<h3>員工列表</h3>
		<br>
		<a href="${context}/back-staff-end/staff/listAllStaff.jsp"><iconify-icon icon="heroicons:arrow-uturn-left-20-solid"></iconify-icon>返回員工列表</a>
	</div>
	<!-- 	search -->
	<div class="row">
		<div class="row div_search">
			<div class="col-md-6">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<div style="margin-left: 40px;">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</div>

				</c:if>
			</div>
			<div class="col-md-6 div_search_right">
			</div>
		</div>
		<!-- search end -->
		<!-- order list -->
		<div class="row">
		
			<table class="box">
				<tr>
					<th>員工編號</th>
					<th>員工姓名</th>
					<th>員工帳號</th>
					<th>員工密碼</th>
					<th>修改</th>
				</tr>
<%-- 				<%@ include file="/front-end/event/page1_bylistOneOrganizer.file"%> --%>
<%-- 				<c:forEach var="staffVO" items="${list}" begin="<%=pageIndex%>" --%>
<%-- 					end="<%=pageIndex+rowsPerPage-1%>"> --%>
					<tr>
						<td>${staffVO.staffNumber}</td>
						<td>${staffVO.staffName}</td>
						<td>${staffVO.staffAccount}</td>
						<td>${staffVO.staffPassword}</td>
						<td>
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/StaffServlet"
								style="margin-bottom: 0px;">
								<input type="submit" value="修改">
								<input type="hidden"
									name="staffNumber" value="${staffVO.staffNumber}">
								<input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>

						</td>
						<!-- 					<td><a href="#">查詢</a></td> -->
					</tr>
<%-- 				</c:forEach> --%>
			</table>
		</div>
		<!-- order list -->
		<div class="row div_search_right" >		
<%-- 				<%@ include file="/front-end/event/page1_1_bylistOneOrganizer.file"%> --%>
<%-- 				<%@ include file="/front-end/event/page2_bylistOneOrganizer.file"%> --%>
			</div>
	</div>
</body>
</html>