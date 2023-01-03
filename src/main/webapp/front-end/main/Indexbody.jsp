<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Index Copy</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/flickity@2/dist/flickity.min.css">    <!-- Css Styles -->
<!--     <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"> -->
    <link rel="stylesheet" href="../main/css/font-awesome.min.css" type="text/css">
<!--     <link rel="stylesheet" href="css/elegant-icons.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="css/nice-select.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="css/jquery-ui.min.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="css/owl.carousel.min.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="css/slicknav.min.css" type="text/css"> -->
<!--     <link rel="stylesheet" href="../member/css/index_header.css" type="text/css"> -->
    <link rel="icon" href="../../main_frame/images/logo.ico" />
    <link rel="stylesheet" href="../main/css/style.css" type="text/css">
<!--     <link rel="stylesheet" href="style_member.css" /> -->
<!--     <link rel="stylesheet" href="./css/shoppingcart.css"> -->
    <link rel="stylesheet" href="../main/css/styleCopy.css">
</head>

<body>
    <!-- Header Section Begin -->
    <header class="header">
	<jsp:include page="/main_frame/index_header.jsp"></jsp:include>
	
	
    </header>
    <!-- Header Section End -->
    
    <!-- Carousel Begin  -->
    <section class="flick">
        <div class="mainCarousel">
	    <input type="hidden" name="memid" value="${sessionScope.memberVO}" id="memid" />
            <div class="gallery js-flickity" data-flickity-options='{ "wrapAround": true }'>
                <div class="gallery-cell">
                    <img src="../../front-end/main/images/sampleEvents/img10.jpg" alt="">
                </div>
                <div class="gallery-cell">
                    <img src="../../front-end/main/images/sampleEvents/img9.jpg" alt="">
                </div>
                <div class="gallery-cell">
                    <img src="../../front-end/main/images/sampleEvents/img12.jpg" alt="">
                </div>
                <div class="gallery-cell">
                    <img src="../../front-end/main/images/sampleEvents/img4.jpg" alt="">
                </div>
                <div class="gallery-cell">
                    <img src="../../front-end/main/images/sampleEvents/img8.jpg" alt="">
                </div>
            </div>
        </div>
    </section>
    <!-- Carousel End -->

<!--     notice start -->
<!--     <section class="notice"> -->
<!--         <div class="container"> -->
<!--             <div class="row"> -->
<!--                 <div class="noticeCol"> -->
<!--                     系統公告: 近期詐騙多，小心你阿嬤被騙，千萬小心 系統公告: 近期詐騙多，小心你阿嬤被騙，千萬小心  -->
<!--                     <div class="noticeContent"> -->
<!--                     </div> -->
<!--                 </div> -->
<!--             </div> -->
<!--         </div> -->
<!--     </section> -->
<!--     notice end  -->
    <!-- Featured Section Begin -->
    <section class="featured spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>熱門活動</h2>
                    </div>
                    <!-- <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".oranges">Oranges</li>
                            <li data-filter=".fresh-meat">Fresh Meat</li>
                            <li data-filter=".vegetables">Vegetables</li>
                            <li data-filter=".fastfood">Fastfood</li>
                        </ul>
                    </div> -->
                </div>
            </div>
            <div class="row featured__filter">
                <div class="col-lg-4 col-md-4 col-sm-6 mix oranges fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img1.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 mix vegetables fastfood">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img2.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 mix vegetables fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img3.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 mix fastfood oranges">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img4.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img5.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6 mix oranges fastfood">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img6.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>

                <div class="col-lg-12">
                    <div class="section-title">
                        <h2>即將舉行</h2>
                    </div>
                    <!-- <div class="featured__controls">
                        <ul>
                            <li class="active" data-filter="*">All</li>
                            <li data-filter=".oranges">Oranges</li>
                            <li data-filter=".fresh-meat">Fresh Meat</li>
                            <li data-filter=".vegetables">Vegetables</li>
                            <li data-filter=".fastfood">Fastfood</li>
                        </ul>
                    </div> -->
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fresh-meat vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img7.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix fastfood vegetables">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img8.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img9.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3 col-md-4 col-sm-6 mix oranges fresh-meat">
                    <div class="featured__item">
                        <div class="featured__item__pic set-bg" data-setbg="images/sampleEvents/img10.jpg">
                            <ul class="featured__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="#"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="featured__item__text">
                            <h6><a href="#">Crab Pool Security</a></h6>
                            <h5>$30.00</h5>
                        </div>
                    </div>
                </div>
                
            </div>
        </div>
    </section>
    <!-- Featured Section End -->



    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>精選活動</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="../../front-end/main/images/sampleEvents/img1.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i>2022.12.25</li>
                                <li><i class="fa fa-comment-o"></i> 2</li>
                            </ul>
                            <h5><a href="#">理財顧問投資工作坊：投資組合的打造與健診</a></h5>
                            <p>【理財顧問投資工作坊：投資組合的打造與健診】將利用10堂課，使用美國理財規劃工具，分析投資組合。協助顧問建構投資組合能力，並帶入案例...</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="../../front-end/main/images/sampleEvents/img2.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i>2023.01.05</li>
                                <li><i class="fa fa-comment-o"></i> 12</li>
                            </ul>
                            <h5><a href="#">星期四 老鳥試段子｜單口喜劇 Open Mic｜曾博恩</a></h5>
                            <p>要通過考驗才能踏上的舞台，能上台的喜劇演員沒一個是菜鳥！最腦洞大開的觀點／最新鮮的時事吐槽／最放蕩不羈的想法 都在《星期四 老鳥試段子》中文... </p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="../../front-end/main/images/sampleEvents/img3.jpg" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i>2023.01.07</li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="#">【街遊 Hidden Taipei】 北車迷城生存指南</a></h5>
                            <p>得意與失意的交會之處 這裏是北上遊子的第一個落腳處，後站的繁華來自尋覓工作機會的人們，交通便捷更讓後站街區成為批發集散地，如今看似褪去風光的華陰街... </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <!-- Footer Section Begin -->
	<jsp:include page="/main_frame/index_footer.jsp"></jsp:include>
    <!-- Footer Section End -->
    <script>
	     let before_url = sessionStorage.getItem("URL_before_login");
	     if(before_url){
	      sessionStorage.removeItem("URL_before_login");
	            window.location.href = before_url;
	     }
    </script>
    <!-- Js Plugins -->
<!--     <script src="js/jquery-3.3.1.min.js"></script> -->
<!--     <script src="js/bootstrap.min.js"></script> -->
    <script src="../main/js/jquery.nice-select.min.js"></script>
    <script src="../main/js/jquery-ui.min.js"></script>
    <script src="../main/js/jquery.slicknav.js"></script>
    <script src="../main/js/mixitup.min.js"></script>
    <script src="../main/js/owl.carousel.min.js"></script>
    <script src="../main/js/main.js"></script>
    <script src="../main/js/indexMainCopy.js"></script>
    <!-- flickity -->
    <script src="https://unpkg.com/flickity@2/dist/flickity.pkgd.min.js"></script>
</body>

</html>