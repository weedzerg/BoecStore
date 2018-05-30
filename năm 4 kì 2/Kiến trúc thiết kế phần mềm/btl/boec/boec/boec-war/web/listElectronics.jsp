<%-- 
    Document   : listBook
    Created on : Apr 13, 2018, 3:52:35 PM
    Author     : ThoConXInhXan
--%>

<%@page import="entities.items.Electronics"%>
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
                List<Electronics> lisElectronic = (List<Electronics>) session.getAttribute("lisElectronic");
                for (int i = 0; i < lisElectronic.size(); i++) {
            %>
            <form class="product">
                <img src="<%=lisElectronic.get(i).getItemId().getImage()%>" width="60%" height="60%"/>
                <p><%=lisElectronic.get(i).getItemId().getName()%></p>
                <h5><%=lisElectronic.get(i).getItemId().getPrice()%> đ</h5>
                <button type="submit" class="btn">Mua</button>
            </form>
            <%
                }
            %>

        </div>
    </body>
</html>
