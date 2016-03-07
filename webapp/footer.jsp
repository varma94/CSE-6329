<%-- 
    Document   : footer
    Created on : Sep 12, 2014, 2:25:09 PM
    Author     : Melissa
--%>
    <footer id="footer">
<!--        <div class = "ui-widget-header">
            © 2014 The University of Texas at Arlington
            701 S. Nedderman Drive, Arlington, TX 76019 
            817-272-2011-->
        <!--</div>-->
        
        
        <!--<script type="text/javascript" src= "js/bootstrap.min.js"></script>-->
        <script type="text/javascript" src= "js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery-ui.js"></script> 
        <script type="text/javascript" src="js/notify.js"></script> 
        <script type="text/javascript" src= "js/jquery.dataTables.js"></script>
        <script type="text/javascript" src="js/jquery.timepicker.js"></script>
        <script type="text/javascript" src= "js/bootstrap.js"></script>
        <script type="text/javascript" src= "js/bootbox.js"></script>
        <link rel="stylesheet" type="text/css" href="css/jquery.timepicker.css" />
        
        <ul class="navigation">
            <li>
                <input id="mysearch" type="search" style="height:26px; padding:0px 10px; font-family: sans-serif; background-color:#00204c; color:#fff; border:solid 1px #aaa;" placeholder="Search..."/>
            </li>
            <li>
                <select id="dynamic_select" style="font-family: sans-serif; background-color:#00204c; color:#aaa; border:solid 1px #aaa;">
                    <option value="http://www.uta.edu/uta/academics/colleges.php">Department</option>
                    <option value="http://www.uta.edu/bioengineering/">Bioengineering</option>
                    <option value="http://www.uta.edu/ce/">Civil Engineering</option>
                    <option value="http://cse.uta.edu/">Computer Science &amp; Engineering</option>
                    <option value="http://www.uta.edu/ee/">Electrical Engineering</option>
                    <option value="http://www.uta.edu/ie/">Industrial, Manufacturing, &amp; Systems Engineering</option>
                    <option value="http://www.uta.edu/mse/">Materials Science &amp; Engineering</option>
                    <option value="http://www.uta.edu/mae/">Mechanical &amp; Aerospace Engineering</option>
                </select>
            </li>

            <li>
                <a href="help.jsp">Instructions</a>
            </li>
            
            <li>
                <a href="feedback.jsp">Feedback</a>
            </li>
            
            <li>
                <a href="reportBugs.jsp">Report Bugs</a>
            </li>
            
            <li>
                <a href="logout.jsp">Logout</a>
            </li>
        </ul>
    </footer>
    <script>
    $(function(){
      // bind change event to select
      $('#dynamic_select').on('change', function () {
          var url = $(this).val(); // get selected value
          if (url) { // require a URL
              //window.location = url; // redirect
              window.open(url,'_blank');

          }
          return false;
      });
    });
</script>
</div>

