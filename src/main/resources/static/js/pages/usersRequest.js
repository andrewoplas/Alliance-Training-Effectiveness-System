
(function ($) {
    "use strict";
    
    
    var table = $("#pending-users-table").DataTable();
    
    $(document).on('click', '.btn-accept', function(){
    	var elem = $(this).parents('tr');
    	var id = elem.attr('data-id');
    	var name = elem.find('.name').text();
    	
    	$.ajax({
            url: "/ates/users/approve",
            type: 'POST',
            data: {id: id},
            success: function(data, textStatus, jqXHR) {
            	if(data == true) {
	            	var row = table.row(elem).remove().draw();
	            	
	            	// Alert Position Bottom Left
	            	$(".myadmin-alert").fadeOut(100);
	            	$("#alert-approve").find('.name').text(name);
	                $("#alert-approve").fadeToggle(350);
            	} else {
            		showErrorAlert();
            	}
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
        });
    });
    
    $(document).on('click', '.btn-decline', function(){
    	var elem = $(this).parents('tr');
    	var id = elem.attr('data-id');
    	var name = elem.find('.name').text();
    	
    	$.ajax({
            url: "/ates/users/decline",
            type: 'POST',
            data: {id: id},
            success: function(data, textStatus, jqXHR) {
            	if(data == true) {
	            	elem.addClass("fadeOut animated");
	            	
	            	setTimeout(function(){
	            		var row = table.row(elem).remove().draw();
	            	}, 500);
	            	
	            	// Alert Position Bottom Left
	            	$(".myadmin-alert").fadeOut(100);
	            	$("#alert-decline").find('.name').text(name);
	                $("#alert-decline").fadeToggle(350);
            	} else {
            		showErrorAlert();
            	}
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
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