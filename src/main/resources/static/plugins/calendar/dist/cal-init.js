
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


    /* on drop */
    CalendarApp.prototype.onDrop = function (eventObj, date) { 
        var $this = this;
            // retrieve the dropped element's stored Event Object
            var originalEventObject = eventObj.data('eventObject');
            var $categoryClass = eventObj.attr('data-class');
            // we need to copy it, so that multiple events don't have a reference to the same object
            var copiedEventObject = $.extend({}, originalEventObject);
            // assign it the date that was reported
            copiedEventObject.start = date;
            if ($categoryClass)
                copiedEventObject['className'] = [$categoryClass];
            // render the event on the calendar
            $this.$calendar.fullCalendar('renderEvent', copiedEventObject, true);
            // is the "remove after drop" checkbox checked?
            if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                eventObj.remove();
            }
    },
    /* on click on event */
    CalendarApp.prototype.onEventClick =  function (calEvent, jsEvent, view) {
        var $this = this;
            var form = $("<form class='floating-labels'></form>");
            form.append("<div class='row'></div>");
            form.find(".row")
            	.append("<div class='col-md-12'>" +
                		"    <div class='form-group'>" +
                		"        <select id='bg' class='form-control validate-empty p-0' name='bg' required=''>" +
                		"			<option value='event-red'>Red</option>" +	
                		"        	<option value='event-pink'>Pink</option>" +	
                		"        	<option value='event-purple'>Purple</option>" +		
                		"        	<option value='event-indigo'>Indigo</option>" +	
                		"        	<option value='event-blue'>Blue</option>" +	
                		"        	<option value='event-teal'>Teal</option>" +	
                		"        	<option value='event-green'>Green</option>" +	
                		"        	<option value='event-orange'>Orange</option>" +	
                		"        	<option value='event-brown'>Brown</option>" +
                		"        </select>" +
                		"        <span class='highlight'></span>" +
                		"        <span class='bar'></span>" +
                		"        <label for='bg'>Event Color</label>" +
                		"		 <span class='help-block help-select-empty hide'>" +
	                	"		 <small class='text-danger'><i class='mdi mdi-close-circle-outline'></i> You haven't selected a training color yet!</small>" +
	                	"		</span>" +
                		"    </div>" +
                		"</div>")
            	.append("<div class='col-md-6'>" +
	            		"    <div class='form-group'>" +
	            		"        <input type='text' id='start_time' class='form-control timepicker' name='start_time' required=''/>" +
	            		"        <span class='highlight'></span>" +
	            		"        <span class='bar'></span>" +
	            		"        <label for='start_time'>Start Time</label>" +
	            		"    </div>" +
            			"</div>")
            	.append("<div class='col-md-6'>" +
            			"    <div class='form-group'>" +
            			"        <input type='text' id='end_time' class='form-control timepicker' name='end_time' required=''/>" +
            			"        <span class='highlight'></span>" +
            			"        <span class='bar'></span>" +
            			"        <label for='end_time'>End Time</label>" +
            			"    </div>" +
            			"</div>");
            
            $('#my-event .modal-title').text("Edit Event");
            $this.$modal.modal({
                backdrop: 'static'
            });
            
            $this.$modal.find('.save-event2').show().end().find('.modal-body').empty().prepend(form).end().find('.save-event2').unbind('click').click(function () {
                form.submit();
            });
            
            $this.$modal.find('.delete-event').show().end().find('.save-event').hide().end().find('.modal-body').empty().prepend(form).end().find('.delete-event').unbind('click').click(function () {
                $this.$calendarObj.fullCalendar('removeEvents', function (ev) {
                    return (ev._id == calEvent._id);
                });
                $this.$modal.modal('hide');
            });
            
            $this.$modal.find('#bg option[value="' + calEvent.className[0] + '"]').attr('selected', 'selected');
            
            $this.$modal.find('form').on('submit', function () {
                var beginning = form.find("input[name='start_time']").val();
                var ending = form.find("input[name='end_time']").val();
                var beginningObj = moment(beginning, 'hh : mm A');
                var endingObj = moment(ending, 'hh : mm A');
                var bgClassName = form.find("select[name='bg']").val();
                	
                // Set Time Start
                calEvent.start.set('hour', beginningObj.get('hour'));
                calEvent.start.set('minute', beginningObj.get('minute'));
                
                // Set Time End
                calEvent.end.set('hour', endingObj.get('hour'));
                calEvent.end.set('minute', endingObj.get('minute'));
                
                // Set className
                calEvent.className[0] = bgClassName;
                
                $this.$calendarObj.fullCalendar('updateEvent', calEvent);
                $this.$modal.modal('hide');
                return false;
            });
            
            // Initialize
            $('#my-event #start_time').wickedpicker({now: calEvent.start.get('hour') + ':' + calEvent.start.get('minute') , minutesInterval: 5});
            $('#my-event #end_time').wickedpicker({now: calEvent.end.get('hour') + ':' + calEvent.end.get('minute') , minutesInterval: 5});
    },
    /* on select */
    CalendarApp.prototype.onSelect = function (start, end, allDay) {
        var $this = this;
            $this.$modal.modal({
                backdrop: 'static'
            });
            var form = $("<form class='floating-labels'></form>");
            form.append("<div class='row'></div>");
            form.find(".row")
                .append("<div class='col-md-8'>" +
                		"    <div class='form-group'>" +
                		"        <input type='text' id='title' class='form-control validate-empty' name='title' required='' onkeypress='return false;'/>" +
                		"        <span class='highlight'></span>" +
                		"        <span class='bar'></span>" +
                		"        <label for='title'>Event Name</label>" +
                		"		 <span class='help-block help-block-empty hide'>" +
	                	"		 <small class='text-danger'><i class='mdi mdi-close-circle-outline'></i> You don't have a training title yet!</small>" +
	                	"		</span>" +
                		"    </div>" +
                		"</div>")
                .append("<div class='col-md-4'>" +
                		"    <div class='form-group'>" +
                		"        <select id='bg' class='form-control validate-empty p-0' name='bg' required=''>" +
                		"        	<option value='' selected disabled></option>" +
                		"			<option value='event-red'>Red</option>" +	
                		"        	<option value='event-pink'>Pink</option>" +	
                		"        	<option value='event-purple'>Purple</option>" +		
                		"        	<option value='event-indigo'>Indigo</option>" +	
                		"        	<option value='event-blue'>Blue</option>" +	
                		"        	<option value='event-teal'>Teal</option>" +	
                		"        	<option value='event-green'>Green</option>" +	
                		"        	<option value='event-orange'>Orange</option>" +	
                		"        	<option value='event-brown'>Brown</option>" +
                		"        </select>" +
                		"        <span class='highlight'></span>" +
                		"        <span class='bar'></span>" +
                		"        <label for='bg'>Event Color</label>" +
                		"		 <span class='help-block help-select-empty hide'>" +
	                	"		 <small class='text-danger'><i class='mdi mdi-close-circle-outline'></i> You haven't selected a training color yet!</small>" +
	                	"		</span>" +
                		"    </div>" +
                		"</div>")
            	.append("<div class='col-md-6'>" +
	            		"    <div class='form-group'>" +
	            		"        <input type='text' id='start_time' class='form-control timepicker' name='start_time' required=''/>" +
	            		"        <span class='highlight'></span>" +
	            		"        <span class='bar'></span>" +
	            		"        <label for='start_time'>Start Time</label>" +
	            		"    </div>" +
            			"</div>")
            	.append("<div class='col-md-6'>" +
            			"    <div class='form-group'>" +
            			"        <input type='text' id='end_time' class='form-control timepicker' name='end_time' required=''/>" +
            			"        <span class='highlight'></span>" +
            			"        <span class='bar'></span>" +
            			"        <label for='end_time'>End Time</label>" +
            			"    </div>" +
            			"</div>");
            
            $this.$modal.find('.delete-event').hide().end().find('.save-event2').hide().end().find('.save-event').show().end().find('.modal-body').empty().prepend(form).end().find('.save-event').unbind('click').click(function () {
                form.submit();
            });
            
            // Initialize
            $('#my-event #start_time').wickedpicker({now: '08:00', minutesInterval: 5});
            $('#my-event #end_time').wickedpicker({now: '18:00', minutesInterval: 5});
            $('#my-event #title').val($('#training').val());
            $('#my-event .modal-title').text("Add Event");
            
            
            if($.trim($('#training').val()).length == 0) { 
            	$('#title').parents('.form-group').addClass('has-error');
            	$('#title').parents('.form-group').find('.help-block').removeClass('hide');
            }
            
            $this.$modal.find('#bg').on('change', function () {
            	$('#bg').parents('.form-group').removeClass('has-error');
            	$('#bg').parents('.form-group').find('.help-select-empty').addClass('hide');
            });
                        
            $this.$modal.find('form').on('submit', function () {
                var title = form.find("input[name='title']").val();
                var bgClassName = form.find("select[name='bg']").val();
                var beginning = form.find("input[name='start_time']").val();
                var ending = form.find("input[name='end_time']").val();
                
                if($.trim($('#bg').val()).length == 0) { 
                	$('#bg').parents('.form-group').addClass('has-error');
                	$('#bg').parents('.form-group').find('.help-select-empty').removeClass('hide');
                	
                	return false;
                }
                
                if (title !== null && title.length != 0) {
                	var beginningObj = moment(beginning, 'hh : mm A');
                	var beginningMS = ((beginningObj.get('hour')*3600) + (beginningObj.get('minute') * 60)) * 1000;                	
                	var endingObj = moment(ending, 'hh : mm A');
                	var endingMS = ((endingObj.get('hour')*3600) + (endingObj.get('minute') * 60)) * 1000;
                	var day = 0;
                	
                	// Loop                	
                	var numberOfDays = Math.abs(start.diff(end, 'days')) - 1;
                	var processing = function() {
                		if ((numberOfDays--) > 0 ) {
                			setTimeout(processing, 5);
                		}
                		
                		$this.$calendarObj.fullCalendar('renderEvent', {
		                        title: title,
		                        start: start.add('day', day) + beginningMS,
		                        end: start + endingMS,
		                        allDay: false,
		                        className: bgClassName
        				}, true);
                		
                		day = 1; 
            		}
            		processing();
	                    
                	$('#calendar').parent().find('.help-block-schedule').addClass('hide');
                    $this.$modal.modal('hide');
                } else {
                	$('#title').parents('.form-group').addClass('has-error');
                	$('#title').parents('.form-group').find('.help-block').removeClass('hide');
                }
                
                return false;
                
            });
            $this.$calendarObj.fullCalendar('unselect');
    },
    CalendarApp.prototype.enableDrag = function() {
        //init events
        $(this.$event).each(function () {
            // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
            // it doesn't need to have a start or end
            var eventObject = {
                title: $.trim($(this).text()) // use the element's text as the event title
            };
            // store the Event Object in the DOM element so we can get to it later
            $(this).data('eventObject', eventObject);
            // make the event draggable using jQuery UI
            $(this).draggable({
                zIndex: 999,
                revert: true,      // will cause the event to go back to its
                revertDuration: 0  //  original position after the drag
            });
        });
    }
    /* Initializing */
    CalendarApp.prototype.init = function() {
        this.enableDrag();

        var $this = this;
        $this.$calendarObj = $this.$calendar.fullCalendar({
            slotDuration: '00:15:00', /* If we want to split day time each 15minutes */
            defaultView: 'month',  
            handleWindowResize: true,
            noEventsMessage: "No training schedule to display",
             
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay',
            },
            
            footer: {
            	center: 'listWeek, listMonth, listYear',
            },
            
            buttonText: {
            	listWeek: 'List By Weeks',
            	listMonth: 'List By Months',
            	listYear: 'List By Year'
            },
            
            editable: true,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            eventLimit: true, // allow "more" link when too many events
            selectable: true,
            drop: function(date) { $this.onDrop($(this), date); },
            select: function (start, end, allDay) { $this.onSelect(start, end, allDay); },
            eventClick: function(calEvent, jsEvent, view) { $this.onEventClick(calEvent, jsEvent, view); }

        });

        //on new event
        this.$saveCategoryBtn.on('click', function(){
            var categoryName = $this.$categoryForm.find("input[name='category-name']").val();
            var categoryColor = $this.$categoryForm.find("select[name='category-color']").val();
            if (categoryName !== null && categoryName.length != 0) {
                $this.$extEvents.append('<div class="calendar-events bg-' + categoryColor + '" data-class="bg-' + categoryColor + '" style="position: relative;"><i class="fa fa-move"></i>' + categoryName + '</div>')
                $this.enableDrag();
            }

        });
    },

   //init CalendarApp
    $.CalendarApp = new CalendarApp, $.CalendarApp.Constructor = CalendarApp
    
}(window.jQuery),

//initializing CalendarApp
function($) {
    "use strict";
    $.CalendarApp.init()
}(window.jQuery);