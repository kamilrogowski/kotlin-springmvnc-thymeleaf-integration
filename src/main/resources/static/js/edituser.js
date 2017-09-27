function back() {

	window.location = "users";

}
$("#button").click(function() {
	$('#edituser').bootstrapValidator({
		fields : {
			password : {
				selector: '.password',
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
				selector: '.email',
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
				selector: '.firstName',
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},


				}
			},

			lastName : {
				selector: '.lastName',
				validators : {
					notEmpty : {
						message : 'Pole nie może byc puste'
					},
				}
			},
			phoneNumber : {
				selector: '.phoneNumber',
				validators : {
					regexp : {
						regexp : /\b\d[-.]?\d[-.]?\d*\b/,
						message : 'Pole musi zawierac cyfry'
					},
				}
			},
		},

	});
});
