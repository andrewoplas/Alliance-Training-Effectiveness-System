
!function($) {
    "use strict";

    var CalendarApp = function() {
        this.$body = $("body")
        this.$calendar = $('#calendar'),
        this.$event = ('#calendar-events div.calendar-events'),
        this.$categoryForm = $('#add-new-event form'),
        this.$extEvents = $('#calendar-events'),
        this.$modal = $('#my-event'),
        this.$saveCategoryBtn = $('.save-category'),
        this.$calendarObj = null
    };

    /* on click on event */
    CalendarApp.prototype.onEventClick =  function (calEvent, jsEvent, view) {
        alert('test');
        console.log(calEvent);
    },
    
    /* Initializing */
    CalendarApp.prototype.init = function(defaultEvents) {     
        var $this = this;
        $this.$calendarObj = $this.$calendar.fullCalendar({
            slotDuration: '00:15:00', /* If we want to split day time each 15minutes */
            defaultView: 'month',  
            handleWindowResize: true,
            noEventsMessage: "No training schedule to display",
             
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'listWeek,listMonth,listYear'
            },
            
            footer: {
            	center: 'month, agendaWeek, agendaDay',
            },
                        
            buttonText: {
            	listWeek: 'List By Weeks',
            	listMonth: 'List By Months',
            	listYear: 'List By Year'
            },
            
            events: defaultEvents,
            eventLimit: true, // allow "more" link when too many events
        });
    },

   //init CalendarApp
    $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
    
}(window.jQuery),

//initializing CalendarApp
function($) {
    "use strict";
    
    $.ajax({
		url: "/ates/training/getTrainings",
		type: 'GET',
		success: function(data, textStatus, jqXHR) {
			var defaultEvents =  [];
			var length = data.length;
			var dateTemp, dateStart, dateEnd;
			
			for(var i=0; i<length; i++) {
	        	dateTemp = (data[i].date + ":" + data[i].timeStart).split(':');  
	        	dateStart = new Date(dateTemp[0], dateTemp[1]-1, dateTemp[2], dateTemp[3], dateTemp[4]);
	        	
	        	dateTemp = (data[i].date + ":" + data[i].timeEnd).split(':');  
	        	dateEnd = new Date(dateTemp[0], dateTemp[1]-1, dateTemp[2], dateTemp[3], dateTemp[4]);
	        	
	        	defaultEvents.push({
	        		title: data[i].title,
	                start: dateStart,
	                end: dateEnd, 
	                className: data[i].className,
	                url: '/ates/training/' + data[i].id
	        	});
	        }
			
			$.CalendarApp.init(defaultEvents);
        },
        error: function(jqXHR, status, error) {
        	console.log(status + ": " + error); 
        	showErrorAlert();
        }
     });
    
}(window.jQuery);