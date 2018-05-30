<%-- 
    Document   : listItem
    Created on : Apr 22, 2018, 10:39:20 AM
    Author     : DoHue
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.items.Item"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="products">
            <%
                if (session.getAttribute("lisItem") == null) {
            %>
            <jsp:forward page="/getItem"></jsp:forward>
            <%
            } else {
                List<Item> lisItem = (List<Item>) session.getAttribute("lisItem");
                for (int i = 0; i < lisItem.size(); i++) {
            %>
            <form class="product">
                <img src="<%=lisItem.get(i).getImage()%>" width="60%" height="60%"/>
                <p><%=lisItem.get(i).getName()%></p>
                <h5><%=lisItem.get(i).getPrice()%> đ</h5>
                <button type="submit" class="btn">Mua</button>
            </form>
            <%
                    }

                }
            %>
        </div>
    </body>
</html>
