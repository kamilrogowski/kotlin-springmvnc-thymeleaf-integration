$(document).ready(function() {

	setFilterGrid("usersTable");

	$('#flt0_usersTable').keyup(hideAndshow);
	$('#flt1_usersTable').keyup(hideAndshow);
	$('#flt2_usersTable').keyup(hideAndshow);
	$('#flt3_usersTable').keyup(hideAndshow);
	$('#flt4_usersTable').keyup(hideAndshow);
	$('#flt5_usersTable').change(hideAndshow);
	$('#flt6_usersTable').keyup(hideAndshow);
	$('#flt7_usersTable').keyup(hideAndshow);

	paginator(1);

	$('.fltrow').children().last().append($('#addButton'));

	$(".glyphicon").hover(function() {
		$(this).tooltip('show');
	});

	$("#usersTable").tablesorter({
		headers : {
			7 : {
				sorter : false
			}
		}
	});
}

);
function hideAndshow() {
	$('.selector').hide();
	$('.pageCountPanel').hide();
	if ($('#flt0_usersTable').val().length === 0
			&& $('#flt0_usersTable').val().length === 0
			&& $('#flt1_usersTable').val().length === 0
			&& $('#flt2_usersTable').val().length === 0
			&& $('#flt3_usersTable').val().length === 0
			&& $('#flt4_usersTable').val().length === 0
			&& $('#flt5_usersTable').val().length === 0
			&& $('#flt6_usersTable').val().length === 0
			&& $('#flt7_usersTable').val().length === 0) {
		$('.selector').show();
		$('.pageCountPanel').show();
		paginator(1);
	}
}
