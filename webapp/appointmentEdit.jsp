<%-- 
    Document   : appointmentEdit
    Created on : Oct 27, 2014, 11:02:21 PM
    Author     : Melissa
--%>

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

                <h3>Appointment Edit</h3>
                <div>
                    <%

                        DatabaseManager dm = new DatabaseManager();
                        if (request.getParameter("apptID") == null || request.getParameter("apptID)") == "" || request.getParameter("apptID").equals("")) {
                            response.sendRedirect("modifyAppointment.jsp");
                        } else {
                            int apptID = Integer.parseInt(request.getParameter("apptID"));
                            Appointment appt = dm.getAppointment(Integer.parseInt(request.getParameter("apptID")));
                            String advisorName = appt.getAdvisorName();
                            String studentMajor = appt.getStudentMajor();
                            String description = appt.getDescription();
                            String date = "" + (appt.getDate().getMonth() + 1) + "/" + appt.getDate().getDate() + "/" + (appt.getDate().getYear() + 1900);
                            String startTime = "";
                            String endTime = "";
                            if (appt.getStartMinute() == 0) {
                                startTime = "" + appt.getStartHour() + ":" + "00";
                            } else {
                                startTime = "" + appt.getStartHour() + ":" + appt.getStartMinute();
                            }
                            if (appt.getEndMinute() == 0) {
                                endTime = "" + appt.getEndHour() + ":" + "00";
                            } else {
                                endTime = "" + appt.getEndHour() + ":" + appt.getEndMinute();
                            }
                            String type = appt.getType();
                            String studentName = appt.getStudentName();
                            String studentID = appt.getStudentID();

                            boolean descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                    %>

                    <form name="edit" action="appointmentEdit.jsp" method="submit" role="form">
                        <%
                            out.print("<input type='hidden' name='apptID' value='" + appt.getApptID() + "'>");
                        %>
                        <div class="form-group">
                            <label for="advisorName">Advisor</label>
                            <input class="form-control" type="text" name="advisorName" size="50" id="advisorName" value = "<%=advisorName%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="advisingType">Advising Type</label>
                            <input class="form-control" type="text" name="advisingType" size="50" id="advisingType" value = "<%=type%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="studentName">Student Name</label>
                            <input class="form-control" type="text" name="studentName" size="50" id="studentName" value = "<%=studentName%>" readonly="true">
                            <input type="hidden" name="studentID" size="50" id="studentID" value = "<%=studentID%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="studentName">Student Major</label>
                            <input class="form-control" type="text" name="studentMajor" size="50" id="major" value = "<%=studentMajor%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="date">Date</label>    
                            <input class="form-control" type="text" name="date" size="50" id="date" value = "<%=date%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="startTime">Start Time</label>

                            <input class="form-control" type="text" name="startTime" size="50" id="startTime" value = "<%=startTime%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="endTime">End Time</label>

                            <input class="form-control" type="text" name="endTime" size="50" id="endTime" value = "<%=endTime%>" readonly="true">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>

                            <textarea class="form-control" name="description" id="description" rows="6" cols="50"><%=description%></textarea>
                        </div>
                        <input type="hidden" value="false" name="remove">
                        <input type="submit" value="Edit Appointment" id="submitBtn" class="btn btn-default">
                    </form>   
                    <form method="submit" action="appointmentEdit.jsp">
                        <%
                            out.print("<input type='hidden' name='apptID' value='" + appt.getApptID() + "'>");
                        %>

                        <input type="hidden" value="true" name="remove"><br>
                        <input type="submit" value="Cancel Appointment" id="cancelBtn" class="btn btn-default">
                    </form>

                    <%
                        if (descriptionSubmitted) {
                           //java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
                            //format.setLenient(false);
                            //java.util.Date newDate = format.parse(request.getParameter("date"));

                    %>       
                    <jsp:useBean id="mab" class="uta.cse4361.beans.ModifyAppointmentBean" scope="session"/>
                    <jsp:setProperty name="mab" property="apptID" value= '<%= appt.getApptID()%>'/>
                    <jsp:setProperty name="mab" property="studentName" value= '<%= appt.getStudentName()%>'/>
                    <jsp:setProperty name="mab" property="studentId" value= '<%= appt.getStudentID()%>'/>
                    <jsp:setProperty name="mab" property="studentMajor" value= '<%= appt.getStudentMajor()%>'/>
                    <jsp:setProperty name="mab" property="advisorName" value= '<%= appt.getAdvisorName()%>'/>
                    <jsp:setProperty name="mab" property="description" value= '<%= request.getParameter("description")%>'/>
                    <jsp:setProperty name="mab" property="type" value= '<%= appt.getType()%>'/>
                    <jsp:setProperty name="mab" property="startHour" value= '<%= appt.getStartHour()%>'/>
                    <jsp:setProperty name="mab" property="startMinute" value= '<%= appt.getStartMinute()%>'/>
                    <jsp:setProperty name="mab" property="endHour" value= '<%= appt.getEndHour()%>'/>
                    <jsp:setProperty name="mab" property="endMinute" value= '<%= appt.getEndMinute()%>'/>
                    <jsp:setProperty name="mab" property="date" value= '<%= appt.getDate()%>'/>
                    <jsp:setProperty name="mab" property="remove" value= '<%=Boolean.parseBoolean(request.getParameter("remove"))%>'/>
                    <%
                                String success = mab.scheduleAppointment();
                                if (success.equals("")) {
                                    response.sendRedirect("modifyAppointment.jsp");
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
