$(document).ready(function() {

	if($('tr').length > 2) {

		setFilterGrid("commentsTable");

		$("#commentsTable").tablesorter({
			headers : {
				0 : {
					sorter : false
				}
			}
		});

	}

	$(".glyphicon").hover(function() {
		  $(this).tooltip('show');
	 });

	$(".comarch-tooltip").hover(function() {
		  $(this).tooltip('show');
	 });
});

function selectNone() {
	 $("input[type=checkbox]").removeProp("checked");
}

function selectAll() {
	$("input[type=checkbox]").each(function() {
		if ($(this).parent().parent().css( "display" ) != "none" ) {
			$(this).prop("checked", "checked");
		}
	});
}

function showModal(id) {
	element = $('#' + id);
	$('#displayCommentModalBody').empty();
	$('#displayCommentModalBody').append('<p>' + element.attr('value') + '</p>');
	$('#displayCommentModalLabel').text(element.attr('title'));
	$('#displayCommentModal').modal('show');
}