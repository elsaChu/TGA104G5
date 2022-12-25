<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>>
<c:set var="context" value="${pageContext.request.contextPath}" />
<%@ page import="tw.com.tibame.event.model.*"%>
<%@ page import="java.util.*"%>

<%
OrderService orderSvc = new OrderService();
List<OrderEventVO> list = orderSvc.findByNumber();
pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="description" content="" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

<!-- Title -->
<title>TICK IT</title>

<!-- Favicon -->
<!-- <link rel="icon" href="img/core-img/logo.ico" /> -->

<!-- Style CSS -->
<link rel="stylesheet" href="${context}/main_frame/css/classy-nav.css" />
<link rel="stylesheet"
	href="${context}/main_frame/css/bootstrap.min.css" />
<link rel="stylesheet" href="${context}/main_frame/css/owl.carousel.css" />
<link rel="stylesheet" href="${context}/main_frame/css/animate.css" />
<link rel="stylesheet" href="${context}/main_frame/css/model.css" />
<link rel="stylesheet"
	href="${context}/main_frame/css/font-awesome.min.css" />
<link rel="stylesheet" href="${context}/main_frame/css/style_member.css" />

<style>
hr {
	border: 0.5px solid black;
}
</style>




</head>

<body>
	<header>
		<!-- 登入開始 -->

		<div class="row">
			<div class="col-2"></div>
			<div class="col-2">
				<a class="www" href="#">我的票券</a><a class="www" href="#">禮品專區</a><a
					class="www" href="#">探索活動</a>
			</div>
			<div class="col-5">
				<div class="box">
					<div class="container-1">
						<span class="icon"><i class="fa fa-search"></i></span> <input
							type="search" id="search" placeholder="搜索活動" />
					</div>
				</div>
			</div>
			<ul class="drop-down-menu">
				<li><a href="#"><iconify-icon
							icon="heroicons:user-circle-solid" width="30" height="30"></iconify-icon></a>
					<ul>
						<li><a href="#"><iconify-icon class="sign-out"
									icon="heroicons:cog-8-tooth" width="20" height="20"></iconify-icon>
								設定</a></li>
						<li><a href="#"><iconify-icon class="sign-out"
									icon="heroicons:ticket" width="20" height="20"></iconify-icon>
								票券訂單</a></li>
						<li><a href="#"><iconify-icon class="sign-out"
									icon="heroicons:gift" width="20" height="20"></iconify-icon>
								禮品訂單</a></li>
						<li><a href="#"><iconify-icon class="sign-out"
									icon="heroicons:arrow-right-on-rectangle-20-solid" width="20"
									height="20"></iconify-icon> 登出</a></li>
					</ul></li>
				<li><a href="#"><iconify-icon
							icon="heroicons:bell-20-solid" width="30" height="30"></iconify-icon></a>
				</li>
				<li><a href="#"><iconify-icon
							icon="heroicons:heart-20-solid" width="30" height="30"></iconify-icon></a>
				</li>
				<li><a href="#"><iconify-icon
							icon="heroicons:shopping-cart-20-solid" width="30" height="30"></iconify-icon></a>
				</li>
			</ul>
		</div>

		<!-- 登入結束 -->

		<!-- 空行 -->
		<div class="row">
			<div class="col-12"></div>
		</div>
	</header>
	\

	<span id="tab-1">帳號設定</span>
	<span id="tab-2">修改密碼</span>
	<span id="tab-3">票券訂單</span>
	<span id="tab-4">商品訂單</span>
	<!-- 頁籤按鈕 -->
	<div id="tab">
		<ul>
			<li><a href="#tab-1">帳號設定</a></li>
			<li><a href="#tab-2">修改密碼</a></li>
			<li><a href="#tab-3">票券訂單</a></li>
			<li><a href="#tab-4">商品訂單</a></li>
		</ul>

		<!-- 頁籤的內容區塊 -->
		<div class="tab-content-1">
			<br />
			<h3>修改帳號設定</h3>
			<br /> <a>使用者帳號</a><br /> <a>Email</a> <input type="email"
				name="email" /><br /> <a>手機號碼</a> <input type="tel"
				name="phoneNumber" /><br /> <a>此手機號碼將作為購票緊急聯絡的用途，部分活動可能需要手機認證才能報名購票，請務必輸入正確的手機號碼。</a><br />
			<a>生日</a> <input type="date" name="birthday" /><br /> <input
				type="checkbox" name="scales" checked /> 是否訂閱TICK IT最新消息<br /> <a>以下資料為報名表單的預填資料</a><br />
			連絡電話 <input type="tel" name="phone2" /><br /> 身分證字號 <input
				type="text" name="IDNumber" /><br /> 郵遞區號 <input type="text"
				name="postalCode" /><br /> 地址 <input type="text" name="address" /><br />
			<br /> <br />
		</div>
		<div class="tab-content-2">
			<br />
			<h3>修改密碼</h3>
			<br /> <a>請輸入舊密碼</a> <input type="password" /><br /> <a>請輸入新密碼</a>
			<input type="password" /><br /> <a>請再次輸入新密碼</a> <input
				type="password" /><br /> <br /> <br />
		</div>
		<div class="tab-content-3">
			<!-- 票券訂單todo============================== -->
			<br>
			<div>
				<h3>個人帳戶清單</h3>
			</div>
			<div>
				<div>
					<c:forEach var="orderEventVO" items="${list}">
					<a>訂單編號</a>${orderEventVO.number}
					<span style="display: inline-block; width: 350px;"></span>
					<a>活動名稱 / 描述</a>
					</c:forEach>
				</div>
			</div>
			<hr class="hr_style">
			<div class="row">
				<img src="my-image.png" alt="My Image"
							width="" height="">
				<br>
				<div class="col">
<!-- 					<a>#動態抓訂單編號</a><br> -->
<!-- 					<img src="my-image.png" alt="My Image" -->
<!-- 						width="" height=""> -->
				</div>
				<c:forEach var="orderEventVO" items="${list}">
				<div class="col">
					<div>
					 <p>活動名稱</p>${orderEventVO.eventName}
            		 <P>開始時間</P>${orderEventVO.eventStartDate}
          			 <p>活動地點</p>${orderEventVO.eventPlace}
        		     <p>主辦單位</p>${orderEventVO.organizerName}
        		     <p>訂單狀態</p>${orderEventVO.orderType}
       			     <p>票券數量</p>${orderEventVO.totalTicket}
            		 <p>總金額</p>${orderEventVO.total}
            		</div>
				</div>
					</c:forEach>
				<div class="col">
					            <div class="myButton1"><input type="submit"  value="修改訂單資訊"></div>
					            <div class="myButton1"><input type="submit"  value="取消訂單"></div>
					<!-- <a># 動態抓訂單編號</a>
          <div>
            <img src="my-image.png" alt="My Image" width="" height=""> 
          </div>-->
				</div>

				<!-- 票券訂單todo=============== =============== -->
				<!-- <p>頁面2顯示的內容</p> -->
			</div>
		</div>
		<div class="tab-content-4">
			<p>頁面3顯示的內容</p>
		</div>
	</div>

	<!-- ##### Footer 開始 ##### -->
	<footer class="footer-area text-center">
		<div class="container">
			<div class="row">
				<div class="col-12">
					<div class="classy-nav-container breakpoint-off">
						<nav class="classy-navbar justify-content-center">
							<div class="classy-navbar-toggler">
								<span class="navbarToggler"><span></span><span></span><span></span></span>
							</div>

							<!-- Menu -->
							<div class="classy-menu">
								<!-- close btn -->
								<div class="classycloseIcon">
									<div class="cross-wrap">
										<span class="top"></span><span class="bottom"></span>
									</div>
								</div>

								<!-- Nav Start -->
								<div class="classynav">
									<ul>
										<li><a href="#">常見問題</a></li>
										<li><a href="#">聯絡我們</a></li>
										<li><a href="#">系統公告</a></li>
										<li><a href="#">網站地圖</a></li>
										<li><a href="#">隱私權政策</a></li>
										<li><a href="#">服務條款</a></li>
									</ul>
								</div>
							</div>
						</nav>
					</div>

					<!-- Footer Social Area -->
				</div>
			</div>
		</div>
	</footer>
	<!-- ##### Footer 結束 ##### -->

	<!-- jQuery (Necessary for All JavaScript Plugins) -->
	<script src="${context}/main_frame/js/jquery/jquery-2.2.4.min.js"></script>
	<!-- 	http://localhost:8080\TGA104G5\src\main\resources\static\main_frame\js/jquery/jquery-2.2.4.min.js -->
	<!-- 	C:\JavaFramework\eclipse-workspace\TGA104G5\src\main\resources\static\main_frame\js -->
	<!-- Popper js -->
	<script src="${context}/main_frame/js/popper.min.js"></script>
	<!-- Bootstrap js -->
	<script src="${context}/main_frame/js/bootstrap.min.js"></script>

	<script src="${context}/main_frame/js/model.js"></script>

	<script
		src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>

	<!-- Plugins js -->
	<script src="${context}/main_frame/js/plugins.js"></script>
	<!-- Active js -->
	<script src="${context}/main_frame/js/active.js"></script>
</body>
</html>
