<%-- 
    Document   : modifyTimeslot
    Created on : Oct 26, 2014, 3:53:32 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.Slot"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Timeslot</title>
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
            <jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
            <div  id="timeslotAccordion" class="centerthis">

                <h3>Timeslots</h3>
                <div>
                    <form name="appointmentForm" action="timeslotEdit.jsp">
                        <table id="timeslotList" class="display" cellpadding= "3" cellspacing= "0">
                            <thead>
                            <th>Date</th>
                                
                            <th>Time</th>
                              
                            <th>Appointment Information</th>
                            
                            <th></th>
                            </thead>
                            <tbody>
                            <%
                                dm = new uta.cse4361.databases.DatabaseManager();
                                java.util.ArrayList<Slot> slots = dm.getTypeSlots();
                                for (Slot s : slots) {
                                    out.print("<tr>");
                                    out.print("<td>");
                                    out.print(s.getDate().getMonth() + 1 + "/" + s.getDate().getDate() + "/" + (s.getDate().getYear() + 1900));
                                    out.print("</td>");
                                    out.print("<td>");
                                    out.print(s.getHour() + ":");
                                    if (s.getMinute() == 0) {
                                        out.print("00");
                                    } else {
                                        out.print(s.getMinute());
                                    }
                                    out.print("</td>");
                                    out.print("<td>");
                                    if (s.isAppointment()) {
                                        out.print("Appointment with " + dm.getAppointment(s.getAppointmentId()).getStudentName() + " at "
                                                + dm.getAppointment(s.getAppointmentId()).getStartHour() + ":");
                                        if (dm.getAppointment(s.getAppointmentId()).getStartMinute() == 0) {
                                            out.print("00" + " to ");
                                        } else {
                                            out.print(dm.getAppointment(s.getAppointmentId()).getStartMinute() + " to ");
                                        }
                                        out.print(dm.getAppointment(s.getAppointmentId()).getEndHour() + ":");
                                        if (dm.getAppointment(s.getAppointmentId()).getEndMinute() == 0) {
                                            out.print("00");
                                        } else {
                                            out.print(dm.getAppointment(s.getAppointmentId()).getEndMinute());
                                        }
                                    } else {
                                        out.print("No Appointment");
                                    }
                                    out.print("</td>");
                                    out.print("<td>");
                                    if (s.isAppointment()) {
                                        out.print("<input type='radio' name='slotID' value='" + "a" + s.getAppointmentId() + "'>");
                                    } else {
                                        out.print("<input type='radio' name='slotID' value='" + "s" + s.getAppointmentId() + "'>");
                                    }
                                    out.print("</td>");
                                    out.print("</tr>");
                                }
                            %>
                            </tbody>
                        </table>
                        <input type="hidden" value ="true" name="first">
                        <div class="centerthis">
                            <br>
                            <input type="submit" value="Modify Timeslots" id="submitBtn" class="btn btn-default">
                        </div>
                    </form>
                </div>


            </div>                   
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    
    <script type="text/javascript" src="js/modifyTimeslot.js"></script>
</html>
