<%-- 
    Document   : listBook
    Created on : Apr 13, 2018, 3:52:35 PM
    Author     : ThoConXInhXan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Cinzel|Open+Sans+Condensed:300" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="css/demo.css">
        <script type="text/javascript" src="js/layout.js"></script>
    </head>
    <body>       
        <header>
            <div class="logo-left">Bo<u>EC</u></div>
            <div class="logo-center">
                <form action="search.jsp" method="GET" class="FormSearch" >
                    <button type="button" id="ButtonSearchChoice">Danh mục</button>
                    <ul class="UlSearch">
                        <li value="0">Tất cả</li>
                    </ul>
                    <input type="hidden" id="valLi" name="categoryID" value="0">
                    <input class="InputSearch" type="text" required name="query" placeholder="Tìm kiếm danh mục sản phẩm hoặc nhà xuất bản,...">
                    <button id="ButtonSearchSubmit" type="submit">Tìm kiếm</button>
                </form>
                <script type="text/javascript" src="js/demo.js"></script>
            </div>
            <div class="logo-right">
                <ul>
                    <%
                        if (request.getSession().getAttribute("user") == null) {

                    %>
                    <li><a href="login.jsp"><img src="images/login.png">Đăng nhập</a></li>
                            <%                        } else {
                            %>
                    <li style="display: block;">
                        <span style="font-size: 15px; font-weight: 200;"><% out.print("Xin chào"); %></span>

                        <a style="display: inline-block" href="logout.jsp">Thoát</a>
                    </li>

                    <%
                        }
                    %>      
                    <li><a href="cart.jsp"><img src="images/shopping.png">Giỏ hàng</a></li>
                    <li><a href="trackorders.jsp"><img src="images/cart.png">Theo dõi đơn hàng</a></li>
                </ul>
            </div>
            <%
                if (!request.getRequestURI().contains("login.jsp")) {
                    request.getSession().setAttribute("abc", request.getRequestURI());
                }

            %>
        </header>
    </body>
</html>
