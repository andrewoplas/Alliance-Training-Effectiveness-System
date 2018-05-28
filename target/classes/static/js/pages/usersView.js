
(function ($) {
    "use strict";

    $('#name').keyup(function(){
    	if($.trim($(this).val()).length == 0) {
    		$(this).parents('.form-group').addClass('has-error');
    		$(this).parents('.form-group').find('.help-block').removeClass('hide');
    	} else {
    		$(this).parents('.form-group').removeClass('has-error');
    		$(this).parents('.form-group').find('.help-block').addClass('hide');
    	}
    });
    
    $('#email').keypress(function(e) {
        e.preventDefault();
    });
    
    $('#confirm_password').keyup(function(){
    	console.log($(this).val() + " " + $('#password').val());
    	
    	if($.trim($(this).val()) != $('#password').val()) {
    		$(this).parents('.form-group').addClass('has-error');
    		$(this).parents('.form-group').find('.help-block').removeClass('hide');
    	} else {
    		$(this).parents('.form-group').removeClass('has-error');
    		$(this).parents('.form-group').find('.help-block').addClass('hide');
    	}
    });
    
    $('#password').keyup(function(){
    	if($.trim($(this).val()) != $('#confirm_password').val() && $('#confirm_password').val().length > 0) {
    		$('#confirm_password').parents('.form-group').addClass('has-error');
    		$('#confirm_password').parents('.form-group').find('.help-block').removeClass('hide');
    	} else {
    		$('#confirm_password').parents('.form-group').removeClass('has-error');
    		$('#confirm_password').parents('.form-group').find('.help-block').addClass('hide');
    	}
    });
    
    $('.btn-submit').click(function(){
    	if($('.has-error').length > 0) {
	       $('html, body').animate({
	           scrollTop: $('.has-error').eq(0).offset().top - 100
	       }, 300);
	       
	       return false;
    	}
    	
    	$.ajax({
            url: "/ates/users/view/edit",
            type: 'POST',
            data: {
            	id: $('#id').val(),
        		name: $('#name').val(),
        		position: $('#position').val(),
        		password: $('#password').val(),
            },
            success: function(data, textStatus, jqXHR) {
                if(data == true){
                    swal({
                       title: "Successful",
                       type: "success",
                       text: "We have successfully edited your credentials",   
                       showConfirmButton: true,
                       allowEscapeKey: false,
                   });
                }
            }, 
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
        });
    });
	
})(jQuery);