$( "#appointmentAccordion" ).accordion({ heightStyle: "content" });
//$("#submitBtn").button().click(function(){});
//$("#cancelBtn").button().click(function(){});
$(document).ready( function () {
    $('#tableform').DataTable({
        "paging":   false,
        "ordering": true,
        "info":     false,
        "searching": false
    });
} );
