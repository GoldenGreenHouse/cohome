var defaultLat=45.070982;
var defaultLng=7.685676;
var defaultZoom=13;
var markers = new Array();
var map=null;
var count=0;
function initialize(lat,lng) {
    
    var mapOptions = {
        zoom: defaultZoom,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: new google.maps.LatLng(lat, lng),
        panControl: false,
        streetViewControl: false,
        mapTypeControl: false,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.SMALL
        }
    };
    
      
    map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);

    // [START region_getplaces]
    // Listen for the event fired when the user selects an item from the
    // pick list. Retrieve the matching places for that item.
    google.maps.event.addListener(searchBox, 'places_changed', function() {
        var places = searchBox.getPlaces();
        for (var i = 0, marker; marker = markers[i]; i++) {
          marker.setMap(null);
        }
        defaultLat = map.getCenter().lat();
        defaultLng = map.getCenter().lng();
        defaultZoom = map.getZoom();
        // For each place, get the icon, place name, and location.
        //markers = [];

        var bounds = new google.maps.LatLngBounds();
        for (var i = 0, place; place = places[i]; i++) {
            bounds.extend(place.geometry.location);
        }
        map.fitBounds(bounds);
        //map.setZoom(12);
    });
    
    // [END region_getplaces]
    google.maps.event.addListener(map, 'bounds_changed', function() {
        var bounds = map.getBounds();
        searchBox.setBounds(bounds);
    });    
}

function initializeSingleMarker(lat,lng) {
    if(map!==null){
       return;
    }
    var mapOptions = {
        zoom: defaultZoom,
        mapTypeId: google.maps.MapTypeId.ROADMAP,
        center: new google.maps.LatLng(defaultLat, defaultLng),
        panControl: false,
        streetViewControl: false,
        mapTypeControl: false,
        zoomControlOptions: {
            style: google.maps.ZoomControlStyle.SMALL
        }
    };
    
      
    map = new google.maps.Map(document.getElementById('map-canvas'),mapOptions);
    addMarker(lat,lng);
    // [START region_getplaces]
    // Listen for the event fired when the user selects an item from the
    // pick list. Retrieve the matching places for that item.
    google.maps.event.addListener(searchBox, 'places_changed', function() {
        var places = searchBox.getPlaces();
        for (var i = 0, marker; marker = markers[i]; i++) {
          marker.setMap(null);
        }
        defaultLat = map.getCenter().lat();
        defaultLng = map.getCenter().lng();
        defaultZoom = map.getZoom();
        // For each place, get the icon, place name, and location.
        //markers = [];

        var bounds = new google.maps.LatLngBounds();
        for (var i = 0, place; place = places[i]; i++) {
            bounds.extend(place.geometry.location);
        }
        map.fitBounds(bounds);
        //map.setZoom(12);
    });
    
    // [END region_getplaces]
    google.maps.event.addListener(map, 'bounds_changed', function() {
        var bounds = map.getBounds();
        searchBox.setBounds(bounds);
    });    
}

function addMarker(lat,lng,id,index,titolo) {
    
    var marker = new google.maps.Marker({
        map: map,
        icon:"dist/image/markerGreen.png",
        //title: place.name,
        animation: google.maps.Animation.DROP,
        position: new google.maps.LatLng(lat,lng)
    });
    
    markers.push(marker);
    count++;
    if (id !== undefined){
        var infowindow = new google.maps.InfoWindow({
            maxWidth: 200,
            closeBoxURL: "dist/image/icon-close.png",
            pane: "floatPane",
            content: "<div class='listing-img media-photo'>"+
                "<div class='listing-img-container'>"+
                "<a href='#'><img src='gallery/"+id+"/1.jpg'></a>" +
                "</div>"+
                "<a href='/CoHome-war/MainServlet?op=viewDettaglioAnnuncioCasa&index="+index+"' class='listing-name media-caption-infowindow h4'>"+titolo+
                "</a>"+
                "</div>"
        });
        google.maps.event.addListener(marker, 'click', function() {
            infowindow.open(map,marker);
        });
    }
    google.maps.event.addListener(marker, 'mouseover', function() {
        marker.setIcon("dist/image/markerBlue.png");
    });
    google.maps.event.addListener(marker, 'mouseout', function() {
        marker.setIcon("dist/image/markerGreen.png");
    });
    google.maps.event.addListener(marker, 'click', toggleBounce);
}
