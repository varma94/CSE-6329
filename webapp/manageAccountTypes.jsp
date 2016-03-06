<%-- 
    Document   : manageAccountTypes
    Created on : Mar 6, 2016, 3:05:50 PM
    Author     : oguni
--%>

<%@page import="uta.cse4361.businessobjects.AccountType"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Account Types</title>
    </head>
<%
            int rank = -1;
            int sessionid = -1;
            if ((session.getAttribute("id") == null) || (session.getAttribute("rank") == null)) {
               response.sendRedirect("index.jsp");
            }
            if (!(session.getAttribute("id") == null)) {
                    sessionid = Integer.parseInt((String) session.getAttribute("id"));
                }
                if (!(session.getAttribute("rank") == null)) {
                    rank = Integer.parseInt((String) session.getAttribute("rank"));
            }
                if(rank != 0 && rank != 1)
                {
               	response.sendRedirect("index.jsp");
                }
        %>
    <body>
        
            <tr>
                <jsp:include page="navigationbar.jsp" />
                <jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
            <div id="wrapper">
                <jsp:include page="header.jsp" />
                    <div id="appointmentAccordion" class="centerthis">

                            <h3>Account Types</h3>
                            <div>
                                <form  name="AccountForm" action="manageAccountTypes.jsp">
                                <table class="display" id="appointmentList" cellpadding= "3" cellspacing= "0" >
                                    <thead>

                                    <th>ID</th>

                                    <th>Name</th>
                                    
                                    <th>Select</th>
                                    
                                    </thead>
                                    <tbody>
                                <%
                                    dm = new uta.cse4361.databases.DatabaseManager(); 
                                    java.util.ArrayList<AccountType> accounts = dm.getAccountTypes(); 
                                    for(AccountType a: accounts) {
                                        out.print("<tr>");
                                        out.print("<td>");
                                        out.print(a.getID());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getName() );
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print("<input type='radio' name='email' value='" + a.getID() +"'>");
                                        out.print("</td>");
                                        out.print("</tr>");
                                        out.print("</script>");
                                    }
                                    %>
                                    </tbody>
                                </table>
                                <br>
                                   <input type="submit" value="Modify Account Type" name="action" value="Update" id="submitBtn" class="btn btn-default" />
                                   <input type="submit" value="Delete Account Type" name="action" value="Delete" class="btn btn-default"/>
                                   <button type="button" value="Create Account" id="createTypeBtn" 
                                           class ="btn btn-default" onclick="window.location.href='CreateAccountType.jsp';" >Create Account Type</button>
                                </form>
                            </div>

                    </div>                   
            </div>
    <jsp:include page="footer.jsp" />                            
    </body>
    <script type="text/javascript" src="js/modifyAppointment.js"></script>
</html>
