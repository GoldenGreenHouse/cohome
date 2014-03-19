<%-- 
    Document   : index
    Created on : 3-mar-2014, 9.53.46
    Author     : Andr3A
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
        
        <script src="dist/js/jquery-1.10.2.js"></script>
        <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script src="dist/js/jquery.ui.core.js"></script>
        <script src="dist/js/jquery.ui.widget.js"></script>
        <script src="dist/js/jquery.ui.datepicker.js"></script>
        
        <script type="text/javascript">
            window.onload=function initialize() {
                var input = document.getElementById('location');
                new google.maps.places.Autocomplete(input);
            };
            google.maps.event.addDomListener(window, 'load', initialize);
        </script>
        <script>
        $(function() {
            $( "#checkin" ).datepicker({
                numberOfMonths: 1,
                beforeShow: function(){    
                        $(".ui-datepicker").css('font-size', 13) 
                    },
                showButtonPanel: true,
                minDate: 0
            });
            $( "#checkout" ).datepicker({
                numberOfMonths: 1,
                showButtonPanel: true,
                minDate:  $( "#checkin" ).datepicker({ dateFormat: 'dd,MM,yyyy' }).val()
                });
              
                
         }); 
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
            <h1>Welcome in CoHome</h1>
            <p class="marketing-byline">Condividi la tua Casa e le tue Esperienze!</p>
            <hr>
            <div class="row">
                <div class="col-xs-4">
                    <h2>Inserisci Annuncio</h2>
                    <p>Condividi la tua casa in pochissimi passi.</p>
                    <img width="192" height="192" src="dist/image/home.png">
                    <p><a href="/CoHome-war/MainServlet?op=inserisciAnnuncio"  class="btn btn-primary">Inserisci Annuncio</a></p>
                </div>
                <div class="col-xs-4">
                    <h2>Login</h2>
                    <p>Entra per accedere al tuo profilo.</p>
                    <img width="192" height="192" src="dist/image/login.png">
                    <p><a href="#" target="_blank" class="btn btn-primary">Login</a></p>
                </div>
                <div class="col-xs-4">
                    <h2>Inserisci Annuncio</h2>
                    <p>Condividi la tua casa in pochissimi passi.</p>
                    <img width="192" height="192" src="dist/image/home.png">
                    <p><a href="/CoHome-war/MainServlet?op=inserisciAnnuncio"  class="btn btn-primary">Inserisci Annuncio</a></p>
                </div>
            </div>
            <hr>
        </div>
        <!-- /.container -->
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
    $("#checkin").change( function() {
        alert($( "#checkin" ).datepicker({ dateFormat: 'dd,MM,yyyy' }).val());
        $( "#checkout" ).datepicker({
            startDate:  $( "#checkin" ).datepicker({ dateFormat: 'dd,MM,yyyy' }).val()
        });
    });  
    </script>
</body>
</html>