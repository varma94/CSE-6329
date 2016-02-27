$( "#timeslotAccordion" ).accordion({ heightStyle: "content" });
//$("#submitBtn").button().click(function(){});
$(document).ready( function () {
    $('#timeslotList').DataTable({
        "paging":   true,
        "ordering": true,
        "info":     true
    });
} );