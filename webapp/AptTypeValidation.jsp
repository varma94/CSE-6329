<%-- 
    Document   : AptTypeValidation
    Created on : Mar 4, 2016, 12:02:12 AM
    Author     : oguni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Appointment Type</title>
        <jsp:useBean id="newAptType" class="uta.cse4361.beans.CreateAptTypeBean"/> 
        <jsp:setProperty name="newAptType" property="name" value='<%=request.getParameter("name")%>' />
    </head>
    <body>
         <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Creating New Appointment Type</h3>
                <div>
                    
                <%
                    String result = newAptType.createAptType();
                    if(result.equals("error"))
                    {
                        out.print("New Appointment Type could not be created");
                    }

                    else{
                        response.sendRedirect("appointmenttypes.jsp");
                    } 
                    
                %>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/LoginValidation.js"></script>
</html>

