

<%@page import="entitiesPerson.Account"%>
<%@page import="java.util.List"%>
<%@page import="entitiesProduct.Typeproduct"%>
<%@page import="sessionProduct.TypeproductFacade"%>
<%@page import="sessionProduct.TypeproductFacadeLocal"%>
<%@page import="entitiesOrder.Item"%>
<%@page import="java.util.Map"%>
<%@page import="entitiesOrder.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
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
    <script src="js/jquery.min.js"></script>
    <link href="css/stylec.css" rel="stylesheet" type="text/css" media="all" />	
    <link rel="stylesheet" type="text/css" href="css/styleslide.css" media="screen" />	
    <script language="javascript" src="js/jquery-1.9.1.min.js"></script>
    <script language="javascript" src="js/custom.js"></script>
    <link href="css/star.css" rel="stylesheet" type="text/css" media="all" />	
    <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Bonfire Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
          Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <link href='http://fonts.googleapis.com/css?family=Exo:100,200,300,400,500,600,700,800,900' rel='stylesheet' type='text/css'>
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

    <%
        TypeproductFacadeLocal categoryDAO = new TypeproductFacade();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        Account users = null;
            if (session.getAttribute("account") != null) {
                users = (Account) session.getAttribute("account");
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
                            
                              <%if (users != null) {%>
                            <li><a href="wishlist.html" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;"><%=users.getUsername()%></a> </li>
                            <li><a href="quanlytaikhoan.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Quản lý tài khoản</a> </li>
                            <li><a href="LogoutController" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Đăng xuất</a> </li>
                            <li><a href="oder_userid.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Quản lý đơn hàng </a> </li>
                                <%}%>

                            <li><a href="http://localhost:8080/BOEC_Full-war/register.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Đăng ký </a></li>
                            <li><a href="login.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;"> Đăng nhập</a></li>
                            <li><a href="bando.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;"> Bản đồ</a></li>
                            <li><a href="cart.jsp" style="font-size: 22px;font-family: Adobe Devanagari; margin-left: -20px;">Thanh toán</a> </li>
                            
            	
                            <li><div class="cart">
                                    <a href="#" class="cart-in"> </a>
                                    <span>                        <%=cart.countItem()%></span>
                                </div>
                                <ul class="sub-icon1 list">
                                    <h3>Sản phẩm trong giỏ hàng</h3>
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
                                        <div class="total_left">Tổng: </div>
                                        <div class="total_right">VND</div>
                                        <div class="clearfix"> </div>
                                    </div>
                                    <div class="login_buttons">
                                        <div class="check_button"><a href="">Thanh toán</a></div>
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
                        <li class="active"><a href="homepage.jsp"><i> </i>Home</a></li>
                        <li><a href="#" >Danh mục</a>
                            <ul class="drop">
                              
                                <li><a href="">Sach</a></li>
                                <li><a href="">Quan ao</a></li>
                                <li><a href="">Do dien tu</a></li>
                                    
       
                            </ul>
                        </li>
                        <li ><a href="#" >Tin tức</a>

                        </li>         
                        <li ><a href="#" >Dạy nấu ăn</a> </li>					  				 
                        <li ><a href="#" >Nhà hàng</a> </li>
                        <li ><a href="#" >Làm đẹp</a> </li>	


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



                   <form action="${pageContext.request.contextPath}/SearchServlet" method="POST">
            <table>
                
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="nameProduct" value="${student.nameProduct}"/></td>
                </tr>
                
                <tr>
                    <td colspan="2">
                    <input type="Submit" name="operation" value="Search" /></td>
                </tr>                
            </table>
        </form>


                    <div class="clearfix"> </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>
<!--end header-->
</body>
</html>
