<%-- 
    Document   : CreateAccount
    Created on : Nov 22, 2014, 5:50:01 PM
    Author     : Melissa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <%
            //Uncomment when there's a proper way to create an admin account
                //this code redirects anyone who is not an admin back to the index page
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
            if(rank != 1 && rank!=2)
                {
                    response.sendRedirect("index.jsp");
                }
        %>
        <script type="text/javascript">
            function validate() {
//                var username = document.forms["create"]["username"].value;
                var email = document.forms["create"]["email"].value;
                var oldPassword = document.forms["create"]["oldPassword"].value;
                var password = document.forms["create"]["password"].value;
                var passwordConfirm = document.forms["create"]["passwordConfirm"].value;
               

//                if (username === null || username === "") {
//                    $("#username").notify("Please enter your username", "error",
//                            {elementPosition: 'bottom center',
//                                globalPosition: 'bottom center'})
//                    return false;
//                }
                if (oldPassword === null || oldPassword === "") {
                    $("#oldPassword").notify("Please enter correct old password", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
               
                if (password === null || password === "") {
                    $("#password").notify("Please enter your password", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
                if (passwordConfirm === null || passwordConfirm === "") {
                    $("#passwordConfirm").notify("Please confirm your password", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
                if (passwordConfirm !== password) {
                    $("#passwordConfirm").notify("Your password does not match", "error",
                            {elementPosition: 'bottom center',
                                globalPosition: 'bottom center'})
                    return false;
                }
               

            }
            
            function passwordChanged() {
            var strength = document.getElementById('strength');
            var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\W).*$", "g");
            var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
            var enoughRegex = new RegExp("(?=.{6,}).*", "g");
            
            var pwd = document.getElementById("password");
            if (pwd.value.length===0) {
                //strength.innerHTML = 'Type Password';
            }
            else if (false === enoughRegex.test(pwd.value)) {
                strength.innerHTML = '<span style="color:red">Weak Strength</span>';
            } else if (strongRegex.test(pwd.value)) {
                strength.innerHTML = '<span style="color:green">Strong Password</span>';
            } else if (mediumRegex.test(pwd.value)) {
                strength.innerHTML = '<span style="color:orange">Medium Strength</span>';
            } else {
                strength.innerHTML = '<span style="color:red">Weak Strength</span>';
            }
    }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Account</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Create Advisor Account</h3>
                <div>
                <form role="form" id="create"  onSubmit="return validate();" action="PasswordConfirmation.jsp" method="POST">
                    <!--                    <div class="form-group">
                                            <label for="username">Username</label>
                                            <input class="form-control" type="text" name="username" id="username" value="">
                                        </div>-->
                    
                    <div class="form-group">
                        <label for="email">Email</label>
                        <input class="form-control" type="email" name="email" id="email" value="">
                    </div>
                    <div class="form-group">
                        <label for="oldPassword">Old Password</label>
                        <input class="form-control" type="password" name="oldPassword" id="oldPassword" value="">
                    </div>
                    <div class="form-group">
                        <label for="password">New Password</label>
                        <input class="form-control" type="password" name="password" id="password" value="">
                        <span id="strength"></span>
                    </div>
                    <div class="form-group">
                        <label for="passwordConfirm">Confirm Password</label>
                        <input class="form-control" type="password" name="passwordConfirm" id="passwordConfirm" value="">
                    </div>
                    
                    <div class="centerthis">
                        <input type="submit" value="Change Password" id="submitBtn" class="btn btn-default">
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
