<%-- 
    Document   : logout.jsp
    Created on : Nov 30, 2014, 4:54:23 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Logout</title>
    </head>
    <body>
        <%
            request.getSession().invalidate();
            response.sendRedirect("index.jsp");
            %>
    </body>
</html>
