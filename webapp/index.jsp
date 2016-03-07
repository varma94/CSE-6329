<%-- 
    Document   : index
    Created on : Sep 12, 2014, 2:12:40 PM
    Author     : Melissa
--%>

<%@page contentType='text/html' pageEncoding='UTF-8'%>
<%@page import  = "java.util.*"%>
<%@page import  = "java.io.*"%>

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>UTA Advising</title>
        <meta charset='UTF-8'>
        <meta name='viewport' content='width=device-width, initial-scale=1.0, maximum-scale=1'>
    </head>

    <body>

        <jsp:include page='navigationbar.jsp' />
        <div id='wrapper'>
            <jsp:include page='header.jsp' />
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
            <table class='centerthis' style='margin: 0 auto;'>
                <tr>
                    <%
                            if(rank == -1){
                                out.print("<td style='width: 1280px'>"); // Abhijeet Chopra Mar 7: Changed width from 640px to 1280px
                            }
                            %>
                    <div id='centerAccordion'>
                        <%
                            if(session.getAttribute("id") == null){
                                out.print("<h3>Faculty and Student Login</h3>");
                            }
                            %>
                        
                        <div>
                            <%
                            if (rank == -1){
                                out.print("Would you like to check on your current schedule?<br><br>"
                                        + "<input type='submit' value='Login to your account' id='loginBtn' class='btn btn-default'>"
                                        + " <a href='CreateStudentAccount.jsp' class='btn btn-default' >Create Account</a><br><br>");
                                
                            }
                            if (rank == 1){
                                out.print("<h3>Welcome administrator.</h3>");
                            }
                            if (rank == 0){
                                out.print("<h3>Welcome Advisor</h3>");
                            }
                            if(rank == 2)
                            {
                                out.print("<h3>Welcome Student</h3>");
                            }
                            if (rank == 3){
                                out.print("<h3>Welcome Lead Advisor</h3>");
                            }
                            if (rank == 4){
                                out.print("<h3>Welcome Staff</h3>");
                            }
                            
                            
                            //Abhijeet Chopra Mar 7: Moved clock out of if statement as needed on every user type's main page
                            response.setIntHeader("Refresh", 30); //Abhijeet Chopra Mar 7: Refreshing page every 30 seconds to update clock
                            Date date = new Date();
                            out.print( "<p align=\"center\">Current Date & Time : <b>" +date.toString()+"</b></p>");
                            
                            %>
                  
                        </div>

                    </div>
                            <%
                            if(rank == 2){
                                out.print("</td>"
                                        + "<td style='width: 640px'>"
                                        + "<div id='rightAccordion'>"
                                        + "<h3>Student</h3>"
                                        + "<div>"
                                        + "Would you like to schedule an appointment with an advisor?<br><br>"
                                        + "<form action='schedule.jsp'>"
                                        + "<input type='submit' value='Make an appointment' id='scheduleBtn' class='btn btn-default'>"
                                        + "</form>"
                                        + "</div>"
                                        + "</div>"
                                        + "</td>");
                            }
                            %>
                </tr>
            </table>


        </div>
        <jsp:include page='footer.jsp' />
    </body>
    

    <script type='text/javascript' src='js/index.js'></script>
</html>
