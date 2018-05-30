<%-- 
    Document   : login
    Created on : 20-Dec-2017, 00:47:11
    Author     : LOAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <script type="text/javascript">
function hide_float_left() {
    var content = document.getElementById('float_content_left');
    var hide = document.getElementById('hide_float_left');
    if (content.style.display == "none")
    {content.style.display = "block"; hide.innerHTML = '<a href="javascript:hide_float_left()">Tắt quảng cáo [X]</a>'; }
        else { content.style.display = "none"; hide.innerHTML = '<a href="javascript:hide_float_left()">Xem quảng cáo...</a>';
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
        <style>.fb-livechat, .fb-widget{display: none}.ctrlq.fb-button, .ctrlq.fb-close{
                position: fixed; right: 24px; cursor: pointer}.ctrlq.fb-button
            {z-index: 999;
            background: url(data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjwhRE9DVFlQRSBzdmcgIFBVQkxJQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMS8vRU4nICAnaHR0cDovL3d3dy53My5vcmcvR3JhcGhpY3MvU1ZHLzEuMS9EVEQvc3ZnMTEuZHRkJz48c3ZnIGVuYWJsZS1iYWNrZ3JvdW5kPSJuZXcgMCAwIDEyOCAxMjgiIGhlaWdodD0iMTI4cHgiIGlkPSJMYXllcl8xIiB2ZXJzaW9uPSIxLjEiIHZpZXdCb3g9IjAgMCAxMjggMTI4IiB3aWR0aD0iMTI4cHgiIHhtbDpzcGFjZT0icHJlc2VydmUiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyIgeG1sbnM6eGxpbms9Imh0dHA6Ly93d3cudzMub3JnLzE5OTkveGxpbmsiPjxnPjxyZWN0IGZpbGw9IiMwMDg0RkYiIGhlaWdodD0iMTI4IiB3aWR0aD0iMTI4Ii8+PC9nPjxwYXRoIGQ9Ik02NCwxNy41MzFjLTI1LjQwNSwwLTQ2LDE5LjI1OS00Niw0My4wMTVjMCwxMy41MTUsNi42NjUsMjUuNTc0LDE3LjA4OSwzMy40NnYxNi40NjIgIGwxNS42OTgtOC43MDdjNC4xODYsMS4xNzEsOC42MjEsMS44LDEzLjIxMywxLjhjMjUuNDA1LDAsNDYtMTkuMjU4LDQ2LTQzLjAxNUMxMTAsMzYuNzksODkuNDA1LDE3LjUzMSw2NCwxNy41MzF6IE02OC44NDUsNzUuMjE0ICBMNTYuOTQ3LDYyLjg1NUwzNC4wMzUsNzUuNTI0bDI1LjEyLTI2LjY1N2wxMS44OTgsMTIuMzU5bDIyLjkxLTEyLjY3TDY4Ljg0NSw3NS4yMTR6IiBmaWxsPSIjRkZGRkZGIiBpZD0iQnViYmxlX1NoYXBlIi8+PC9zdmc+) center no-repeat #0084ff; width: 60px; height: 60px; text-align: center; bottom: 200px; border: 0; outline: 0; border-radius: 60px; -webkit-border-radius: 60px; -moz-border-radius: 60px; -ms-border-radius: 60px; -o-border-radius: 60px; box-shadow: 0 1px 6px rgba(0, 0, 0, .06), 0 2px 32px rgba(0, 0, 0, .16); -webkit-transition: box-shadow .2s ease; background-size: 80%; 
            transition: all .2s ease-in-out}.ctrlq.fb-button:focus, .ctrlq.fb-button:hover{transform: scale(1.1); box-shadow: 0 2px 8px rgba(0, 0, 0, .09), 0 4px 40px rgba(0, 0, 0, .24)}.fb-widget{background: #fff; z-index: 1000; position: fixed; width: 360px; height: 435px; overflow: hidden;
                      opacity: 0; bottom: 0; right: 24px; border-radius: 6px; 
                      -o-border-radius: 6px; -webkit-border-radius: 6px; box-shadow: 0 5px 40px rgba(0, 0, 0, .16);
                      -webkit-box-shadow: 0 5px 40px rgba(0, 0, 0, .16); -moz-box-shadow: 0 5px 40px rgba(0, 0, 0, .16); 
                      -o-box-shadow: 0 5px 40px rgba(0, 0, 0, .16)}.fb-credit{text-align: center; margin-top: 8px}.fb-credit a{transition: none; 
                     color: #bec2c9; font-family: Helvetica, Arial, sans-serif; font-size: 12px; text-decoration: none; border: 0; font-weight: 400}.ctrlq.fb-overlay{z-index: 0; position: fixed; height: 100vh; width: 100vw; -webkit-transition: opacity .4s, visibility .4s; transition: opacity .4s, visibility .4s; top: 0; left: 0; background: rgba(0, 0, 0, .05); display: none}.ctrlq.fb-close{z-index: 4; padding: 0 6px; background: #365899; font-weight: 700; font-size: 11px; color: #fff; margin: 8px; border-radius: 3px}.ctrlq.fb-close::after{content: "X"; font-family: sans-serif}.bubble{width: 20px; height: 20px;
                            background: #c00; color: #fff; position: absolute; z-index: 999999999; text-align: center; vertical-align: middle; top: -2px; left: -5px; border-radius: 50%;}.bubble-msg{width: 120px; left: -140px; top: 5px; position: relative; background: rgba(59, 89, 152, .8); color: #fff; padding: 5px 8px; border-radius: 8px; text-align: center; font-size: 13px;}</style><div class="fb-livechat"> <div class="ctrlq fb-overlay"></div><div class="fb-widget"> <div class="ctrlq fb-close"></div><div class="fb-page" data-href="https://www.facebook.com/chanhtuoicom" data-tabs="messages" data-width="360" data-height="400" data-small-header="true" data-hide-cover="true" data-show-facepile="false"> </div><div class="fb-credit"> <a href="https://chanhtuoi.com" target="_blank">Powered by Chanhtuoi</a> </div><div id="fb-root"></div></div><a href="https://m.me/chanhtuoicom" title="Gửi tin nhắn cho chúng tôi qua Facebook" class="ctrlq fb-button"> <div class="bubble">1</div>
                <div class="bubble-msg">Bạn cần hỗ trợ?</div></a></div>
        <script src="https://connect.facebook.net/en_US/sdk.js#xfbml=1&version=v2.9"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script>$(document).ready(function(){function detectmob(){if( navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPod/i) || navigator.userAgent.match(/BlackBerry/i) || navigator.userAgent.match(/Windows Phone/i) ){return true;}else{return false;}}var t={delay: 125, overlay: $(".fb-overlay"), widget: $(".fb-widget"), button: $(".fb-button")}; setTimeout(function(){$("div.fb-livechat").fadeIn()}, 8 * t.delay); if(!detectmob()){$(".ctrlq").on("click", function(e){e.preventDefault(), t.overlay.is(":visible") ? (t.overlay.fadeOut(t.delay), t.widget.stop().animate({bottom: 0, opacity: 0}, 2 * t.delay, function(){$(this).hide("slow"), t.button.show()})) : t.button.fadeOut("medium", function(){t.widget.stop().show().animate({bottom: "30px", opacity: 1}, 2 * t.delay), t.overlay.fadeIn(t.delay)})})}});
        
        </script>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="container" style="margin-left: 400px;">
                <div class="account">
                    <h2 >Đăng nhập</h2>
                    <form action="LoginServlet" method="POST">
                   
                    <table style="margin-top: 20px; margin-bottom: 20px;">
                <tr >
                    <td >Tên đăng nhập</td>
                    <td ><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td >Mật khẩu</td>
                    <td ><input type="text" name="password" /></td>
                </tr>

                <tr>
                    <td colspan="2">

                        <input type="Submit" name="operation" value="Login" />
                    </td>
                </tr>                
            </table>
                    <p style="margin-left: 400px; margin-top: -20px;">Bạn chưa có tài khoản ?<a href="http://localhost:8080/LapTrinhWeb/register.jsp"> Đăng ký</a></p>
                </form>
            </div>
        </div>
       <div class="footer" style="margin-left: 30px; margin-top: 0px;">
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
