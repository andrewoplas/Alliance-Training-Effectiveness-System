(function ($) {
    "use strict";

    $('#all-users-table').footable({
		"filtering": {
			"enabled": true
		}
	});

	// Pagination
	// -----------------------------------------------------------------
	$('#show-entries').change(function (e) {
		e.preventDefault();
		var pageSize = $(this).val();
		$('#all-users-table').data('page-size', pageSize);
		$('#all-users-table').trigger('footable_initialized');
	});
	
	$(document).on('click', '.btn-danger', function(){
		var elem = $(this).parents('tr');
		var id = elem.attr('data-id');
		var name = elem.find('td:first').text();
		
		swal({   
            title: "Are you sure?",   
            text: "You will not be able to recover " + name + "!",   
            type: "warning",   
            showCancelButton: true,   
            confirmButtonColor: "#f44336",
            confirmButtonText: "Delete",   
            cancelButtonText: "Cancel",   
            closeOnConfirm: false,   
        }, function(isConfirm){   
            if (isConfirm) {            	
            	$.ajax({
        			url: "/ates/training/delete",
        			type: 'POST',
        			data: {id: id},
        			success: function(data, textStatus, jqXHR) {
        				$("#ajax-process").modal('hide');
                         if(data == true) {
                        	 
                        	 if(elem.next().hasClass('footable-row-detail')){
                         		elem.next().addClass('fadeOut animated');
                         		setTimeout(function(){
                         			elem.next().remove();
                     			}, 500);
                         	}
                         	
                         	elem.addClass('fadeOut animated');
                         	setTimeout(function(){
                 				elem.remove();
                 			}, 500);
                        	 
                        	 
                        	 swal("Deleted!", "Training '" + name + "' is successfully deleted!", "success");
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
	
	$('table tfoot tr td div').append(
		'<a href="/ates/training/list/calendar">' +
			'<button class="m-t-20 btn btn-info btn-raised btn-fix waves-effect waves-light pull-left">' +
				'View Calendar' +
			'</button>' +
		'</a>'
	);	
})(jQuery);