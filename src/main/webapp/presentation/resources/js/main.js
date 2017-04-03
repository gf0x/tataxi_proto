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
    const DEPT_MAX_PHONES = 3;
    var addEditDept = function (e, firstEdit) {
        if ($(this).attr('disabled')) {
            return;
        }
        var dept = {
            id: $('#dept_id').val(),
            city: $('#dept_city').val(),
            address: $('#dept_address').val(),
            pricePerKm: $('#dept_price_per_km').val(),
            phoneNums: []
        };
        for (var i=1; i<=DEPT_MAX_PHONES; ++i){
            var n=$('#dept_phone_'+i).val();
            if(n.length>0)
                dept.phoneNums.push(n);
        }
        var error = false;
        if (dept.city.length <= 0
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
        if (dept.address.length <= 0) {
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
        if (isNaN(dept.pricePerKm) || dept.pricePerKm <= 0) {
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
        if (dept.phoneNums.length === 0) {
            error = true;
            for (var i = 1; i <= DEPT_MAX_PHONES; ++i)
                $('#fg_dept_phone_' + i).addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'No phone number!',
                message: 'Please input at least one phone number',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        for (var i = 0; i < dept.phoneNums.length; ++i)
            if (!(/^\+[0-9]{12}$/.test(dept.phoneNums[i]))) {
                error = true;
                $('#fg_dept_phone_' + (i+1)).addClass('has-warning');
                $.notify({
                    icon: 'glyphicon glyphicon-warning-sign',
                    title: 'Invalid phone number',
                    message: 'Please input valid phone number',
                    target: '_blank'
                }, {
                    type: 'warning'
                });
            }
        if (!error) {
            $('#fg_dept_city').removeClass('has-warning');
            $('#fg_dept_address').removeClass('has-warning');
            $('#fg_dept_price_per_km').removeClass('has-warning');
            for (var i = 0; i < dept.phoneNums.length; ++i) $('#fg_dept_phone_' + (i+1)).removeClass('has-warning');
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
            $.ajax({
                method: 'POST',
                url: (firstEdit) ? '/dept/create' : ('/dept/edit'),
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(dept),
                dataType: 'json',
                success: function () {
                    $.notify({
                        icon: 'glyphicon glyphicon-warning-sign',
                        title: 'Success: ',
                        message: 'Department modified',
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
                        message: 'Could not modify department',
                        target: '_blank'
                    }, {
                        type: 'danger'
                    });
                    $('#btn_dept_edit').attr('disabled', false);
                }
            });
        }
    };
    $('#btn_dept_create').click(function (e) {
        addEditDept(e, true);
    });
    $('#btn_dept_edit').click(function (e) {
        addEditDept(e, false);
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