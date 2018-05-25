	$('select').change(function(){
		$(this).parent().removeClass('has-error');
		$(this).prev().find('i').removeClass('hide');
	});
	
	$('.btn-submit').click(function(){
		var hash = [];
		
		$('.form-control').each(function () {
			if($(this).val() == null) {
				$(this).parent().addClass('has-error');
				hash.push($(this).parent().attr('id'));
			}
		});
		
		if(hash.length > 0) {
			var moreOffset = 100;
			
			$('html, body').animate({
				scrollTop: $('#' + hash[0]).offset().top - 200
			}, 300);
			
			return false;
		}
		
		var object = {
				categoryID : [],
				answers : [],
				assignmentID: $('[name=id]').val()
			}
		
		$('.form-control').each(function () {			
			object.categoryID.push($(this).attr('data-id'));
			object.answers.push($(this).val());
		});
		
		$("#ajax-process").modal('show');
		
		var name = $.trim($('.user-name').text());
		
		$.ajax({
			url: "/ates/general/training/skills-assessment/answer/",
			type: 'POST',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data: JSON.stringify(object),
			success: function(data, textStatus, jqXHR) {
				$("#ajax-process").modal('hide');
                 if(data == true) {
                	 swal({   
                         title: "Success!",
                         type: "success",
                         text: "We have successfully submitted your Skills Assessment Form for " + name,
                         confirmButtonColor: "#1e88e5",
                         showConfirmButton: true,
                	 });               	 
                 } else {
                	 showErrorAlert();
                 }
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });
	});
	
	