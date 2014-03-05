<%-- 
    Document   : viewAnnunci.jsp
    Created on : 4-mar-2014, 14.41.32
    Author     : Andrea
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="ejb.Annuncio;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<Annuncio> annunci= (List<Annuncio>)request.getAttribute("annunci"); %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="dist/css/template-cohome.css">
    <script src="dist/js/jquery-1.10.2.js"></script>
    <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
    <title>Marker Animations</title>
    <style>
    
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <script>
    var defaultLat=45.079958;
    var defaultLng=7.687942;
    var defaultZoom=13;
    var markers = [];
    var map;
    function initialize() {
        
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
        

        /*var defaultBounds = new google.maps.LatLngBounds(
            new google.maps.LatLng(-33.8902, 151.1759),
            new google.maps.LatLng(-33.8474, 151.2631)
        );
        map.fitBounds(defaultBounds);*/
        
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
            markers = [];
            var bounds = new google.maps.LatLngBounds();
            for (var i = 0, place; place = places[i]; i++) {
                /*var image = {
                    url: place.icon,
                    size: new google.maps.Size(71, 71),
                    origin: new google.maps.Point(0, 0),
                    anchor: new google.maps.Point(17, 34),
                    scaledSize: new google.maps.Size(25, 25)
                };
          
                // Create a marker for each place.
                var marker = new google.maps.Marker({
                    map: map,
                    icon: image,
                    title: place.name,
                    animation: google.maps.Animation.DROP,
                    position: place.geometry.location
                });
                google.maps.event.addListener(marker, 'click', toggleBounce);
    
                markers.push(marker);*/
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
    function addMarker(lat,lng) {
       
        var marker = new google.maps.Marker({
                    map: map,
                    //icon: image,
                    //title: place.name,
                    animation: google.maps.Animation.DROP,
                    position: new google.maps.LatLng(lat,lng)
                });
        google.maps.event.addListener(marker, 'click', toggleBounce);
        markers.push(marker);
    
    }

    //google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div role="navigation" class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button data-target=".navbar-collapse" data-toggle="collapse" class="navbar-toggle" type="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a href="#" class="navbar-brand">CoHome</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
     </div>
    <div id="map-canvas"></div>
    <script>
        initialize();
    </script>   
    <script>
        addMarker(45.0839,7.68886);
    </script>
    <div id="viewResult">
        <%  ListIterator<Annuncio> iter = annunci.listIterator(); 
            while(iter.hasNext()){
                Annuncio a=iter.next();
                String lat=a.getLat();
                String lng=a.getLng();
        %>
        <script>
            addMarker(<%=lat%>,<%=lng%>);
        </script>
        <%    }
        %>
        
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
    <script src="dist/js/bootstrap.min.js"></script>  
  </body>
</html>
