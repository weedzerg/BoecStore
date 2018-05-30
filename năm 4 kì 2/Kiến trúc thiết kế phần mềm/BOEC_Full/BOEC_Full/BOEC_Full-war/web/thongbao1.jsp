<%-- 
    Document   : thongbao1
    Created on : Apr 21, 2018, 11:04:18 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="${pageContext.request.contextPath}/TypeproductServlet" method="POST">
            <table style="margin-left: 400px;">
                
                
                <tr>
                    <td>Tên thể loại </td>
                    <td><input style="margin-left: 20px; margin-top: 30px; margin-bottom: 40px;" type="text" name="nameType" value="${student.nameType}"/></td>
                </tr>
               
                              
            </table>
        </form>
    </body>
</html>
