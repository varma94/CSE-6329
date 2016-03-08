<%-- 
    Document   : feedback
    Created on : Mar 7, 2016, 1:24:49 PM
    Author     : Abhishek
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FeedBack</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Feedback Form</h3>
                <div>
      <form action="" method="POST">

            Your name: <br>
            <input type="text" name="fullname"><br>
            <br>

            Your email: <br>
            <input type="text" name="email"><br>
            <br>

            Your comments: <br>
            <textarea name="comments" cols="50"></textarea><br><br>

            <input type="submit" value="Submit">
 
</form>
                </div>
            </div>
        </div>
        
 
   <%
   String fullname = request.getParameter("fullname");
   String email = request.getParameter("email");
   String comments = request.getParameter("comments");
   /* Create string of connection url within specified 
   format with machine name, 
    port number and database name. Here machine name id 
    localhost and database name is student. */
    String connectionURL = "jdbc:mysql://localhost:3306/ADVISING";
          // declare a connection by using Connection interface 
    Connection connection = null;
        // declare object of Statement interface that uses for 
   // executing sql statements.
     PreparedStatement pstatement = null;
         // Load JBBC driver "com.mysql.jdbc.Driver"
     Class.forName("com.mysql.jdbc.Driver").newInstance();
          int updateQuery = 0;
     
     	 // check if the text box is empty
	 if(fullname!=null && email!=null && comments!=null){
	 		 // check if the text box having only blank spaces
	     if(fullname!="" && email!="" && comments!="") {
	                 try {
              /* Create a connection by using getConnection()
              method that takes parameters of string type 
              connection url, user name and password to connect 
		to database. */
              connection = DriverManager.getConnection
              (connectionURL, "root", "root");
                            // sql query to insert values in the secified table.
              String queryString = "INSERT INTO FEEDBACK(fullname,email,comments) VALUES (?, ?, ?)";
              	      /* createStatement() is used for create statement
              object that is used for 
		sending sql statements to the specified database. */
              pstatement = connection.prepareStatement(queryString);
              pstatement.setString(1, fullname);
			  pstatement.setString(2, email);
			  pstatement.setString(3, comments);
              updateQuery = pstatement.executeUpdate();
                            if (updateQuery != 0) { %>
	           <br>
	           <TABLE style="background-color: #E3E4FA;" 
                   WIDTH="30%" border="1">
		      <tr><th>Thank you for the feedback!
                    </th></tr>
		   </table>
              <%
              }
            } 
            catch (Exception ex) {
            out.println("Unable to connect to batabase.");
   
               }
            finally {
                // close all the connections.
                pstatement.close();
                connection.close();
            }
	  }
	}
%>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>
