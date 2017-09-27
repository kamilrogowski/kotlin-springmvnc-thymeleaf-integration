$(document).ready(function() {
   $('#createCommentModal').on('hidden.bs.modal', function () {
       $("#createCommentForm").trigger('reset');
       $('#commentFormError').css('display','none');
   });
});

function saveComment() {
    test = jQuery.ajax("/resource-manager-web/save_new_comment", {
        type: "POST",
        data: {
            title:$("#title").val(), content:$("#content").val()
        },
        success:function(data) {
            var success = $(data).find('.bg-success');
            var error = $(data).find('.bg-danger');
            if (success.length > 0) {
                $('.page-container').prepend("<p class='bg-success' style='text-align: center;'>" + success.text() + "</p>");
                $('#createCommentModal').modal('hide');
            }
            else {
                $('#commentFormError').text(error.text());
                $('#commentFormError').css('display','block');
            }
        }
    });
}