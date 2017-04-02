const GOOGLE_ZOOM = 13;
const GOOGLE_SIZE = '680x400';
const GOOGLE_MAPTYPE = 'roadmap';
const GOOGLE_CENTER_DEFAULT = 'Київ';
const GOOGLE_COUNTRY_DEFAULT = ',%20Україна';

$(function () {

    //login & registration UI
    $('#login-form-link').click(function (e) {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });
    $('#register-form-link').click(function (e) {
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $(this).addClass('active');
        e.preventDefault();
    });

    //show warning on illegal registration arguments
    $("#register-submit").click(function (e) {
        if ($("#pass").val() !== $("#pass_conf").val()) {
            e.preventDefault();
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Password confirmation failed',
                message: 'Password and it\'s confirmation don\'t match',
                target: '_blank'
            }, {
                type: 'warning'
            });
        } else if (!(/^\+[0-9]{12}$/.test($("#phone_num").val()))) {
            e.preventDefault();
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Invalid phone number type',
                message: 'Please input phone number in requested format',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
    });

    //MENU UI
    htmlbodyHeightUpdate()
    $(window).resize(function () {
        htmlbodyHeightUpdate()
    });
    $(window).scroll(function () {
        height2 = $('.main').height()
        htmlbodyHeightUpdate()
    });

    //dept add/edit static map handling
    $('#dept_city').focusout(function () {
        if ($(this).val().length > 0) {
            var newRequest = '//maps.googleapis.com/maps/api/staticmap?';
            newRequest += 'center=' + $(this).val() + GOOGLE_COUNTRY_DEFAULT;
            newRequest += '&zoom=' + GOOGLE_ZOOM;
            newRequest += '&maptype=' + GOOGLE_MAPTYPE;
            newRequest += '&size=' + GOOGLE_SIZE;
            newRequest += '&key=' + GOOGLE_API_KEY;
            $('#dept_static_map').attr('src', newRequest);
        }
    });
    //dept add/edit validation
    $('#btn_dept_edit').click(function (e) {
        if($(this).attr('disabled')===true){
            e.preventDefault();
        }
        var city = $('#dept_city').val();
        var address = $('#dept_address').val();
        var price_per_km = $('#dept_price_per_km').val();
        var error = false;
        if (city.length <= 0
            && !(/^([A-ZА-ЯЇЙІ][a-zа-яйїі]*((,)? )?)+$/.test(city))) {
            error = true;
            $('#fg_dept_city').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Invalid city name',
                message: 'Please input real city name',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if (address.length <= 0) {
            error = true;
            $('#fg_dept_address').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Invalid address',
                message: 'Please input address',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if (isNaN(price_per_km) || price_per_km <= 0) {
            error = true;
            $('#fg_dept_price_per_km').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Invalid price per km',
                message: 'Please input number',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if(!error){
            $('#fg_dept_city').removeClass('has-warning');
            $('#fg_dept_address').removeClass('has-warning');
            $('#fg_dept_price_per_km').removeClass('has-warning');
            $('#btn_dept_edit').attr('disabled', true);
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'In progress: ',
                message: 'Sending request to server...',
                target: '_blank'
            }, {
                type: 'info',
                showProgressbar: true
            });
            var data = {city: city, address: address, pricePerKm: price_per_km};
            $.ajax({
                method: 'POST',
                url: '/dept/create',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function () {
                    $.notify({
                        icon: 'glyphicon glyphicon-warning-sign',
                        title: 'Success: ',
                        message: 'New department added',
                        target: '_blank'
                    }, {
                        type: 'success'
                    });
                    //TO-DO: redirect after 10 sec. if success
                },
                error: function () {
                    $.notify({
                        icon: 'glyphicon glyphicon-warning-sign',
                        title: 'Error: ',
                        message: 'Could not add department',
                        target: '_blank'
                    }, {
                        type: 'danger'
                    });
                    $('#btn_dept_edit').attr('disabled', false);
                }
            });
        }
    });
});

function htmlbodyHeightUpdate() {
    var height3 = $(window).height()
    var height1 = $('.nav').height() + 50
    height2 = $('.main').height()
    if (height2 > height3) {
        $('html').height(Math.max(height1, height3, height2) + 10);
        $('body').height(Math.max(height1, height3, height2) + 10);
    }
    else {
        $('html').height(Math.max(height1, height3, height2));
        $('body').height(Math.max(height1, height3, height2));
    }

}