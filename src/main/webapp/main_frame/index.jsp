<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <c:set var="context" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title -->
    <title>TICK IT</title>

    <!-- Favicon -->
    <link rel="icon" href="${context}/main_frame/images/a0svr-jih0d-001.ico" />

    <!-- Style CSS -->
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <header>

    <!-- 空行 -->
    <div class="container">　</div>

        <!-- Logo -->
        <div class="logo-area text-center">
            <div class="container h-100">
                <div class="row h-100 align-items-center">
                    <div class="col-12">
                        <a href="index.html" class="original-logo"><img src="images/logoa.png" alt=""></a>
                    </div>
                </div>
            </div>
        </div>

<!-- 空行 -->
<br>
    
    <!-- 未登入開始 -->
    <div class="row">
       <div class="col-2"></div>
       <div class="col-2"><a  class="www" href="#">禮品專區</a><a  class="www" href="#">探索活動</a></div>        
       <div class="col-5">
           <div class="box">
               <div class="container-1">
                    <span class="icon"><i class="fa fa-search"></i></span>
                    <input type="search" id="search" placeholder="搜索活動" />
               </div>
           </div>
       </div>   
                                
       <div class="col-3"><a class="www" href="#">註冊</a>|<a class="www" href="#">登入</a></div>                            
    </div>
     <!-- 未登入結束 -->
    <div class="row"> 
        <div class="col-12">　

        </div>
    </div>
   
                            
    <!-- 登入開始 -->
                            
    <div class="row">
        <div class="col-2"></div>
        <div class="col-2"><a  class="www" href="#">我的票券</a><a  class="www" href="#">禮品專區</a><a  class="www" href="#">探索活動</a>
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
            <li><a href="#"><iconify-icon icon="heroicons:user-circle-solid" width="30" height="30"></iconify-icon></a>
                <ul>
                    <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:cog-8-tooth" width="20" height="20"></iconify-icon> 設定</a>
                    </li>
                    <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:ticket" width="20" height="20"></iconify-icon> 票券訂單</a>
                    </li>
                    <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:gift" width="20" height="20"></iconify-icon> 禮品訂單</a>
                    </li>
                    <li><a href="#"><iconify-icon class="sign-out" icon="heroicons:arrow-right-on-rectangle-20-solid" width="20" height="20"></iconify-icon> 登出</a>
                    </li>
                </ul>
            </li>
            <li><a href="#"><iconify-icon icon="heroicons:bell-20-solid" width="30" height="30"></iconify-icon></a>
            </li>
            <li><a href="#"><iconify-icon icon="heroicons:heart-20-solid" width="30" height="30"></iconify-icon></a>
            </li>
            <li><a href="#"><iconify-icon icon="heroicons:shopping-cart-20-solid" width="30" height="30"></iconify-icon></a>
            </li>
        </ul>
    </div> 
    <!-- 登入結束 -->

    <!-- 空行 -->
    <div class="row"> <div class="col-12">　</div></div>              
    </header>
    <!-- content -->
	<div>
	
	
	</div>
	<!-- content end -->
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
                                    <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
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
    <!-- Popper js -->
    <script src="${context}/main_frame/js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${context}/main_frame/js/bootstrap.min.js"></script>
    <!-- Active js -->
    <script src="${context}/main_frame/js/active.js"></script>

    <script src="${context}/main_frame/js/model.js"></script>

    <script src="https://code.iconify.design/iconify-icon/1.0.1/iconify-icon.min.js"></script>
	<!-- Plugins js -->
    <script src="${context}/main_frame/js/plugins.js"></script>
</body>

</html>
