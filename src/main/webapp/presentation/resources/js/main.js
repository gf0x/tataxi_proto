//from net
$(function() {

    $('#login-form-link').click(function(e) {
		$("#login-form").delay(100).fadeIn(100);
 		$("#register-form").fadeOut(100);
		$('#register-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});
	$('#register-form-link').click(function(e) {
		$("#register-form").delay(100).fadeIn(100);
 		$("#login-form").fadeOut(100);
		$('#login-form-link').removeClass('active');
		$(this).addClass('active');
		e.preventDefault();
	});

	//show warning on illegal registration arguments
	$("#register-submit").click(function (e) {
	if($("#pass").val()!==$("#pass_conf").val()) {
		e.preventDefault();
		//TO-DO
		$.notify({
			icon: 'glyphicon glyphicon-warning-sign',
			title: 'Password confirmation failed',
			message: 'Password and it\'s confirmation don\'t match',
			target: '_blank'
		}, {
			type: 'warning'
		});
	}else if(!(/^\+[0-9]{12}$/.test($("#phone_num").val()))){
		e.preventDefault();
		$.notify({
			icon: 'glyphicon glyphicon-warning-sign',
			title: 'Invalid phone number type',
			message: 'Please input phone number in requested format',
			target: '_blank'
		},{
			type: 'warning'
		});
	}
	});
});
