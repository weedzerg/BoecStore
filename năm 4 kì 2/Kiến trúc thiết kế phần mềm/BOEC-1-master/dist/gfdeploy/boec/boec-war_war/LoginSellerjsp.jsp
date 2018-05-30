<%-- 
    Document   : LoginSellerjsp
    Created on : May 6, 2018, 6:38:20 PM
    Author     : DaiPhongPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Seller Login</title>
    </head>
    <body>
        <form action="LoginServelet" method="POST" style="text-align: center">
            <h1>Login</h1>
            <p>Username 		
                <input type="text" name="un"/></p>	
            <p>Password
                <input type="password" name="pw"/></p>
            <input type="submit" value="Login">	
        </form>
    </body>
</html>
