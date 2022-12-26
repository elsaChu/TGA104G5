<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bulletin</title>
<link rel="stylesheet" type="text/css" href="datetimepicker/jquery.datetimepicker.css" /> 
<link rel="stylesheet" type="text/css" href="css/BulletinIndex.css" /> 
</head>

<body>
<jsp:include page="/main_frame/index_Staff.jsp"></jsp:include>

<div class = "container mainDiv">
	<h1>管理系統公告(佈告欄)</h1>
	<div class="container bInfo">
		<div class="topRow">
			<div class = newB>
				<form action="AddBulletin.jsp">
					<button type="submit">
							新增系統公告
					</button>
				</form>
			</div>
		</div>
		<div class="bChart">
			
		</div>	
	</div>
</div>
</body>

</html>