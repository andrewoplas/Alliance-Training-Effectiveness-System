
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
	
})(jQuery);