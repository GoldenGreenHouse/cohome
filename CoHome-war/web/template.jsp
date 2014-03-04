<%-- 
    Document   : template.jsp
    Created on : 4-mar-2014, 15.26.45
    Author     : Andrea
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
    <head>
        <meta charset="utf-8">
        <meta content="IE=edge" http-equiv="X-UA-Compatible">
        <meta content="width=device-width, initial-scale=1" name="viewport">
        <title>CoHome Index</title>
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
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
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner">
                <div class="item active" data-bg-opacity="1">
                    <img src="dist/image/slide-1.jpg" alt="Slide 1">
                    <div class="carousel-caption">
                    </div>
                </div>
                <div class="item" data-bg-opacity="1">
                    <img src="dist/image/slide-2.jpg" alt="Slide 2">
                    <div class="carousel-caption">
                    </div>
                </div>
                <div class="item" data-bg-opacity="1">
                    <img src="dist/image/slide-3.jpg" alt="Slide 3">
                    <div class="carousel-caption">
                    </div>
                </div>
            </div>
            <div class="search-area">
                <div class="page-container page-container-fixed">
                    <div class="row">
                        <div class="col-11">
                        <div style="display: block; opacity: 1;" id="blob-bg"></div>
                         <h1 class="text-special">Trova un alloggio.</h1>
                            <form class="custom show-search-options position-left form-inline" id="search_form" action="/CoHome-war/MainServlet">
                                <div class="input-wrapper">
                                    <input id="location" type="text" placeholder="Dove vuoi andare?" name="location" autocomplete="off"  class="location input-large input-contrast" onFocus="geolocate()">
                                    <p style="display:none;" class="bad" id="enter_location_error_message">Seleziona la località</p>
                                </div>
                                <div class="input-wrapper" id="checkinWrapper">
                                    <input type="text" placeholder="Check-in" name="checkin" class="checkin search-option input-large input-contrast ui-datepicker-target" id="checkin">
                                    <span class="search-area-icon"></span>
                                </div>
                                <div class="input-wrapper" id="checkoutWrapper">
                                    <input type="text" placeholder="Check-out" name="checkout" class="checkout search-option input-large input-contrast ui-datepicker-target" id="checkout">
                                    <span class="search-area-icon search-area-icon-checkout"></span>
                                </div>
                                <div class="input-wrapper">
                                    <div class="custom-select-container">
                                        <select class="search-option" name="guests" id="guests">
                                            <option value="1">1 Ospite</option>
                                            <option value="2">2 Ospiti</option>
                                            <option value="3">3 Ospiti</option>
                                            <option value="4">4 Ospiti</option>
                                            <option value="5">5 Ospiti</option>
                                            <option value="6">6+ Ospiti</option>                         
                                         </select>
                                        <div aria-hidden="true" class="btn btn-large dropdown-toggle input-contrast">
                                          
                                        </div>
                                    </div>
                                </div>
                                <input type="hidden" value="cercaAnnunci" name="op">
                                <button id="submit_location" class="btn btn-primary btn-large btn-contrast" type="submit">Cerca</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
        <div class="container">
            <div class="starter-template">
                <a href="CoHome-war/MainServlet?op=inserisciAnnuncio">Inserisci Annuncio</a>
            </div>
        </div><!-- /.container -->
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
    <script src="dist/js/bootstrap.min.js"></script>  
    <script>
    $( document ).ready(function() {
       $('.carousel').carousel({
             interval: 5000,
             wrap: true,
             pause:""
        });       
    });      
    </script>
</body>
</html>
