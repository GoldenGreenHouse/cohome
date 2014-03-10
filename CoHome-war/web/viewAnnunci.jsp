<%-- 
    Document   : viewAnnunci.jsp
    Created on : 4-mar-2014, 14.41.32
    Author     : Andrea
--%>

<%@page import="ejb.AnnuncioCasa"%>
<%@page import="java.util.ListIterator"%>
<%@page import="java.util.List"%>
<%@page import="ejb.Annuncio;"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% List<AnnuncioCasa> annunci= (List<AnnuncioCasa>)request.getAttribute("annunci"); %>
<!DOCTYPE html>
<html style="overflow-y: hidden;">
  <head>
    <meta charset="utf-8">
    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="dist/css/bootstrap.min.css">
    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="dist/css/template-cohome.css">
    <script src="dist/js/jquery-1.10.2.js"></script>
    <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
    <script type="text/javascript" src="http://google-maps-utility-library-v3.googlecode.com/svn/trunk/styledmarker/src/StyledMarker.js"></script>
    <title>Marker Animations</title>
    <style>
    
    </style>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    
    <script>
    var defaultLat=45.079958;
    var defaultLng=7.687942;
    var defaultZoom=13;
    var markers = new Array();
    var map;
    var count=0;
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
            //markers = [];
            
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
                    icon:"dist/image/markerGreen.png",
                    //title: place.name,
                    animation: google.maps.Animation.DROP,
                    position: new google.maps.LatLng(lat,lng)
                });
        markers.push(marker);
        count++;
        google.maps.event.addListener(marker, 'mouseover', function() {
            marker.setIcon("dist/image/markerBlue.png");
        });
        google.maps.event.addListener(marker, 'mouseout', function() {
            marker.setIcon("dist/image/markerGreen.png");
        });
        google.maps.event.addListener(marker, 'click', toggleBounce);
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
                <a href="/CoHome-war/index.jsp" class="navbar-brand">CoHome</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/CoHome-war/index.jsp">Home</a></li>
                    <li><a href="#about">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
            </div><!--/.nav-collapse -->
        </div>
     </div>
    <div id="map">
        <div id="map-canvas"></div>
        <script>
            initialize();
        </script>
    </div>
    <div class="search-results">
        <div class="outer-listings-container">
           <ul id="results" class="listings-container list-unstyled clearfix">
           <!-- N°Annunci=<%= annunci.size() %> !-->
            <%  ListIterator<AnnuncioCasa> iter = annunci.listIterator(); 
                int c=0;
                while(iter.hasNext()){
                    Annuncio a=iter.next();
                    String lat=a.getLat();
                    String lng=a.getLng();
                    c++;
            %>
            <script>
                addMarker(<%=lat%>,<%=lng%>);
            </script>
            <li class="search-result" data-marker="<%=c-1%>">
                <div class="listing" data-id="868303" data-user="3647027" data-url="/rooms/868303?checkin=08-03-2014&amp;checkout=15-03-2014&amp;s=4_eE" data-name="CAMERA - Torino Centro" data-lng="7.68603707453739" data-lat="45.062101148056506">
                    <div class="listing-img media-photo">
                        <div class="listing-img-container">
                            <a href="#"><img data-urls="[&quot;https://a1.muscache.com/ic/pictures/12663332/3ab3cd29_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12662855/1882df84_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a1.muscache.com/ic/pictures/12663413/1e2b5a74_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12663250/9b87aca2_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12662939/f0fbc593_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12662997/8ddb7ef6_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12663082/50259dce_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a1.muscache.com/ic/pictures/12663203/2f049cb0_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12663501/bccd7fdb_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;]" data-current="0" class="" src="https://a1.muscache.com/ic/pictures/12663332/3ab3cd29_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u"></a>
                        </div>
                        <a href="/rooms/868303?checkin=08-03-2014&amp;checkout=15-03-2014&amp;s=4_eE" class="listing-name media-caption h4">
                          CAMERA - Torino Centro
                        </a>
                    </div>
                    <div class="listing-footer clearfix">
                        <a class="media-link media-photo host-img" href="/users/show/3647027">
                            <img src="https://a1.muscache.com/ic/users/3647027/profile_pic/1348479910/original.jpg?interpolation=progressive-bicubic&amp;crop=w:w;*,*&amp;crop=h:h;*,*&amp;resize=50:*&amp;output-format=jpg&amp;output-quality=85">
                        </a>
                        <a title="Stanza privata | Torino" href="/rooms/868303?checkin=08-03-2014&amp;checkout=15-03-2014&amp;s=4_eE" class="listing-quick-info">
                            <span class="listing-room-type">
                                Stanza privata<br>
                                Torino
                            </span>
                        </a>
                    </div>
              </div>
            </li>
            <% } %>
            <script>              
                $( ".search-result" ).mouseover(function() {
                    google.maps.event.trigger(markers[$(this).attr("data-marker")], 'mouseover');
                });
                $( ".search-result" ).mouseout(function() {
                    google.maps.event.trigger(markers[$(this).attr("data-marker")], 'mouseout');
                });
            </script>
            </ul>
        </div>
    </div>
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
    <script src="dist/js/bootstrap.min.js"></script>  
  </body>
</html>
