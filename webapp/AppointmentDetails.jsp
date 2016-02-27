<%-- 
    Document   : AppointmentDetails
    Created on : Sep 29, 2014, 12:01:50 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Appointment Created</title>
    </head>
    <body>

        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
        <div id="accordion" class="centerthis">
            <h3>Success</h3>
            <div>
                Appointment has been created.
            </div>
        </div>                   
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/AppointmentDetails.js"></script>
</html>
