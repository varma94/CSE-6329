$( "#appointmentAccordion" ).accordion({ heightStyle: "content" });
//$("#submitBtn").button().click(function(){});
$(document).ready( function () {
    $('#appointmentList').DataTable({
        "paging":   true,
        "ordering": true,
        "info":     true
    });
} );