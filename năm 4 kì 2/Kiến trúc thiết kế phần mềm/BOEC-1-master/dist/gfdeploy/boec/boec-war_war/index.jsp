<%-- 
    Document   : listBook
    Created on : Apr 13, 2018, 3:52:35 PM
    Author     : ThoConXInhXan
--%>

<%@page import="entities.person.Account"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BoEC</title>
    </head>
    <body>
        <%
            if(session.getAttribute("listTypeCategory") == null ){
                %>
                <jsp:forward page="/getCategory"></jsp:forward>
        <%
            }
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        <jsp:include page="bander.jsp"></jsp:include>
        <jsp:include page="content.jsp"></jsp:include>
        <jsp:include page="listItem.jsp"></jsp:include>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
