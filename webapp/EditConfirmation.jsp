<%-- 
    Document   : appointmentEdit
    Created on : Oct 27, 2014, 11:02:21 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.StudentAccount"%>
<%@page import="uta.cse4361.businessobjects.Appointment"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Appointment</title>
    </head>
<%
            int rank = 2;
            int sessionid = 2;
          
            
        %>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="appointmentAccordion" class="centerthis" >

                <h3>Account Edit</h3>
                <div>
                    <%

                        DatabaseManager dm = new DatabaseManager();
                            
                          
                            String emailParam = request.getParameter("email");
                            StudentAccount appt = dm.getStudentAccount(emailParam);
                            out.println(appt);
                            String name = appt.getName();
                            String email = appt.getEmail();
                            String department = appt.getDepartment();
                            int userRank = appt.getRank();
                             boolean descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                        
                    %>

                         
                        
                  
                        <%
                            out.print("<input type='hidden' name='id' value='" + appt.getID() + "'>");
                            out.print("<input type='hidden' name='email' value='" + appt.getEmail() + "'>");
                        %>

                    
                        <%
                            if (descriptionSubmitted) {
                        %>
                        <jsp:useBean id="macb" class="uta.cse4361.beans.ModifyAccountBean" scope="session"/>
                        <jsp:setProperty name="macb" property="ID" value= '<%= appt.getID() %>'/>
                        <jsp:setProperty name="macb" property="remove" value= '<%=Boolean.parseBoolean(request.getParameter("remove"))%>'/>
                        <%
                            if (Boolean.parseBoolean(request.getParameter("remove")) == false){
                        %>
                        <jsp:setProperty name="macb" property="name" value= '<%= request.getParameter("userName")%>'/>
                        <jsp:setProperty name="macb" property="email" value= '<%= request.getParameter("email")%>'/>
                        <jsp:setProperty name="macb" property="department" value= '<%= request.getParameter("department")%>'/>
                        <jsp:setProperty name="macb" property="rank" value= '<%=Integer.parseInt(request.getParameter("userRank"))%>'/>
                        
                        
                        <%
                            }
                        	String success = macb.modifyAccount();
                                if (success.equals("")) {
                                    response.sendRedirect("Accounts.jsp");
                                }
                            }
                        
                        %>
                        
                        
                </div>

            </div> 
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/appointmentEdit.js"></script>
</html>
