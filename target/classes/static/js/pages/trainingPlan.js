$(document).ready(function() {
	var trainingPlanObject = {};
	
	// Nestable
	var updateOutput = function(e) {
	    var list = e.length ? e : $(e.target),
	        output = list.data('output');
	    if (window.JSON) {
	        output.val(window.JSON.stringify(list.nestable('serialize'))); //, null, 2));
	    } else {
	        output.val('JSON browser support required for this demo.');
	    }
	};
	$('#nestable2').nestable({
	    group: 1
	}).on('change', updateOutput);
	updateOutput($('#nestable2').data('output', $('#nestable2-output')));
	$('#nestable2').on('click', function(e) {
	    var target = $(e.target),
	        action = target.data('action');
	    if (action === 'expand-all') {
	        $('.dd').nestable('expandAll');
	    }
	    if (action === 'collapse-all') {
	        $('.dd').nestable('collapseAll');
	    }
	});
	
	// Add item to nestable
	var counter = 1;
	$('.btn-add-item').on('click', function(){
		var nestableList = '<li class="dd-item dd3-item" data-id="' + counter + '">' +
	        '<div class="dd-handle dd3-handle"></div>' +
	        '<div class="dd3-content" id="id-' + counter + '">New Item</div>' +
	        '<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">'+
	        	'<i class="mdi mdi-close"></i></button>' +
        	'</li>';
		
		$('.dd-list:first').append(nestableList);
		$('#id-' + counter).editable();
		
		counter++;
		
		if(!$('.help-block-outline').hasClass('hide')) {
			$('.help-block-outline').addClass('hide');
		}
	})
	
	// Initialize editable
	$('#id-0').editable();
	
	
	// Remove item to nestable
	$(document).on('click', '.btn-remove-item', function(){
		var parent = $(this).parents('li');
			parent.addClass('zoomOut animated');
			setTimeout(function(){
				parent.remove();
			}, 500);
	});
	
	
	//turn to inline mode
	$.fn.editable.defaults.mode = 'popup';
	
    
//	var dateOutputField;
//	var dateStart = $('#date_start');
//	var dateEnd = $('#date_end');
//	
//	var picker = new MaterialDatetimePicker().on('submit', function(d) {
//		dateOutputField.val(d.format('MMMM DD, YYYY'));
//		
//		if(dateStart.val() != '' && dateEnd.val() != ''){
//			var dateStartObj = moment(dateStart.val(), 'MMMM DD, YYYY');
//			var dateEndObj = moment(dateEnd.val(), 'MMMM DD, YYYY');
//			
//			
//			if(dateStart.attr('id') === dateOutputField.attr('id') && dateStartObj.isAfter(dateEndObj)) {
//				dateOutputField.parents('.form-group').addClass('has-error')
//					.find('.help-block-date').removeClass('hide');
//			} else if(dateEnd.attr('id') === dateOutputField.attr('id') && dateEndObj.isBefore(dateStartObj)) {
//				dateOutputField.parents('.form-group').addClass('has-error')
//					.find('.help-block-date').removeClass('hide');
//			} else {
//				dateStart.parents('.form-group').removeClass('has-error')
//					.find('.help-block-date').addClass('hide');
//				dateEnd.parents('.form-group').removeClass('has-error')
//					.find('.help-block-date').addClass('hide');
//			}
//		}
//	}).on('close', function() {
//		if($.trim(dateOutputField.val()).length == 0) {
//			dateOutputField.parents('.form-group').addClass('has-error');
//			dateOutputField.parents('.form-group').find('.help-block-empty').removeClass('hide');
//			$('#first-step').addClass('disabled');
//		} else {
//			if(!dateOutputField.parents('.form-group').find('.help-block-date').hasClass('hide')) {
//				dateOutputField.parents('.form-group').removeClass('has-error');
//			}
//			dateOutputField.parents('.form-group').find('.help-block-empty').addClass('hide');
//		}
//	});
//	
//	$('#date_start, #date_end').click(function(){
//		picker.open();
//		dateOutputField = $(this);
//	})
//	
//	
	// Validation First Fieldset
	$('.first-fieldset .validate-empty').blur(function(){
		if($.trim($(this).val()).length == 0) {
			$(this).parents('.form-group').addClass('has-error');
			$(this).parents('.form-group').find('.help-block').removeClass('hide');
		} else {
			$(this).parents('.form-group').removeClass('has-error');
			$(this).parents('.form-group').find('.help-block').addClass('hide');
		}
	})	
	
	// Multi-select (Semantic) Configuration
	$('.multi-select').dropdown({
	    onAdd: function(value, text, $selectedItem) {
	    	$('.multi-select .menu').find('[data-value='+ value +']').hide();
	    	
	    	$(this).parents('.panel').find('.help-block').addClass('hide');
	    },
	    onRemove: function(value, text, $selectedItem) {
	    	$('.multi-select .menu').find('[data-value='+ value +']').show();
	    },
	    onLabelRemove: function(e) {
	    	$(this).tooltip('destroy');
	    	
            return !0
        },
	    onLabelCreate: function(t, n) {
	    	var value = $(this).attr('data-value');
	    	var option = $('.multi-select select').find('[value='+value+']');
	    	var email = option.attr('data-email');
	    	
	    	$(this).attr('data-toggle', 'tooltip');
	    	$(this).attr('data-placement', 'top');
	    	$(this).attr('title', email);
	    	$(this).tooltip();
	    	
	    	return $(this);
        },
	  });
	
	// Initialize tooltip
	$('[data-toggle=tooltip]').tooltip();
	
	// Gather Summary Information
	$('#third-step').on('click', function(){
		// First Fieldset
		var title = $('#training').val();
		var description = $('#description').val();
		var calendar = $('#calendar').fullCalendar('clientEvents');			
		
		// Second Fieldset				
		var item = $('#nestable2').nestable('serialize');
		$.each(item, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
		
		// Third Fieldset
		var supervisors = [], facilitators = [], participants  = [];				
		$.each($('#supervisor-select').dropdown('get activeItem'), function(index, value){
			supervisors.push($(value).text());
		});
		
		$.each($('#facilitator-select').dropdown('get activeItem'), function(index, value){
			facilitators.push($(value).text());
		});
		
		$.each($('#participant-select').dropdown('get activeItem'), function(index, value){
			participants.push($(value).text());
		});
		
		
		// Set it!
		$('[data-value=training]').text(title);
		$('[data-value=description]').text(description);
		
		var scheduleHTML = "";
		$.each(calendar, function(index, item) {
			scheduleHTML += 
				'<tr>' +
					'<td>'+ (index+1) +'</td>' +
					'<td>'+ item.start.format('MMM D, YYYY') +'</td>' +
					'<td>'+ item.start.format('h:mm A') +'</td>' +
					'<td>'+ item.end.format('h:mm A') +'</td>' +
					'<td>'+ item.start.format('dddd') +'</td>' +
				'</tr>';
		});
		
		$('#schedule-table tbody').html(scheduleHTML);
		if (!$.fn.dataTable.isDataTable('#schedule-table')) {
			$('#schedule-table').DataTable({
		        "paging":   false,
		        "info":     false,
		        "searching": false
		    });
		}
		
		var courseOutlineHTML = $('<ol id="parent-outline"></ol>');
		$.each(item, function(index, value){
			courseOutlineHTML.append('<li>' + value.content + '</li>')
			if ('children' in value){
				courseOutlineHTML.find('li:last').append(addContentHTML(value.children));
			}
		});
		$('[data-value=courseOutline]').html(courseOutlineHTML);
		
		var supervisorHTML = "";
		for(var i=0; i<supervisors.length; i++) {
			supervisorHTML += 
				'<span class="m-b-10">' +
					supervisors[i] +
				'</span><br/>';
		}
		
		var facilitatorHTML = "";
		for(var i=0; i<facilitators.length; i++) {
			facilitatorHTML += 
				'<span class="m-b-10">' +
					facilitators[i] +
				'</span><br/>';
		}
		
		var participantHTML = "";
		for(var i=0; i<participants.length; i++) {
			participantHTML += 
				'<span class="m-b-10">' +
					participants[i] +
				'</span><br/>';
		}
		
		$('[data-value=supervisors]').html(supervisorHTML);
		$('[data-value=facilitators]').html(facilitatorHTML);
		$('[data-value=participants]').html(participantHTML);	
		
		
		// Setup the Object
		trainingPlanObject['title'] = title;
		trainingPlanObject['description'] = description;
		trainingPlanObject['calendar'] = calendar;
		trainingPlanObject['courseOutline'] = item;
		trainingPlanObject['supervisors'] = $('#supervisor-select').dropdown('get value');
		trainingPlanObject['facilitators'] = $('#facilitator-select').dropdown('get value');
		trainingPlanObject['participants'] = $('#participant-select').dropdown('get value');
	});
	
	function addContent(items){
		$.each(items, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
	}
	
	function addContentHTML(items){
		var parent = $("<ol></ol>");
		$.each(items, function(index, value){
			parent.append('<li>' + value.content + '</li>');
			if ('children' in value){
				parent.find('li:last').append(addContentHTML(value.children));
			}
		});
		
		return parent;
	}
	
	$(".submit").on('click', function(){
		console.log(trainingPlanObject);
		
		$.ajax({
			url: "/ates/save",
			type: 'POST',
			data: { trainingPlan: trainingPlanObject },
			success: function(data, textStatus, jqXHR) {
                 console.log(data);
             },
             error: function(jqXHR, status, error) {
                 console.log(status + ": " + error);
             }
         });
	});
	
	
});