<%-- 
    Document   : accountDelete
    Created on : Mar 8, 2016, 11:35:54 AM
    Author     : oguni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:useBean id="macb" class="uta.cse4361.beans.ModifyAccountBean" scope="session"/>
        <jsp:setProperty name="macb" property="ID" value='<%=Integer.parseInt(request.getParameter("id"))%>' />
        <jsp:setProperty name="macb" property="remove" value='<%=Boolean.parseBoolean("true")%>' />
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Deletion</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Account Deletion</h3>
                <div>
                    <%
                        String result = macb.modifyAccount();
                        if (result == "") {
                            out.print( "Account has been Deleted");
                        } else {
                            out.print("Account could not be Deleted.");
                        }
                    %>
                </div>
            </div>
        </div>

    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/AccountConfirmation.js"></script>
</html>
