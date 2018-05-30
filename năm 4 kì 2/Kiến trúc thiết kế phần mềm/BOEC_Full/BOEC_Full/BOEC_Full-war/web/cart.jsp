<%-- 
    Document   : cart
    Created on : 05-Dec-2017, 21:28:39
    Author     : LOAN
--%>


<%@page import="java.util.Map"%>
<%@page import="entitiesOrder.Item"%>
<%@page import="entitiesOrder.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Giỏ hàng</title>
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
        <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js" charset="utf-8"></script>
        <link rel="stylesheet" type="text/css" href="cartroom.css">
        <script language="javascript" src="cartroom.js"></script>
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
        <%
           
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>
        <!--header-->
        <div class="header">
            <div class="header-top">
                <div class="container">	
                    <div class="header-top-in">			
                        <div class="logo">
                            <a href="index.html"><img src="images/log.png" alt=" " style="width: 200px; height: 200px; margin-top: -50px; margin-left: 20px;"></a>
                            <p style="color: #865b35; font-size:60px; font-family: Edwardian Script ITC ; font-weight: bolder; margin-top: -130px; margin-left: -60px;">Foody</p>
                        </div>
                        <div class="header-in">
                            <ul class="icon1 sub-icon1">
                               
                                <li><a href="wishlist.html" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Quản lý đơn hàng </a> </li>
                                <li><a href="register.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Đăng ký </a></li>
                                <li><a href="http://localhost:8080/shop1/login.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;"> Đăng nhập</a></li>
                                <li><a href="http://localhost:8080/shop1/contact.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;"> Bản đồ</a></li>
                                <li><a href="cart.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Thanh toán</a> </li>	
                                <li><div class="cart">
                                        <a href="#" class="cart-in"> </a>
                                        <span> <%=cart.countItem()%></span>
                                    </div>
                                    <ul class="sub-icon1 list">
                                        <h3>Recently added items</h3>
                                        <div class="shopping_cart">

                                            <%for (Map.Entry<Long, Item> list : cart.getCartItems().entrySet()) {%>
                                        <div class="cart_box">
                                            <div class="message">
                                                <div class="alert-close"> </div> 
                                                <div class="list_img"><img src="<%=list.getValue().getProduct().getImage()%>" class="img-responsive" alt=""></div>
                                                <div class="list_desc"><h4><a href="CartServlet?command=remove&idProduct=<%=list.getValue().getProduct().getIdProduct()%>"><%=list.getValue().getProduct().getNameProduct()%></a></h4>
                                                    <%=list.getValue().getQuantity()%> x<span class="actual"> <%=list.getValue().getProduct().getPrice()%> VND</span>
                                                </div>
                                                <div class="clearfix"></div>
                                            </div>
                                        </div>
                                        <%}%>

                                        </div>
                                        <div class="total">
                                            <div class="total_left">Cart Subtotal: </div>
                                            <div class="total_right"><%=cart.totalCart()%> VND</div>
                                            <div class="clearfix"> </div>
                                        </div>
                                        <div class="login_buttons">
                                            <div class="check_button"><a href="checkout.jsp">Check out</a></div>
                                            <div class="clearfix"></div>
                                        </div>
                                        <div class="clearfix"></div>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="clearfix"> </div>
                    </div>
                </div>
            </div>

            <div class="header-bottom">
                <div class="container">
                    <div class="h_menu4">
                        <a class="toggleMenu" href="#">Menu</a>
                        <ul class="nav">
                            <li class="active"><a href="index.jsp"><i> </i>Home</a></li>
                            <li><a href="#" >Danh mục</a>
                               
                            <ul class="drop">
                              
                                <li><a href="">Sach</a></li>
                                <li><a href="">Quan ao</a></li>
                                <li><a href="">Do dien tu</a></li>
                                    
       
                            </ul>
                        
                            </li>
                            <li ><a href="#" >Món tráng miệng</a>

                            </li>         
                            <li ><a href="#" >Món khai vị</a> </li>					  				 
                            <li ><a href="#" >Đồ ăn nhanh</a> </li>
                            <li ><a href="#" >Đồ uống</a> </li>	
                            <li ><a href="#" >Công thức nấu ăn</a>

                        </ul>
                        <script type="text/javascript" src="js/nav.js"></script>
                    </div>
                </div>
            </div>
            <div class="header-bottom-in">
                <div class="container">
                    <div class="header-bottom-on">
                        <p class="wel" style="font-weight: bolder; font-family: Adobe Devanagari; font-size: 18px; color: #125827;margin-right: 80px;">
                            Hơi ấm tỏa ra từ gian bếp nhà</p>



                        <form method="post" name="frm" action="SearchServlet" style="margin-left: 60px;">
                            <tr><td colspan=2 style="font-size:12pt;" align="center">


                                    <input  type="text" name="pid" id="pid">
                                </td></tr>        
                            <tr><td colspan=2 align="center">
                                    <input  type="submit" name="submit" value="Search" ></td></tr>
                        </form>


                        <div class="clearfix"> </div>
                    </div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>
    </div>


    <h1 >Giỏ hàng</h1>

    <div class="shopping-cart">

        <div class="column-labels">
            <label class="product-image">Image</label>
            <label class="product-details">Mô tả</label>
            <label class="product-price">Giá</label>
            <label class="product-quantity">Số lượng</label>
            <label class="product-removal">Xóa</label>
            <label class="product-line-price">Tổng</label>
        </div>

        <div class="product">
            <%for (Map.Entry<Long, Item> list : cart.getCartItems().entrySet()) {%>
            <div class="product-image">
                <div class="magnify">
                    <img class="small" src="<%=list.getValue().getProduct().getImage()%>" />	
                </div>
            </div>
            <div class="product-details">
                <div class="product-title"><a href="CartServlet?command=remove&idProduct=<%=list.getValue().getProduct().getIdProduct()%>"><%=list.getValue().getProduct().getNameProduct()%></a></div>
                <p class="product-description"> 
                    Những chiếc bánh cupcake nho phù hợp với thời tiết giá lạnh cùng với những ly trà ấm nóng</p> 
            </div>
            <div class="product-price"><%=list.getValue().getProduct().getPrice()%> VND</div>
            <div class="product-quantity">
                <input type="number" value= <%=list.getValue().getQuantity()%>  min="1">
            </div> 
            <div class="product-removal">

                <a href="CartServlet?command=remove&idProduct=<%=list.getValue().getProduct().getIdProduct()%>">
                    Xóa</a>
            </div>
            <div class="product-line-price"><%=cart.total()%></div>
            <%}%>
        </div>
        <div class="totals">
            <div class="totals-item">
                <label>Tổng</label>
                <div class="totals-value" id="cart-subtotal"><%=cart.total()%> VND</div>
            </div>
            <div class="totals-item">
                <label>Tax (5%)</label>
                <div class="totals-value" id="cart-tax"><%=cart.tax()%></div>
            </div>
            <div class="totals-item">
                <label>Shipping</label>
                <div class="totals-value" id="cart-shipping">15000</div>
            </div>
            <div class="totals-item totals-item-total">
                <label>Chung</label>
                <div class="totals-value" id="cart-total"><%=cart.sum()%></div>
            </div> 
        </div>
        <div class="login_buttons">
            <div class="check_button"><a href="homepage.jsp">Tiếp tục mua hàng</a></div>
            <div class="clearfix"></div>
        </div>
        <div class="login_buttons">
            <div class="check_button"><a href="checkout.jsp">Thanh toán </a></div>
            <div class="clearfix"></div>
        </div>

        <div class="clearfix"></div>

    </div>
    <script src="prefixfree.js" type="text/javascript"></script>
    <!-- You can download it from http://leaverou.github.com/prefixfree/ -->

    <!-- Time for jquery action -->
    <script src="jquery-1.7.1.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            var native_width = 0;
            var native_height = 0;

            //Now the mousemove function
            $(".magnify").mousemove(function (e) {
                //When the user hovers on the image, the script will first calculate
                //the native dimensions if they don't exist. Only after the native dimensions
                //are available, the script will show the zoomed version.
                if (!native_width && !native_height)
                {
                    //This will create a new image object with the same image as that in .small
                    //We cannot directly get the dimensions from .small because of the 
                    //width specified to 200px in the html. To get the actual dimensions we have
                    //created this image object.
                    var image_object = new Image();
                    image_object.src = $(".small").attr("src");

                    //This code is wrapped in the .load function which is important.
                    //width and height of the object would return 0 if accessed before 
                    //the image gets loaded.
                    native_width = image_object.width;
                    native_height = image_object.height;
                } else
                {
                    //x/y coordinates of the mouse
                    //This is the position of .magnify with respect to the document.
                    var magnify_offset = $(this).offset();
                    //We will deduct the positions of .magnify from the mouse positions with
                    //respect to the document to get the mouse positions with respect to the 
                    //container(.magnify)
                    var mx = e.pageX - magnify_offset.left;
                    var my = e.pageY - magnify_offset.top;

                    //Finally the code to fade out the glass if the mouse is outside the container
                    if (mx < $(this).width() && my < $(this).height() && mx > 0 && my > 0)
                    {
                        $(".large").fadeIn(100);
                    } else
                    {
                        $(".large").fadeOut(100);
                    }
                    if ($(".large").is(":visible"))
                    {
                        //The background position of .large will be changed according to the position
                        //of the mouse over the .small image. So we will get the ratio of the pixel
                        //under the mouse pointer with respect to the image and use that to position the 
                        //large image inside the magnifying glass
                        var rx = Math.round(mx / $(".small").width() * native_width - $(".large").width() / 2) * -1;
                        var ry = Math.round(my / $(".small").height() * native_height - $(".large").height() / 2) * -1;
                        var bgp = rx + "px " + ry + "px";

                        //Time to move the magnifying glass with the mouse
                        var px = mx - $(".large").width() / 2;
                        var py = my - $(".large").height() / 2;
                        //Now the glass moves with the mouse
                        //The logic is to deduct half of the glass's width and height from the 
                        //mouse coordinates to place it with its center at the mouse coordinates

                        //If you hover on the image now, you should see the magnifying glass in action
                        $(".large").css({left: px, top: py, backgroundPosition: bgp});
                    }
                }
            })
        })
    </script>

    <div class="footer" style="margin-left: 10px; margin-top: 20px;">
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


