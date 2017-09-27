$(document).ready(function() {
    $("#addbutton").hover(function() {
        $('#addbutton').tooltip('show');
    });
    $(".glyphicon-pencil").hover(function() {
        $(this).tooltip('show');
    });
    $(".glyphicon-trash").hover(function() {
        $(this).tooltip('show');
    });
    $(".glyphicon-plus").hover(function() {
        $(this).tooltip('show');
    });
    $(".glyphicon-minus").hover(function() {
        $(this).tooltip('show');
    });
    $(".glyphicon-floppy-disk").hover(function() {
        $(this).tooltip('show');
    });
    $("#hiddenButton").hide();

    /*
    $("#attributeType1").change(function() {
         if($("#attributeType1").val()=="logiczny"){
             $("#selectInput1").show();
             $("#valueInput1").hide();
             $("#valueInput1").val("");
         }
         else if($("#attributeType1").val()=="tekstowy" || $("#attributeType1").val()=="rzeczywisty" || $("#attributeType1").val()=="calkowity"){
             $("#valueInput1").show();
             $("#selectInput1").hide();
             $("#selectInput1").val("");
         }
         else{
             $("#selectInput1").hide();
             $("#valueInput1").hide();
             $("#selectInput1").val("");
             $("#valueInput1").val("");
         }

    });
    */

    $('.selectInput').each(function(i, obj) {
        attributeIndex++;
        if ($(this).val().length === 0) {
            $(this).hide();
        }
    });

    if (attributeIndex >= 2) {
        $("#hiddenButton").show();
    }

    $('.valueInput').each(function(i, obj) {
            if ($(this).val().length === 0 ) {
                $(this).hide();
            }
        });

    $('.attributeType').each(function(i, obj) {
             if($(this).val() == "logiczny") {
                 $(this).parent().parent().find($('.selectInput')).show();
             }
             else if($(this).val() == "tekstowy" || $(this).val() == "rzeczywisty" || $(this).val() == "calkowity") {
                  $(this).parent().parent().find($('.valueInput')).show();
             }

             $(this).change(function () {
                 $(this).parent().parent().find($('.selectInput')).val("");
                 $(this).parent().parent().find($('.valueInput')).val("");
                 if ($(this).val() == "logiczny"){
                     $(this).parent().parent().find($('.selectInput')).show();
                     $(this).parent().parent().find($('.valueInput')).hide();
                 }
                 else if ($(this).val() == "tekstowy" || $(this).val() == "rzeczywisty" || $(this).val() == "calkowity") {
                     $(this).parent().parent().find($('.valueInput')).show();
                     $(this).parent().parent().find($('.selectInput')).hide();
                 }
                 else {
                     $(this).parent().parent().find($('.valueInput')).show();
                     $(this).parent().parent().find($('.selectInput')).hide();
                 }
            });
        });
    /*
    $("#typeForm").bootstrapValidator({
        fields : {
            typeName : {
                validators : {
                    notEmpty : {
                        message : 'Pole nie może byc puste'
                    }
                }
            }
        }
    });
    $("#editForm").bootstrapValidator({
        fields : {
            typeName : {
                validators : {
                    notEmpty : {
                        message : 'Pole nie może byc puste'
                    }
                }
            }
        }
    });*/
});


//var elements = new Array();
//var addelements = new Array();
var attributeIndex = 0;

/**
 *
 */
function addField() {
    $('#attributesList').append(
            "<div id='hiddenOptions' class='clone1'>" +
                "<div class='form-group'>" +
                    "<label class='col-md-3 control-label'>Nazwa atrybutu *</label>" +
                    "<div class='col-md-3'>" +
                        "<input type='text' class='form-control' id='attributeName1' name='attributeTypeValue["+attributeIndex+"].attribute' value='' />" +
                    "</div>" +
                    "<div class='col-md-2'>" +
                        "<select class='form-control attributeType' id='attributeType1' name='attributeTypeValue["+attributeIndex+"].type'>" +
                            "<option value='' selected='selected'>Wybierz typ atrybutu *</option>" +
                            "<option value='calkowity'>calkowity</option>" +
                            "<option value='logiczny' >logiczny</option>" +
                            "<option value='rzeczywisty'>rzeczywisty</option>" +
                            "<option value='tekstowy'>tekstowy</option>" +
                        "</select>" +
                    "</div>" +
                    "<label class='col-md-1 control-label'>Wartość</label>" +
                    "<div class='col-md-3' id='normalAttribute1'>" +
                        "<input type='text' class='form-control valueInput' id='attributeTypeValue0.textValue' name='attributeTypeValue["+attributeIndex+"].textValue' value='' />" +
                    "</div>" +
                    "<div class='col-md-3' id='logicAttribute1'>" +
                        "<select class='form-control selectInput' id='attributeTypeValue0.logicValue' name='attributeTypeValue["+attributeIndex+"].logicValue'>" +
                            "<option value='' selected='selected'>Wybierz typ atrybutu *</option>" +
                            "<option value='Tak'>Tak</option>" +
                            "<option value='Nie'>Nie</option>" +
                        "</select>" +
                    "</div>" +
                "</div>" +
            "</div>");

    $('.selectInput').last().hide();
    $('.valueInput').last().hide();
    attributeIndex++;

    $('.attributeType').last().change(function () {
         $(this).parent().parent().find($('.selectInput')).val("");
         $(this).parent().parent().find($('.valueInput')).val("");

         if ($(this).val() == "logiczny") {
             $(this).parent().parent().find($('.selectInput')).show();
             $(this).parent().parent().find($('.valueInput')).hide();
         }
         else if ($(this).val() == "tekstowy" || $(this).val() == "rzeczywisty" || $(this).val() == "calkowity") {
             $(this).parent().parent().find($('.valueInput')).show();
             $(this).parent().parent().find($('.selectInput')).hide();
         }
         else {
             $(this).parent().parent().find($('.valueInput')).show();
             $(this).parent().parent().find($('.selectInput')).hide();
         }
    });
    $("#hiddenButton").show(); // pokazanie przycisku usuwania;
}

/**
 *
 */
function removeField() {
    if (attributeIndex > 0) {
        attributeIndex--;
        $('.clone1').last().remove();
    }
    if (attributeIndex == 0) {
        $("#hiddenButton").hide();
    }
    //console.log(attributeIndex);
}

/**
 *
 */
function removePicture() {
    if ($(".fotorama__active").find("img").attr('name') != null) {
        $("#image-id").val($(".fotorama__active").find("img").attr('name'));
    }

    if ($(".fotorama").find("img").attr('name') != null) {
        $("#image-id").val($(".fotorama").find("img").attr('name'));
    }
    $("#pictureForm").submit();
}
