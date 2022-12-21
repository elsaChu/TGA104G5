<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="tw.com.tibame.member.model.*"%>
<% MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="description" content="" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>TICK IT</title>

    <!-- Favicon -->
    <link rel="icon" href="images/logo.ico" />

    <!-- Style CSS -->
    <link rel="stylesheet" href="css/memberCentre.css" />
    <style>
/*     .buttonOUT input[type="submit"] { */
/* 	font-size: 14px; */
/* 	width: 40px; */
/* 	background: #FFF; */
/* 	border-color:#FFF; */
/* 	border-style:none; */
/* } */
    </style>
  </head>

  <body>
    <header>
      <!-- 登入開始 -->
      <br />
      <div class="row">
        <div class="col-2"></div>
        <div class="col-2">
          <a class="www" href="#">我的票券</a
          ><a class="www" href="#">禮品專區</a
          ><a class="www" href="#">探索活動</a>
        </div>
        <div class="col-5">
          <div class="box">
            <div class="container-1">
              <span class="icon"><i class="fa fa-search"></i></span>
              <input type="search" id="search" placeholder="搜索活動" />
            </div>
          </div>
        </div>
        <ul class="drop-down-menu">
          <li>
            <a href="#"
              ><iconify-icon
                icon="heroicons:user-circle-solid"
                width="30"
                height="30"
              ></iconify-icon
            ></a>
            <ul>
              <li>
                <a href="#"
                  ><iconify-icon
                    class="sign-out"
                    icon="heroicons:cog-8-tooth"
                    width="20"
                    height="20"
                  ></iconify-icon>
                  設定</a
                >
              </li>
              <li>
                <a href="#"
                  ><iconify-icon
                    class="sign-out"
                    icon="heroicons:ticket"
                    width="20"
                    height="20"
                  ></iconify-icon>
                  票券訂單</a
                >
              </li>
              <li>
                <a href="#"
                  ><iconify-icon
                    class="sign-out"
                    icon="heroicons:gift"
                    width="20"
                    height="20"
                  ></iconify-icon>
                  禮品訂單</a
                >
              </li>
              <div class="buttonOUT">
              <form action="MemberServlet" method="POST" >
              <li id="Signout" >
              
              <iconify-icon
                    class="sign-out"
                    icon="heroicons:arrow-right-on-rectangle-20-solid"
                    width="20"
                    height="20"
                    style="margin: 0px -7px 0px 2px;"
                  ></iconify-icon>
              <input type="submit" value="登出">
              <input type="hidden" name="action" value="logout">
              </li>
				
              </form>
            </ul>
          </li>
          
          <li>
            <a href="#"
              ><iconify-icon
                icon="heroicons:bell-20-solid"
                width="30"
                height="30"
              ></iconify-icon
            ></a>
          </li>
          <li>
            <a href="#"
              ><iconify-icon
                icon="heroicons:heart-20-solid"
                width="30"
                height="30"
              ></iconify-icon
            ></a>
          </li>
          <li>
            <a href="#"
              ><iconify-icon
                icon="heroicons:shopping-cart-20-solid"
                width="30"
                height="30"
              ></iconify-icon
            ></a>
          </li>
        </ul>
      </div>
      </div>

      <!-- 登入結束 -->

      <!-- 空行 -->
      <div class="row"><div class="col-12">　</div></div>
    </header>

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
        <br />
        <%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
        <div class="Data-Content">
          <div class="Data-Title">
            <div class="AlignRight">
              <label for="txt">使用者帳號</label><br />
              <label for="txt">姓名</label><br />
              <label for="txt">信箱</label><br />
              <label for="txt">生日</label><br />
              <label for="txt">手機號碼</label><br /><br /><br />
              <hr style="width: 400%" />
              <label for="txt" style="font-weight: 600; font-size: 16px"
                >以下資料為報名表單的預填資料：</label
              ><br />
              <label for="txt">身份證字號</label><br /><br /><br /><br />
            </div>
          </div>
        </div>
        <form action="MemberServlet" method="POST" >
        <div class="Data-Items">
      		<input type="text" name="account" readonly="readonly" value="<%=(memberVO == null) ? "" : memberVO.getAccount()%>"/><br />
          	<input type="text" name="name" value="<%=(memberVO == null) ? "" : memberVO.getName()%>"/><br />
          	<input type="email" name="email" value="<%=(memberVO == null) ? "" : memberVO.getEmail()%>"/><br />
          	<input type="date" name="birthday" value="<%=(memberVO == null) ? "" : memberVO.getBirthday()%>"/>
         	 <span class="error" style="color: red">${errors.birthday}</span><br />
          	<input type="tel" name="phoneNumber" value="<%=(memberVO == null) ? "" : memberVO.getPhoneNumber()%>"/>
          	<span class="error" style="color: red">${errors.phoneNumber}</span><br />
          	<label for="txt"
            >此手機號碼將作為購票緊急聯絡的用途，部分活動可能需要手機認證才能報名購票，請務必輸入正確的手機號碼。</label
          ><br /><br /><br /><br />
          <hr style="width: 0%" />
<%--           	<input type="text" name="IDNumber" value="<%=(memberVO == null) ? "" : memberVO.getIdNumber()%>"/> --%>
<%--           	<span class="error" style="color: red">${errors.IDNumber}</span><br /><br /> --%>
        </div>
        <div class="checkbox">
          <input type="checkbox" name="subscription" id="subscription"  value="<%=(memberVO == null) ? "" : memberVO.getSubscription()%>"
          
          />
          是否訂閱TICK IT最新消息<br />
        </div>
        <div class="submit">
        	<input type="hidden" name="action" value="update">
          <input type="submit" value="儲存個人資料" /><br /><br />
          </form>
        </div>
        </div>
        
        <script type="text/javascript">
        var subscription = document.getElementById("subscription")
        subscription.addEventListener('change',(e) => {
        	if(e.currentTarget.checked){
        		subscription.value = "true";
        	} else {
        		subscription.value = "false";
        	}
        })

        </script>

      <div class="tab-content-2">
        <br />
        <h3>修改密碼</h3>
        <br />
        <div class="Data-Content2">
          <div class="Data-Title2">
            <div class="AlignRight2">
              <a>請輸入舊密碼</a><br />
              <a>請輸入新密碼</a><br />
              <a>請再次輸入新密碼</a><br />
            </div>
          </div>
        </div>
        <div class="Data-Items2">
          <input type="password" /><br />
          <input type="password" /><br />
          <input type="password" /><br /><br /><br />
        </div>
        <div class="submit">
          <input type="submit" value="確定修改密碼" /><br /><br />
        </div>
      </div>
      <div class="tab-content-3">
        <p>頁面2顯示的內容</p>
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
                  <span class="navbarToggler"
                    ><span></span><span></span><span></span
                  ></span>
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
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>

    <script src="js/model.js"></script>

    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
    
     <script>
   $('body').on('click', '#Signout', function() {
   var yes = confirm('確定登出嗎？');
   if (yes) {
       onsole.log('yes');	
       do_deletion();
             } 
     });
      </script>
    
    
  </body>
</html>
