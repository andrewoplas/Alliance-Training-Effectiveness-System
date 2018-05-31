(function ($) {
    "use strict";

    if($.trim($("[name=id]").val()).length == 0) {
    	swal({   
            title: "Oppps!",
            type: "error",
            text: "The training that you are trying to access does not exists. You will be redirect to training list page.",
            confirmButtonColor: "#1e88e5",
            showConfirmButton: false,
            timer: 3000
   	 	}, function(){
   	 		window.location.replace("/ates/training/list");
   	 	});
    }
   
    $('.row-checkbox').change(function(){
    	var checkbox = $(this);
    	if(checkbox.is(':checked')) {
    		checkbox.parents('tr').addClass('active');
    	} else {
    		checkbox.parents('tr').removeClass('active');
    	}
    	
    	var check = $('.row-checkbox:checked').length;
    	if(check == 0) {
    		$('#all').prop('checked', false);
    	}
    	
    	if(check > 0) {
    		$('.button-container').show(500);
    	} else {
    		$('.button-container').hide(500);
    	}
    });
    
    $('.all').change(function(){
    	if($(this).is(':checked')) {
    		$(this).parents('table').find('.row-checkbox').prop('checked', true);
    		$(this).parents('table').find('tbody tr').addClass('active');
    		$('.button-container').show(500);
    	} else {
    		$(this).parents('table').find('.row-checkbox').prop('checked', false);
    		$(this).parents('table').find('tbody tr').removeClass('active');
    		$('.button-container').hide(500);
    	}
    });
    
    $('#timepickerTimeIn').timepicki();
    $('#timepickerTimeOut').timepicki();	
    
    $('#timeIn').modal('hide')
    $('#timeOut').modal('hide')
    
    $('#timeIn, #timeOut').on('hidden.bs.modal', function (e) {
    	$(this).find('[name=user_id]').val('');
	})
	
    $('#btn-timeIn').click(function(){
    	var trainingId = $('[name=id]').val();
    	var timeIn = $('#timepickerTimeIn').val();
    	var date = $('li.selected').find('[name=date]').val();
    	timeIn = moment(timeIn, "hh:mm A").format("HH:mm");
    	var checkboxes = $('.row-checkbox:checked');
    	var id = checkboxes.map(function() {
			    	    return this.value;
			    	}).get();
    	var checkedId = id.length >= 1 ? id.join() : $('#timeIn [name=user_id]').val();
    	
    	$.ajax({
			url: "/ates/training/attendance/timein",
			type: 'POST',
			data: { 
					training: trainingId, 
					time: timeIn, 
					ids: checkedId,
					date: date
			},
			success: function(data, textStatus, jqXHR) {
				$.each( checkboxes, function() {
					$(this).parents('tr').find('.timeIn').text($('#timepickerTimeIn').val());
					
					if($(this).parents('tr').find('.timeOut span').text() == 'Absent') {
						$(this).parents('tr').find('.timeOut span').remove();
					}
				});
				
				if($('#timeIn [name=user_id]').val().length > 0) {
					var dataId = $('#timeIn [name=user_id]').val();
					$('li.selected').find('[data-id=' + dataId + ']').find('.timeIn').text($('#timepickerTimeIn').val())
					if($('li.selected').find('[data-id=' + dataId + ']').find('.timeOut span').text() == 'Absent') {
						$('li.selected').find('[data-id=' + dataId + ']').find('.timeOut span').remove();
					}
				}
				
				swal({   
		            title: "Success",
		            type: "success",
		            text: "We have now set the Time In of these users.",
		   	 	});
				
				$("#timeIn").modal('hide');
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });
    	
    	
    });
    
    $('#btn-timeOut').click(function(){
    	var trainingId = $('[name=id]').val();
    	var timeOut = $('#timepickerTimeOut').val();
    	var date = $('li.selected').find('[name=date]').val();
    	timeOut = moment(timeOut, "hh:mm A").format("HH:mm");
    	var checkboxes = $('.row-checkbox:checked');
    	var id = checkboxes.map(function() {
			    	    return this.value;
			    	}).get();
    	var checkedId = id.length >= 1 ? id.join() : $('#timeOut [name=user_id]').val();
    	
    	$.ajax({
			url: "/ates/training/attendance/timeout",
			type: 'POST',
			data: { 
					training: trainingId, 
					time: timeOut, 
					ids: checkedId,
					date: date
			},
			success: function(data, textStatus, jqXHR) {
				if (data == 'timein_violation') {
					swal({   
			            title: "Oops!",
			            type: "error",
			            text: "Please set a Time In first.",
			   	 	});
				} else if(data == 'time_difference_violation') {
					swal({   
			            title: "Oops!",
			            type: "error",
			            text: "Time out must be after Time In",
			   	 	});
				} else if(data == 'error') {
					showErrorAlert();
				} else {
					$.each( checkboxes, function() {
						$(this).parents('tr').find('.timeOut').text($('#timepickerTimeOut').val());
					});

					if($('#timeOut [name=user_id]').val().length > 0) {
						var dataId = $('#timeOut [name=user_id]').val();
						$('li.selected').find('[data-id=' + dataId + ']').find('.timeOut').text($('#timepickerTimeOut').val())
					}
					
					swal({   
			            title: "Success",
			            type: "success",
			            text: "We have now set the Time Out of these users.",
			   	 	});
					
					$("#timeOut").modal('hide');
				}
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
    	});    	
    });
    
    $('.btn-absent').click(function(){
    	var elem = $(this).parents('tr');
    	var trainingId = $('[name=id]').val();
    	var date = $('li.selected').find('[name=date]').val();
    	var id = elem.attr('data-id');
    	
    	$.ajax({
			url: "/ates/training/attendance/absent",
			type: 'POST',
			data: { 
					training: trainingId, 
					date: date,
					id: id,
			},
			success: function(data, textStatus, jqXHR) {
				elem.find('.timeIn').html('<span class="badge badge-danger">Absent</span>');
				elem.find('.timeOut').html('<span class="badge badge-danger">Absent</span>');
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });    	
    });
    
    $('.btn-reset').click(function(){
    	var elem = $(this).parents('tr');
    	var trainingId = $('[name=id]').val();
    	var id = elem.attr('data-id');
    	var date = $('li.selected').find('[name=date]').val();
    	
    	$.ajax({
			url: "/ates/training/attendance/reset",
			type: 'POST',
			data: { 
					training: trainingId,
					date: date,
					id: id,
			},
			success: function(data, textStatus, jqXHR) {
				elem.find('.timeIn').html('');
				elem.find('.timeOut').html('');
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });    	
    });
    
    $('.btn-timeIn').click(function(){
    	var id = $(this).parents('tr').attr('data-id');
    	$('#timeIn [name=user_id]').val(id);
    });
    
    $('.btn-timeOut').click(function(){
    	var id = $(this).parents('tr').attr('data-id');
    	$('#timeOut [name=user_id]').val(id);
    });
    
})(jQuery);