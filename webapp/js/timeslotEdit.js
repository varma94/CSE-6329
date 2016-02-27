$( "#mainAccordion" ).accordion({ heightStyle: "content" });
//$("#submitBtn").button().click(function(){});
//$("#cancelBtn").button().click(function(){});
$(document).ready( function () {
    $('#info').DataTable({
        "paging":   false,
        "ordering": true,
        "info":     false,
        "searching":false,
    });
    $('#info2').DataTable({
        "paging":   false,
        "ordering": true,
        "info":     false,
        "searching": false
    });
} );
