<%-- 
    Document   : listitemadmin
    Created on : May 7, 2018, 1:40:58 PM
    Author     : DaiPhongPC
--%>

<%@page import="entities.items.Item"%>
<%@page import="java.util.List"%>
<%@page import="entities.items.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
                if (session.getAttribute("lisItem") == null) {
            %>
            <jsp:forward page="/getItem"></jsp:forward>
            <%
            } else {
                List<Item> lisItem = (List<Item>) session.getAttribute("lisItem");
                for (int i = 0; i < lisItem.size(); i++) {
            %>
      <form action="SearchBookServlet">
            <p>Nhập tên sách</p>
            <input type="text" name="keysearch"/>
            <input type="submit" value="Search"/>
        </form>
        <h1 align="center">List Book</h1>
        <ul>
            <%                for (Item b : lisItem) {
                    String editURL = "edit.jsp?masp=" + b.getId();
                    String deleteURL = "DeleteServlet?masp=" + b.getId();
            %>
            <li style="display: -moz-inline-grid;margin-left: 20px;margin-bottom: 30px;">
                <div>
                    <a>
                        <img width="200px" height="300px" src="<%= b.getImage()%>"/>
                    </a>

                    <div>
                        <span style="display: block"><%=b.getName()%></span>
                        <span><%=b.getPrice()%> vnd</span>
                    </div> 
                    <a >
                        <img src="img/cart.png" width="50px" height="50px">
                    </a>
                </div>
            </li>
            <% }
            %>
        </ul>
    </body>
</html>
