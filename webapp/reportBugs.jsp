<%-- 
    Document   : Feedback
    Created on : Mar 07, 2016, 7:38:01 PM
    Author     : Abhijeet Chopra
--%>

<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            function validate() {
                var email = document.forms["create"]["email"].value;
                var atpos = email.indexOf("@");
                var dotpos = email.lastIndexOf(".")
                var name = document.forms["create"]["name"].value;
                var remarks = document.forms["create"]["remarks"].value;


                if (email === null || email === "") {
                    $("#email").notify("Please enter your email", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }

                if (name === null || name === "") {
                    $("#name").notify("Please enter your name", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }

                if (remarks === null || remarks === "") {
                    $("#remarks").notify("Please enter your remarks", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }

            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Report Bugs</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Report Bugs</h3>
                <div>

                    <form role="form" id="create"  onSubmit="return validate();" action="" method="POST">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input class="form-control" type="text" name="name" id="name" value="">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="text" name="email" id="email" value="">
                        </div>
                        <div class="form-group">
                            <label for="bugs">Report Bugs/Issues</label>
                            <textarea class="form-control" name="bugs" id="bugs" rows="10"></textarea>
                        </div>
                        <div class="centerthis">
                            <input type="submit" value="Submit" id="submitBtn" class="btn btn-default">
                            <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                        </div>
                    </form>
                </div> 
            </div>
        </div>
        
 
   <%
   String name = request.getParameter("name");
   String email = request.getParameter("email");
   String bugs = request.getParameter("bugs");
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
	 if(name!=null && email!=null && bugs!=null){
	 		 // check if the text box having only blank spaces
	     if(name!="" && email!="" && bugs!="") {
	                 try {
              /* Create a connection by using getConnection()
              method that takes parameters of string type 
              connection url, user name and password to connect 
		to database. */
              connection = DriverManager.getConnection
              (connectionURL, "root", "root");
                            // sql query to insert values in the secified table.
              String queryString = "INSERT INTO  REPORT_BUGS(name,email,bugs) VALUES (?, ?, ?)";
              	      /* createStatement() is used for create statement
              object that is used for 
		sending sql statements to the specified database. */
              pstatement = connection.prepareStatement(queryString);
              pstatement.setString(1, name);
			  pstatement.setString(2, email);
			  pstatement.setString(3, bugs);
              updateQuery = pstatement.executeUpdate();
                            if (updateQuery != 0) { %>
	           <br>
	           <TABLE style="background-color: #E3E4FA;" 
                   WIDTH="30%" border="1">
		      <tr><th>Thank you for Reporting!
                    </th></tr>
		   </table>
              <%
              }
            } 
            catch (Exception ex) {
            out.println("Unable to connect to database.");
   
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
