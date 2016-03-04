<%-- 
    Document   : testmail
    Created on : Mar 4, 2016, 1:10:24 AM
    Author     : oguni
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <script>
            var cal_single = ics();
            cal_single.addEvent('Best Day', 'This is the best day to demonstrate a single event.', 'New York', '11/12/1987', '11/12/1987');
            cal.download("test.ics");
         </script>
         <script type='text/javascript' src='js/ics.js'></script>
         <a href="javascript:cal.download()">Demo</a>
         
         <script>
             var icsMSG = "BEGIN:VCALENDAR\nVERSION:2.0\nPRODID:-//Our Company//NONSGML v1.0//EN\nBEGIN:VEVENT\nUID:me@google.com\nDTSTAMP:20120315T170000Z\nATTENDEE;CN=My Self ;RSVP=TRUE:MAILTO:me@gmail.com\nORGANIZER;CN=Me:MAILTO::me@gmail.com\nDTSTART:" + "9:30am" +"\nDTEND:" + "10:30am" +"\nLOCATION:" + "California" + "\nSUMMARY:Our Meeting Office\nEND:VEVENT\nEND:VCALENDAR";
            window.open( "data:text/calendar;charset=utf8," + escape(icsMSG));
         </script>
    </body>
   
</html>
