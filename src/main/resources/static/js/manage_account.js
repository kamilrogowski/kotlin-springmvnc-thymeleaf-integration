$(document).ready(function() {
    $.getJSON("historyCountREST").done(function(data) {
        howMuchRows = data;
        if(howMuchRows == 0) {
            $("#historyPanel").hide();
        }
    });
    showHistory();
    $(".comarch-tooltip").hover(function() {
          $(this).tooltip('show');
     });
});

var howMuchPerClick = 5;
var howMuchRows = 0;
function showMore() {
    howMuchPerClick = howMuchPerClick + 5;
    showHistory();
    $('html, body').animate({scrollTop : $(document).height()}, 'fast');
}

function showHistory() {
    var historyAPI = "historyREST";
    var scriptParams = document.getElementById('manage_script');
    var message = scriptParams.getAttribute('tooltip-text');
    $.getJSON(historyAPI, {howMuch: howMuchPerClick}).done(function(data) {
        var i = 1;
        for (var item in data) {
            if (i > $('.removeClass').length) {
                var image;
                if (data[item].image == "nophoto") {
                    image = "<td><img class='image-list' src='resources/img/default.jpg'></img></td>";
                }
                else {
                    image = "<td><img class='image-list' src=" + data[item].image + "></img></td>";
                }

                $("#tableRow").append("<tr id='tableRow' class='removeClass'>" +
                        image +
                        "<td>" + data[item].comarchID + "</td>" +
                        "<td>" +
                        "<form action='resourcedetails' method='post'>" +
                        "<input type='hidden' name='id' value=" + data[item].id + " />" +
                        "<a class='header' onclick='this.parentNode.submit()' data-toggle='tooltip'  data-placement='left' title=" + message + ">" + data[item].name + "</a></form>" +
                        "</td>" +
                        "<td>" + data[item].transferData + "</td>" +
                        "<td>" + data[item].transferOwner + "</td>" +
                        "<td>" + data[item].oldOwner + "</td>" +
                        "<td>" + data[item].newOwner + "</td>" +
                        "</tr>");
            }
            i++;
        }

        if (i > howMuchRows) {
            $("#moreButton").hide();
        }

        $(".header").hover(function() {
              $(this).tooltip('show');
         });
    });
}

function showModal(resourceId, userId) {
    $('#modalForm').trigger('reset');
    $('#resourceId').val(resourceId);
    $('select option').each(function() {
        if ($(this).val() == userId) {
            $(this).remove();
        }
    });
    $('#myModal').modal('show');
}
