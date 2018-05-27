
(function ($) {
    "use strict";
    
    $('.btn-assign').click(function(){
    	var elem = $(this);
    	var userEventID = elem.parents('tr').attr('data-id');
    	var formID = $("#formID").val();
    	
    	
    	$.ajax({
			url: "/ates/forms/assign",
			type: 'POST',
			data: {
				userEventID: userEventID,
				formID: formID
			},
			success: function(data, textStatus, jqXHR) {
                 if(data == true) {
                	 elem.parents('tr').find('.status').html('<span class="badge badge-success">Assigned</span>');
                	 elem.remove();
                 } else {
                	 showErrorAlert();
                 }
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });
    });
    
    $('.btn-release').click(function(){
    	var elem = $(this);
    	var trainingPlanID = $("#trainingPlan").val(); 
    	var formID = $("#formID").val();
    	
    	swal({   
	        title: "Are you sure?",   
	        text: "You will not be able to undo the release.",   
	        type: "warning",   
	        showCancelButton: true,   
	        confirmButtonText: "Release",   
	        cancelButtonText: "Cancel",   
	        closeOnConfirm: false,   
	    }, function(isConfirm){   
	        if (isConfirm) {            	
	        	$.ajax({
					url: "/ates/forms/assign/all",
					type: 'POST',
					data: {
						trainingPlanID: trainingPlanID,
						formID: formID
					},
					success: function(data, textStatus, jqXHR) {
		                 if(data == true) {
		                	 $('.status').html('<span class="badge badge-success">Assigned</span>');
		                	 swal("Success!", "We've successfully distributed "+ $('.page-title').text() +" to participants", "success");
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