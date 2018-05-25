$(document).ready(function() {
	// Multi-select (Semantic) Configuration
	$('.multi-select').dropdown({
		maxSelections: 5
	});
	
	$('#submit').click(function(){
		var rows = [];
		
		$('table .advance-table-row').each(function(){
			var id = $(this).attr('data-id');
			var peersArr = $('#' + id + '-peer').dropdown('get value');
			var supervisorsArr = $('#' + id + '-supervisor').dropdown('get value');
			var user = {
				peers: peersArr == null? [] : peersArr,
				supervisors: supervisorsArr == null? [] : supervisorsArr,
				user: id,
			}
			rows.push(user);
		});
		
		var trainingID = $('[name=id]').val();
		
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