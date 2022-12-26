<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.organizer.model.*"%>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%
OrganizerVO organizerVO = (OrganizerVO) request.getAttribute("organizerVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>TICK IT 廠商後台</title>

 <link href="css/organizer_sen.css" rel="stylesheet" />
 
</head>
<body>
	<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>

	<br />
	<div class="set">
		<h3 style="font-weight: 600;">基本資料設定</h3>
		<br><br><br><br>
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
		<form action="organizerServlet" method="POST">
			<div class="Data-Items">
				<input type="text" name="organizerName" value="${OrganizerVO.organizerName}"/><br /> 
				<input type="text" name="windowName" value="${OrganizerVO.windowName}"/><br /> 
				<input type="text" name="windowPhone" value="${OrganizerVO.windowPhone}"/><br /> 
				<input type="text" name="windowEmail" value="${OrganizerVO.windowEmail}"/><br />
				<input type="text" name="taxIDNumber" value="${OrganizerVO.taxIDNumber}"/><br /> 
				<input type="text" name="boss" value="${OrganizerVO.boss}"/><br /> 
				<input type="text" name="organizerPhone" value="${organizerPhone}"/><br />
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