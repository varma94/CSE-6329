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
            var startMin = parseInt(min[k]);
            var itemLength = apLength[k];
            
            endHour = parseInt(hour[k]) + Math.floor(itemLength/60);
            var additionalMinutes  = itemLength%60;
            endMin = startMin + additionalMinutes;
        
            if (endMin >= 60)
            {
                endMin = endMin%60;
                endHour++;
            }
        
        if(isAppt[k] == "true"){          
                formattedEventData.push({            
                start: new Date(year[k], month[k], day[k], hour[k], min[k], 0, 0), 
                end: new Date(year[k], month[k], day[k], endHour, endMin, 0, 0), 
                sHour: hour[k], 
                sMin: min[k],
                eHour: endHour,
                eMin: endMin,
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
                eHour: endHour,
                eMin: endMin,
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
                
                if(event.eMin === 0){
                    $('input[name="endTime"]').val(event.eHour+":00"); //24 hour format
                }
                else{
                    $('input[name="endTime"]').val(event.eHour+":" + event.eMin); //24 hour format
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