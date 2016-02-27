$("#allocateAccordion").accordion({heightStyle: "content"});
$("#timeaccordion").accordion({heightStyle: "content"});
//$("#button").button();

//Andrews code
$(function () {
    $("#datepicker").datepicker({
        minDate: 0, 
        maxDate: "+14D"});
});

options = {'step': 15,
    'minTime': '7:00am',
    'maxTime': '7:00pm',
    'timeFormat': 'h:i A'};

$('#starttimepicker').timepicker(options);
$('#endtimepicker').timepicker(options);


$(document).ready(function () {

    var k;
    if (size > 0)
    {
        var formattedEventData = new Array();
        var k;
        var endHour, endMin;
        for (var k = 0; k < size; k++) {
            if (parseInt(min[k]) === 0)
            {
                endHour = parseInt(hour[k]);
                endMin = 15;
            }
            if (parseInt(min[k]) === 15)
            {
                endHour = parseInt(hour[k]);
                endMin = 30;
            }
            if (parseInt(min[k]) === 30)
            {
                endHour = parseInt(hour[k]);
                endMin = 45;
            }
            if (parseInt(min[k]) == 45)
            {
                endHour = parseInt(hour[k]) + 1;
                endMin = 0;
//                alert(endHour+":"+endMin);
            }
            if (isAppt[k] == "true") {
                formattedEventData.push({
                    start: new Date(year[k], month[k], day[k], hour[k], min[k], 0, 0),
                    end: new Date(year[k], month[k], day[k], endHour, endMin, 0, 0),
                    sHour: hour[k],
                    sMin: min[k],
                    title: "Busy",
                    color: '#b03434'
                });
            }
            else {
                formattedEventData.push({
                    start: new Date(year[k], month[k], day[k], hour[k], min[k], 0, 0),
                    end: new Date(year[k], month[k], day[k], endHour, endMin, 0, 0),
                    sHour: hour[k],
                    sMin: min[k],
                    title: "Open",
                    color: '#001131'
                });
            }
        }
    }
    var calendar = $('#calendar').fullCalendar({
        allDaySlot: false,
        slotDuration: "00:15",
        minTime: "07:00",
        maxTime: "19:00",
        hiddenDays:[0,6],
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay'
        },
        //load events
        events: formattedEventData,
        
        dayClick: function(date, event, view) {
        $('#datepicker').val(date.format('MM/DD/YYYY'));
        $('#datepicker').notify("Date" + " Selected", "success",
        {
//            elementPosition:'top center',
            globalPosition:'top center'
        })
    },

    })//fullcalendar end
});//ready end
