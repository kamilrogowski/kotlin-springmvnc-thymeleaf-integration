function back() {

	window.location = "resource";

}
$(document).ready(function() {

	$("#additionalEquipment").hide();
	$("#addidionalEquipmentButtonButton").hide();


});
var addelements = new Array();
var couter = 0;
function showfirstEquipment() {
	if (couter >= 0) {
		$("#addidionalEquipmentButtonButton").show();
		$("#additionalEquipment").show();
		if (couter != 0) {
			var a = $("#additionalEquipment").clone();
			a.find("input").val("");

			a.insertAfter($(".clone2").last());
			addelements.push(a);
		}
		couter++;
	}

}
function removeEquipment() {
	if (couter > 0) {
		couter--;
	}
	if (couter != 0) {
		var a = addelements.pop();
		if (a != null) {
			a.remove();

		}

	} else {
		$("#additionalEquipment").hide();
		$("#addidionalEquipmentButtonButton").hide();
		$("#additionalEquipment").find("input").val("");
	}

}
$("#button").click(function() {
	$('#resourcefrom').bootstrapValidator({
		fields : {
			comarchID : {
				validators : {
					notEmpty : {
						message : 'Pole nie mo¿e byc puste'
					},
				}
			},
			name : {
				validators : {
					notEmpty : {
						message : 'Pole nie mo¿e byc puste'
					},
				}
			},
		},

	});
});