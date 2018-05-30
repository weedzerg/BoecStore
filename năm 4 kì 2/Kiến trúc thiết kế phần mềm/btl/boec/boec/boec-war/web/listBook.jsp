<%-- 
    Document   : listBook
    Created on : Apr 13, 2018, 3:52:35 PM
    Author     : ThoConXInhXan
--%>

<%@page import="entities.items.Book"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="bander.jsp"></jsp:include>

            <div class="products">
            <%
                List<Book> lisBooks = (List<Book>) session.getAttribute("lisBooks");
                for (int i = 0; i < lisBooks.size(); i++) {
            %>
            <form class="product">
                <img src="<%=lisBooks.get(i).getItemId().getImage()%>" width="60%" height="60%"/>
                <p><%=lisBooks.get(i).getItemId().getName()%></p>
                <h5><%=lisBooks.get(i).getItemId().getPrice()%> đ</h5>
                <button type="submit" class="btn">Mua</button>
            </form>
            <%
                }
            %>

        </div>
    </body>
</html>
