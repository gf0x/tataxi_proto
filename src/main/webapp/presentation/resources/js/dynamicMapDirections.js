/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
var CITY = 'Kyiv';
var FROM_ADDRESS = 'Kyiv-Mohyla academy';
var TO_ADDRESS = 'Maidan nezalezhnosti';

function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13,
        center: CITY
    });

    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer({
        draggable: true,
        map: map,
        panel: document.getElementById('right-panel')
    });

    directionsDisplay.addListener('directions_changed', function() {
        computeTotalDistance(directionsDisplay.getDirections());
    });

    displayRoute(FROM_ADDRESS, TO_ADDRESS, directionsService,
        directionsDisplay);
}

function displayRoute(origin, destination, service, display) {
    service.route({
        origin: origin,
        destination: destination,
        travelMode: google.maps.TravelMode.DRIVING,
        avoidTolls: true
    }, function(response, status) {
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
            });        }
    });
}

function computeTotalDistance(result) {
    var total = 0;
    var myroute = result.routes[0];
    for (var i = 0; i < myroute.legs.length; i++) {
        total += myroute.legs[i].distance.value;
    }
    total = total / 1000;
    document.getElementById('total').innerHTML = total + ' km';
}