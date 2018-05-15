
(function ($) {
    "use strict";
    
    if($.trim($("[name=id]").val()).length == 0) {
    	swal({   
            title: "Oppps!",
            type: "error",
            text: "The user that you are trying to edit does not exists. You will be redirect to user list page.",
            confirmButtonColor: "#1e88e5",
            showConfirmButton: false,
            timer: 3000
   	 	}, function(){
   	 		window.location.replace("/ates/users/list");
   	 	});
    } else {
    	var id = $("[name=position-id]").val();
    	
    	$("#form-edit-user select option").removeAttr('selected');
    	$("#form-edit-user select").find('[value=' + id + ']').attr('selected', 'selected');
    }
    
    $(".notification-create").hide();
    $("#password").on('change', function(){
    	if($(this).is(':checked')) {
    		$(".notification-create").show();
    		$(".notification-create").removeClass('fadeOut');
    		$(".notification-create").addClass('fadeInDown');
    		
    	} else {
    		$(".notification-create").removeClass('fadeInDown');
    		$(".notification-create").addClass('fadeOut');    		
    	}
    })
    
    
    $("#form-edit-user").on('submit', function(e){
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
                        text: "We have successfully edited " + name + "'s account.",
                        confirmButtonColor: "#1e88e5",
                        showConfirmButton: true,
               	 	});
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
            }
        });
    	
    	e.preventDefault();
    });
    
	
})(jQuery);