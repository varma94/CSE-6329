$( "#timeaccordion" ).accordion({ heightStyle: "content"});
$( "#scheduleAccordion" ).accordion({ heightStyle: "fill"});
//$( "#timeaccordion" ).accordion({ heightStyle: "content" });
//$("#submitBtn").button().click(function(){});

$(document).ready(function() {
// page is now ready, initialize the calendar...       
    var formattedEventData = new Array();
    var k; 
    var endHour, endMin;
    for (var k = 0; k < size; k++) {
        if(parseInt(min[k]) === 0)
            {
                endHour = parseInt(hour[k]);
                endMin = 15;
            }
        if(parseInt(min[k]) === 15)
        {
                endHour = parseInt(hour[k]);
                endMin = 30;
        }
        if(parseInt(min[k]) === 30)
        {
                endHour = parseInt(hour[k]);
                endMin = 45;
        }
        if(parseInt(min[k])== 45)
            {
                endHour = parseInt(hour[k])+1;
                endMin = 0;
//                alert(endHour+":"+endMin);
            }
        if(isAppt[k] == "true"){          
            formattedEventData.push({            
            start: new Date(year[k], month[k], day[k], hour[k], min[k], 0, 0), 
            end: new Date(year[k], month[k], day[k], endHour, endMin, 0, 0), 
            sHour: hour[k], 
            sMin: min[k],  
            title: "Occupied",
            color: '#808080'
                                });
        }
        else{
        formattedEventData.push({            
            start: new Date(year[k], month[k], day[k], hour[k], min[k], 0, 0), 
            end: new Date(year[k], month[k], day[k], endHour, endMin, 0, 0), 
            sHour: hour[k], 
            sMin: min[k],  
            title: "Available",
            color: '#00377b'
                                });
         }
                                    };
//    }
 
//force description into textarea...
$('#description').val(desc);

    //customize calendar settings
var calendar = $('#calendar').fullCalendar({
        defaultView: 'agendaDay',
        defaultDate: $('#date').val(),
        allDaySlot:false,
        slotDuration: "00:15",
        minTime: "07:00",
        maxTime: "19:00",
        header: {
            left: '',
            center: 'title',
            right: ''
	}, 
      
        //when you click a event delete it
        eventClick: function(event, element) {  
            //get the date string and parse it to convert to a Date
            var eDate = Date.parse(event.start.toString());
            var cDate = new Date(eDate);
            
            if(event.title === 'Occupied')
            {
                $("#endTime").notify("Not available", "error", 
                {elementPosition: 'bottom center',
                 globalPosition: 'bottom center'})
            }
            else
            {
                if(event.sMin === '0'){
                    $('input[name="startTime"]').val(event.sHour+":00"); //24 hour format
                }
                else{
                    $('input[name="startTime"]').val(event.sHour+":" + event.sMin); //24 hour format
                }
                //fix this later
                if(event.sMin === '45')
                {
                    $('input[name="endTime"]').val((parseInt(event.sHour)+1)+":00");
                }
                if(event.sMin === '0')
                {
                    $('input[name="endTime"]').val((event.sHour)+":15");
                }
                if(event.sMin === '15')
                {
                    $('input[name="endTime"]').val((event.sHour)+":30");
                }
                if(event.sMin === '30')
                {
                    $('input[name="endTime"]').val((event.sHour)+":45");
                }
                $("#endTime").notify("Time selected", "success", 
                {elementPosition: 'bottom center',
                 globalPosition: 'bottom middle'});
            }
        },
        
        //load events
        events: formattedEventData
                          
    })//fullcalendar end
  
});//ready end
