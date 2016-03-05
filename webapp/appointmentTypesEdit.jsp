<%-- 
    Document   : appointmentTypesEdit
    Created on : Mar 3, 2016, 6:24:34 PM
    Author     : oguni
--%>

<%@page import="uta.cse4361.businessobjects.AppointmentType"%>
<%@page import="uta.cse4361.businessobjects.AdvisorAccount"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modify Appointment Type</title>
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
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="appointmentAccordion" class="centerthis" >

                <h3>Appointment Type Edit</h3>
                <div>
                    <%

                        DatabaseManager dm = new DatabaseManager();
                        if (request.getParameter("id") == null || request.getParameter("id") == "" || request.getParameter("id").equals("")) {
                            response.sendRedirect("appointmenttypes.jsp");
                        }
                        else 
                        {
                            int id = Integer.parseInt(request.getParameter("id"));
                            AppointmentType appt = dm.getAppointmentType(id);
                            String name = appt.getName();
                            int length = appt.getLength();
                            boolean descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                        
                    %>

                    <form name="edit" action="appointmentTypesEdit.jsp" method="submit" role="form">
                        <%
                            out.print("<input type='hidden' name='id' value='" + appt.getID() + "'>");
                        %>
                        <div class="form-group">
                            <label for="typeName">Name</label>
                            <input class="form-control" type="text" name="typeName" size="50" id="typeName" value = "<%=name%>">
                        </div>
                        <div class="form-group">
                            <label for="typeLength">Length</label>
                            <input class="form-control" type="text" name="typeLength" size="50" id="typeLength" value = "<%=length%>">
                        </div>
                        <input type="hidden" value="false" name="remove">
                        <input type="submit" value="Edit Appointment Type" id="submitBtn" class="btn btn-default">
                    </form>
                        
                    <form method="submit" action="appointmentTypesEdit.jsp">
                        <%
                            out.print("<input type='hidden' name='id' value='" + appt.getID() + "'>");
                        %>

                        <input type="hidden" value="true" name="remove"><br>
                        <input type="submit" value="Delete Appointment Type" id="deleteBtn" class="btn btn-default">
                    </form>
                        <%
                            if (descriptionSubmitted) {
                        %>
                        <jsp:useBean id="matb" class="uta.cse4361.beans.ModifyAppointmentTypeBean" scope="session"/>
                        <jsp:setProperty name="matb" property="appTypeID" value= '<%= appt.getID() %>'/>
                        <jsp:setProperty name="matb" property="remove" value= '<%=Boolean.parseBoolean(request.getParameter("remove"))%>'/>
                        <%
                            if (Boolean.parseBoolean(request.getParameter("remove")) == false){
                        %>
                        <jsp:setProperty name="matb" property="appTypeName" value= '<%= request.getParameter("typeName")%>'/>
                        <jsp:setProperty name="matb" property="appTypeLength" value='<%=Integer.parseInt(request.getParameter("typeLength"))%>'/>

                        <%
                            }
                        	String success = matb.modifyAppointmentType();
                                if (success.equals("")) {
                                    response.sendRedirect("appointmenttypes.jsp");
                                }
                            }
                        }
                        %>
                        
                </div>

            </div> 
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/appointmentEdit.js"></script>
</html>

