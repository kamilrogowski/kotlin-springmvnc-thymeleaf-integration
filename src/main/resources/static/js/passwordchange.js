function back() {

	window.location = "manage_account";

}

$("#button").click(function() {
	$('#passwordchange').bootstrapValidator({
		fields : {
			newpass : {
				validators : {
					stringLength : {
						min : 8,
						message : 'Minimalna liczba znaków to 8'
					},
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
					regexp : {
						regexp : /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30})+$/,
						message : 'Pole musi zawierac jedna cyfre i jedna dużą litere'
					},
				}
			},
			repass : {
				validators : {
					stringLength : {
						min : 8,
						message : 'Minimalna liczba znaków to 8'
					},
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
					regexp : {
						regexp : /((?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,30})+$/,
						message : 'Pole musi zawierac jedna cyfre i jedna dużą litere'
					},
				}
			},
			oldpass : {
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},

				}
			},
		},

	});
});
