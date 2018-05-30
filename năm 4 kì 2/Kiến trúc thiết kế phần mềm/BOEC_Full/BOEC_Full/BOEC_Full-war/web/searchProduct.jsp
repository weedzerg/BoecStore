

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Student Information</title>
    </head>
    <body>
     
         <table border="1">
            <td><a href="studentinfo.jsp">Student Info</a></td>
            <td><a href="${pageContext.request.contextPath}/AllStudents">All Students</a></td>            
        </table>
        <br />

        <form action="${pageContext.request.contextPath}/SearchServlet" method="POST">
            <table>
                <tr>
                    <td>Student ID:</td>
                    <td><input type="text" name="idProduct" value="${student.idProduct}"/></td>
                </tr>
                <tr>
                    <td>Name:</td>
                    <td><input type="text" name="nameProduct" value="${student.nameProduct}"/></td>
                </tr>
                
                <tr>
                    <td colspan="2"><input type="Submit" name="operation" value="Add" />
                    <input type="Submit" name="operation" value="Edit" />
                    <input type="Submit" name="operation" value="Delete" />
                    <input type="Submit" name="operation" value="Search" /></td>
                </tr>                
            </table>
        </form>
           
    </body>
</html>
