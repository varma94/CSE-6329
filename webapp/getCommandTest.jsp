<%-- 
    Document   : getCommandTest
    Created on : Oct 27, 2014, 2:46:52 PM
    Author     : Han
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="uta.cse4361.businessobjects.Appointment"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Get Command Test</h1>
        <%
            DatabaseManager dbm = new DatabaseManager();
            Appointment newAppt = dbm.getAppointment(1);
            out.println("After getting appointment with id 1<br>");
            out.println("Advisor Name: " + newAppt.getAdvisorName()+"<br>");
            out.println("Student Name: " + newAppt.getStudentName()+"<br>"+"<br>");
            
            //String result = dbm.saveAppointment(newAppt);
            //out.println("The result of saving is: "+result);
            newAppt.setAdvisorName("New Name Changed");
            
            // To delete, simply leave the second attribute null
            // e.g. result = dbm.modifyAppointment(newAppt.getApptID(), null);
            // This will delete the appointment with given ID
            String result = dbm.modifyAppointment(newAppt.getApptID(), newAppt);
            
            out.println("After Modifying appointment with id 1 by change advisor name<br>");
            out.println("Advisor Name: " + newAppt.getAdvisorName()+"<br>"+"<br>");

            ArrayList<Appointment> appts = dbm.getAppointments();
            out.println("<br>"+"After getting all appointments"+"<br>");
            for (Appointment a : appts) {
                out.println("Appointment ID: " + a.getApptID()+"<br>");
                out.println("Date: "+a.getDate()+"<br>");
                out.println("Start Time:"+a.getStartHour()+":"+a.getStartMinute()+"<br>");
                out.println("End Time:"+a.getEndHour()+":"+a.getEndMinute()+"<br>");
                out.println("Advisor Name: " + a.getAdvisorName()+"<br>");
                out.println("Student Name: " + a.getStudentName()+"<br>"+"<br>");
            }
        %>
    </body>
</html>
