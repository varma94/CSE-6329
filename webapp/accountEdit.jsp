<%-- 
    Document   : appointmentEdit
    Created on : Oct 27, 2014, 11:02:21 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.AdvisorAccount"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Appointment</title>
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
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="appointmentAccordion" class="centerthis" >

                <h3>Account Edit</h3>
                <div>
                    <%

                        DatabaseManager dm = new DatabaseManager();
                        if (request.getParameter("email") == null || request.getParameter("email") == "" || request.getParameter("email").equals("")) {
                            response.sendRedirect("DeleteAccount.jsp");
                        }
                        else 
                        {
                            String emailParam = request.getParameter("email");
                            AdvisorAccount appt = dm.getAccount(emailParam);
                            String name = appt.getName();
                            String email = appt.getEmail();
                            String department = appt.getDepartment();
                            int userRank = appt.getRank();
                            boolean descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                        
                    %>

                    <form name="edit" action="accountEdit.jsp" method="submit" role="form">
                        <div class="form-group">
                            <label for="userName">Name</label>
                            <input class="form-control" type="text" name="userName" size="50" id="userName" value = "<%=name%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="text" name="email" size="50" id="email" value = "<%=email%>">
                        </div>
                        <div class="form-group">
                            <label for="studentName">Department</label>
                            <input class="form-control" type="text" name="department" size="50" id="department" value = "<%=department%>">
                        </div>
                        <div class="form-group">
                            <label for="userRank">Rank</label>
                            <input class="form-control" type="text" name="userRank" size="50" id="userRank" value = "<%=userRank%>">
                        </div>
                        
                        <input type="hidden" value="false" name="remove">
                        <input type="submit" value="Edit Account" id="submitBtn" class="btn btn-default">
                    </form>
                        
                    <form method="submit" action="accountEdit.jsp">
                        <%
                            out.print("<input type='hidden' name='id' value='" + appt.getID() + "'>");
                            out.print("<input type='hidden' name='email' value='" + appt.getEmail() + "'>");
                        %>

                        <input type="hidden" value="true" name="remove"><br>
                        <input type="submit" value="Delete Account" id="deleteBtn" class="btn btn-default">
                    </form>
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
                                    response.sendRedirect("DeleteAccount.jsp");
                                }
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
