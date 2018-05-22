
(function ($) {
    "use strict";
    
    if($.trim($("[name=id]").val()).length == 0) {
    	swal({   
            title: "Oppps!",
            type: "error",
            text: "The training that you are trying to access does not exists. You will be redirect to training list page.",
            confirmButtonColor: "#1e88e5",
            showConfirmButton: false,
            timer: 3000
   	 	}, function(){
   	 		window.location.replace("/ates/training/list");
   	 	});
    }

    var nestableData = $('.nestable-serialized').val();
    	nestableData = JSON.parse(nestableData);
    
    var courseOutlineHTML = $('<ol id="parent-outline"></ol>');
	$.each(nestableData, function(index, value){
		courseOutlineHTML.append('<li>' + value.content + '</li>')
		if ('children' in value){
			courseOutlineHTML.find('li:last').append(addContentHTML(value.children));
		}
	});
	$('.outline-body').html(courseOutlineHTML);
    
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
	
})(jQuery);