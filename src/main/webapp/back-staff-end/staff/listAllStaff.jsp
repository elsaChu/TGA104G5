<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="tw.com.tibame.staff.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />

<%
StaffService staffSvc = new StaffService();
List<StaffVO> list = staffSvc.getAll();
pageContext.setAttribute("list", list);
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

// 	function searchStaffNumber() {
// 		document.getElementById("search").submit();

// 	}
</script>

</head>

<body>
	<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
	<div>
		<h3>所有員工列表</h3>
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
				<FORM METHOD="post" id="search"
					ACTION="<%=request.getContextPath()%>/StaffServlet" name="action">
					<b>搜尋員工編號:</b>
					<input type="text" name="searchStaffNumber" value=""
						style="" placeholder="員工編號">						
					<input type="hidden" name="action" value="search">
   					<input style="color: white; font-size: 14px;width: 10%;  background: #000000;padding: 5px 5px; 
   					margin: 50px 0px 0px 0px;" type="submit" value="搜尋" />
				</FORM>
				

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
					<th>修改員工</th>
				</tr>
				<%@ include file="/front-end/event/page1_bylistOneOrganizer.file"%>
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
								<input type="submit" value="修改">
								<input type="hidden"
									name="staffNumber" value="${staffVO.staffNumber}">
								<input
									type="hidden" name="action" value="getOne_For_Update">
							</FORM>

						</td>
						<!-- 					<td><a href="#">查詢</a></td> -->
					</tr>
				</c:forEach>
			</table>
		</div>
		<!-- order list -->
		<div class="row div_search_right" >		
				<%@ include file="/front-end/event/page1_1_bylistOneOrganizer.file"%>
				<%@ include file="/front-end/event/page2_bylistOneOrganizer.file"%>
			</div>
	</div>
</body>
</html>