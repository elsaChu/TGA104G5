<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.* ,com.organizer.model.OrganizerVO" %>



	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>OrganizerSelectAll</title>
		<link href="<%=request.getContextPath()%>/back-organizer-end/css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" />
		<!-- Custom Theme files -->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="keywords"
			content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
		<link rel="stylesheet"
			href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
		<!--Google Fonts-->
		<link rel="icon" href="../../main_frame/images/a0svr-jih0d-001.ico" />
<!-- 		Dynamic Webpage必須要使用下面兩種動態的位址才不會在跳轉(rd.forward)之後 載不到CSS) -->
<%-- 		<link href="<%= request.getContextPath() %>/css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" /> --%>
<%-- 		link href="${pageContext.request.contextPath}/css/sample.css" --%>
	</head>

	<body>
		<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>
		<div class="login" name="SeachAll">
			<h2>搜尋廠商</h2>
			<div class="login-top">
				<h1>搜尋</h1>
				<form action="<%=request.getContextPath()%>/OrganizerSelectAll" name="OrganizerSelectAllForm"
					method="POST">
							<h1>
							<%=request.getContextPath()%>
							<%// int i
							%>
							<%
							// int j
							%>
							
							</h1>
							
<!-- 					<input type="text" placeholder="搜尋廠商" name="organizerName" /> -->
<!-- 					<input type="text" placeholder="帳號" name="OAccount" /> -->
					<div class="forgot">
						<input type="submit" value="搜尋所有" />
					</div>
				</form>
				
			</div>
			<div class="login-bottom">
				<a class="img" href="index.html"> <img src="../../main_frame/images/logo80.png" />
				</a>
				<h3>
					<a href="<%=request.getContextPath()%>/OrganizerSelectAll">返回首頁
				</h3>
				</a>
			</div>
		</div>
	
	
	
		
		<%System.out.println("Java command on JSP file running");%>
		<%/* List<OrganizerVO> allList = (List<OrganizerVO>) session.getAttribute("saAttribute");*/%>
		
		<% List<OrganizerVO> allList = (List<OrganizerVO>) request.getAttribute("saAttribute"); %>
		<%if(allList == null || (allList.size() == 0)){
			System.out.println("allList is null, or allList.size() == 0 ");	}%>
		<%if (allList != null && (allList.size() > 0)){ %>
		
		<div class="showList">
			<font size="+3">所有廠商：</font>
			<div>
				<table borganizer1="1" width="740" id="resultTable">
					<tr bgcolor="#999999">
						<th width="200">廠商編號</th>
						<th width="200">廠商帳號</th>
						<th width="100">廠商密碼</th>
						<th width="100">廠商名稱</th>
						<th width="120">窗口姓名</th>
						<th width="100">窗口電話</th>
						<th width="120">窗口EMAIL</th>
					</tr>
					<% for (int index = 0; index < allList.size(); index++) {
								OrganizerVO organizer1 = allList.get(index); %>
					<tr>     
						<td width="200"><div align="center"><b><%=organizer1.getOrganizerNumber()%></b></div></td>
						<td width="200"><div align="center"><b><%=organizer1.getOAccount()%></b></div></td>
						<td width="100"><div align="center"><b><%=organizer1.getOpassword()%></b></div></td>
						<td width="100"><div align="center"><b><%=organizer1.getOrganizerName()%></b></div></td>
						<td width="100"><div align="center"><b><%=organizer1.getWindowName()%></b></div></td>
						<td width="100"><div align="center"><b><%=organizer1.getWindowPhone()%></b></div></td>
						<td width="100"><div align="center"><b><%=organizer1.getWindowEmail()%></b></div></td>
						<!-- <td width="100"><div align="center">	
							<input type="hidden" name="action" value="DELETE">
							<input type="submit" value="刪除"></div>
						</td> -->
					</tr>
					<%}%>
				</table>
				<%}%>
			</div>
			  
		</div>

		<style>
			.showList{
				background-color: antiquewhite;
				width: 75%;
				margin: auto;
			}
			#resultTable{
				align-self: center;
				background-color: azure;
				borganizer1: 2px solid black;
				margin: auto;
				margin-bottom: 10px;
			}
			td{
				borganizer1: 1px solid gray;
			}
		</style>
		
	</body>

</html>