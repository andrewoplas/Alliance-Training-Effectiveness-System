
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
	
    
    $(document).on('click', '.btn-remove', function(){
    	var elem = $(this).parents('tr');
    	var id = elem.attr('data-id');
    	var name = elem.find('.name').text();
    	
    	$.ajax({
            url: "/ates/users/remove",
            type: 'POST',
            data: {id: id},
            success: function(data, textStatus, jqXHR) {
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
            	
//            	// Alert Position Bottom Left
//            	$(".myadmin-alert").fadeOut(100);
//            	$("#alert-decline").find('.name').text(name);
//                $("#alert-decline").fadeToggle(350);
            },
            error: function(jqXHR, status, error) { console.log(status + ": " + error); }
        });
    	
    });
    
    //Alerts
    $(".myadmin-alert .closed").click(function(event) {
        $(this).parents(".myadmin-alert").fadeToggle(350);
        return false;
    });
    /* Click to close */
    $(".myadmin-alert-click").click(function(event) {
        $(this).fadeToggle(350);
        return false;
    });
	
})(jQuery);