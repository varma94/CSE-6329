<div id="navbar">
    <% 
        int rank = -1;
        int sessionid = -1;
        if(!(session.getAttribute("id") == null)){
        sessionid = Integer.parseInt((String)session.getAttribute("id"));
    }
        if(!(session.getAttribute("rank") == null)){
        rank = Integer.parseInt((String)session.getAttribute("rank"));
    }
    %>
            <ul class="navigation">
            	<li>
                    <a href="http://www.uta.edu/uta/" target="_blank"><img src="css/images/logo.png"></a>
                </li>
                <li>
                    <a href="https://sis-portal-prod.uta.edu/psp/AEPPRD/EMPLOYEE/EMPL/h/?tab=PAPP_GUEST" target="_blank">MyMav</a>
                </li>
                <li>
                    <a href="https://login.microsoftonline.com/login.srf?" target="_blank"><img src="css/images/email-icon.png"></a>
                </li>
               
                <li class="home">
                    <a href="index.jsp" >Home</a>
                </li>
    <%
        if(session.getAttribute("rank") == null)
        {
            
        }
        else{
            if (rank == 1){
                out.print("<li class='account'>"
                        + "<a href='DeleteAccount.jsp' >Accounts</a>"
                        + "</li>");
            }
         if (rank == 2){
                
                out.print("<li class='account'>"
                        + "<a href='studentAccountEdit.jsp' >Edit Account</a>"
                        + "</li>");
                out.print("<li class='account'>"
                        + "<a href='PasswordManagement.jsp' >Password Change</a>"
                        + "</li>");
            }
            
            if (rank == 0 || rank == 1){
                out.print("<li class='calendar'>"
                        + "<a href='AdvisorCalendar.jsp' >Calendar</a>"
                        + "</li>");
                out.print("<li class='timeslot'>"
                        + "<a href='modifyTimeslot.jsp' >Time slot</a>"
                        + "</li>");
                out.print("<li class='appointment'>"
                        + "<a href='modifyAppointment.jsp' >Appointment</a>"
                        + "</li>");
            }
            if (rank == 1 || rank == 0 || rank==2){
                out.print("<li class='logout'>"
                        + "<a href='logout.jsp' >Log Out</a>"
                        + "</li>");
            }
        }
        if ((session.getAttribute("id") == null) || (session.getAttribute("rank") == null)){
            out.print("<li class='schedule'>"
                    + "<a href='schedule.jsp' >Schedule Appointment</a>"
                    + "</li>");
        }
    %>                
            </ul>
        </div>
