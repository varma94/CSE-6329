/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$( "#accordion" ).accordion({ heightStyle: "content" });
$(function() {
    $( "#datepicker" ).datepicker({ minDate: 0, maxDate: "+14D" });
});

options = {'step': 15, 
    'minTime': '7:00am',
    'maxTime': '7:00pm',
     'timeFormat': 'h:i A'};

$('#starttimepicker').timepicker(options);

$('#endtimepicker').timepicker(options);
