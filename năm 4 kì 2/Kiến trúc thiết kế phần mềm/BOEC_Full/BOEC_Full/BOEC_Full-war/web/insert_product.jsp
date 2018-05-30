<%-- 
    Document   : admin
    Created on : Dec 11, 2017, 11:03:44 AM
    Author     : hoacn
--%>



<%@page import="entitiesProduct.Typeproduct"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link href="bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link href="stylec.css" rel="stylesheet" type="text/css" media="all" />
        
    </head>
    <body>
       
        <div class="content">
            <div class="main">
           <form action="ProductServlet" method="POST">
                <h2 align="left"> Thêm sản phẩm</h2>
                <table>
                    <tr>
                        <td> Mã sản phẩm </td>
                        <td><input type="text" name="food_id"></td>
                    </tr>
                    
                    <tr>
                         <span>Ma The Loai *</span>
                       <select name="category_id" style="margin-left: 10px; height: 40px;">
                            <%     
                List<Typeproduct> lisBooks = (List<Typeproduct>) session.getAttribute("lisBooks"); 
    for (int i = 0; i < lisBooks.size(); i++) {
        %>
        
         <option value="Ma the loai" > <%=lisBooks.get(i). getIdtypeProduct()%></option>
        
        <%                 } 
  %> 
                       
                    </select>
                    </tr>
                    <tr>
                        <td> Tên sản phẩm </td>
                        <td><input type="text" name="food_name"></td>
                    </tr>
                    <tr>
                        <td> Hình ảnh </td>
                        <td><input type="file" name="food_image"></td>
                    </tr>
                    <tr>
                        <td> Giá </td>
                        <td><input type="text" name="food_price"></td>

                    </tr>
                    <tr>
                        <td> Mô tả </td>
                        <td><input type="text" name="food_description"></td>
                    </tr>
                    <tr>

                       
                    <td colspan="2">
                    <input type="Submit" name="operation" value="Add" />
                    <input type="Submit" name="operation" value="Edit" />
                    <input type="Submit" name="operation" value="Delete" />
                    </td>
               
                    </tr>
            </form>
            </div>
        </div>
    </body>
</html>
