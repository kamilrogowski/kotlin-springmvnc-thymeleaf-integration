$(document).ready(function() {
    $.getJSON("historyCountByResourceREST",{id: $("#idResource").val()}).done(function(data) {
        howMuchRows=data;
        if(howMuchRows==0){
            $("#historyPanel").hide();
        }
    });

//	$("#resourceEdit").bootstrapValidator({
//		fields : {
//			name : {
//				selector: '.comarch-resource-name',
//				validators : {
//					notEmpty : {
//						message : 'Pole nie może być puste.'
//					}
//				}
//			},
//			id  : {
//				selector: '.comarch-resource-id',
//				validators : {
//					notEmpty : {
//						message : 'Pole nie może być puste.'
//					}
//				}
//			}
//		}
//	});
//

        showHistory();
        $(".glyphicon").hover(function() {
              $(this).tooltip('show');
         });

        $(".comarch-tooltip").hover(function() {
              $(this).tooltip('show');
         });
});

var howMuchPerClick=5;
var howMuchRows=0;
function showMore() {
    howMuchPerClick = howMuchPerClick + 5;
    showHistory();
    $('html, body').animate({scrollTop : $(document).height()}, 'fast');
}
function showHistory(){
    var historyAPI = "transferREST";
    $.getJSON(historyAPI,{howMuch: howMuchPerClick}).done(function(data) {
        var i=1;
        for (var item in data) {
            if(i>$('.removeClass').length){
            $( "#tableRow" ).append( "<tr id='tableRow' class='removeClass'>" +
                    "<td>"+data[item].transferData+"</td>"+
                    "<td>"+data[item].transferOwner+"</td>"+
                    "<td>"+data[item].oldOwner+"</td>"+
                    "<td>"+data[item].newOwner+"</td>"+
                    "<td>"	+
                    "</td> "+
                    "</tr> "
            );
        }
            i++;
        }
        if(i>howMuchRows){
            $("#moreButton").hide();
        }

    });
}



// funkcja wywolujaca POST na typeForm podczas zmiany typu do dodania
$("#type").change(function() {
    $("#type-id").val($(this).val());
    $("#typeForm").submit();
});

//funkcja odpowiedzialna za dodawanie nowych inputboxow przy dodawaniu akcesoriow
function newElement() {
    var lastElement = $(".new-acessory-row:last").children().val();
    var scriptParams = document.getElementById('transfer');
    var message = scriptParams.getAttribute('input-placeholder-text');
     if (lastElement.length > 0) {
         $(".new-acessory").append("<tr><td class='new-acessory-row'><input type='text' placeholder= '" + message + "' class='form-control new-accessory-name' onkeypress='newElement()' name='newAccessory'</input></td></tr>");
     }


}

function removeElement() {
    if ($(".new-acessory-row").size() > 1) {
         $(".new-acessory-row:last").remove();
    }
}

// funkcja ustawiajaca id aktualnie wybranego obrazka na inputboxie i wywolujaca POST ktory usunie obrazek z podanym ID
function removePicture() {
    $("#image-id").val($(".fotorama__active").find("img").attr('name'));
    $("#pictureForm").submit();
}

// funkcja ustawiajaca id wybranego akcesorium na inputboxie i wywoluje POST usuwajacy akcesorium
$(".accessory").click(function() {
    $("#accessory-id").val($(this).parent().find("input").val());
    $("#accessoryForm").submit();

});


var isNameValid = true;
var isComarchIdValid = true;

$("#send-button").click(function() {

    if (isNameValid && isComarchIdValid) {
        $("#resourceEdit").submit();
    }


});


$(".comarch-resource-name").change(function() {

    if ($(this).val().length > 0) {
        $(this).parent().removeClass("has-error");
        $(this).parent().addClass("has-success");
        $(this).parent().find("small").css( "display", "none" );
        isNameValid = true;
    } else {
        $(this).parent().removeClass("has-success");
        $(this).parent().addClass("has-error");
        $(this).parent().find("small").css( "display", "block" );
        isNameValid = false;
    }

});

$(".comarch-resource-id").change(function() {

    if ($(this).val().length > 0) {
        $(this).parent().removeClass("has-error");
        $(this).parent().addClass("has-success");
        $(this).parent().find("small").css( "display", "none" );
        isComarchIdValid = true;
    } else {
        $(this).parent().removeClass("has-success");
        $(this).parent().addClass("has-error");
        $(this).parent().find("small").css( "display", "block" );
        isComarchIdValid = false;
    }

});
