<%-- 
    Document   : appointmenttypes
    Created on : Mar 3, 2016, 4:25:27 PM
    Author     : oguni
--%>

<%@page import="uta.cse4361.businessobjects.Appointment"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Types</title>
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

                            <h3>Appointment Types</h3>
                            <div>
                                <form  name="AccountForm" action="appointmentTypesEdit.jsp">
                                <table class="display" id="appointmentList" cellpadding= "3" cellspacing= "0" >
                                    <thead>

                                    <th>Appointment Type Id</th>

                                    <th>Appointment Type</th>
                                    
                                    <th>Type Length (Minutes) </th>

                                    <th>Select</th>
                                    
                                    </thead>
                                    <tbody>
                                <%
                                    dm = new uta.cse4361.databases.DatabaseManager(); 
                                    java.util.ArrayList<uta.cse4361.businessobjects.AppointmentType> types = dm.getAppointmentTypesObj();
                                    for(uta.cse4361.businessobjects.AppointmentType a: types) {
                                        out.print("<tr>");
                                        out.print("<td>");
                                        out.print(a.getID());
                                        out.print("</td>");
                                        
                                        out.print("<td>");                                       
                                        out.print(a.getName());
                                        out.print("</td>");
                                        
                                        out.print("<td>");                                       
                                        out.print(a.getLength());
                                        out.print("</td>");
                                        
                                        out.print("<td>");
                                        out.print("<input type='radio' name='id' value='" + a.getID() +"'>");
                                        out.print("</td>");
                                        out.print("</tr>");
                                        out.print("</script>");
                                    }
                                    %>
                                    </tbody>
                                </table>
                                <br>
                                   <input type="submit" value="Modify Appointment Type" id="submitBtn" class="btn btn-default" >
                                   
                                </form>
                                    <input type='submit' value='Create New Appointment Type' id='newAptTypeBtn' class='btn btn-default'>
                            </div>

                    </div>                   
            </div>
    <jsp:include page="footer.jsp" />                            
    </body>
    <script type="text/javascript" src="js/modifyAppointment.js"></script>
    <script type='text/javascript' src='js/createAptType.js'></script>
</html>
