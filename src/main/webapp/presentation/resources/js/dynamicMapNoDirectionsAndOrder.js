/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
var CITY = null;
var FROM_ADDRESS = null;// = 'Kyiv-Mohyla academy';
var TO_ADDRESS = null;// = 'Maidan nezalezhnosti';
var GEOCODER;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13
        //center: CITY
    });
    if(CITY===null)
        CITY = $('#order_dept').find('option:first-child').val();
    GEOCODER = new google.maps.Geocoder();
    GEOCODER.geocode({'address': CITY}, function (results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            map.setCenter(results[0].geometry.location);
        }
    });
    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer({
        draggable: true,
        map: map
    });

    map.addListener('click', function (e) {
        if (FROM_ADDRESS === null) {
            geocodePosition(e.latLng, true);
        } else if (TO_ADDRESS === null) {
            geocodePosition(e.latLng, false);
        }
    });
    directionsDisplay.addListener('directions_changed', function () {
        computeTotalDistance(directionsDisplay.getDirections());
        FROM_ADDRESS = directionsDisplay.getDirections().routes[0].legs[0]["start_address"];
        TO_ADDRESS = directionsDisplay.getDirections().routes[0].legs[0]["end_address"];
        $('#order_from_address').val(FROM_ADDRESS);
        $('#order_to_address').val(TO_ADDRESS);
    });
    if (FROM_ADDRESS && TO_ADDRESS)
        displayRoute(FROM_ADDRESS, TO_ADDRESS, directionsService, directionsDisplay);
}

function displayRoute(origin, destination, service, display) {
    service.route({
        origin: origin,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING,
        avoidTolls: true,
        language: 'EN'
    }, function (response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            display.setDirections(response);
        } else {
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Routing failed',
                message: status,
                target: '_blank'
            }, {
                type: 'danger'
            });
        }
    });
}

function computeTotalDistance(result) {
    var total = 0;
    var myroute = result.routes[0];
    for (var i = 0; i < myroute.legs.length; i++) {
        total += myroute.legs[i].distance.value;
    }
    total = total / 1000;
    $('#order_dist').text(total + ' km');
    var pricePerKm = $('#order_dept').find('option:selected').attr('price_per_km');
    var multiplier = $('#order_is_fast').find('option:selected').attr('id');
    $('#order_price').text((total*pricePerKm*multiplier).toFixed(2)+'₴');
}

function geocodePosition(position, addrFrom) {
    GEOCODER.geocode({
        latLng: position
    }, function (responses) {
        if (responses && responses.length > 0) {
            if (addrFrom) {
                FROM_ADDRESS = responses[0].formatted_address;
                $('#order_from_address').val(FROM_ADDRESS);
            }
            else {
                TO_ADDRESS = responses[0].formatted_address;
                $('#order_to_address').val(TO_ADDRESS);
                initMap();
            }
        } else {
            if (addrFrom) {
                FROM_ADDRESS = 'Unknown address';
                $('#order_from_address').val(FROM_ADDRESS);
            }
            else {
                TO_ADDRESS = 'Unknown address';
                $('#order_to_address').val(TO_ADDRESS);
            }
        }
    });
}

$(function () {
    $('#order_dept').change(function (e) {
        CITY = $(this).val();
        FROM_ADDRESS = null;
        TO_ADDRESS = null;
        $('#order_from_address').val(FROM_ADDRESS);
        $('#order_to_address').val(TO_ADDRESS);
        $('#order_dist').text('');
        $('#order_price').text('');
        initMap();
    });
    $('#order_from_address').change(function () {
        FROM_ADDRESS = $(this).val() + ', ' + CITY;
        initMap();
    });
    $('#order_to_address').change(function () {
        TO_ADDRESS = $(this).val() + ', ' + CITY;
        initMap();
    });

    $('#order_is_fast').change(function (e) {
        var totalPrice = parseFloat($('#order_dist').text());
        var pricePerKm = $('#order_dept').find('option:selected').attr('price_per_km');
        var multiplier = $('#order_is_fast').find('option:selected').attr('id');
        $('#order_price').text((totalPrice*pricePerKm*multiplier).toFixed(2)+'₴');
    });

    $('#btn_order_create').click(function (e) {
        if ($(this).attr('disabled')) {
            e.preventDefault();
            return;
        }
        var order = {
            distance: parseFloat($('#order_dist').text()),
            price: parseFloat($('#order_price').text()),
            from: {
              address: $('#order_from_address').val()
            },
            to: {
              address: $('#order_to_address').val()
            },
            seats: $('#order_seats').val(),
            isFast: ($('#order_is_fast').find('option:selected').attr('id') !== 1),
            city: $('#order_dept').find('option:selected').val(),
            extraLuggage: $('#order_extra_luggage').find('option:selected').attr('id')=='true',
            deptId: $('#order_dept').find('option:selected').attr('id')
        };
        var error = false;
        if (order.from.address.length === 0){
            error = true;
            $('#fg_order_from').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Error: ',
                message: 'Please input your location',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if(order.to.address.length === 0){
            error = true;
            $('#fg_order_to').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Error: ',
                message: 'Please input destination',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if(isNaN(order.seats) || order.seats<=0 || order.seats >8){
            error = true;
            $('#fg_order_passangers').addClass('has-warning');
            $.notify({
                icon: 'glyphicon glyphicon-warning-sign',
                title: 'Error: ',
                message: 'Please input valid amount of passengers (from 1 to 8)',
                target: '_blank'
            }, {
                type: 'warning'
            });
        }
        if (!error) {
            $('#fg_order_from').removeClass('has-warning');
            $('#fg_order_to').removeClass('has-warning');
            $('#fg_order_passangers').removeClass('has-warning');
            $('#btn_order_create').attr('disabled', true);
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
                url: '/order/create',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(order),
                dataType: 'json',
                success: function (data) {
                    if(data.code == '200') {
                        $.notify({
                            icon: 'glyphicon glyphicon-warning-sign',
                            title: 'Success: Order accepted',
                            message: 'Please wait for a call back',
                            target: '_blank'
                        }, {
                            type: 'success'
                        });
                        $('#btn_order_create').text('Thanks for your order!');
                    }else {
                        $.notify({
                            icon: 'glyphicon glyphicon-warning-sign',
                            title: 'Error: '+data.code,
                            message: data.message,
                            target: '_blank'
                        }, {
                            type: 'danger'
                        });
                        $('#btn_order_create').attr('disabled', false);
                    }
                    //TO-DO: redirect after 10 sec. if success
                },
                error: function (data) {
                    $.notify({
                        icon: 'glyphicon glyphicon-warning-sign',
                        title: 'Error: ',
                        message: data.message,
                        target: '_blank'
                    }, {
                        type: 'danger'
                    });
                    $('#btn_order_create').attr('disabled', false);
                }
            });
        }
    });
});