<%-- 
    Document   : modifyAppointment
    Created on : Oct 26, 2014, 3:53:12 PM
    Author     : Melissa
--%>

<%@page import="uta.cse4361.businessobjects.Appointment"%>
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
        
            <tr>
                <jsp:include page="navigationbar.jsp" />
                <jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
            <div id="wrapper">
                <jsp:include page="header.jsp" />
                    <div id="appointmentAccordion" class="centerthis">

                            <h3>Appointments</h3>
                            <div>
                                <form  name="appointmentForm" action="appointmentEdit.jsp">
                                <table class="display" id="appointmentList" cellpadding= "3" cellspacing= "0" >
                                    <thead>

                                    <th>Date</th>

                                    <th>Start</th>

                                    <th>End</th>

                                    <th>Advisor Name</th>

                                    <th>Advising Type</th>
                                    
                                    <th></th>
                                    </thead>
                                    <tbody>
                                <%
                                    dm = new uta.cse4361.databases.DatabaseManager(); 
                                    java.util.ArrayList<uta.cse4361.businessobjects.Appointment> appts = dm.getAppointments(); 
                                    for(Appointment a: appts) {
                                        out.print("<tr>");
                                        out.print("<td>");
                                        out.print(a.getDate().getMonth()+1+"/"+a.getDate().getDate()+"/"+(a.getDate().getYear()+1900));
                                        out.print("</td>");
                                        out.print("<td>");                                       
                                        out.print(a.getStartHour() +":");
                                        if(a.getStartMinute() == 0)
                                        {
                                            out.print("00");
                                        }
                                        else
                                        {
                                            out.print(a.getStartMinute());
                                        }
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getEndHour() + ":" );
                                        if(a.getEndMinute() == 0)
                                        {
                                            out.print("00");
                                        }
                                        else
                                        {
                                            out.print(a.getEndMinute());
                                        }
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getAdvisorName());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print(a.getType());
                                        out.print("</td>");
                                        out.print("<td>");
                                        out.print("<input type='radio' name='apptID' value='" +a.getApptID()+"'>");
                                        out.print("</td>");
                                        out.print("</tr>");
                                        out.print("</script>");
                                    }
                                    %>
                                    </tbody>
                                </table>
                                <br>
                                   <input type="submit" value="Modify Appointment" id="submitBtn" class="btn btn-default" class="btn btn-default">
                                </form>
                            </div>

                    </div>                   
            </div>
    <jsp:include page="footer.jsp" />                            
    </body>
    <script type="text/javascript" src="js/modifyAppointment.js"></script>
</html>
