<%-- 
    Document   : Calendar
    Created on : Sep 12, 2014, 9:59:13 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page import="uta.cse4361.businessobjects.EmailServlet1"%>
<%@page import="uta.cse4361.businessobjects.AppointmentType" %>
<%@page import="uta.cse4361.businessobjects.AdvisorAccount" %>
<%@page import="uta.cse4361.businessobjects.Slot" %>
<%@page import="uta.cse4361.businessobjects.EventSync" %>
<%@page import="java.util.Calendar" %>

<html>
    <head>
    	<script>
			//Create timeout script
			<%
			String clock = request.getParameter( "clock" );
			if( clock == null ) clock = "600";
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
        <%--<jsp:useBean id="dm" class="DatabaseManager" scope="session"/>--%>
        <%!
        public int getHour(String time){
            String[] hour = time.split(":");
            return Integer.parseInt(hour[0]);
        }
        public int getMin(String time){
            return Integer.parseInt(time.substring(time.length()-2));
        }
        %>
        
        <% 
            
            java.text.DateFormat format = new java.text.SimpleDateFormat("MM/dd/yyyy");
            java.util.Date newDate = format.parse(request.getParameter("date"));
            
            
            DatabaseManager dm = new DatabaseManager();
            
            int typeID = Integer.parseInt(request.getParameter("typeID"));
           AppointmentType type = dm.getAppointmentType(typeID);
           String typeName = type.getName();
           
           int advisorID = Integer.parseInt(request.getParameter("advisorID"));
           AdvisorAccount acct = dm.getAdvisor(advisorID);
           String advisorName = acct.getName();
            
            java.util.ArrayList<Slot> fw = dm.getTypeSlots(advisorID, typeID);
            Calendar currentDate = Calendar.getInstance();
            Iterator<Slot> iter = fw.iterator();

            while (iter.hasNext()) {
                Slot slot = iter.next();
                Calendar cal = Calendar.getInstance();
                cal.setTime(slot.getDate());
                cal.set(Calendar.HOUR_OF_DAY, slot.getHour());
                cal.set(Calendar.MINUTE, slot.getMinute());
                if (cal.before(currentDate)){
                    iter.remove();
                }
            }
           int fwsize= fw.size();
          
           
           StringBuilder sbAppt = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                    sbAppt.append(fw.get(i).isAppointment()+",");        
                   
           //Retrieve all the data into seperate parts
            StringBuilder sbDay = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                sbDay.append(fw.get(i).getDate().getDate()+",");

            StringBuilder sbHour = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                sbHour.append(fw.get(i).getTime()/60+",");

            StringBuilder sbMin = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                sbMin.append(fw.get(i).getTime()%60+",");

            StringBuilder sbMonth = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                sbMonth.append(fw.get(i).getDate().getMonth()+",");

            StringBuilder sbYear = new StringBuilder();
            for(int i=0;i<fwsize;i++) 
                sbYear.append((fw.get(i).getDate().getYear()+1900)+",");
            
            StringBuilder sbLength = new StringBuilder();
            for (int i=0; i<fwsize; i++){
                Slot slot = fw.get(i);
                int apLength = dm.getAppointmentType(slot.getAppTypeID()).getLength();
                sbLength.append(apLength +",");
            }
                  
           
           String desc = request.getParameter("description");
           
           boolean timeSubmitted =  !(request.getParameter("startTime")==null || request.getParameter("startTime")=="" );
           String studentID = "";
           if (request.getParameter("sID") == null || request.getParameter("sID")=="" || request.getParameter("sID").equals("null")){
               studentID = "1000000000";
           } else {
               studentID = request.getParameter("sID");
           }
           
                                
        %>
        
        
        
        <!--<script type="text/javascript" src="js/arrayProcess.js"></script>-->                
        <jsp:useBean id="newAppt" class="uta.cse4361.beans.ScheduleAppointmentBean"/> 
        <jsp:setProperty name="newAppt" property="studentEmail" param="email" />
        <jsp:setProperty name="newAppt" property="studentID" value='<%= studentID%>' /> 
        <jsp:setProperty name="newAppt" property="studentName" param="sName" /> 
        <jsp:setProperty name="newAppt" property="studentMajor" param="major" /> 
        <jsp:setProperty name="newAppt" property="advisorName" value='<%= advisorName%>' />
        <jsp:setProperty name="newAppt" property="type" value='<%= typeName%>' /> 
        <jsp:setProperty name="newAppt" property="date" value='<%= newDate%>' /> 
        <jsp:setProperty name="newAppt" property="description" param="description" /> 
        <% if(timeSubmitted){ %>
            <jsp:setProperty name="newAppt" property="startHour" value= '<%= getHour(request.getParameter("startTime")) %>'/>
            <jsp:setProperty name="newAppt" property="startMinute" value= '<%= getMin(request.getParameter("startTime")) %>'/>
            <jsp:setProperty name="newAppt" property="endHour" value= '<%= getHour(request.getParameter("endTime")) %>'/>
            <jsp:setProperty name="newAppt" property="endMinute" value= '<%= getMin(request.getParameter("endTime")) %>'/>
            <jsp:setProperty name="newAppt" property="typeID" value= '<%= Integer.parseInt(request.getParameter("typeID")) %>'/>
            <jsp:setProperty name="newAppt" property="advisorID" value= '<%= Integer.parseInt(request.getParameter("advisorID")) %>'/>
        <%}%>
        <title>Advising Calendar</title>
        <link rel='stylesheet' href='css/fullcalendar.css' />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>

    <body>    
        <jsp:include page="navigationbar.jsp" />
        
        <div id="wrapper">
        <jsp:include page="header.jsp" />
        <table id="table" style="margin:0 auto; height:1024px">       
            <tr>
                <td style="vertical-align: top;">
                    
                        <div id="scheduleAccordion" style="vertical-align: top; height: inherit">
                            <h3>Appointment Summary</h3>
                                <div>                         
                                    <form name="appointmentSummary" role="form">
                                        <div class="form-group">
                                            <label for="email">Email</label>
                                        <input class="form-control" type="text" name="email" id="email" value="<jsp:getProperty name="newAppt" property="studentEmail"/>"  readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="sID">Student ID</label>
                                        <input class="form-control" type="text" name="sID" id="sID" value="<jsp:getProperty name="newAppt" property="studentID"/>"  readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="sName">Student Name</label>
                                        <input class="form-control" type="text" name="sName" id="sName" value="<jsp:getProperty name="newAppt" property="studentName"/>" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="major">Major</label>
                                        <input class="form-control" type="text" name="major" id="major" value="<jsp:getProperty name="newAppt" property="studentMajor"/>" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="aName">Advisor</label>
                                        <input class="form-control" type="text" name="aName" id="aName" value="<%= advisorName %>" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="type">Advising Type</label>
                                        <input class="form-control" type="text" name="type" id="type" value="<%= typeName %>" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="date">Date</label>
                                        <input class="form-control" type="text" name="date" id="date" value="<%= request.getParameter("date") %>" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="startTime">Start Time</label>
                                        <input class="form-control" type="text" id="startTime" name="startTime" value="" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="endTime">End Time</label>
                                        <input class="form-control" type="text" id="endTime" name="endTime" value="" readonly="readonly">
                                        </div>
                                        <div class="form-group">
                                            <label for="description">Description</label> 
                                        <textarea class="form-control" name="description" id="description" value="" readonly="readonly"></textarea>
                                        </div>
                                        <input type="hidden" id="typeID" name="typeID" value="<%= typeID%>" />
                                        <input type="hidden" id="advisorID" name="advisorID" value="<%= advisorID%>" />
                                        <input type="submit" value="Make Appointment" id="submitBtn" class="btn btn-default">
                                    </form>
                                    <!--  <%@page import="java.io.*,java.util.*,javax.mail.*"%>
									<%@page import="javax.mail.internet.*,javax.activation.*"%>
									<%@page import="javax.servlet.http.*,javax.servlet.*" %>
									 -->
                                    <%if(timeSubmitted){
                                            String result = newAppt.scheduleAppointment(); 

                                            if(result==""){
		                                        //newAppt.sendEmail(newAppt.generateStudentMessage(), newAppt.getStudentEmail());
                                                //newAppt.sendEmail(newAppt.generateAdvisorMessage(), );
                                                //sending notification
                                                /* 
                                                String result_delivery; 
                                                String to = "abcd@gmail.com"; 
                                                String from = "mcmohd@gmail.com"; 
                                                String host = "localhost"; 
                                                Properties properties = System.getProperties(); 
                                                properties.setProperty("mail.smtp.host", host); 
                                                Session mailSession = Session.getDefaultInstance(properties); 
                                                try { 
                                                	MimeMessage message = new MimeMessage(mailSession); 
                                                	message.setFrom(new InternetAddress(from)); 
                                                	message.addRecipient(Message.RecipientType.TO, new InternetAddress(newAppt.getStudentEmail())); 
                                                	message.setSubject("Appointment Successful!");
                                                	message.setContent(newAppt.generateStudentMessage(), "text/html" ); 
                                                	Transport.send(message); 
                                                	result_delivery = "Message successfully sent...."; 
                                                }
                                                catch (MessagingException mex) { 
                                                	mex.printStackTrace(); 
                                                	result_delivery = "Sending failed...."; 
                                                } */
                                                
                                                String email = request.getParameter("email");
                                                String adName = request.getParameter("aName");
                                                String tos = request.getParameter("type");
                                                String date = request.getParameter("date");
                                                String time = request.getParameter("startTime");
                                                response.sendRedirect("AppointmentDetails.jsp?email="+email+"&aName="+adName+"&type="+tos+"&date="+date+"&startTime="+time);
                                                
                                            }
                                            else{
                                                out.println(result);
                                            }
                                        }%>
                                        
                                        
                                </div>                              
                        </div>

                <td style="vertical-align: top; float: right; height:inherit">
                    <div style="vertical-align: top; float: right" id="timeaccordion">
                        <h3>Calendar</h3>
                        <div id="calendar">                            
                        </div>                                           
                    </div>
                </td>
                </td>
                
                
            </tr>
            
        </table>
        </div>

    </body>
    
        <jsp:include page="footer.jsp" />
                <script type="text/javascript">
                    
                window.size = '<%=fwsize%>';
                
                var temp="<%=sbAppt.toString()%>";                               
                var isAppt = new Array(); 
                window.isAppt = temp.split(',',size);   
    
                var temp="<%=sbDay.toString()%>";                               
                var day = new Array(); 
                window.day = temp.split(',',size);

                var temp="<%=sbHour.toString()%>";
                var hour = new Array();
                window.hour = temp.split(',',size);

                var temp="<%=sbMin.toString()%>";
                var min = new Array();
                window.min = temp.split(',',size);

                var temp="<%=sbMonth.toString()%>";
                var month = new Array();
                window.month = temp.split(',',size);

                var temp="<%=sbYear.toString()%>";
                var year = new Array();
                window.year = temp.split(',',size);

                var temp="<%=sbLength.toString() %>";
                var apLength = new Array();
                window.apLength = temp.split(',',size);
                
                
                //alert("array: "+array);
                
                var desc = "<%=desc%>";
        </script> 
        <script type="text/javascript" src="js/fullcalendar/moment.min.js"></script>
        <script type="text/javascript" src="js/fullcalendar/fullcalendar.js"></script>
        <script type="text/javascript" src="js/StudentCalendar.js"></script>
        <script>
			timer();
		</script>
       
</html>
