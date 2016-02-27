<%-- 
    Document   : AllocateTime
    Created on : Sep 23, 2014, 8:02:28 PM
    Author     : Andrew
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Allocate Time</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <jsp:include page="header.jsp" />
    <body>
        <%!
        public int getHour(String time){
            int hour = Integer.parseInt(time.substring(0,2));
            if("PM".equalsIgnoreCase(time.substring(time.length() - 2))){
                if(hour!=12){hour += 12;};
            };
            return hour;
        }
        public int getMin(String time){
            return Integer.parseInt(time.substring(3,5));
        }
        %>
        <% boolean dateSubmitted = !(request.getParameter("date")==null || request.getParameter("date")=="");
            boolean startSubmitted =  !(request.getParameter("startTime")==null || request.getParameter("startTime")=="");
            boolean endSubmitted = !(request.getParameter("endTime")==null || request.getParameter("endTime")=="");
            boolean formSubmitted = dateSubmitted || startSubmitted || endSubmitted;
            boolean validFormSubmitted = dateSubmitted && startSubmitted && endSubmitted;%>
        
        <div id="accordion">
            <h3>Allocate Time</h3>
            <div>
                <form>
                <p>Date: <input type="text" id="datepicker" name="date"<% if (dateSubmitted){out.println(" value=\""+request.getParameter("date")+"\"");}%> readonly></p>
                <p>Start Time: <input type="datetime" id = "starttimepicker" name="startTime"<% if (startSubmitted){out.println(" value=\""+request.getParameter("startTime")+"\"");}%>></p>
                <p>End Time: <input type="datetime" id ="endtimepicker" name="endTime"<% if (endSubmitted){out.println(" value=\""+request.getParameter("endTime")+"\"");}%>></p>
                <input type="submit" value="Submit">
                </form>
                <% if (validFormSubmitted){
                       java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
                       java.util.Date newDate = format.parse(request.getParameter("date"));
                    %>
                     <jsp:useBean id="allocateTimeBean" class="uta.cse4361.beans.TimeAllocationBean" scope="session"/>
                     <jsp:setProperty name="allocateTimeBean" property="date" value= '<%= newDate %>'/>
                     <jsp:setProperty name="allocateTimeBean" property="startHour" value= '<%= getHour(request.getParameter("startTime")) %>'/>
                     <jsp:setProperty name="allocateTimeBean" property="startMinute" value= '<%= getMin(request.getParameter("startTime")) %>'/>
                     <jsp:setProperty name="allocateTimeBean" property="endHour" value= '<%= getHour(request.getParameter("endTime")) %>'/>
                     <jsp:setProperty name="allocateTimeBean" property="endMinute" value= '<%= getMin(request.getParameter("endTime")) %>'/>
                    <%
                    String result = allocateTimeBean.allocateTime();
                    if(result == "")
                        out.println("Time Allocation Success, Thank You.");
                    else
                        out.println(result);}
                    else if(formSubmitted){
                        if(!dateSubmitted){
                            out.println("Please select a date.\n");
                        }
                        if (!startSubmitted){
                            out.println("Please select a start time.\n");
                        }
                        if (!endSubmitted){
                            out.println("Please select an end time.\n");
                        }
                    }%>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript">
        document.getElementById('starttimepicker').onkeydown = function(e){
            e.preventDefault();
        }
         document.getElementById('endtimepicker').onkeydown = function(e){
            e.preventDefault();
        }
    </script>
    <script type="text/javascript" src="js/AllocateTime.js"></script>
</html>
