<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.* ,tw.com.tibame.member.model.MemberVO" %>
    <%@ page import="java.util.* ,tw.com.tibame.event.model.EventVO" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="css/styleCopy.css">

</head>
<body>	

<h1>this is your favorites</h1>
<!-- 	<div class="#" name="SeachAll"> -->
<!-- 		<h2>搜尋廠商</h2> -->
<!-- 		<div class="#"> -->
<!-- 			<h1>搜尋</h1> -->
<%-- 			<form action="<%=request.getContextPath()%>/MemberFavorite" name="" --%>
<!-- 				method="POST"> -->
<!-- 						<h1> -->
<%-- 						<%=request.getContextPath()%> --%>
						
<!-- 						</h1> -->
						
<!-- <!-- 					<input type="text" placeholder="搜尋廠商" name="organizerName" /> --> -->
<!-- <!-- 					<input type="text" placeholder="帳號" name="OAccount" /> --> -->
<!-- 				<div class="forgot"> -->
<!-- 					<input type="submit" value="搜尋所有" /> -->
<!-- 				</div> -->
<!-- 			</form> -->
			
<!-- 		</div> -->
<!-- 		<div class="login-bottom"> -->
<!-- 			<a class="img" href="index.html"> <img src="../../main_frame/images/logo80.png" /> -->
<!-- 			</a> -->
<!-- 			<h3> -->
<%-- 				<a href="<%=request.getContextPath()%>/OrganizerUpdate">返回首頁 --%>
<!-- 			</h3> -->
<!-- 			</a> -->
<!-- 		</div> -->
<!-- 	</div> -->



	
	<%System.out.println("Java command on JSP file running");%>
<%-- 	<% List<OrganizerVO> favoriteList = (List<OrganizerVO>) request.getAttribute("saAttribute");%> --%>
	<% List<EventVO> favoriteList = (List<EventVO>) session.getAttribute("favoriteList"); %>
	
	<%if(favoriteList == null || (favoriteList.size() == 0)){System.out.println("favoriteList is null, or favoriteList.size() == 0 ");}%>
	<%if (favoriteList != null && (favoriteList.size() > 0)){	%>
	
	<div class="showList">
		<font size="+3">所有廠商：</font>
		<div>
			<table bevent1="1" width="1000" id="resultTable">
				<tr bgcolor="#999999">
					<th width="200">eventNumber</th>
					<th width="200">eventName</th>
					<th width="100">eventPlace密碼</th>
					<th width="100">廠商名稱</th>
					<th width="120">窗口姓名</th>
					<th width="100">窗口電話</th>
					<th width="120">窗口EMAIL</th>
					<th width="100">刪除</th>
				</tr>
				<%for (int index = 0; index < favoriteList.size(); index++) {EventVO event1 = favoriteList.get(index);%>
				<tr>     
					<td width="100"><div align="center"><b><%=event1.getEventNumber()%></b></div></td>
					<td width="200"><div align="center"><b><%=event1.getEventName()%></b></div></td>
					<td width="200"><div align="center"><b><%=event1.getEventPlace()%></b></div></td>
<%-- 					<td width="100"><div align="center"><b><%=event1.getOrganizerName()%></b></div></td> --%>
<%-- 					<td width="100"><div align="center"><b><%=event1.getWindowName()%></b></div></td> --%>
<%-- 					<td width="100"><div align="center"><b><%=event1.getWindowPhone()%></b></div></td> --%>
<%-- 					<td width="100"><div align="center"><b><%=event1.getWindowEmail()%></b></div></td> --%>
					<td width="100"><div align="center">	
						<form action="<%=request.getContextPath()%>/OrganizerUpdateOne" method="post">
							<input type="hidden" name="whichRow" value="<%=event1.getEventNumber()%>">
							<input type="submit" value="更新"></div>
						</form>
					</td> 
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