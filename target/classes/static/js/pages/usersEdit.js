
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
    
	
})(jQuery);