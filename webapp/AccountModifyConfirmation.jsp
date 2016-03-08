<%-- 
    Document   : AccountModifyConfirmation
    Created on : Mar 8, 2016, 2:46:49 AM
    Author     : Abhijeet
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Abhijeet Chopra Mar 6-->
        <jsp:useBean id="newadvisor" class="uta.cse4361.beans.ModifyAdvisorAccountBean"/> 
        <jsp:setProperty name="newadvisor" property="email" value='<%=request.getParameter("email")%>' />
        <jsp:setProperty name="newadvisor" property="department" value='<%=request.getParameter("dept")%>' />
        <jsp:setProperty name="newadvisor" property="name" value='<%=request.getParameter("advisorName")%>' />
        <jsp:setProperty name="newadvisor" property="rank" value='<%=Integer.parseInt(request.getParameter("rank"))%>' />
        <jsp:setProperty name="newadvisor" property="ID" value='<%=Integer.parseInt(request.getParameter("advisorid"))%>' />
        <%--<jsp:setProperty name="newadvisor" property="lognum" value='<%=Integer.parseInt(request.getParameter("advisorid"))%>' />--%>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Confirmation</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Account Confirmation</h3>
                <div>
                    <%
                        String result = newadvisor.Advisor();
                        if (result == "") {
                            out.print(newadvisor.getName() + "'s has been Account Updated");
                        } else {
                            out.print("Account could not be Updated.");
                        }
                    %>
                </div>
            </div>
        </div>

    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/AccountConfirmation.js"></script>
</html>
