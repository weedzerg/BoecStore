<%-- 
    Document   : login
    Created on : Dec 9, 2017, 11:22:18 PM
    Author     : QuyTaNgoc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="bander.jsp"></jsp:include>
        </div>
        <div class="content">
        <%--
        session là 1 phiên làm việc, lưu trữ toàn bộ data nó sẽ ko mất đi khi tắt trình duyệt chỉ mất đi khi xóa session
        nó khác cookie là cookie xét thời gian hết thì nó sẽ mất đi
        --%>
        <%
            if (session.getAttribute("user") != null) {
        %>
        <jsp:forward page="index.jsp"></jsp:forward>
        <%
            }
        %>


        <form action="loginServlet" method="POST">
            <h2>Đăng nhập</h2>
            <div>
                <p style="color: red;"><%
                    if (request.getAttribute("error") != null) {
                        out.print(request.getAttribute("error"));
                    }
                    %></p>

            </div>
            <div class="form-group">
                <label for="">USERNAME</label>
                <input type="text" class="form-control" name="username"  placeholder="Enter your username" >

            </div>
            <div class="form-group">
                <label for="exampleInputPassword1">PASSWORD</label>
                <input type="password" class="form-control" name="password" placeholder="Password">
            </div>
            <div>
                <p>Bạn chưa có tài khoản, đến ngay với màn hình <a href="register.jsp">Đăng ký</a></p>
            </div>
            <input type="hidden" name="command" value="login">
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>
