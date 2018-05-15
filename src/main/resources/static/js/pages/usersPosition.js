(function ($) {
    "use strict";

    var table = $("#position-table").DataTable();
    
    [].slice.call(document.querySelectorAll('.sttabs')).forEach(function(el) {
        new CBPFWTabs(el);
    });
    
    $("#add-position-form").on('submit', function(e){    
    	var formData = $(this).serialize();
    	var name = $(this).find('[name=position]').val();
    	
    	$.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: formData,
            success: function(data, textStatus, jqXHR) {
            	console.log(data);
            	data = data.split(':');
            	
                if(data[0] == 'success') {
                	// Alert Position Bottom Left
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-approve").find('.name').text(name);
                    $("#alert-approve").fadeToggle(350);
                	
                    // Draw to table and select
                	$("#old-position").append('<option value="' + data[1] + '">' + name + '</option>');
                	table.row.add(
                			[name, '<button type="button" class="btn-delete btn btn-danger btn-outline btn-circle p-t-0 p-b-0"><i class="mdi mdi-delete"></i></button>'])
                			.draw().nodes().to$().addClass( 'new' ).find('td:first').addClass('description');
                	$("#position-table").find('.new').attr('data-id', data[1]);
                	$("#position-table").find('.new').removeClass('new');
                	                	
                	$("#add-position-form")[0].reset();
                } else if(data[0] == 'already_exists'){
                	// Alert Position Bottom Left
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-error").find('.name').text("Position description already exists!");
                    $("#alert-error").fadeToggle(350);
                } else {
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-error").find('.name').text("An error occur during the creation.");
                    $("#alert-error").fadeToggle(350);
                }
            }
        });
    	
    	e.preventDefault();
    });
    
    $("#edit-position-form").on('submit', function(e){    
    	var formData = $(this).serialize();
    	var id = $(this).find('[name=old-position]').val();
    	var old_name = $(this).find('[name=old-position] option:selected').text();
    	var name = $(this).find('[name=new-position]').val();
    	
    	$.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            data: formData,
            success: function(data, textStatus, jqXHR) {
                if(data == 'success') {
                	// Alert Position Bottom Left
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-edit").find('.name').text(old_name);
                	$("#alert-edit").find('.new-name').text(name);
                    $("#alert-edit").fadeToggle(350);
                    
                    
                    // Set table and selected a new name
                    $("#position-table").find('[data-id=' + id + ']').find('.description').text(name);
                    $("#edit-position-form").find('[name=old-position] option:selected').text(name);
                    
                	$("#add-position-form")[0].reset();
                } else if(data == 'already_exists'){
                	// Alert Position Bottom Left
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-error").find('.name').text("Position description already exists!");
                    $("#alert-error").fadeToggle(350);
                } else {
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-error").find('.name').text("An error occur during the creation.");
                    $("#alert-error").fadeToggle(350);
                }
            }
        });
    	
    	e.preventDefault();
    });
    
    $(document).on('click', '.btn-delete', function(e){    
    	var elem = $(this).parents('tr');
    	var id = elem.attr('data-id');
    	
    	$.ajax({
            url: '/ates/users/position/remove',
            type: 'POST',
            data: {id: id},
            success: function(data, textStatus, jqXHR) {
                if(data == 'success') {
                	elem.addClass("fadeOut animated");
                	
                	setTimeout(function(){
                		var row = table.row(elem).remove().draw();
                	}, 500);
                } else {
                	$(".myadmin-alert").fadeOut(100);
                	$("#alert-error").find('.name').text("Someone is still using the position. Can't delete it");
                    $("#alert-error").fadeToggle(350);
                }
            }
        });
    	
    	e.preventDefault();
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