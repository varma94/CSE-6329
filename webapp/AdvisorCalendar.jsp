<%-- 
    Document   : Calendar
    Created on : Sep 12, 2014, 9:59:13 PM
    Author     : Melissa
--%>

<%@page import="java.util.Date"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page import="uta.cse4361.businessobjects.AppointmentType" %>
<%@page import="uta.cse4361.businessobjects.Slot" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Advisor Calendar</title>
        <link rel='stylesheet' href='css/fullcalendar.css' />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    <%--<jsp:useBean id="dm" class="DatabaseManager" scope="session"/>--%>
    <%

        int fwsize = 0;
        DatabaseManager dm = new DatabaseManager();
        Date currDate = new Date();
        java.util.ArrayList<Slot> fw = dm.getTypeSlots();

        StringBuilder sbDay = new StringBuilder();
        StringBuilder sbHour = new StringBuilder();
        StringBuilder sbMin = new StringBuilder();
        StringBuilder sbMonth = new StringBuilder();
        StringBuilder sbYear = new StringBuilder();
        StringBuilder sbAppt = new StringBuilder();
        if (fw.isEmpty()) {
            fwsize = 0;
        } else {
            fwsize = fw.size();

            for (int i = 0; i < fwsize; i++) {
                Slot slot = fw.get(i);
                sbAppt.append(slot.isAppointment() + ",");
                sbDay.append(slot.getDate().getDate() + ",");
                sbHour.append(slot.getTime() / 60 + ",");
                sbMin.append(slot.getTime() % 60 + ",");
                sbMonth.append(slot.getDate().getMonth() + ",");
                sbYear.append((slot.getDate().getYear() + 1900) + ",");
            }
        }
    %>
    <script type="text/javascript">
        var size = '<%=fwsize%>';
    </script>

    <script type="text/javascript">
        //Parse String Slots to array
        var size = '<%=fwsize%>';
        if (size == 0)
        {

        }
        else
        {

            var temp = "<%=sbAppt.toString()%>";
            var isAppt = new Array();
            window.isAppt = temp.split(',', size);

            var temp = "<%=sbDay.toString()%>";
            var day = new Array();
            window.day = temp.split(',', size);

            var temp = "<%=sbHour.toString()%>";
            var hour = new Array();
            window.hour = temp.split(',', size);

            var temp = "<%=sbMin.toString()%>";
            var min = new Array();
            window.min = temp.split(',', size);

            var temp = "<%=sbMonth.toString()%>";
            var month = new Array();
            window.month = temp.split(',', size);

            var temp = "<%=sbYear.toString()%>";
            var year = new Array();
            window.year = temp.split(',', size);
//                                    alert("p2:" + size);
        }

    </script>


    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <table id="table">
                <tr>
                    <td style="vertical-align: top; float:left; width:300px;" >

                        <div  id="allocateAccordion">
                            <!--Andrews code-->
                            <%!
                                public int getHour(String time) {
                                    int hour = Integer.parseInt(time.substring(0, 2));
                                    if ("PM".equalsIgnoreCase(time.substring(time.length() - 2))) {
                                        if (hour != 12) {
                                            hour += 12;
                                        };
                                    };
                                    return hour;
                                }

                                public int getMin(String time) {
                                    return Integer.parseInt(time.substring(3, 5));
                                }
                            %>
                            <% boolean dateSubmitted = !(request.getParameter("date") == null || request.getParameter("date") == "");
                                boolean startSubmitted = !(request.getParameter("startTime") == null || request.getParameter("startTime") == "");
                                boolean endSubmitted = !(request.getParameter("endTime") == null || request.getParameter("endTime") == "");
                                boolean formSubmitted = dateSubmitted || startSubmitted || endSubmitted;
                                boolean validFormSubmitted = dateSubmitted && startSubmitted && endSubmitted;
                                if (validFormSubmitted){
                                    int advisingType = Integer.parseInt(request.getParameter("advisingType"));
                                }
                            %>
                                

                            <h3>Allocate Time</h3>
                            <div>
                                <form action="AdvisorCalendar.jsp" method="post" role="form">
                                    <div class="centerthis">
                                    <div class="form-group">
                                        <label for="date">Date</label>
                                    <input class="form-control" type="text" id="datepicker" name="date"<% if (dateSubmitted) {
                                                    out.println(" value=\"" + request.getParameter("date") + "\"");
                                                }%> readonly>
                                    </div>
                                    <div class="form-group">
                                        <label for=starttimepicker">Start Time</label>

                                    <input class="form-control" type="datetime" id = "starttimepicker" name="startTime"<% if (startSubmitted) {
                                                    out.println(" value=\"" + request.getParameter("startTime") + "\"");
                                                }%>>
                                    </div>
                                    <div class="form-group">
                                        <label for="endtimepicker">End Time</label>
                                    <input class="form-control" type="datetime" id ="endtimepicker" name="endTime"<% if (endSubmitted) {
                                                    out.println(" value=\"" + request.getParameter("endTime") + "\"");
                                                }%>>
                                    </div>
                                    <div class ="form-group">
                                        <label for="advisingType">Advising Type</label>
                                        <select class ="form-control" name="advisingType" id="advisingType">
                                            <%
                                                java.util.ArrayList<AppointmentType> advisingTypes = dm.getAppointmentTypesObj();
                                                for (AppointmentType type : advisingTypes){ 
                                                    out.print("<option value='" + type.getID() + "'>" + type.getName() + "</option>");
                                                }
                                            %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="numbers">Number of Repeats</label>
                                    <select class="form-control" name="numbers" id="numbers">
                                        <option value="1">1 Week</option>
                                        <option value="2">2 Weeks</option>
                                        <option value="3">3 Weeks</option>
                                        <option value="4">4 Weeks</option>
                                        <option value="6">6 Weeks</option>
                                        <option value="8">8 Weeks</option>
                                    </select>
                                    </div>
                                    <input id="button" type="submit" value="Submit" class="btn btn-default">
                                    </div>
                                </form>
                                <% if (validFormSubmitted) {
                                        java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
                                        format.setLenient(false);
                                        java.util.Date newDate = format.parse(request.getParameter("date"));
                                %>
                                <jsp:useBean id="allocateTimeBean" class="uta.cse4361.beans.TimeAllocationBean" scope="session"/>
                                <jsp:setProperty name="allocateTimeBean" property="date" value= '<%= newDate%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="startHour" value= '<%= getHour(request.getParameter("startTime"))%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="startMinute" value= '<%= getMin(request.getParameter("startTime"))%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="endHour" value= '<%= getHour(request.getParameter("endTime"))%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="endMinute" value= '<%= getMin(request.getParameter("endTime"))%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="advisingType" value= '<%= Integer.parseInt(request.getParameter("advisingType"))%>'/>
                                <jsp:setProperty name="allocateTimeBean" property="advisor" value= '<%= sessionid %>'/>
                                <jsp:setProperty name="allocateTimeBean" property="appType" value='<%= Integer.parseInt(request.getParameter("advisingType"))%>'/>
                                
                                <%
                                    String r = request.getParameter("numbers");
                                    int times = Integer.parseInt(r);
                                    //allocateTimeBean.allocateTime();
                                    allocateTimeBean.allocateTimeRepeat(times);

                                    //new fdm to get a fresh copy of the flyweights
                                    dm = new DatabaseManager();
                                    fw = dm.getSlots();

                                    sbDay = new StringBuilder();
                                    sbHour = new StringBuilder();
                                    sbMin = new StringBuilder();
                                    sbMonth = new StringBuilder();
                                    sbYear = new StringBuilder();
                                    sbAppt = new StringBuilder();

                                    if (fw.isEmpty()) {
                                        fwsize = 0;
                                    } else {
                                        fwsize = fw.size();

                                        for (int i = 0; i < fwsize; i++) {
                                            Slot slot = fw.get(i);
                                            sbAppt.append(slot.isAppointment() + ",");
                                            sbDay.append(slot.getDate().getDate() + ",");
                                            sbHour.append(slot.getTime() / 60 + ",");
                                            sbMin.append(slot.getTime() % 60 + ",");
                                            sbMonth.append(slot.getDate().getMonth() + ",");
                                            sbYear.append((slot.getDate().getYear() + 1900) + ",");
                                        }
                                    }

                                %>

                                <script type="text/javascript">
                                    var size = '<%=fwsize%>';

                                    if (size == 0) {
                                    }
                                    else
                                    {
                                        var temp = "<%=sbAppt.toString()%>";
                                        var isAppt = new Array();
                                        window.isAppt = temp.split(',', size);

                                        var temp = "<%=sbDay.toString()%>";
                                        var day = new Array();
                                        window.day = temp.split(',', size);

                                        var temp = "<%=sbHour.toString()%>";
                                        var hour = new Array();
                                        window.hour = temp.split(',', size);

                                        var temp = "<%=sbMin.toString()%>";
                                        var min = new Array();
                                        window.min = temp.split(',', size);

                                        var temp = "<%=sbMonth.toString()%>";
                                        var month = new Array();
                                        window.month = temp.split(',', size);

                                        var temp = "<%=sbYear.toString()%>";
                                        var year = new Array();
                                        window.year = temp.split(',', size);
                                    }
                                </script>

                                <%
                                    } else if (formSubmitted) {
                                        if (!dateSubmitted) {
                                            out.println("Please select a date.\n");
                                        }
                                        if (!startSubmitted) {
                                            out.println("Please select a start time.\n");
                                        }
                                        if (!endSubmitted) {
                                            out.println("Please select an end time.\n");
                                        }
                                    }%>


                            </div> 
                        </div>
                    </td>

                    <td style="vertical-align: top; float: right; width:980px">
                        <div id="timeaccordion">
                            <h3>Calendar</h3>
                            <div id="calendar"></div>                        
                        </div> <!--timeaccordion end-->
                    </td>       
                </tr>           
            </table>
        </div>                        
    </body>


    <script type="text/javascript">
        document.getElementById('starttimepicker').onkeydown = function (e) {
            e.preventDefault();
        }
        document.getElementById('endtimepicker').onkeydown = function (e) {
            e.preventDefault();
        }
    </script>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/fullcalendar/moment.min.js"></script>
    <script type="text/javascript" src="js/fullcalendar/fullcalendar.js"></script>
    <script type="text/javascript" src="js/AdvisorCalendar.js"></script>
</html>
