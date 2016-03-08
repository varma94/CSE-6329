<%-- 
    Document   : appointmentEdit
    Created on : Oct 27, 2014, 11:02:21 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.AdvisorAccount"%>
<%@page import="uta.cse4361.businessobjects.Appointment"%> <%-- Abhijeet Chopra Mar 8 --%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page import="uta.cse4361.databases.RelationalDatabaseImpl"%> <!-- Abhijeet Chopra Mar 8 -->
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
        if (rank != 0 && rank != 1) {
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
                        } else {
                            String emailParam = request.getParameter("email");
                            AdvisorAccount appt = dm.getAccount(emailParam);
                            String name = appt.getName();
                            String email = appt.getEmail();
                            String department = appt.getDepartment();
                            int userRank = appt.getRank();
                            //boolean descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                            int adviserid=appt.getID(); // Abhijeet Chopra Mar 8: to get ID of the record from DB
%>
                    <!-- Abhijeet Chopra Mar 6 -->
                    <form role="form" id="create"  onSubmit="return validate();" action="AccountModifyConfirmation.jsp" method="POST">
                        <div class="form-group">
                            <label for="userName" ID</label>
                            <input class="form-control"  type="hidden" name="advisorid" size="50" id="userid" value = "<%= adviserid%>" readonly>
                        </div>
                        <div class="form-group">
                            <label for="userName">Name</label>
                            <input class="form-control" type="text" name="advisorName" size="50" id="advisorName" value = "<%=name%>">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="text" name="email" size="50" id="email" value = "<%=email%>">
                        </div>
                        <div class="form-group">
                            <label for="studentName">Department</label>
                            <input class="form-control" type="text" name="dept" size="50" id="dept" value = "<%=department%>">
                        </div>
                        <div class="form-group">
                            <label for="userRank">Rank</label>
                            <input class="form-control" type="text" name="rank" size="50" id="rank" value = "<%=userRank%>">
                        </div>

                        <%
                            //}
                        %>
                        <!------- Abhijeet 06-Mar-2016------------------>
                        <input type="submit" value="Update Account" id="submitBtn" class="btn btn-default">
                        <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                        
                        
                        <input type="button" value="Delete Account" id="deleteBtn" class="btn btn-default" onClick="alert(deleteAccount('<%= adviserid %>'))">
                        
                        <%
                            }
                        %>
                        </div>
                        <div class="centerthis">

                        </div>
                    </form>
                </div> 
            </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/appointmentEdit.js"></script>
</html>
