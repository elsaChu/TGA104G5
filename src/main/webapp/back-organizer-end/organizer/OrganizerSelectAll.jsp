<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ page import="java.util.* , com.organizer.model.OrganizerVO" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="UTF-8">
		<title>OrganizerSelectAll</title>
		<link href="<%= request.getContextPath() %>/back-organizer-end/organizer/css/style_manufacturer.css" rel="stylesheet" type="text/css" media="all" />
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
		<div class="login" name="SeachAll">
			<h2>搜尋廠商</h2>
			<div class="login-top">
				<h1>搜尋</h1>
				<form action="<%=request.getContextPath()%>/OrganizerSelectAll" name="OrganizerSelectAllForm"
					method="POST">
					<h1><%=request.getContextPath()%></h1>
					<input type="text" placeholder="搜尋廠商" name="organizerName" />
					<input type="text" placeholder="帳號" name="OAccount" />
					<div class="forgot">
						<input type="submit" value="搜尋所有" />
					</div>
				</form>
			</div>
			<div class="login-bottom">
				<a class="img" href="index.html"> <img src="../../main_frame/images/logo80.png" />
				</a>
				<h3>
					&nbsp;<a href="login_manufacturer.html">返回首頁
				</h3>
				</a>
			</div>
		</div>
	
	
	
		
		<%System.out.println("Java command on JSP file, running");%>
		<%List<OrganizerVO> allList = (List<OrganizerVO>) session.getAttribute("saAttribute");%>
		<%
		if(allList == null || (allList.size() == 0)){
			System.out.println("allList is null, or allList.size() == 0 ");
		}
		%>
		<%if (allList != null && (allList.size() > 0)) {%>
		
		<div class="showList">
			<font size="+3">所有廠商：</font>
			<div>
				<table border="1" width="740" id="resultTable">
					<tr bgcolor="#999999">
						<th width="200">書名</th><th width="100">作者</th>
						<th width="100">出版社</th><th width="100">價格</th>
						<th width="120">數量</th><th width="120"></th>
					</tr>
				
				<%
				 for (int index = 0; index < allList.size(); index++) {
					OrganizerVO order = allList.get(index);
				%>
					<tr>     
						<td width="200"><div align="center"><b><%=order.getOAccount()%></b></div></td>
						<td width="100"><div align="center"><b><%=order.getOpassword()%></b></div></td>
						<td width="100"><div align="center"><b><%=order.getOrganizerName()%></b></div></td>
						<td width="100"><div align="center"><b><%=order.getWindowName()%></b></div></td>
						<td width="100"><div align="center"><b><%=order.getWindowPhone()%></b></div></td>
						<td width="100"><div align="center"><b><%=order.getWindowEmail()%></b></div></td>
						<!-- <td width="100"><div align="center">	
							<input type="hidden" name="action" value="DELETE">
							<input type="hidden" name="del" value="<%= index %>">
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
				border: 2px solid black;
				margin: auto;
				margin-bottom: 10px;
			}
			td{
				border: 1px solid gray;
			}
		</style>
		
	</body>

</html>