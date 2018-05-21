
(function ($) {
    "use strict";


    /*==================================================================
    [ Focus input ]*/
    $('.input100').each(function(){
        $(this).on('blur', function(){
            if($(this).val().trim() != "") {
                $(this).addClass('has-val');
            }
            else {
                $(this).removeClass('has-val');
            }
        })    
    })
  
  
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');
    var password = $("[name='password']");
    var confirm = $("[name='confirm_password']");

    $('.validate-form').on('submit',function(e){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }
        
        
        if(validate(confirm) && password.val() != confirm.val()){
        	showValidatePassword(confirm);
        	check = false;
        } else {
        	removeValidatePassword(confirm);
        }
        
        if($('[name=email]').parent().hasClass('alert-email')){
        	check = false;
        }
        
        if(check){
	    	var data = $(this).serialize();
	    	
	    	$.ajax({
	            url: "/register/saveRegistration",
	            type: 'POST',
	            data: data,
	            success: function(data, textStatus, jqXHR) {
	                if(data == true){
                        swal({
                           title: "Successfully Registered",
                           type: "success",
                           text: "You will be redirected to the login page...",   
                           timer: 3000,
                           showConfirmButton: false 
                       }, function(){   
                    	   	 window.location.href = "/login";
                       });
	                } else {
	                	swal({   
                           title: "Oopss!",
                           type: "error",
                           text: "Email already used. Please sign in if you already have an account.",   
                       });
	                }
	            }
	        });
        }
    	
    	e.preventDefault();
    });
    
    confirm.on('keyup', function(){
    	if(validate(confirm) && password.val() != confirm.val()){
        	showValidatePassword(confirm);
        } else {
        	removeValidatePassword(confirm);
        }
    });   
    
    confirm.on('focus', function(){
    	if(validate(confirm) && password.val() != confirm.val()){
        	showValidatePassword(confirm);
        } else {
        	removeValidatePassword(confirm);
        }
    });   

    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
        
        return true;
    }
    
    function showValidateEmail(input){
    	var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-email');
    }
    
    function removeValidateEmail(input){
    	var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-email');
    }

    
    function showValidatePassword(input){
    	var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-confirm');
    }
    
    function removeValidatePassword(input){
    	var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-confirm');
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
    /*==================================================================
    [ Show pass ]*/
    var showPass = 0;
    $('.btn-show-pass').on('click', function(){
        if(showPass == 0) {
            $(this).next('input').attr('type','text');
            $(this).find('i').removeClass('zmdi-eye-off');
            $(this).find('i').addClass('zmdi-eye');
            showPass = 1;
        }
        else {
            $(this).next('input').attr('type','password');
            $(this).find('i').addClass('zmdi-eye-off');
            $(this).find('i').removeClass('zmdi-eye');
            showPass = 0;
        }
        
    });
    
    /*==================================================================
    [ Check email validity ]*/
    var timeoutSearchInput;
	$('[name=email]').on('keyup', function() {
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
	            	console.log(data);
	                if(data == true){
	                	showValidateEmail(elem);
	                } else {
	                	removeValidateEmail(elem);
	                }
	            }
	        });
	    }, 500);
	});
	
	$('.ui.dropdown').dropdown();

})(jQuery);