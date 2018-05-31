
(function ($) {
    "use strict";
    
    $(document).on('change keyup paste', '.option-item input, .scale input, .materialize-textarea', function(){
    	$(this).parents('.question-container').find('.help-block').addClass('hide');
    });
    
    $('.btn-submit').click(function(){    	
    	var error = false;
		var assignmentID = $('#assignment').val();
    	var answers = [], description, questionID, questionType;
    	
    	$('.question-container').each(function(){
        	questionID = $(this).attr('data-id');
        	questionType = $(this).attr('data-type');
        	
        	if(questionType == 'textbox') {
        		description = $(this).find('textarea').val();
        	} else if(questionType == 'radiobutton') {
        		description = $(this).find('[name=question' + questionID + ']:checked').val();
        	} else if(questionType == 'checkbox') {
        		description = $(this).find('[name=question' + questionID + ']:checked').map(function() {
        		      				return $(this).val();
        	    				}).get().join(',');
        	} else {
        		description = $(this).find('[name=question' + questionID + ']:checked').val();
        	} 
        	
        	if(description === undefined || description.length == 0) {
        		$(this).find('.help-block').removeClass('hide');
        		error = true;
        	} else {
        		$(this).find('.help-block').addClass('hide');
        	}
      	
        	answers.push({
    			description: description,
    			assignmentID: assignmentID,
    			questionID: questionID,
    			questionType: questionType 
    		});
    	});
    	
    	if(error) {
    		return false;
    	}
    	
    	$("#ajax-process").modal('show');
    	$.ajax({
			url: "/ates/general/training/form/answer/" + assignmentID,
			type: 'POST',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data: JSON.stringify(answers),
			success: function(data, textStatus, jqXHR) {
				$("#ajax-process").modal('hide');
				if(data == true) {
	           	 	swal({   
						title: "Success!",
						type: "success",
						text: "We have successfully submitted your Form",
				        showConfirmButton: false,
				        allowEscapeKey: false,
				        timer: 3000,
	                 }, function(){   
	              	   	 window.location.href = "/ates/general/training";
	                 });            	 
                } else {
               	 	showErrorAlert();
                }
            },
            error: function(jqXHR, status, error) {
            	$("#ajax-process").modal('hide');
            	showErrorAlert();
            }
         });
	
    });
	
})(jQuery);