$(document).ready(function() {

    $("#commentForm").bootstrapValidator({
        fields : {
            title : {
                validators : {
                    stringLength : {
                        max : 90,
                        message : 'Maksymalna długość tytułu to 90 znaków.'
                    },
                    notEmpty : {
                        message : 'Pole nie może byc puste.'
                    }
                }
            },
            content : {
                validators : {
                    stringLength : {
                        max : 2000,
                        message : 'Maksymalna długość uwagi to 2000 znaków.'
                    },
                    notEmpty : {
                        message : 'Pole nie może byc puste.'
                    }
                }
            }
        }
    });

});