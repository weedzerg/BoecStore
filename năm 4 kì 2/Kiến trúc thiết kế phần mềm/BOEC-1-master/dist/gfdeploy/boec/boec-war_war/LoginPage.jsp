<%-- 
    Document   : LoginPage
    Created on : Oct 20, 2017, 5:17:43 PM
    Author     : thuy
--%>

<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Login Employee</title>
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

