<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.* ,tw.com.tibame.organizer.model.OrganizerVO" %>

<!DOCTYPE html>
<html>

<head>
  <title>Select By　Result</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="keywords"
    content="Login form web template, Sign up Web Templates, Flat Web Templates, Login signup Responsive web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
  <!-- Custom Theme files -->
  <link href="<%=request.getContextPath()%>/back-organizer-end/css/OrganizeSelectAllResult.css" rel="stylesheet" type="text/css" media="all" />

  <!-- <link href="css/OrganizeSelectAllResult.css" rel="stylesheet" type="text/css" media="all" /> -->
  <!-- Custom Theme files -->
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<body>
  <div class="login">
    <h2>廠商註冊</h2>
    <div class="login-top">
      <h1>搜尋</h1>
      <div>
        <div class="searchOrganizerDiv">
          
          <form action="<%=request.getContextPath()%>/OrganizerSelectBy" name="OrganizerSelectAllForm" method="POST">
          <h1><%=request.getContextPath()%></h1>
			<h3>
			
			<%List<OrganizerVO> a = (List<OrganizerVO>) request.getAttribute("matchList");%>
			<table style="border: 1px solid black">
				<tr style="border: 1px solid black">
					<th style="border: 1px solid black">  Organizer Num </th>
					<th>  Organizer Name  </th>
				</tr>
			<%	if(a != null && a.size() != 0){     %>
			<%	for(int i =0; i < a.size() ; i++){         %>
				<tr style="border: 1px solid black">
					<td style="border: 1px solid black">
					<%= a.get(i).getOrganizerNumber() %>
					</td>
					<td>
					<%= a.get(i).getOrganizerName()%>
					</td>
				</tr>
					<%
				}}
			%>
			</table>
			</h3>
            <div class="forgot row">
              <div class="searchBarDiv col-10">
                <input type="text" placeholder="搜尋廠商(searchKeyword)" name="searchKeyword" class="searchBar">
              </div>
              <div class="col-2">
                <input type="submit" value="搜索" />
              </div>
            </div>
            <input type="hidden" name="testHidden" value="<%=request.getContextPath()%>">
          </form>
     
          <div class="organizerSearchBy">
            <!-- <input type="text" placeholder="SearchByOrganizerName" /> -->
            <!-- <label for="">Order By: </label>
            <select name="" id="">
              <option value="">Organizer Name</option>
              <option value="">Organizer UserName</option>
              <option value="">Organizer 註冊日期</option>
            </select> -->
            <!-- <input type="text" placeholder="公司登記地址" /> -->
          </div>
        </div>
      </div>

      
    </div>
    <div class="login-bottom">
      <a class="img" href="index.html">
        <img src="../../main_frame/images/logo80.png" />
      </a>
      <h3>我已經有帳號了&nbsp;<a href="login_manufacturer.html">登入</h3></a>
    </div>
    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>

</body>

</html>