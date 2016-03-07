<%-- 
    Document   : ActTypeValidation
    Created on : Mar 6, 2016, 3:57:27 PM
    Author     : oguni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create New Account Type</title>
        <jsp:useBean id="newActType" class="uta.cse4361.beans.CreateActTypeBean"/> 
        <jsp:setProperty name="newActType" property="name" value='<%=request.getParameter("name")%>' />
        <jsp:setProperty name="newActType" property="privilege" value='<%=Integer.parseInt(request.getParameter("privilege"))%>' />
    </head>
    <body>
         <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Creating New Account Type</h3>
                <div>
                    
                <%
                    String result = newActType.createActType();
                    if(result.equals("error"))
                    {
                        out.print("New Account Type could not be created");
                    }

                    else{
                        response.sendRedirect("manageAccountTypes.jsp");
                    } 
                    
                %>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/LoginValidation.js"></script>
</html>
