(function ($) {
    "use strict";
    
    var textbox = 
		'<div class="form-container m-b-10 p-30 p-b-0" style="border: 1px solid #eee">' +
			'<span class="numbers"></span>' +
			'<input type="text" class="form-control m-b-5 question" data-type="textbox"  placeholder="Question" />' +
			'<input type="text" class="form-control p-10" readonly value="Answer Container" />' +
			'<div class="text-right p-t-10 p-b-10"><i class="btn-delete mdi mdi-delete" data-toggle="tooltip" title="Delete " data-placement="top"></i></div>' +
		'</div>';
    
    var scale = 
		'<div class="form-container m-b-10 p-30 p-b-0" style="border: 1px solid #eee">' +
			'<span class="numbers"></span>' +
			'<input type="text" class="form-control m-b-5 question" data-type="scale" placeholder="Question" />' +
			
			'<div class="option-container text-center p-10">' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Strongly Agree' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Agree' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Neutral' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Disagree' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i> Strongly Disagree' +
	    	'</div>' +
			
			'<div class="text-right p-t-10 p-b-10"><i class="btn-delete mdi mdi-delete" data-toggle="tooltip" title="Delete " data-placement="top"></i></div>' +
		'</div>';
    
    var radiobutton = 
		'<div class="form-container m-b-10 p-30" style="border: 1px solid #eee">' +
			'<span class="numbers"></span>' +
			'<input type="text" class="form-control m-b-5 question"  data-type="radiobutton" placeholder="Question" />' +
	    	
			'<div class="option-container">' +
				'<div class="item">' +
		    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>' +
		    	'<input type="text" class="form-control optionName" placeholder="Option">' +
		    	'</div>' +
	    	'</div>' +
	    	
	    	'<a href="javascript:void(0)" class="btn-add-option">' + 
				'<i class="mdi mdi-plus"></i> Add Option' +
			'</a>' +
			
			'<div class="text-right p-t-10 p-b-10"><i class="btn-delete mdi mdi-delete" data-toggle="tooltip" title="Delete " data-placement="top"></i></div>' +
		'</div>';
    
    var radiobuttonOption =
		'<div class="item">' +
	    	'<i class="mdi mdi-radiobox-blank text-info m-l-30"></i>' +
	    	'<input type="text" class="form-control optionName" placeholder="Option">' +
			'<i class="btn-remove mdi mdi-close text-muted m-l-20" data-toggle="tooltip" title="Remove" data-placement="right"></i>' +
		'</div>' ;
	
    var checkbox = 
		'<div class="form-container m-b-10 p-30" style="border: 1px solid #eee">' +
		'<span class="numbers"></span>' +
			'<input type="text" class="form-control m-b-5 question" data-type="checkbox" placeholder="Question" />' +
	    	
			'<div class="option-container">' +
				'<div class="item">' +
		    	'<i class="mdi mdi-checkbox-blank-outline text-info m-l-30"></i>' +
		    	'<input type="text" class="form-control optionName" placeholder="Option">' +
		    	'</div>' +
	    	'</div>' +
	    	
	    	'<a href="javascript:void(0)" class="btn-add-optionChkbox">' + 
				'<i class="mdi mdi-plus"></i> Add Option' +
			'</a>' +
			
			'<div class="text-right p-t-10 p-b-10"><i class="btn-delete mdi mdi-delete" data-toggle="tooltip" title="Delete " data-placement="top"></i></div>' +
		'</div>';
    
    var checkboxOption =
		'<div class="item">' +
	    	'<i class="mdi mdi-checkbox-blank-outline text-info m-l-30"></i>' +
	    	'<input type="text" class="form-control optionName" placeholder="Option">' +
			'<i class="btn-remove mdi mdi-close text-muted m-l-20" data-toggle="tooltip" title="Remove" data-placement="right"></i>' +
		'</div>' ;
	
    
    //turn to inline mode
	$.fn.editable.defaults.mode = 'inline';
	    
	// Textbox
    $('.btn-add-textbox').click(function(){
    	$('.controls-container').append(textbox);
    	$('[data-toggle=tooltip]').tooltip();
    	resetQuestionNumbers();
    });
    
	// Scale
    $('.btn-add-scale').click(function(){
    	$('.controls-container').append(scale);
    	$('[data-toggle=tooltip]').tooltip();
    	resetQuestionNumbers();
    });
    
	// RadioButton    
    $('.btn-add-rbtn').click(function(){
    	$('.controls-container').append(radiobutton);
    	$('[data-toggle=tooltip]').tooltip();
    	resetQuestionNumbers();
    });
    
    $(document).on('click', '.btn-add-option', function(){
		$(this).parent().find('.option-container').append(radiobuttonOption);
		$('[data-toggle=tooltip]').tooltip();
		resetQuestionNumbers();
    });
    
	// Checkbox
    $('.btn-add-checkbox').click(function(){    	
    	$('.controls-container').append(checkbox);
    	$('[data-toggle=tooltip]').tooltip();
    	resetQuestionNumbers();
    });
    
    $(document).on('click', '.btn-add-optionChkbox', function(){
		$(this).parent().find('.option-container').append(checkboxOption);
		$('[data-toggle=tooltip]').tooltip();
		resetQuestionNumbers();
    });
    
	// Container Active State
    $(document).on('click', '.form-container', function(){
    	$('.form-container').removeClass('active-container');
    	$(this).addClass('active-container');
    });
    
	// Remove Option
    $(document).on('click', '.btn-remove', function(){
    	$(this).parent().remove();
    });
    
    // Remove Control
    $(document).on('click', '.btn-delete', function(){
    	$(this).parents('.form-container').remove();
    	resetQuestionNumbers();
    });
    
    
    // Set / Reset Numbering
    function resetQuestionNumbers() {
    	var count = 1;
    	
    	$('.numbers').each(function(){
    		$(this).text(count++);
    	});
    }
    
    // Remove has-error class
    $(document).on('change', '.has-error .form-control', function(){
    	$(this).parents('.has-error').removeClass('has-error');
    });
    
    // Save Question
    $("#save").click(function(){
    	// Check for empty fields
    	var hash = [];
    	$('input').each(function(){
    		if($.trim($(this).val()).length == 0) {
    			$(this).parents('.form-container').addClass('has-error');
    			hash.push($(this));
    		}
    	});
    	
    	if(hash.length > 0) {
			// animate
	       $('html, body').animate({
	           scrollTop: $(hash[0]).offset().top - 100
	       }, 300);
	       
	       return false
		}
    	
    	$("#ajax-process").modal('show');
    	var formType = $('[name=formType]').val();
    	var forms = [];
    	
    	$('.form-container').each(function(){
    		var type = $(this).find('.question').attr('data-type'); 
    		var options = [];
    		
    		if(type == 'radiobutton' || type == 'checkbox') {
    			$(this).find('.optionName').each(function(){
    				options.push({
    					id: $(this).attr('data-id'),
    					description: $(this).val()
    				});
    			});
    		}  
    		
    		forms.push({
				question: $(this).find('.question').val(),
    			type: type,
    			option: options,
    			formID: formType,
    			id: $(this).find('.question-id').val()
    		});
    	});    	

    	$.ajax({
			url: "/ates/forms/questions/" + formType,
			type: 'POST',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data: JSON.stringify(forms),
			success: function(data, textStatus, jqXHR) {
				$("#ajax-process").modal('hide');
				if(data == true) {
	           	 	swal({   
						title: "Success!",
						type: "success",
						text: "We have successfully saved your Form",
						confirmButtonColor: "#1e88e5",
				        showConfirmButton: true,
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