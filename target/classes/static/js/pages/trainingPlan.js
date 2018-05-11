$(document).ready(function() {
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
	
	var counter = 1;
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
	
	$('#id-0').editable();
	
	$(document).on('click', '.btn-remove-item', function(){
		var parent = $(this).parents('li');
			parent.addClass('zoomOut animated');
			setTimeout(function(){
				parent.remove();
			}, 500);
	});
	
	$("#nice").on('click', function(){
		var item = $('#nestable2').nestable('serialize');
		
		$.each(item, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
		
		console.log(item);
	});
	
	function addContent(items){
		$.each(items, function(index, value){
			value['content'] = $('[data-id=' + value.id + ']').find('.dd3-content').first().text();
			if ('children' in value){
				addContent(value.children);
			}
		});
	}
	
	//turn to inline mode
	$.fn.editable.defaults.mode = 'popup';
	
    
//	var dateOutputField;
//	var dateStart = $('#date_start');
//	var dateEnd = $('#date_end');
//	
//	var picker = new MaterialDatetimePicker().on('submit', function(d) {
//		dateOutputField.val(d.format('MMMM DD, YYYY'));
//		
//		if(dateStart.val() != '' && dateEnd.val() != ''){
//			var dateStartObj = moment(dateStart.val(), 'MMMM DD, YYYY');
//			var dateEndObj = moment(dateEnd.val(), 'MMMM DD, YYYY');
//			
//			
//			if(dateStart.attr('id') === dateOutputField.attr('id') && dateStartObj.isAfter(dateEndObj)) {
//				dateOutputField.parents('.form-group').addClass('has-error')
//					.find('.help-block-date').removeClass('hide');
//			} else if(dateEnd.attr('id') === dateOutputField.attr('id') && dateEndObj.isBefore(dateStartObj)) {
//				dateOutputField.parents('.form-group').addClass('has-error')
//					.find('.help-block-date').removeClass('hide');
//			} else {
//				dateStart.parents('.form-group').removeClass('has-error')
//					.find('.help-block-date').addClass('hide');
//				dateEnd.parents('.form-group').removeClass('has-error')
//					.find('.help-block-date').addClass('hide');
//			}
//		}
//	}).on('close', function() {
//		if($.trim(dateOutputField.val()).length == 0) {
//			dateOutputField.parents('.form-group').addClass('has-error');
//			dateOutputField.parents('.form-group').find('.help-block-empty').removeClass('hide');
//			$('#first-step').addClass('disabled');
//		} else {
//			if(!dateOutputField.parents('.form-group').find('.help-block-date').hasClass('hide')) {
//				dateOutputField.parents('.form-group').removeClass('has-error');
//			}
//			dateOutputField.parents('.form-group').find('.help-block-empty').addClass('hide');
//		}
//	});
//	
//	$('#date_start, #date_end').click(function(){
//		picker.open();
//		dateOutputField = $(this);
//	})
//	
//	
	// Validation First Fieldset
	$('.first-fieldset .validate-empty').blur(function(){
		if($.trim($(this).val()).length == 0) {
			$(this).parents('.form-group').addClass('has-error');
			$(this).parents('.form-group').find('.help-block').removeClass('hide');
		} else {
			$(this).parents('.form-group').removeClass('has-error');
			$(this).parents('.form-group').find('.help-block').addClass('hide');
		}
	})	
	
	$('.multi-select').dropdown({
	    onAdd: function(value, text, $selectedItem) {
	    	$('.multi-select .menu').find('[data-value='+ value +']').hide();
	    	
	    	$(this).parents('.panel').find('.help-block').addClass('hide');
	    },
	    onRemove: function(value, text, $selectedItem) {
	    	$('.multi-select .menu').find('[data-value='+ value +']').show();
	    },
	    onLabelRemove: function(e) {
	    	$(this).tooltip('destroy');
	    	
            return !0
        },
	    onLabelCreate: function(t, n) {
	    	var value = $(this).attr('data-value');
	    	var option = $('.multi-select select').find('[value='+value+']');
	    	var email = option.attr('data-email');
	    	
	    	$(this).attr('data-toggle', 'tooltip');
	    	$(this).attr('data-placement', 'top');
	    	$(this).attr('title', email);
	    	$(this).tooltip();
	    	
	    	return $(this);
        },
	  });
	
	
});