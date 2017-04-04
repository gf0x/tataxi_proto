/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
var CITY = $('#order_dept').val();// 'Kyiv';
var FROM_ADDRESS = null;// = 'Kyiv-Mohyla academy';
var TO_ADDRESS = null;// = 'Maidan nezalezhnosti';
var GEOCODER;

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13
        //center: CITY
    });
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
        console.log("AAAA")
        if (FROM_ADDRESS === null) {
            console.log("BBBB");
            geocodePosition(e.latLng, true);
            console.log(FROM_ADDRESS);
        } else if (TO_ADDRESS === null) {
            console.log("CCCC");
            geocodePosition(e.latLng, false);
            console.log(TO_ADDRESS);
        }
    });
    directionsDisplay.addListener('directions_changed', function () {
        console.log(map);
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
        avoidTolls: true
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
    $('#order_price').text((total*pricePerKm).toFixed(2)+'₴');
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
});