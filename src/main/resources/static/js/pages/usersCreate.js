(function ($) {
    "use strict";

    $("#form-create-user").on('submit', function(e){
    	if(!$(".help-block").hasClass('hide')) {
    		return false;
    	}
    	var name = $(this).find('#name').val();
    	var formData = $(this).serialize();
    	
    	$.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: formData,
            success: function(data, textStatus, jqXHR) {
                if(data == 'success') {
                	swal({   
                        title: "Success!",
                        type: "success",
                        text: "We have successfully created " + name + "'s account.",
                        confirmButtonColor: "#1e88e5",
                        showConfirmButton: true,
               	 	});
                	
                	$("#form-create-user")[0].reset();
                } else if(data == 'email_exists'){
                	swal({   
                        title: "Oppps!",
                        type: "error",
                        text: "Email already exists. Please try again with another unique email.",
                        confirmButtonColor: "#1e88e5",
                        showConfirmButton: true,
               	 	});
                } else {
                	swal({   
                        title: "Oppps!",
                        type: "error",
                        text: "Something went wrong as we process your request.",
                        confirmButtonColor: "#1e88e5",
                        showConfirmButton: true,
               	 	});
                }
            }, error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
        });
    	
    	e.preventDefault();
    });
    
    /*==================================================================
    [ Check email validity ]*/
    
    var timeoutSearchInput;
	$('[name=email]').on('keyup', function() {
		$(".help-block").addClass('hide');
	    if (timeoutSearchInput) {
	        clearTimeout(timeoutSearchInput);
	        timeoutSearchInput = null;
	    }
	    
	    var elem = $('[name=email]');
	    timeoutSearchInput = setTimeout(function() {
	        var input = elem.val();
	        $.ajax({
	        	url: '/register/checkEmail',
	            type: "POST",
	            data: {email: input},
	            success: function(data) {
	            	if(data == true) {
	            		$(".help-block").removeClass('hide');
	            	} else {
	            		$(".help-block").addClass('hide');
	            	}
	            }
	        });
	    }, 500);
	});
	
})(jQuery);