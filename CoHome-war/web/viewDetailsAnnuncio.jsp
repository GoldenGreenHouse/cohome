<%-- 
    Document   : viewDetailsAnnuncio
    Created on : 12-mar-2014, 18.49.49
    Author     : Andr3A
--%>

<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="ejb.AnnuncioCasa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ricercaAnnunciCasa" scope="session" class="Bean.RicercaAnnunciCasa"/>
<% AnnuncioCasa annuncio = ricercaAnnunciCasa.getSingleAnnuncio(Integer.parseInt(request.getParameter("index")));%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        
        <script src="dist/js/jquery-1.10.2.js"></script>
        <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
        <script type="text/javascript" src="dist/js/jquery_cycle.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script src="dist/js/mappaGoogle.js"></script>
        <script type="text/javascript">
            $(document).ready(function() {
              $('#photos').cycle({
                fx:'fade',
                speed:  1000,
                timeout: 2000,
                pager:  '#thumbnails ul',
                next:   '#next',
                prev:   '#prev',
                pagerAnchorBuilder: function(idx, slide) {
                    return '<li class="thumb"><a href="#" title="Thumbnail"><img width="40" height="40" src="' + slide.src + '"/></a></li>';
                }
              });
            });
        </script>
       
        <title>Dettaglio Annuncio</title>
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
        <div id="containerTop">
            <h1><%= annuncio.getTitolo() %></h1>
            <h3><%= annuncio.getIndirizzo()%>, <%= annuncio.getLocalita()%></h3>
        </div>
        <div id="cointainerLeft">
            <!-- Nav tabs -->
            <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#foto" data-toggle="tab">Foto</a></li>
              <li class="mappa"><a href="#mappa" data-toggle="tab">Mappa</a></li>
              <li><a href="#descrizione" data-toggle="tab">Descrizione</a></li>
              <li><a href="#servizi" data-toggle="tab">Servizi</a></li>
              <li><a href="#regole" data-toggle="tab">Regole</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="foto">
                    <div id="navPhotos">
                        <a id="prev" href=""><img src="dist/image/button_prev.png" alt="Prev photo"></a>
                        <a id="next" href=""><img src="dist/image/button_next.png" alt="Next photo"></a>
                    </div>
                    <div id="photos">                        
                       <img src="gallery/<%= annuncio.getId()%>/1.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/2.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/3.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/4.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/5.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/6.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/7.jpg" alt=""/>
                       <img src="gallery/<%= annuncio.getId()%>/8.jpg" alt=""/>
                       
                    </div>
                    <div id="thumbnails">
                       <ul></ul>
                    </div>
                </div>
                <div class="tab-pane" id="mappa">
                    <div id="map-canvas" style="height:400px;"></div>
                    <!--<script>
                        addMarker(<%= annuncio.getLat() %>,<%= annuncio.getLng() %>);
                    </script>!-->
                </div>
                <div class="tab-pane" id="descrizione"><%= annuncio.getDescrizione()%></div>
                <div class="tab-pane" id="servizi">Servizi</div>
                <div class="tab-pane" id="regole">Regole</div>
            </div>
            <!--
            <ul id="myTab2" class="nav nav-tabs">
              <li class="active"><a href="#commenti" data-toggle="tab">Commenti</a></li>
              <li><a href="#recensioni" data-toggle="tab">Recensioni</a></li>
            </ul>!-->

            <!-- Tab panes -->
            <!--<div class="tab-content">
                <div class="tab-pane active" id="commenti">
                    <div class="comment">
                        <div class="imgUser">
                            <a target="blank" name="review_10767095" class="media-photo media-link">  
                                <img width="68" height="68" title="Collier" src="https://a0.muscache.com/ic/users/4673477/profile_pic/1388281416/original.jpg?interpolation=progressive-bicubic&amp;crop=w:w;*,*&amp;crop=h:h;*,*&amp;resize=68:*&amp;output-format=jpg&amp;output-quality=85" data-original="https://a0.muscache.com/ic/users/4673477/profile_pic/1388281416/original.jpg?interpolation=progressive-bicubic&amp;crop=w:w;*,*&amp;crop=h:h;*,*&amp;resize=68:*&amp;output-format=jpg&amp;output-quality=85" class="lazy" alt="Collier" style="display: inline;">
                            </a>          
                            <div class="nameUser">
                                <a target="blank">Collier</a>
                            </div>
                        </div>
                        <div class="media-body">
                            <div class="panel panel-quote panel-dark ">
                                <div class="panel-body">
                                    <div class="review-text" data-original-text="" data-review-id="10767095">
                                        <p>I absolutely loved Barbara's beautiful Torino apartment! The location was ideal --- just a few steps from the train station, which made arrival and departure a breeze, and day trips to the surrounding area were super simple. The apartment itself is in a gorgeous old palace, and is decorated beautifully -- and the sunsets over the courtyard from the balcony are breathtaking. Barbara was super helpful, and provided tons of maps and snacks to get me started enjoying Torino. I was only there for a week, but I cannot WAIT to come back again soon!</p>
                                    </div>
                                    <div class="text-muted review-subtext">
                                        <div class="date">Marzo 2014</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane" id="recensioni">
                    Recensioni
                </div>
           </div>!-->
        </div>
        
        <div id="cointainerRight">
            
        </div>
         <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
        <script src="dist/js/bootstrap.min.js"></script> 
        <script>
             $( ".mappa").click(function() {
                initialize(<%= annuncio.getLat() %>,<%= annuncio.getLng() %>);
             });
        </script>
        <script>
            $('#myTab a').click(function (e) {
                e.preventDefault();
                $(this).tab('show');
            });
            
        </script>
    </body>
</html>
