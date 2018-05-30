

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
    </head>
    <body>
       <jsp:include page="header.jsp"></jsp:include>
        <form action="${pageContext.request.contextPath}/TypeproductServlet" method="POST">
            <table style="margin-left: 400px;">
                <tr>
                    <td>Mã thể loại </td>
                    <td><input style="margin-left: 20px; margin-top: 10px;" type="text" name="idtypeProduct" value="${student.idtypeProduct}"/></td>
                </tr>
                <tr>
                    <td>Tên thể loại </td>
                    <td><input style="margin-left: 20px; margin-top: 30px; margin-bottom: 40px;" type="text" name="nameType" value="${student.nameType}"/></td>
                </tr>
               
                <tr>
                    <td colspan="2"><input type="Submit" name="operation" value="Add" />
                    <input type="Submit" name="operation" value="Edit" />
                    <input type="Submit" name="operation" value="Delete" />
                    <input type="Submit" name="operation" value="Search" /></td>
                </tr>                
            </table>
        </form>
              <div class="footer" style="margin-left: 20px; margin-top: 40px;">
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
