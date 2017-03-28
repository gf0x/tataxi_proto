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

	$("#register-submit").click(function (e) {
	if($("#pass").val()!==$("#pass_conf").val()
		|| !(/^\+[0-9]{12}$/.test($("#phone_num").val()))) {
		e.preventDefault();
		//TO-DO
		//show warning here
	}

	});
});
