function back() {

	window.location = "users";

}
$("#button").click(function() {
	$('#adduser').bootstrapValidator({
		fields : {
			password : {
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

			email : {
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
					emailAddress:{
						message : 'Pole nie jest emailem'

					},
				}
			},

			firstName : {
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
					regexp : {
						regexp: /^[a-zA-Z]+$/i,
						message : 'Pole moze zawierac jedynie litery'
					},

				}
			},

			lastName : {
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
					regexp : {
						regexp: /^[a-zA-Z]+$/i,
						message : 'Pole moze zawierac jedynie litery'
					},

				}
			},

			phoneNumber : {
				validators : {
					regexp : {
						regexp: /^[0-9]+$/i,
						message : 'Pole moze zawierac jedynie cyfry'
					},

				}
			},

		},

	});
});
