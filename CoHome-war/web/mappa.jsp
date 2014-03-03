<%-- 
    Document   : mappa
    Created on : 3-mar-2014, 12.05.58
    Author     : Andr3A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <link type="text/css" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500">
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    <script type="text/javascript">
        window.onload=function initialize() {
            var input = document.getElementById('location');
            new google.maps.places.Autocomplete(input);
        }
        google.maps.event.addDomListener(window, 'load', initialize);
    </script>
  </head>
  <body>
    <div id="locationField">
      <input id="location"  autocomplete="off" placeholder="Enter your address"  type="text"></input>
    </div>
  </body>

</html>
