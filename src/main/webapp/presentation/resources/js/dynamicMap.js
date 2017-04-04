/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 4,
        center: {lat: -24.345, lng: 134.46}  // Australia.
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

    displayRoute('Perth, WA', 'Sydney, NSW', directionsService,
        directionsDisplay);
}

function displayRoute(origin, destination, service, display) {
    service.route({
        origin: origin,
        destination: destination,
        waypoints: [{location: 'Cocklebiddy, WA'}, {location: 'Broken Hill, NSW'}],
        travelMode: google.maps.TravelMode.DRIVING,
        avoidTolls: true
    }, function(response, status) {
        if (status === google.maps.DirectionsStatus.OK) {
            display.setDirections(response);
        } else {
            alert('Could not display directions due to: ' + status);
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
    document.getElementById('total').innerHTML = total + ' km';
}