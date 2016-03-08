<%-- 
    Document   : Feedback
    Created on : Mar 07, 2016, 6:13:01 PM
    Author     : Abhijeet Chopra
--%>

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
        <title>Feedback</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Feedback Form</h3>
                <div>
                    <!--
                    !!!READ THIS TRACY!!!
                    TODO : Change AccountConfirmation.jsp to something like feedbackConfirmation.jsp
                    Upon filling the form and clicking the Submit button, the Name, Email and Feedback shall be stored in a table in the database.
                    --> 
                    <form role="form" id="create"  onSubmit="return validate();" action="AccountConfirmation.jsp" method="POST">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <input class="form-control" type="text" name="name" id="name" value="">
                        </div>
                        <div class="form-group">
                            <label for="email">Email</label>
                            <input class="form-control" type="text" name="email" id="email" value="">
                        </div>
                        <div class="form-group">
                            <label for="remarks">Feedback</label>
                            <textarea class="form-control" name="remarks" id="remarks" rows="10">
                            </textarea>
                        </div>
                        <div class="centerthis">
                            <input type="submit" value="Submit" id="submitBtn" class="btn btn-default">
                            <input type="reset" value="Reset" id="resetBtn" class="btn btn-default">
                        </div>
                    </form>
                </div> 
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript" src="js/CreateAccount.js"></script>
</html>
