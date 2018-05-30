<%-- 
    Document   : listClothes
    Created on : Apr 15, 2018, 5:02:45 PM
    Author     : ThoConXInhXan
--%>

<%@page import="entities.items.Clothes"%>
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
                List<Clothes> lisClothes = (List<Clothes>) session.getAttribute("lisClothes");
                for (int i = 0; i < lisClothes.size(); i++) {
            %>
            <form class="product">
                <img src="<%=lisClothes.get(i).getItemId().getImage()%>" width="60%" height="60%"/>
                <p><%=lisClothes.get(i).getItemId().getName()%></p>
                <h5><%=lisClothes.get(i).getItemId().getPrice()%> đ</h5>
                <button type="submit" class="btn">Mua</button>
            </form>
            <%
                }
            %>

        </div>
    </body>
</html>
