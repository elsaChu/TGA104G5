<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,tw.com.tibame.organizer.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%OrganizerVO organizerVO = (OrganizerVO) request.getSession().getAttribute("loginOrganizer");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT 廠商後台</title>

 <link href="${context}/back-organizer-end/register-login/css/organizer_sen.css" rel="stylesheet" />
 
</head>
<body>
	<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

	<br />
	
	<div class="set">
		<h3 style="font-weight: 600;">基本資料設定</h3>
		<br><br><br><br>
		<%-- 錯誤表列 --%>
		<div style="position: relative; left:350px;">
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>	
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	</div>
		<div class="Data-Content">
			<div class="Data-Title">
				<div class="AlignRight">

					<label for="txt">廠商名稱</label><br /> 
					<label for="txt">窗口姓名</label><br />
					<label for="txt">窗口電話</label><br /> 
					<label for="txt">窗口信箱</label><br />
					<label for="txt">公司統編</label><br /> 
					<label for="txt">公司負責人</label><br />
					<label for="txt">公司電話</label><br />
				</div>
			</div>
		</div>
		<form action="<%=request.getContextPath()%>/OrganizerServlet" method="POST">
			<div class="Data-Items">
				<input type="text" name="organizerName" value="<%=organizerVO.getOrganizerName()%>"/><br /> 
				<input type="text" name="windowName" value="<%=(organizerVO == null) ? "" : organizerVO.getWindowName()%>"/><br /> 
				<input type="text" name="windowPhone" value="<%=(organizerVO == null) ? "" : organizerVO.getWindowPhone()%>"/><br /> 
				<input type="text" name="windowEmail" value="<%=(organizerVO == null) ? "" : organizerVO.getWindowEmail()%>"/><br />
				<input type="text" name="taxIDNumber" value="<%=(organizerVO.getTaxIDNumber() == null) ? "" : organizerVO.getTaxIDNumber()%>"/><br /> 
				<input type="text" name="boss" value="<%=(organizerVO.getBoss() == null) ? "" : organizerVO.getBoss()%>"/><br /> 
				<input type="text" name="organizerPhone" value="<%=(organizerVO.getOrganizerPhone() == null) ? "" : organizerVO.getOrganizerPhone()%>"/><br />
			</div>
			<div class="submit">
				<br />
				<br />
				<input type="hidden" name="action" value="save">
				<input type="submit" value="儲存個人資料" /><br />
				<br />
			</div>
		</form>
	</div>

</body>
</html>