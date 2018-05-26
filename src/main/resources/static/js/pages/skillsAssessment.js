$(document).ready(function() {
	var trainingPlanObject = {};
	
	$('#skillsAssessment').DataTable();
	
	// Nestable
	var updateOutput = function(e) {
	    var list = e.length ? e : $(e.target),
	        output = list.data('output');
	    if (window.JSON) {
	        output.val(window.JSON.stringify(list.nestable('serialize'))); //, null, 2));
	    } else {
	        output.val('JSON browser support required for this demo.');
	    }
	};
	$('#nestable2').nestable({
	    group: 1
	}).on('change', updateOutput);
	updateOutput($('#nestable2').data('output', $('#nestable2-output')));
	$('#nestable2').on('click', function(e) {
	    var target = $(e.target),
	        action = target.data('action');
	    if (action === 'expand-all') {
	        $('.dd').nestable('expandAll');
	    }
	    if (action === 'collapse-all') {
	        $('.dd').nestable('collapseAll');
	    }
	});
	
	// Initialize editable
	var ids = [];
	$('.dd3-content').each(function () {
		var id = $(this).parents('.dd3-item').attr('data-id');		
		$('#id-' + id).editable({emptytext: 'This is empty. Click to change text.'});
		
		ids.push(id);
	});
	
	// Add item to nestable
	var counter = ids.length > 0 ? Math.max.apply(Math, ids) : 1;
	$('.btn-add-item').on('click', function(){
		var nestableList = '<li class="dd-item dd3-item" data-id="' + (++counter) + '">' +
	        '<div class="dd-handle dd3-handle"></div>' +
	        '<div class="dd3-content" id="id-' + counter + '"></div>' +
	        '<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">'+
	        	'<i class="mdi mdi-close"></i></button>' +
        	'</li>';
		
		$('.dd-list:first').append(nestableList);
		$('#id-' + counter).editable({emptytext: 'This is empty. Click to change text.'});
		
		if(!$('.help-block-outline').hasClass('hide')) {
			$('.help-block-outline').addClass('hide');
		}
	})
	
	// Remove item to nestable
	$(document).on('click', '.btn-remove-item', function(){
		var parent = $(this).parent();
			parent.addClass('zoomOut animated');
			setTimeout(function(){
				parent.remove();
			}, 500);
	});
	
	
	//turn to inline mode
	$.fn.editable.defaults.mode = 'popup';
	
	function addContent(items, order){
		$.each(items, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			value['order'] = order++;
			if ('children' in value){
				addContent(value.children, order);
			}
		});
	}
	
	$('#save').click(function(){
		var item = $('#nestable2').nestable('serialize');
		var order = 1;
		$.each(item, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			value['order'] = order++;
			if ('children' in value){
				addContent(value.children, order);
			}
		});
		
		$.ajax({
			url: "/ates/forms/skills-assessment",
			type: 'POST',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data: JSON.stringify(item),
			success: function(data, textStatus, jqXHR) {
				 swal({   
                     title: "Success!",
                     type: "success",
                     text: "We have successfully edited the Skills Assessment Form",
                     confirmButtonColor: "#1e88e5",
                     showConfirmButton: true,
            	 });
            },
            error: function(jqXHR, status, error) {
            	showErrorAlert();
            }
         });
	});
	
	$('.btn-clear').click(function(){
		$('.btn-remove-item').trigger('click');
	});
	
});