<%-- 
    Document   : body
    Created on : 05-Dec-2017, 19:52:12
    Author     : LOAN
--%>


<%@page import="java.util.List"%>
<%@page import="entitiesProduct.Product"%>
<%@page import="entitiesOrder.Cart"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Body</title>
    <html>
        <head>
            <title>FOODY</title>
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
</head>
<body>

    <%

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
                <div class="slide" style="margin-left: 80px;" >
                    <img src="images/baner1.jpg" style="width: 900px; height: 300px;"/>
                    <img src="images/baner2.jpg" style="width: 900px; height: 300px;"/>
                    <img src="images/baner3.jpg" style="width: 900px; height: 300px;"/>
                    <img src="images/baner4.jpg" style="width: 900px; height: 300px;"/>
                </div>
                <div class="container" style=" margin-top: 100px;">
                    <div class="content">
                        <div class="content-top">
                            <h3 class="future">NỔI BẬT</h3>
                            <div class="content-top-in">
                                <%
                                    List<Product> p = (List<Product>) session.getAttribute("listProduct");
                                    for (int i = 0; i < p.size(); i++) {
                                %> 
                                <div class="col-md-3 md-col">
                                    <div class="col-md">
                                        <a href="fooddetail.jsp?idProduct=<%=p.get(i).getIdProduct()%>"><img  src="<%=p.get(i).getImage()%>" alt="<%=p.get(i).getNameProduct()%>" style="height: 140px;" /></a> 
                                        <div class="top-content">
                                            <h5><a href="fooddetail.jsp?idProduct=<%=p.get(i).getIdProduct()%>"><%=p.get(i).getNameProduct()%></a></h5>
                                            <div class="white">
                                                <a href="CartServlet?command=plus&idProduct=<%=p.get(i).getIdProduct()%>" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">ADD TO CART</a>
                                                <p class="dollar"><span class="in-dollar"></span><span><%=p.get(i).getPrice()%> </span></p>
                                                <div class="clearfix"></div>
                                            </div>

                                        </div>                          
                                    </div>
                                </div>
                                <%
                                    }

                                %>


                            </div>
                            <br/>
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
                                            <div class="slide-image"><img src="images/laptop1.jpg" style="width: 140px; height: 140px;" /></div>
                                            <div class="slide-image"><img src="images/vay1.png" style="width: 140px; height: 140px;"/></div>
                                            <div class="slide-image"><img src="images/sach2.jpg" style="width: 140px; height: 140px;"/></div>
                                            <div class="slide-image"><img src="images/sach1.jpg" style="width: 140px; height: 140px;"/></div>

                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="content-top">
                                <h3 class="future">Mới nhất</h3>
                                <div class="content-top-in">
                                    <div class="col-md-3 md-col">
                                        <div class="col-md">
                                            <a href="fooddetail.jsp"><img  src="images/dienthoai.jpg" alt="" style="height: 140px;" /></a>  
                                            <div class="top-content">
                                                <h5><a href="fooddetail.jsp">Bánh kem cacao</a></h5>
                                                <div class="white">
                                                    <a href="fooddetail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2 ">ADD TO CART</a>
                                                    <p class="dollar"><span class="in-dollar"></span><span>50</span><span>000</span></p>
                                                    <div class="clearfix"></div>
                                                </div>

                                            </div>                          
                                        </div>
                                    </div>
                                    <div class="col-md-3 md-col">
                                        <div class="col-md">
                                            <a href="fooddetail.jsp"><img  src="images/laptop1.jpg" alt=""  style="height: 140px;" />   </a>
                                            <div class="top-content">
                                                <h5><a href="fooddetail.jsp">Bánh bông lan cuộn</a></h5>
                                                <div class="white">
                                                    <a href="fooddetail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                                    <p class="dollar"><span class="in-dollar"></span><span>40</span><span>000</span></p>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>                          
                                        </div>
                                    </div>
                                    <div class="col-md-3 md-col">
                                        <div class="col-md">
                                            <a href="fooddetail.jsp"><img  src="images/sach2.jpg" alt="" style="height: 140px;" /></a>    
                                            <div class="top-content">
                                                <h5><a href="fooddetail.jsp">Caramen dâu tây</a></h5>
                                                <div class="white">
                                                    <a href="fooddetail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                                    <p class="dollar"><span class="in-dollar"></span><span>90</span><span>000</span></p>
                                                    <div class="clearfix"></div>
                                                </div>
                                            </div>                          
                                        </div>
                                    </div>
                                    <div class="col-md-3 md-col">
                                        <div class="col-md">
                                            <a href="fooddetail.jsp"><img  src="images/tivi.jpg" alt="" style="height: 140px;" /></a>   
                                            <div class="top-content">
                                                <h5><a href="fooddetail.jsp">Súp dâu</a></h5>
                                                <div class="white">
                                                    <a href="fooddetail.jsp" class="hvr-shutter-in-vertical hvr-shutter-in-vertical2">ADD TO CART</a>
                                                    <p class="dollar"><span class="in-dollar"></span><span>70</span><span>000</span></p>
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
                    <!---->
                    </body>
                    </html>
