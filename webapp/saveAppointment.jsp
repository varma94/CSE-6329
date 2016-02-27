<%--
    Document   : saveAppointment
    Created on : Sep 21, 2014, 6:23:58 PM
    Author     : Han
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> 

<!DOCTYPE html> 
<html> 
    <head> 
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
        <title>Insert title here</title> 
    </head> 
    <body bgcolor="#99CCFF"> 
        <%
            java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
            java.util.Date newDate = format.parse(request.getParameter("date"));
        %>;
        
        <jsp:useBean id="newAppt" class="uta.cse4361.beans.ScheduleAppointmentBean"/> 
        <jsp:setProperty name="newAppt" property="studentID" param="sID" /> 
        <jsp:setProperty name="newAppt" property="studentName" param="sName" /> 
        <jsp:setProperty name="newAppt" property="date" value='<%= newDate%>' /> 
        <jsp:setProperty name="newAppt" property="description" param="description" /> 
        <div align="center">
            <h2> Appointment Bean Test</h2> 
            Student ID: <jsp:getProperty name="newAppt" property="studentID"/><br> 
            Student Name: <jsp:getProperty name="newAppt" property="studentName"/><br> 
            Appointment Date: <jsp:getProperty name="newAppt" property="date"/><br> 
            Description: <jsp:getProperty name="newAppt" property="description"/> 
        </div> 
    </body> 
</html>