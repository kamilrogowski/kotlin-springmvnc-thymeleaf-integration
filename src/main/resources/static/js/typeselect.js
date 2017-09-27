$("#type").change(function() {
    if ($("#type").val() == "n") {
        counter = 0;
        $("#hiddenOptions").show();
        $("#hiddenButton").show();
        $("#typeName").show();
        $("#hiddenButtonButton").hide();
    }
    else {
        $("#hiddenOptions").hide();
        $("#typeName").hide();
        $("#hiddenButton").hide();
        while(elements.length > 0) {
            var a = elements.pop();
            if (a != null) {
                a.remove();
            }
        }
        $("#hiddenOptions").find("input").val("");
    }
});

$(document).ready(function() {
    $("#hiddenOptions").hide();
    $("#hiddenButton").hide();
    $("#typeName").hide();
});

var elements = new Array();
var addelements = new Array();
var counter = 0;

function addField() {
    counter++;
    var a = $("#hiddenOptions").clone();
    a.find("input").val("");
    a.insertAfter($(".clone1").last());
    $("#hiddenButtonButton").show();

    elements.push(a);
}

function removeField() {
    var a = elements.pop();
    if (a != null) {
        a.remove();
    }
    if (counter > 0) {
        counter--;
    }
    if (counter === 0) {
        $("#hiddenButtonButton").hide();
    }
}

$("#button").click(function() {
    $('#typeForm').bootstrapValidator({
        fields : {
            type : {
                validators : {
                    regexp : {
                        regexp : /([n1-9]\d*)/,
                        message : 'Nie wybrano typu!'
                    },

                }
            },
        },

    });
});