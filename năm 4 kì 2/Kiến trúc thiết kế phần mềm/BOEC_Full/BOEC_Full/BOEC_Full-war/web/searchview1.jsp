<%-- 
    Document   : index
    Created on : 20-Nov-2017, 13:08:17
    Author     : LOAN
--%>


<%@page import="entitiesOrder.Cart"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>    
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">   
        <title>kq_Search</title>
    <div id="fb-root"></div>
    <script>(function (d, s, id) {
            var js, fjs = d.getElementsByTagName(s)[0];
            if (d.getElementById(id))
                return;
            js = d.createElement(s);
            js.id = id;
            js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v2.11&appId=823085731196901';
            fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" type="text/css" href="menu_room.css">
    <script language="javascript" src="jquery-3.2.1.js"></script>
    <script src="js/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script src="src/jquery.spritezoom.js"></script>
    <script src="js/doubletaptogo.js"></script>
    <script>
        $(function ()
        {
            $('#nav li:has(ul)').doubleTapToGo();
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
<script language="javascript">
    window.onload = function () {
        // Lấy danh sách button
        var button = document.getElementsByTagName('button');

        // Lặp qua từng button
        for (var i = 0; i < button.length; i++) {

            // gán sự kiện click
            button[i].addEventListener("click", function () {
                // Lấy thẻ tr
                var parent = this.parentElement.parentElement;
                // và thực hiện xóa
                parent.remove();
            });
        }
    };
</script>
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
<script type="text/javascript">
    function hide_float_left() {
        var content = document.getElementById('float_content_left');
        var hide = document.getElementById('hide_float_left');
        if (content.style.display == "none")
        {
            content.style.display = "block";
            hide.innerHTML = '<a href="javascript:hide_float_left()">Tắt quảng cáo [X]</a>';
        } else {
            content.style.display = "none";
            hide.innerHTML = '<a href="javascript:hide_float_left()">Xem quảng cáo...</a>';
        }
    }
</script>
<style>
    .float-ck { position: fixed; bottom: 0px; z-index: 9000}
    * html .float-ck {position:absolute;bottom:auto;top:expression(eval (document.documentElement.scrollTop+document.docum entElement.clientHeight-this.offsetHeight-(parseInt(this.currentStyle.marginTop,10)||0)-(parseInt(this.currentStyle.marginBottom,10)||0))) ;}
    #float_content_left {border: 1px solid #01AEF0;}
    #hide_float_left {text-align:left; font-size: 11px;}
    #hide_float_left a {background: #01AEF0; padding: 2px 4px; color: #FFF;}
</style>
<div class="float-ck" style="left: 0px" >
    <div id="hide_float_left">
        <a href="javascript:hide_float_left()">Tắt Quảng Cáo [X]</a></div>
    <div id="float_content_left">
        <!-- Start quang cao-->
        <img src="images/poster-quang-cao-sua-vinamilk-04.jpg" style="width: 150px;
             height: 150px;">
        <!-- End quang cao --> 
    </div>
</div>
</head>
<body>
    <style>.fb-livechat, .fb-widget{display: none}.ctrlq.fb-button, .ctrlq.fb-close{position: fixed; right: 24px; cursor: pointer}.ctrlq.fb-button{
            z-index: 999; 
            background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjwhRE9DVFlQRSBzdmcgIFBVQkxJQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4nICAnaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkJz48c3ZnIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDEyOCAxMjgiIGhlaWdodD0iMTI4cHgiIGlkPSJMYXllcl8xIiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMjggMTI4IiB3aWR0aD0iMTI4cHgiIHhtbDpzcGFjZT0icHJlc2VydmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjxnPjxyZWN0IGZpbGw9IiMwMDg0RkYiIGhlaWdodD0iMTI4IiB3aWR0aD0iMTI4Ii8+PC9nPjxwYXRoIGQ9Ik02NCwxNy41MzFjLTI1LjQwNSwwLTQ2LDE5LjI1OS00Niw0My4wMTVjMCwxMy41MTUsNi42NjUsMjUuNTc0LDE3LjA4OSwzMy40NnYxNi40NjIgIGwxNS42OTgtOC43MDdjNC4xODYsMS4xNzEsOC42MjEsMS44LDEzLjIxMywxLjhjMjUuNDA1LDAsNDYtMTkuMjU4LDQ2LTQzLjAxNUMxMTAsMzYuNzksODkuNDA1LDE3LjUzMSw2NCwxNy41MzF6IE02OC44NDUsNzUuMjE0ICBMNTYuOTQ3LDYyLjg1NUwzNC4wMzUsNzUuNTI0bDI1LjEyLTI2LjY1N2wxMS44OTgsMTIuMzU5bDIyLjkxLTEyLjY3TDY4Ljg0NSw3NS4yMTR6IiBmaWxsPSIjRkZGRkZGIiBpZD0iQnViYmxlX1NoYXBlIi8+PC9zdmc+) center no-repeat #0084ff; 
            width: 60px; height: 60px; text-align: center; bottom: 70px; border: 0; outline: 0; border-radius: 60px; 
            -webkit-border-radius: 60px; -moz-border-radius: 60px; -ms-border-radius: 60px; -o-border-radius: 60px;
            box-shadow: 0 1px 6px rgba(0, 0, 0, .06), 0 2px 32px rgba(0, 0, 0, .16); -webkit-transition: box-shadow .2s ease;
            background-size: 80%; transition: all .2s ease-in-out}.ctrlq.fb-button:focus, .ctrlq.fb-button:hover{transform: scale(1.1); box-shadow: 0 2px 8px rgba(0, 0, 0, .09), 0 4px 40px rgba(0, 0, 0, .24)}.fb-widget{background: #fff; z-index: 1000; position: fixed; width: 360px; height: 435px; overflow: hidden; opacity: 0; bottom: 0; right: 24px; border-radius: 6px; -o-border-radius: 6px; -webkit-border-radius: 6px; box-shadow: 0 5px 40px rgba(0, 0, 0, .16); -webkit-box-shadow: 0 5px 40px rgba(0, 0, 0, .16); -moz-box-shadow: 0 5px 40px rgba(0, 0, 0, .16); -o-box-shadow: 0 5px 40px rgba(0, 0, 0, .16)}.fb-credit{text-align: center; margin-top: 8px}.fb-credit a{transition: none; color: #bec2c9; font-family: Helvetica, Arial, sans-serif; font-size: 12px; text-decoration: none; border: 0; font-weight: 400}.ctrlq.fb-overlay{z-index: 0; 
                                                                                                                                                                                                        position: fixed; height: 100vh; width: 100vw; -webkit-transition: opacity .4s, visibility .4s; transition: opacity .4s, visibility .4s; top: 0; left: 0; background: rgba(0, 0, 0, .05); display: none}.ctrlq.fb-close{z-index: 4; padding: 0 6px; background: #365899; font-weight: 700; font-size: 11px; color: #fff; margin: 8px; border-radius: 3px}.ctrlq.fb-close::after{content: "X"; font-family: sans-serif}.bubble{width: 20px; height: 20px; background: #c00; color: #fff; position: absolute; z-index: 999999999; text-align: center; vertical-align: middle; top: -2px; left: -5px; border-radius: 50%;}.bubble-msg{width: 120px; left: -140px; top: 5px; position: relative; background: rgba(59, 89, 152, .8); color: #fff; padding: 5px 8px; border-radius: 8px; text-align: center; font-size: 13px;}</style><div class="fb-livechat"> <div class="ctrlq fb-overlay"></div><div class="fb-widget"> <div class="ctrlq fb-close"></div>
            <div class="fb-page" data-href="https://www.facebook.com/chanhtuoicom" data-tabs="messages" data-width="360" data-height="400" data-small-header="true" data-hide-cover="true" data-show-facepile="false"> </div><div class="fb-credit"> <a href="https://chanhtuoi.com" target="_blank">Powered by Chanhtuoi</a> </div><div id="fb-root"></div></div><a href="https://m.me/chanhtuoicom" title="Gửi tin nhắn cho chúng tôi qua Facebook" class="ctrlq fb-button"> <div class="bubble">1</div><div class="bubble-msg">Bạn cần hỗ trợ?</div></a></div><script src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9"></script><script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js">

    </script><script>$(document).ready(function () {
            function detectmob() {
                if (navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i)) {
                    return true;
                } else {
                    return false;
                }
            }
            var t = {delay: 125, overlay: $(".fb-overlay"), widget: $(".fb-widget"), button: $(".fb-button")};
            setTimeout(function () {
                $("div.fb-livechat").fadeIn()
            }, 8 * t.delay);
            if (!detectmob()) {
                $(".ctrlq").on("click",
                        function (e) {
                            e.preventDefault(), t.overlay.is(":visible") ? (t.overlay.fadeOut(t.delay), t.widget.stop().animate({bottom: 0, opacity: 0}, 2 * t.delay, function () {
                                $(this).hide("slow"), t.button.show()
                            })) : t.button.fadeOut("medium", function () {
                                t.widget.stop().show().animate({bottom: "30px", opacity: 1}, 2 * t.delay), t.overlay.fadeIn(t.delay)
                            })
                        })
            }
        });
    </script>
    <jsp:include page="header.jsp"></jsp:include>
    <%

        String category_id = "";
        if (request.getParameter("category") != null) {
            category_id = request.getParameter("category");
        }
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
    %>



    <div class="wrapper">
        <div class="content">
            <!-- left -->
            <div class="left">
                <!-- category -->
                <table cellspacing="0" cellpadding="5" >
                    <tr>
                        <td>
                            <div class="facebook">
                                <a href="#">
                                    <img src="images/face.jpg" style="width: 170px; margin-top: -10px;">
                                    <p style="margin-top: -100px; margin-bottom: 80px; margin-left: 70px; font-size: 25px; color: white; font-family: VNI-WIN Sampl;">
                                        <a href="#"></a> Food</p>
                                </a>


                                <div class="fb-like" data-href="https://developers.facebook.com/docs/plugins/" data-layout="standard" data-action="like" data-size="small" data-show-faces="true" data-share="true"></div>

                        </td>

                        </div>
                    </tr>
                    <tr>
                        <td>
                            <p style="margin-top: 20px;">Bình chọn</p>
                            <div class="stars" style="margin-left: -80px;">

                                <form action="">
                                    <input class="star star-5" id="star-5" type="radio" name="star"/>
                                    <label class="star star-5" for="star-5"></label>
                                    <input class="star star-4" id="star-4" type="radio" name="star"/>
                                    <label class="star star-4" for="star-4"></label>
                                    <input class="star star-3" id="star-3" type="radio" name="star"/>
                                    <label class="star star-3" for="star-3"></label>
                                    <input class="star star-2" id="star-2" type="radio" name="star"/>
                                    <label class="star star-2" for="star-2"></label>
                                    <input class="star star-1" id="star-1" type="radio" name="star"/>
                                    <label class="star star-1" for="star-1"></label>
                                </form>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <img src="images/today.jpg" style="width: 150px;
                                 height: 150px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <img src="images/giaohang.jpg" style="width: 150px;
                                 height: 150px;">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="static">
                                <ul>
                                    <li>Online: <span>88888</span></li>
                                    <li>Lượt truy cập: <span>88888</span></li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="support">
                                <div class="support_title">HỖ TRỢ TRỰC TUYẾN</div>
                                <div class="support_content">
                                    <ul>
                                        <li>Hỗ trợ kỹ thuật</li>
                                        <li><img src="images/yahoo.jpg"></li>
                                        <li>Hỗ trợ bán hàng</li>
                                        <li><img src="images/yahoo.jpg"></li>
                                        <li>Chăm sóc sức khoẻ</li>
                                        <li><img src="images/yahoo.jpg"></li>
                                    </ul>
                                </div>
                                <div class="support_contact">
                                    <ul>
                                        <li>Mobile: (84-4) 0913 234 678</li>
                                        <li>Emai: lanphuong.co@fpt.vn</li>
                                    </ul>
                                </div>
                                <div style="clear: both;"></div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div class="box_video">[Video]Hướng dẫn làm kem
                                <iframe  style="margin-left: -170px; margin-bottom: 20px;" width="170" height="200" src="https://www.youtube.com/embed/Ir3DGnUZUpk" frameborder="0" allowfullscreen></iframe>
                            </div>
                        </td>
                    </tr>
                </table>

            </div>
            <!-- end left -->
            <div class="main">
                <!-- slide -->
                <div class="container">
                    <div class="content">
                        <div class="content-top">
                            <h3 class="future">Kết quả tìm kiếm</h3>
                            <div class="content-top-in">


                                <div class="col-md-3 md-col">
                                    <div class="col-md">

                                        <form action="SearchServlet" method="POST">            
                                            <a href="fooddetail.jsp?idProduct=${student.idProduct}"><img  src="${student.image}" alt="" /></a>	
                                            <div class="top-content" style="margin-bottom: 30px;">
                                                <h5><a href="fooddetail.jsp?idProduct=${student.idProduct}">${student.nameProduct}</a></h5>
                                                <div class="white">
                                                    <a href="CartServlet?command=plus&idProduct=${student.idProduct}" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">ADD TO CART</a>
                                                    <p class="dollar"><span class="in-dollar"></span><span>${student.price} VND</span></p>
                                                    <div class="clearfix"></div>
                                                </div>

                                            </div>

                                        </form>

                                        <div class="clearfix"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="clearfix"></div>
                    </div>

                    <div class="clearfix"></div>
                    <div class="content-top">
                        <h3 class="future">ƯA THÍCH</h3>
                        <div class="slide-holder">
                            <div class="slide-pager">
                                <div class="slide-control-prev">«</div>
                                <div class="slide-control-next">»</div>
                            </div>
                            <div class="slide-container">
                                <div class="slide-stage">
                                    <div class="slide-image"><img src="images/imga1.jpg" style="width: 140px; height: 140px;" /></div>
                                    <div class="slide-image"><img src="images/imga2.jpg" style="width: 140px; height: 140px;"/></div>
                                    <div class="slide-image"><img src="images/imga3.jpg" style="width: 140px; height: 140px;"/></div>
                                    <div class="slide-image"><img src="images/imga4.jpg" style="width: 140px; height: 140px;"/></div>

                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="content-top">
                        <h3 class="future">Mới nhất</h3>
                        <div class="content-top-in">
                            <div class="col-md-3 md-col">
                                <div class="col-md">
                                    <a href="food_detail.jsp"><img  src="images/khaivi.jpg" alt="" style="height: 140px;" /></a>  
                                    <div class="top-content">
                                        <h5><a href="food_detail.jsp">Bánh kem cacao</a></h5>
                                        <div class="white">
                                            <a href="food_detail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">ADD TO CART</a>
                                            <p class="dollar"><span class="in-dollar">VND</span><span>50</span><span>000</span></p>
                                            <div class="clearfix"></div>
                                        </div>

                                    </div>                          
                                </div>
                            </div>
                            <div class="col-md-3 md-col">
                                <div class="col-md">
                                    <a href="food_detail.jsp"><img  src="images/khaivi1.jpg" alt=""  style="height: 140px;" />   </a>
                                    <div class="top-content">
                                        <h5><a href="food_detail.jsp">Bánh bông lan cuộn</a></h5>
                                        <div class="white">
                                            <a href="food_detail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                            <p class="dollar"><span class="in-dollar">VND</span><span>40</span><span>000</span></p>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>                          
                                </div>
                            </div>
                            <div class="col-md-3 md-col">
                                <div class="col-md">
                                    <a href="food_detail.jsp"><img  src="images/khaivi2.jpg" alt="" style="height: 140px;" /></a>    
                                    <div class="top-content">
                                        <h5><a href="food_detail.jsp">Caramen dâu tây</a></h5>
                                        <div class="white">
                                            <a href="food_detail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                            <p class="dollar"><span class="in-dollar">VND</span><span>90</span><span>000</span></p>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>                          
                                </div>
                            </div>
                            <div class="col-md-3 md-col">
                                <div class="col-md">
                                    <a href="food_detail.jsp"><img  src="images/khaivi3.jpg" alt="" style="height: 140px;" /></a>   
                                    <div class="top-content">
                                        <h5><a href="food_detail.jsp">Súp dâu</a></h5>
                                        <div class="white">
                                            <a href="food_detail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                            <p class="dollar"><span class="in-dollar">VND</span><span>70</span><span>000</span></p>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>                          
                                </div>
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </div>
                    <ul class="start">
                        <li ><a href="#"><i></i></a></li>
                        <li><span>1</span></li>
                        <li class="arrow"><a href="#">2</a></li>
                        <li class="arrow"><a href="#">3</a></li>
                        <li class="arrow"><a href="#">4</a></li>
                        <li class="arrow"><a href="#">5</a></li>
                        <li ><a href="#"><i  class="next"> </i></a></li>
                    </ul>
                </div>
            </div>



            </body>
            </html>
