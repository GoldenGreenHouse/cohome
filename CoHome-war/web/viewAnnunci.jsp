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
<jsp:useBean id="ricercaAnnunciCasa" scope="session" class="Bean.RicercaAnnunciCasa"/>
<% ricercaAnnunciCasa.setAnnunci(annunci); %>
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
    <title>Ricerca Annunci</title>
    <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
    
    <script src="dist/js/mappaGoogle.js"></script>
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
                <a href="index.jsp" class="navbar-brand">CoHome</a>
            </div>
            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="index.jsp">Home</a></li>
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
                    AnnuncioCasa a=iter.next();
                    String lat=a.getLat();
                    String lng=a.getLng();
                    c++;
            %>
            <script>
                addMarker(<%=lat%>,<%=lng%>);
            </script>
            <li class="search-result" data-marker="<%=c-1%>">
                <div class="listing" data-id="<%=c-1%>" data-user="3647027" data-name="CAMERA - Torino Centro" data-lng="7.68603707453739" data-lat="45.062101148056506">
                    <div class="listing-img media-photo">
                        <div class="listing-img-container">
                            <a href="#"><img data-urls="[&quot;https://a1.muscache.com/ic/pictures/12663332/3ab3cd29_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12662855/1882df84_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a1.muscache.com/ic/pictures/12663413/1e2b5a74_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12663250/9b87aca2_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12662939/f0fbc593_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a0.muscache.com/ic/pictures/12662997/8ddb7ef6_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12663082/50259dce_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a1.muscache.com/ic/pictures/12663203/2f049cb0_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;, &quot;https://a2.muscache.com/ic/pictures/12663501/bccd7fdb_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u&quot;]" data-current="0" class="" src="https://a1.muscache.com/ic/pictures/12663332/3ab3cd29_original.jpg?interpolation=progressive-bicubic&amp;size=x_medium&amp;output-format=jpg&amp;output-quality=85&amp;wm=u"></a>
                        </div>
                        <a href="/CoHome-war/MainServlet?op=viewDettaglioAnnuncioCasa&index=<%=c-1%>" class="listing-name media-caption h4">
                            <%= a.getTitolo() %>
                        </a>
                    </div>
                    <div class="listing-footer clearfix">
                        <a class="media-link media-photo host-img" href="/CoHome-war/MainServlet?op=viewDettaglioAnnuncioCasa&index=<%=c-1%>">
                            <img src="https://a1.muscache.com/ic/users/3647027/profile_pic/1348479910/original.jpg?interpolation=progressive-bicubic&amp;crop=w:w;*,*&amp;crop=h:h;*,*&amp;resize=50:*&amp;output-format=jpg&amp;output-quality=85">
                        </a>
                        <a title="Stanza privata | Torino" href="/CoHome-war/MainServlet?op=viewDettaglioAnnuncioCasa&index=<%=c-1%>" class="listing-quick-info">
                            <span class="listing-room-type">
                                <%= a.getIndirizzo() %><br>
                                <%= a.getLocalita() %>
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
