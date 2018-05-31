(function ($) {
    "use strict";

    $('#all-users-table').footable({
		"filtering": {
			"enabled": true
		}
	});

	// Pagination
	// -----------------------------------------------------------------
	$('#show-entries').change(function (e) {
		e.preventDefault();
		var pageSize = $(this).val();
		$('#all-users-table').data('page-size', pageSize);
		$('#all-users-table').trigger('footable_initialized');
	});
	
	$(document).on('click', '.btn-accept', function(e){
		e.preventDefault();
		var parent = $(this).parents('tr');
		var id = parent.attr('data-id');
		
		$.ajax({
			url: "/ates/general/invitation/accept",
			type: 'POST',
			data: {id: id},
			success: function(data, textStatus, jqXHR) {
				if(data == true) {
					parent.find('.status .badge').removeClass('badge-danger');
					parent.find('.status .badge').addClass('badge-success');
					parent.find('.status .badge').text('Approved');
					parent.find('.btn-accept').addClass('hide');
					parent.find('.btn-decline').removeClass('hide');
				} else {
					showErrorAlert();
				}
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
    	});
	});
	
	$(document).on('click', '.btn-decline', function(e){
		e.preventDefault();
		var parent = $(this).parents('tr');
		var id = parent.attr('data-id');
		var training = parent.find('.training').text(); 
    	
    	swal({   
            title: "Really?",   
            text: "Are you sure you to decline " + name + "?",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#f44336",
            confirmButtonText: "Decline",   
            cancelButtonText: "Cancel",   
            closeOnConfirm: false,   
        }, function(isConfirm){   
            if (isConfirm) {            	
            	$.ajax({
            		url: "/ates/general/invitation/decline",
    	            type: 'POST',
    	            data: {id: id},
    	            success: function(data, textStatus, jqXHR) {
    	            	if(data == true) {
	    	            	swal("Invitation Declined", "You have successfully declined the invitation to join " + training, "success");
	    	            	
	    	            	parent.find('.status .badge').removeClass('badge-success');
	    					parent.find('.status .badge').addClass('badge-danger');
	    					parent.find('.status .badge').text('Declined');
	    					parent.find('.btn-decline').addClass('hide');
	    					parent.find('.btn-accept').removeClass('hide');
    	            	} else {
    	            		showErrorAlert();
    	            	}
    	            },
    	            error: function(jqXHR, status, error) { 
    	            	showErrorAlert(); 
    	        	}
    	    	});
        	}
        });
    });
	
	
	
})(jQuery);