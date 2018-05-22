$(document).ready(function() {
	// Setup Nestable Data
	var nestableIDS = [];
	var nestableData = $('.nestable-serialized').val();
	nestableData = JSON.parse(nestableData);
	
	var counter = 0;
	var nestableDataHTML = $('<ol class="dd-list"></ol>');
	$.each(nestableData, function(index, value){
		if(counter < value.id) counter = value.id;
		nestableDataHTML.append(
				'<li class="dd-item dd3-item" data-id="' + value.id + '">' +
				'<div class="dd-handle dd3-handle"></div>' +
				'<div class="dd3-content" id="id-' + value.id + '">' + value.content + '</div>' + 
				'<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">'+
	        	'<i class="mdi mdi-close"></i></button>' +
				'</li>'
			);
		nestableIDS.push(value.id);
		if ('children' in value){
			nestableDataHTML.find('li:last').append(addDataHTML(value.children));
		}
	});
	$('#nestable2').html(nestableDataHTML);
	
	function addDataHTML(items){
		var parent = $('<ol class="dd-list"></ol>');
		$.each(items, function(index, value){
			if(counter < value.id) counter = value.id;
			parent.append(
					'<li class="dd-item dd3-item" data-id="' + value.id + '">' +
					'<div class="dd-handle dd3-handle"></div>' +
					'<div class="dd3-content" id="id-' + value.id + '">' + value.content + '</div>' + 
					'<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">'+
		        	'<i class="mdi mdi-close"></i></button>' +
					'</li>'
				);
			nestableIDS.push(value.id);
			if ('children' in value){
				parent.find('li:last').append(addDataHTML(value.children));
			}
		});
		
		return parent;
	}
	
		
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
	
	// Add item to nestable
	counter = counter + 1;
	$('.btn-add-item').on('click', function(){
		var nestableList = '<li class="dd-item dd3-item" data-id="' + counter + '">' +
	        '<div class="dd-handle dd3-handle"></div>' +
	        '<div class="dd3-content" id="id-' + counter + '">New Item</div>' +
	        '<button type="button" class="btn-remove-item btn btn-danger btn-outline btn-circle">'+
	        	'<i class="mdi mdi-close"></i></button>' +
        	'</li>';
		
		$('.dd-list:first').append(nestableList);
		$('#id-' + counter).editable();
		
		counter++;
		
		if(!$('.help-block-outline').hasClass('hide')) {
			$('.help-block-outline').addClass('hide');
		}
	})
	

	for(var i=0; i<nestableIDS.length; i++) {
		$('#id-' + nestableIDS[i]).editable();
	}
	
	// Initialize editable
	$('#id-0').editable();
	
	
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
	
	// Validation First Fieldset
	$('.first-fieldset .validate-empty, .second-fieldset .validate-empty').blur(function(){
		if($.trim($(this).val()).length == 0) {
			$(this).parents('.form-group').addClass('has-error');
			$(this).parents('.form-group').find('.help-block').removeClass('hide');
		} else {
			$(this).parents('.form-group').removeClass('has-error');
			$(this).parents('.form-group').find('.help-block').addClass('hide');
		}
	})
	
	// Gather Summary Information
	$('.submit').on('click', function(){
		var id = $('.training-id').val();
		var userEventId = $('.userEvent-id').val();
		var description = $('#description').val();				
		var item = $('#nestable2').nestable('serialize');
		$.each(item, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
		
		$.ajax({
			url: "/ates/general/training/edit",
			type: 'POST',
			data: {
				description: description,
				outline: JSON.stringify(item),
				id: id,
				userEventId: userEventId
			},
			success: function(data, textStatus, jqXHR) {
                 if(data == true) {
                	 swal({   
                         title: "Success!",
                         type: "success",
                         text: "We have successfully edited the training plan",
                         confirmButtonColor: "#1e88e5",
                         showConfirmButton: true,
                	 }, function(isConfirm){   
                         if (isConfirm) {     
                        	 window.location.href = "/ates/general/training/list";   
                         } 
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
	
	function addContent(items){
		$.each(items, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
	}
	
	function addContentHTML(items){
		var parent = $("<ol></ol>");
		$.each(items, function(index, value){
			parent.append('<li>' + value.content + '</li>');
			if ('children' in value){
				parent.find('li:last').append(addContentHTML(value.children));
			}
		});
		
		return parent;
	}	
});