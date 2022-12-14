<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.product.model.*"%>

<%
ProductVO prodVo = (ProductVO) request.getAttribute("ProductVO");
%>

<c:set var="context" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>修改商品資料</title>
<%-- <link rel="stylesheet" type="text/css" href="${context}/main_frame/css/button.css" /> --%>
<jsp:include page="/main_frame/index_manufacturer.jsp"></jsp:include>
<%-- <link rel="stylesheet" type="text/css" href="${context}/back-organizer-end/product/css/updateProduct.css" /> --%>

<style>

td.prodNo {
	display: inline;
	color: #8E8E8E
}

h3 {
        font-weight: bold;
        color: #8E8E8E;
        margin: 20px 10px 10px 10px;
        display: inline-block;
        text-align: center;
      }

.my_size {
        margin: 0 auto;
        width: 970px;
}

div.div_text{
  display: inline-block;
  vertical-align:top;
}

div.div_img{
  display: inline-block;
  margin-left: 30px;
}

div.myButton{
  text-align: left;
  margin-top: 20px;
}

img.preview{
  max-width: 250px;
  border-radius: 3px;
}

ul.picture_list{
  list-style: none;
  margin: 0;
  padding: 0;
}

ul.picture_list>li{
  display: inline-block;
  vertical-align: top;
  margin: 10px 10px 10px 0px;
}
</style>

</head>
<body>
	<div class="my_size">
		<h3>修改商品資料</h3>
		<h4>
			<a
				href="<%=request.getContextPath()%>/back-organizer-end/product/selectProduct.jsp">回首頁</a>
		</h4>
		<div class="row">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red">請修正以下錯誤:</font>
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li style="color: red">${message}</li>
					</c:forEach>
				</ul>
			</c:if>
			<form method="post"
				action="<%=request.getContextPath()%>/ProductServlet" name="form1"
				enctype="multipart/form-data">
				<div class="div_text col-md-5">
					<table>
						<tr>
							<td>商品編號:<input type="text" name="prodNo"
								size="20" class="form-control" value="<%=prodVo.getProdNo()%>"
								readonly /></td>
						</tr>
						<tr>
							<td>活動編號:</td>
							<td><input type="text" name="eventNumber" size="20"
								class="form-control" value="<%=prodVo.getEventNumber()%>" /></td>
						</tr>
						<tr>
							<td>廠商編號:</td>
							<td><input type="text" name="organizerNumber" size="20"
								class="form-control" value="<%=prodVo.getOrganizerNumber()%>" /></td>
						</tr>
						<tr>
							<td>商品名稱:</td>
							<td><input type="text" name="prodName" size="20"
								class="form-control" value="<%=prodVo.getProdName()%>" /></td>
						</tr>
						<tr>
							<td>商品規格:</td>
							<td><input type="text" name="prodSpec" size="20"
								class="form-control" value="<%=prodVo.getProdSpec()%>" /></td>
						</tr>
						<tr>
							<td>商品單價:</td>
							<td><input type="number" name="unitPrice" size="20"
								class="form-control" value="<%=prodVo.getUnitPrice()%>" /></td>
						</tr>
						<tr>
							<td>庫存數量:</td>
							<td><input type="number" name="prodStock" size="20"
								class="form-control" value="<%=prodVo.getProdStock()%>" /></td>
						</tr>
						<tr>
							<td>商品詳情:</td>
							<td><input type="text" name="prodDetails" size="20"
								class="form-control" value="<%=prodVo.getProdDetails()%>" /></td>
						</tr>
						<tr>
							<td>商品是否上架:</td>
							<td><input type="radio" name="isPOn"
								value="<%=prodVo.getIsPOn()%>">是 <input type="radio"
								name="isPOn" value="<%=prodVo.getIsPOn()%>">否</td>
						</tr>
					</table>
				</div>
				<div class="div_img col-md-5">
					<span>商品圖片：</span>
					<ul class="picture_list">
						<li><img src=class=></li>
					</ul>
					<input type="file" id="prodIMG" name="prodIMG" class="prodIMG"
						accept="image/*">
					<div class="myButton">
						<input type="submit" value="確認修改">
					</div>
					<input type="hidden" name="action" value="update" />
				</div>
			</form>
		</div>
	</div>

	<%-- 	<script src="${context}/back-organizer-end/product/js/updateProduct.js"></script> --%>
</body>
</html>