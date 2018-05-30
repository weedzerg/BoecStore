<%-- 
    Document   : listtype
    Created on : Apr 15, 2018, 11:21:14 PM
    Author     : nguye
--%>

<%@page import="entitiesProduct.Typeproduct"%>
<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    </head>
    <body>
        <table border="1">
            <td><a href="http://localhost:8080/BOEC_Final-war/typedetail.jsp">Type Info</a></td>
            <td><a href="http://localhost:8080/BOEC_Final-war/listtype.jsp">All Types</a></td> 

        </table>
        <table  border="1">
            <tr>   
                <th>id</th>  
                <th>name type</th>  

            </tr>       

            <%
                List<Typeproduct> lisBooks = (List<Typeproduct>) session.getAttribute("lisBooks");
                for (int i = 0; i < lisBooks.size(); i++) {
            %>
            <tr>
                <td><%=lisBooks.get(i).getIdtypeProduct()%></td>
                <td><%=lisBooks.get(i).getNameType()%></td>


            </tr>  
            <%                 }
            %>         </table> 
    </body>

</html>
