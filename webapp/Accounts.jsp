<%-- 
    Document   : CreateAccount
    Created on : Nov 22, 2014, 5:50:01 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Account</title>
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

                            <h3>Accounts</h3>
                            <div>
                                <form  name="AccountForm" action="accountEdit.jsp">
                                <table class="display" id="appointmentList" cellpadding= "3" cellspacing= "0" >
                                    <thead>

                                    <th>User Id</th>

                                    <th>User Email</th>

                                    <th>User Name</th>

                                    <th>User Department</th>

                                    <th>User Type</th>
                                    
                                    <th>Select</th>
                                    
                                    </thead>
                                    <tbody>
                                <%
                                    dm = new uta.cse4361.databases.DatabaseManager(); 
                                    java.util.ArrayList<uta.cse4361.businessobjects.AdvisorAccount> accounts = dm.getAccounts(); 
                                    for(uta.cse4361.businessobjects.AdvisorAccount a: accounts) {
                                        out.print("<tr>");
                                        out.print("<td>");
                                        out.print(a.getID());
                                        out.print("</td>");
                                        out.print("<td>");                                       
                                        out.print(a.getEmail());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getName() );
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getDepartment());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(dm.getAccountType(a.getRank()).getName());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print("<input type='radio' name='email' value='" + a.getEmail() +"'>");
                                        out.print("</td>");
                                        out.print("</tr>");
                                        out.print("</script>");
                                    }
                                    %>
                                    </tbody>
                                </table>
                                <br>
                                   <input type="submit" value="Modify This Account" id="submitBtn" class="btn btn-default" />
                                   <button type="button" value="Create New Account" id="createActBtn" 
                                           class ="btn btn-default" onclick="window.location.href='CreateAccount.jsp';" >Create New Account</button>
                                   <button type="button" value="Manage Account Types" id="createActBtn" 
                                           class ="btn btn-default" onclick="window.location.href='manageAccountTypes.jsp';" >Manage Account Types</button>      
                                </form>
                            </div>

                    </div>                   
            </div>
    <jsp:include page="footer.jsp" />                            
    </body>
    <script type="text/javascript" src="js/modifyAppointment.js"></script>
</html>
