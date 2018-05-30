<%-- 
    Document   : footer
    Created on : 05-Dec-2017, 19:57:04
    Author     : LOAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Foot</title>
    <head>
        <title>FOODY</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
        <script src="js/jquery.min.js"></script>
        <!-- Custom Theme files -->
        <!--theme-style-->
        <link href="css/stylec.css" rel="stylesheet" type="text/css" media="all" />	
        <link rel="stylesheet" type="text/css" href="css/styleslide.css" media="screen" />	
        <script language="javascript" src="js/jquery-1.9.1.min.js"></script>
        <script language="javascript" src="js/custom.js"></script>
        <link href="css/star.css" rel="stylesheet" type="text/css" media="all" />	
        <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>
        <!--//theme-style-->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Bonfire Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
        <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
        <!--fonts-->
        <link href='http://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
        <!--//fonts-->
        <script type="text/javascript" src="js/move-top.js"></script>
        <script type="text/javascript" src="js/easing.js"></script>
        <script type="text/javascript">
            jQuery(document).ready(function ($) {
                $(".scroll").click(function (event) {
                    event.preventDefault();
                    $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
                });
            });
        </script>
        <!--slider-script-->
        <script src="js/responsiveslides.min.js"></script>
        <script>
            $(function () {
                $("#slider1").responsiveSlides({
                    auto: true,
                    speed: 500,
                    namespace: "callbacks",
                    pager: true,
                });
            });
        </script>
        <!--//slider-script-->
        <script>$(document).ready(function (c) {
                $('.alert-close').on('click', function (c) {
                    $('.message').fadeOut('slow', function (c) {
                        $('.message').remove();
                    });
                });
            });
        </script>
        <script>$(document).ready(function (c) {
                $('.alert-close1').on('click', function (c) {
                    $('.message1').fadeOut('slow', function (c) {
                        $('.message1').remove();
                    });
                });
            });
        </script>
    </head>
    <script type="text/javascript">
        $(function () {
            $('.slide img:gt(0)').hide();
            setInterval(function () {
                $('.slide :first-child').fadeOut()
                        .next('img').fadeIn()
                        .end().appendTo('.slide');
            },
                    6000);
        });

    </script>
</head>
<body>
    <div class="footer" style="margin-left: -180px; margin-top: 0px;">
        <div class="footer-top">
            <div class="container">
                <div class="col-md-4 footer-in">
                    <h4><i> </i>Nhập vị trí của bạn</h4>
                    <p>Chọn quận của bạn để hiển thị danh sách nhà hàng</p>
                </div>
                <div class="col-md-4 footer-in">
                    <h4><i class="cross"> </i>Chọn nhà hàng và món ăn.</h4>
                    <p>Bạn muốn ăn món gì? Chọn món từ nhiều nhà hàng khác nhau.</p>
                </div>
                <div class="col-md-4 footer-in">
                    <h4><i class="down"> </i>Thanh toán và giao hàng tận nơi</h4>
                    <p>hanh toán tiền mặt hay Thẻ tín dụng, PayPal.. Bon appetit!</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!---->
        <div class="footer-middle">
            <div class="container">
                <div class="footer-middle-in">
                    <h6>Ưu đãi cho bạn</h6>
                    <p>Đăng ký bản tin để nhận được các chương trình khuyến mãi cực chất nào!</p>
                    <p>Khuyến mãi hằng ngày và xu hướng mới trên Twitter, Facebook và Instagram.</p>
                </div>
                <div class="footer-middle-in">
                    <h6>Đảm bảo với bạn</h6>
                    <p>Không tính phí dịch vụ</p>
                    <p>Chất lượng dịch vụ tuyệt vời</p>
                    <p>Giá đảm bảo: thanh toán với giá bằng với giá tại nhà hàng</p>
                </div>

                <div class="footer-middle-in">
                    <h6>Lợi ích cho bạn</h6>
                    <p>500 nhà hàng đối tác giao tận nơi cho mọi nhu cầu của bạn</p>
                    <p>Thanh toán online hoặc bằng tiền mặt</p>
                    <p>Đặt món mọi lúc mọi nơi bằng ứng dụng di động</p>
                </div>
                <div class="footer-middle-in">
                    <h6>Food.com</h6>
                    <p>Dịch vụ khách hàng</p>
                    <p>TGiới thiệu một nhà hàng</p>
                    <p>Đăng ký nhà hàng</p>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <p class="footer-class">Copyright © 2015 Modern Template by  </p>
        <script type="text/javascript">
            $(document).ready(function () {
                /*
                 var defaults = {
                 containerID: 'toTop', // fading element id
                 containerHoverID: 'toTopHover', // fading element hover id
                 scrollSpeed: 1200,
                 easingType: 'linear' 
                 };
                 */

                $().UItoTop({easingType: 'easeOutQuart'});

            });
        </script>
        <a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

    </div>
</body>
</html>
