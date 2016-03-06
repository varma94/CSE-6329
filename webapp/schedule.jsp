 <%--
    Document   : Appointment
    Created on : Sep 19, 2014, 12:43:15 PM
    Author     : Han
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page import="uta.cse4361.businessobjects.Slot"%>
<%@page import="uta.cse4361.businessobjects.AdvisorAccount"%>
<%@page import="uta.cse4361.businessobjects.AppointmentType" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
		<script>
			//Create timeout script
			<%
			String clock = request.getParameter( "clock" );
			if( clock == null ) clock = "300";
			%>
			var timeout = <%=clock%>;
			function timer()
			{
				if( --timeout > 0 )
			{
			//document.forma.clock.value = timeout;
				window.setTimeout( "timer()", 1000 );
			}
			else
			{
				//document.forma.clock.value = "Time over";
				alert("Timeout has occurred. Please try again");
				window.location.assign("/AdvisingSched/");
				
			}
			}
			//-->
		</script>

        <%
            DatabaseManager dm = new DatabaseManager();
//            ArrayList<Date> availableDates = dm.getDatesForAvailability();
            ArrayList<Slot> availableDates = dm.getAvailableSlots();
            ArrayList<String> availables = new ArrayList<String>();
            for (Slot s : availableDates) {
                int dd = s.getDate().getDate();
                int mm = s.getDate().getMonth() + 1;
                int yy = s.getDate().getYear() + 1900;
                String newRecord = "" + dd + "-" + mm + "-" + yy;
                availables.add(newRecord);
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < availables.size(); i++) {
                sb.append(availables.get(i) + ",");
            }
        %>

        <script type="text/javascript">
            temp = "<%=sb.toString()%>";
            var availableDates = new Array();
            availableDates = temp.split(',', '<%=availables.size()%>');

            //alert("array: " + availableDates);
            function available(date) {
                dmy = date.getDate() + "-" + (date.getMonth() + 1) + "-" + date.getFullYear();
                if ($.inArray(dmy, availableDates) !== -1) {
                    return [true, "", "Available"];
                } else {
                    return [false, "", "unAvailable"];
                }
            }
            $(function () {
                $('#date').datepicker({
                    beforeShowDay: available,
                    changeMonth: true,
                    changeYear: true});
            })
        </script>


        <script type="text/javascript">

            function isNumberKey(evt)
            {
                var e = evt || window.event; //window.event is safer, thanks @ThiefMaster
                var charCode = e.which || e.keyCode;
                if (charCode > 31 && (charCode < 47 || charCode > 57))
                    return false;
                if (e.shiftKey)
                    return false;
                return true;
            }
            function validate() {
                var sID = document.forms["schedule"]["sID"].value;
                var sName = document.forms["schedule"]["sName"].value;
                var email = document.forms["schedule"]["email"].value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".");
                var ddate = document.forms["schedule"]["date"].value;
                var dp = document.forms["schedule"]["description"].value;
                if (email === null || email === "") {
                    $("#email").notify("Please enter your email", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    document.forms["schedule"]["email"].focus();
                    return false;
                }
                if (atpos < 1 || dotpos < atpos + 2 || dotpos + 2 >= email.length) {
                    $("#email").notify("Please enter a valid email", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    document.forms["schedule"]["email"].focus();
                    return false;
                }
                if (sID === null || sID === "") {
                    
                } else {
                    if (isNaN(sID)) {
                        $("#sID").notify("Your student ID must be a number", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                        document.forms["schedule"]["sID"].focus();
                        return false;
                    }
                    
                    if (sID.length !== 10) {
                        $("#sID").notify("Your student ID must be a 10-digit number", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                        document.forms["schedule"]["sID"].focus();
                        return false;
                    }
                    
                    if (sID.indexOf("100") === -1 && sID.indexOf("6000") === -1 ) {
                        $("#sID").notify("Your student ID should begin with 100 or 6000", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                        document.forms["schedule"]["sID"].focus();
                        return false;
                    }
                }
                
                
                
                if (sName === null || sName === "") {
                    $("#sName").notify("Please enter your name", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    document.forms["schedule"]["sName"].focus();
                    return false;
                }
                if (ddate === null || ddate === "") {
                    $("#date").notify("Please select your appointment date", "error",
                            {elementPosition: 'right middle',
                                globalPosition: 'right middle'})
//                    document.forms["schedule"]["date"].focus();
                    return false;
                }
                if (dp === null || dp === "") {
                    $("#description").notify("Please enter a proper description", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    document.forms["schedule"]["description"].focus();
                    return false;
                }
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Schedule Appointment</title>
    </head>
        <jsp:include page="header.jsp" />
    <style>
        .ui-datepicker {
            background: url('css/images/calender back.png');
            background-size: 100% 100%;
        }
        .ui-widget-header {
            color: #000000;
        }
    </style>
    <body>
    <tr>
        <jsp:include page="navigationbar.jsp" />
    <div id="wrapper">
        
        <div id="accordion" class="centerthis">
            <h3>Schedule Appointment</h3>
            <div class="centerthis">
                <form role="form" name="schedule" action="StudentCalendar.jsp" onSubmit="return validate();" >
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input type="text"  name="email" id="email" value="" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="sID">Student ID</label>
                        <input type="text" onkeypress="return isNumberKey(event)" name="sID" id="sID" value="" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="sName">Student Name</label>
                        <input type="text" name="sName" id="sName" value="" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="major">Major</label>
                        <select name="major" id="major" class="form-control" >
                            <option value="CSE">CSE</option>
                            <option value="SE">SE</option>
                            <option value="CPE">CPE</option>
                            <option value="Undecided">Undecided</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="advisorID">Advisor</label>
                        <select name="advisorID" id="advisorID" class="form-control" >
                            <%
                                java.util.ArrayList<AdvisorAccount> advisors = dm.getAdvisors();
                                for (AdvisorAccount acct : advisors){ 
                                    out.print("<option value='" + acct.getID() + "'>" + acct.getName() + "</option>");
                                }
                                %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="typeID">Advising Type</label>
                        <select name="typeID" id="typeID" class="form-control">
                                <%
                                    java.util.ArrayList<AppointmentType> advisingTypes = dm.getAppointmentTypesObj();
                                    for (AppointmentType type : advisingTypes){ 
                                        out.print("<option value='" + type.getID() + "'>" + type.getName() + "</option>");
                                    }
                                %>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="text" name="date"  id="date" readonly="readonly" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="description">Description</label>
                        <textarea name="description" id="description"  value="" class="form-control"></textarea>
                    </div>
                    <input type="submit" value="Submit" id="submitBtn" class="btn btn-default">
                    <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                </form>
            </div>
        </div>
    </div>
    <br>
</body>
<jsp:include page="footer.jsp" />
<script type="text/javascript" src="js/schedule.js"></script>
<script>
	timer();
</script>
</html>
