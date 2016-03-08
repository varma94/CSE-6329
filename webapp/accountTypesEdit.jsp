<%--
    Document   : accountTypesEdit
    Created on : Mar 6, 2016, 5:03:34 PM
    Author     : oguni
--%>

<%@page import="uta.cse4361.businessobjects.AppointmentType"%>
<%@page import="uta.cse4361.businessobjects.AdvisorAccount"%>
<%@page import="uta.cse4361.databases.DatabaseManager"%>
<%@page import="uta.cse4361.businessobjects.AccountType"%>
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
                            response.sendRedirect("manageAccountTypes.jsp");
                        }
                        else 
                        {
                            int id = Integer.parseInt(request.getParameter("id"));
                            AccountType acct = dm.getAccountType(id);
                            String name = acct.getName();
                            int privilege = acct.getPrivilege();
                            boolean descriptionSubmitted = false;
                            boolean deleting = !(request.getParameter("action") == null) 
                                    && !(request.getParameter("action") == "")
                                    && request.getParameter("action").equals("Delete Account Type");
                            
                            descriptionSubmitted = !(request.getParameter("remove") == null || request.getParameter("remove") == "");
                        
                    %>

                    <form name="edit" action="accountTypesEdit.jsp" method="submit" role="form">
                        <%
                            out.print("<input type='hidden' name='id' value='" + acct.getID() + "'>");
                        %>
                        <div class="form-group">
                            <label for="typeName">Name</label>
                            <input class="form-control" type="text" name="typeName" size="50" id="typeName" value = "<%=name%>">
                        </div>
                        
                        <div class="form-group">
                            <label for="privilege">Privilege</label>
                            <select name="privilege" id="privilege" class="form-control" value ="<%=privilege%>" >
                                <option value="0">Advisor</option>
                                <option value="1">Administrator</option>
                                <option value="2">Student</option>
                                <option value="3">Lead Advisor</option>
                                <option value="4">Staff</option>
                            </select>
                        </div>
                        <input type="hidden" value="false" name="remove">
                        <input type="submit" value="Edit Appointment Type" id="submitBtn" class="btn btn-default">
                    </form>
                        <%
                            if (deleting  || descriptionSubmitted) {
                        %>
                        <jsp:useBean id="macb" class="uta.cse4361.beans.ModifyAccountTypeBean" scope="session"/>
                        <jsp:setProperty name="macb" property="acctTypeID" value= '<%= acct.getID() %>'/>
                        
                        <%
                            if (descriptionSubmitted && (Boolean.parseBoolean(request.getParameter("remove")) == false)){
                        %>
                                <jsp:setProperty name="macb" property="acctTypeName" value= '<%= request.getParameter("typeName")%>'/>
                                <jsp:setProperty name="macb" property="remove" value= '<%=Boolean.parseBoolean(request.getParameter("remove"))%>'/>
                                <jsp:setProperty name="macb" property="privilege" value= '<%=Integer.parseInt(request.getParameter("privilege"))%>'/>

                        <%
                            } else {

                        %>
                                <jsp:setProperty name="macb" property="remove" value= '<%=Boolean.parseBoolean("true")%>'/>
                                
                        <%
                        }
                        	String success = macb.modifyAccountType();
                                if (success.equals("")) {
                                    response.sendRedirect("manageAccountTypes.jsp");
                                } else {
                                    out.print("<p>" + success + "</p>");
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

