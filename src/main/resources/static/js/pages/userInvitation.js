(function ($) {
    "use strict";
	
	$(document).on('click', '.btn-accept', function(e){
		e.preventDefault();
		var elem = $(this).parents('.parent-div');
		var id = $(this).val();
		var training = elem.find('.training-title').text();
		
		$.ajax({
			url: "/ates/general/invitation/accept",
			type: 'POST',
			data: {id: id},
			success: function(data, textStatus, jqXHR) {
				elem.remove();
				swal("Invitation Accepted", "You have successfully accepted the invitation to join " + training, "success");
				
				if ($('.parent-div').length == 0) { 
					$('#page-wrapper').css('background-color', '#fff');
					$('.empty-container').removeClass('hide');
				}
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
    	});
	});
	
	$(document).on('click', '.btn-decline', function(e){
		e.preventDefault();
		var elem = $(this).parents('.parent-div');
		var id = $(this).val();
		var training = elem.find('.training-title').text();
    	
    	swal({   
            title: "Really?",   
            text: "Are you sure you won't attend " + name + "?",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#f44336",
            confirmButtonText: "Yep, I won't",   
            cancelButtonText: "Cancel",   
            closeOnConfirm: false,   
        }, function(isConfirm){   
            if (isConfirm) {            	
            	$.ajax({
            		url: "/ates/general/invitation/decline",
    	            type: 'POST',
    	            data: {id: id},
    	            success: function(data, textStatus, jqXHR) {
    	            	elem.remove();
    	            	swal("Invitation Declined", "You have successfully declined the invitation to join " + training, "success");
    	            	
    	            	if ($('.parent-div').length == 0) { 
    						$('#page-wrapper').css('background-color', '#fff');
    						$('.empty-container').removeClass('hide');
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