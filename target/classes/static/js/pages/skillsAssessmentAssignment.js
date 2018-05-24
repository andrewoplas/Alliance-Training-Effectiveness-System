$(document).ready(function() {
	// Multi-select (Semantic) Configuration
	$('.multi-select').dropdown({
		maxSelections: 5
	});
	
	$('#submit').click(function(){
		var rows = [];
		
		$('table .advance-table-row').each(function(){
			var id = $(this).attr('data-id');
			var user = {
				peers: $('#' + id + '-peer').dropdown('get value'),
				supervisors: $('#' + id + '-supervisor').dropdown('get value'),
				user: id,
			}
			rows.push(user);
		});
		
		var trainingID = $('[name=id]').val();
		console.log(rows);
		$.ajax({
			url: "/ates/general/training/skills-assessment/" + trainingID,
			type: 'POST',
			contentType : 'application/json; charset=utf-8',
			dataType : 'json',
			data: JSON.stringify(rows),
			success: function(data, textStatus, jqXHR) {
                 if(data == true) {
                	 swal({   
                         title: "Success!",
                         type: "success",
                         text: "We have successfully assigned the Skills Assessment Forms to designated participants and peers.",
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
});