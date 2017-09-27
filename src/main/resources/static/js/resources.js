$(document).ready(function() {

    setFilterGrid("resourceTable");

    // Konfiguracja sortowania:
    if ($('#flt7_resourceTable').length > 0) {
        $("#resourceTable").tablesorter({
            headers : {
                0 : { sorter : false },
                7 : { sorter : false }
            },
            sortList: [[3,0]] // Sortowanie po czwartej kolumnie, ASC;
        });
    }
    else {
        $("#resourceTable").tablesorter({
            headers : {
                0 : { sorter : false },
                6 : { sorter : false }
            },
            sortList: [[3,0]] // Sortowanie po czwartej kolumnie, ASC;
        });
    }

    $('#flt1_resourceTable').change(hideAndshow);
    $('#flt2_resourceTable').change(hideAndshow);
    $('#flt3_resourceTable').change(hideAndshow);
    $('#flt4_resourceTable').change(hideAndshow);
    $('#flt5_resourceTable').change(hideAndshow);

    paginator(2);

    $('.fltrow').children().last().append($('#addButton'));

    $(".glyphicon").hover(function() {
        $(this).tooltip('show');
    });

    $(".comarch-tooltip").hover(function() {
        $(this).tooltip('show');
    });

    $("#transferSelect").select2(); // Select2 do wybrania nowego właściciela;
});

function hideAndshow() {
    $('.selector').hide();
    $('.pageCountPanel').hide();
    if ($('#flt1_resourceTable').val().length === 0
            && $('#flt2_resourceTable').val().length === 0
            && $('#flt5_resourceTable').val().length === 0
            && $('#flt4_resourceTable').val().length === 0
            && $('#flt3_resourceTable').val().length === 0) {
        $('.selector').show();
        $('.pageCountPanel').show();
        paginator(2);
    }
}

function showPopup() {
    $('.selector').show();
}

function showTransferModal(resourceId, userId, resourceName, ownerIdentity) {
    $('#modalForm').trigger('reset');
    $('#transferModalResource').text(resourceName);
    $('#transferModalUser').text(ownerIdentity);
    $('#resourceId').val(resourceId);
    $('select option').each(function() {
        if ($(this).val() == userId) {
            $(this).attr("disabled", "disabled");
            $(this).attr("hidden", "hidden");
        }
        else {
            $(this).removeAttr("disabled");
            $(this).removeAttr("hidden");
        }
    });
    $('#transferModal').modal('show');

    // Disable przycisku submit dla wybranego prompt:
    $('#transferSelect').change(function() {
        if ($(this).val() == 0) { // wybrany prompt:
            $('#transferButton').attr("disabled", "disabled");
        }
        else { // wybrany user:
            $('#transferButton').removeAttr("disabled");
        }
    });
}

function showUserModal(id) {
    identity = $('#' + id + "identity").val();
    email = $('#' + id + "email").val();
    phone = $('#' + id + "phone").val();
    room = $('#' + id + "room").val();
    $('#modalBody').empty();
    $('#modalBody').append("<p><dl class='dl-horizontal'><dt>Adres e-mail: </dt><dd>"
            + email + "</dd><dt>Nr telefonu: </dt><dd>" + phone + "</dd><dt>Nr pokoju: </dt><dd>"
            + room + "</dd></dl></p>");
    $('#myModalLabel2').text(identity);
    $('#myModal2').modal('show');
}
