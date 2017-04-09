/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
/**
 * Created by Alex_Frankiv on 04.04.2017.
 */
function runMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 13
    });

    var directionsService = new google.maps.DirectionsService;
    var directionsDisplay = new google.maps.DirectionsRenderer({
        draggable: false,
        map: map,
        panel: document.getElementById('right-panel')
    });

    displayRoute({lat: FROM_LAT, lng: FROM_LNG}, {lat: TO_LAT, lng: TO_LNG}, directionsService,
        directionsDisplay);
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
