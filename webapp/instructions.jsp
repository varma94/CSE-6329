<%-- 
    Document   : Feedback
    Created on : Mar 08, 2016, 1:05:01 AM
    Author     : Abhijeet Chopra
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="dm" class="uta.cse4361.databases.DatabaseManager" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #accordion2{
                white-space: normal;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Instructions</title>
    </head>
    <body>
        <jsp:include page="navigationbar.jsp" />
        <div id="wrapper">
            <jsp:include page="header.jsp" />
            <div id="accordion">
                <h3>Instructions</h3>
                <div id="accordion-resizer" class="ui-widget-content">
                    <div id="accordion2">
                        <h3>Section 1</h3>
                        <div>
                            <p>Mauris mauris ante, blandit et, ultrices a, suscipit eget, quam. Integer ut neque. Vivamus nisi metus, molestie vel, gravida in, condimentum sit amet, nunc. Nam a nibh. Donec suscipit eros. Nam mi. Proin viverra leo ut odio. Curabitur malesuada. Vestibulum a velit eu ante scelerisque vulputate.</p>
                        </div>
                        <h3>Section 2</h3>
                        <div>
                            <p>Sed non urna. Donec et ante. Phasellus eu ligula. Vestibulum sit amet purus. Vivamus hendrerit, dolor at aliquet laoreet, mauris turpis porttitor velit, faucibus interdum tellus libero ac justo. Vivamus non quam. In suscipit faucibus urna. </p>
                        </div>
                        <h3>Section 3</h3>
                        <div>
                            <p>Nam enim risus, molestie et, porta ac, aliquam ac, risus. Quisque lobortis. Phasellus pellentesque purus in massa. Aenean in pede. Phasellus ac libero ac tellus pellentesque semper. Sed ac felis. Sed commodo, magna quis lacinia ornare, quam ante aliquam nisi, eu iaculis leo purus venenatis dui. </p>
                            <ul>
                                <li>List item one</li>
                                <li>List item two</li>
                                <li>List item three</li>
                            </ul>
                        </div>
                        <h3>Section 4</h3>
                        <div>
                            <p>Cras dictum. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aenean lacinia mauris vel est. </p><p>Suspendisse eu nisl. Nullam ut libero. Integer dignissim consequat lectus. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript">
        $(function () {
            $("#accordion").accordion({collapsible: false, heightStyle: "content"});
            $("#accordion2").accordion({collapsible: true, heightStyle: "content", active: false});
            
        });
    </script>
</html>
