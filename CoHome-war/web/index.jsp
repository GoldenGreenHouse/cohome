<%-- 
    Document   : index
    Created on : 3-mar-2014, 9.53.46
    Author     : Andr3A
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
        <!--JQuery UI Theme!-->
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        <!-- FancyBox css-->
        <link rel="stylesheet" type="text/css" href="dist/css/jquery.fancybox.css?v=2.1.5" media="screen" />

        <script type="text/javascript" src="dist/js/jquery-1.10.1.min.js"></script>
        <script src="dist/js/jquery.fancybox.js?v=2.1.5"></script>
        <!--<script src="dist/js/jquery-1.10.2.js"></script>!-->
        <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script src="dist/js/jquery.ui.core.js"></script>
        <script src="dist/js/jquery.ui.widget.js"></script>
        <script src="dist/js/jquery.ui.datepicker.js"></script>
        
        <script src="dist/js/mappaGoogle.js"></script>
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
                        $(".ui-datepicker").css('font-size', 12) 
                    },        
                showButtonPanel: true,
                minDate: 0
            });
            $( "#checkout" ).datepicker({
                numberOfMonths: 1,
                beforeShow: function(){    
                        $(".ui-datepicker").css('font-size', 12) 
                    },
                showButtonPanel: true,
                minDate:  0
                });     
         }); 
        </script>
        <script>
            $( document ).ready(function() {
                $('.fancybox').fancybox({
                    scrolling	: 'no',
                    titleShow	: false,
                    onClosed	: function() {
                        $("#login_error").hide();
                    }
                });
            });
	</script>
    </head>
    <body>
        <c:if test="${pageContext.request.getAttribute('error')!= null }"> 
            <div class="alert alert-danger" style="width:90%; text-align: center; margin-left: auto; margin-right: auto;">  
                <a class="close" data-dismiss="alert">×</a>
                <strong>Warning!</strong> <c:out value="${pageContext.request.getAttribute('error')}"/>
            </div>  
        </c:if>
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
                                                      
                    <c:choose>
                    <c:when test="${pageContext.request.userPrincipal == null}">
                    
                        <div class="login">
                            <form id="login_form" method="post" action="j_security_check">
                                <label for="login_name">Username: </label>
                                <input type="text" id="login_name" name="j_username" size="10" />
                                <label for="login_pass">Password: </label>
                                <input type="password" id="login_pass" name="j_password" size="10" />
                                <input type="submit" value="Login"  class="btn btn-success">
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="logout"><a href="/CoHome-war/MainServlet?op=logout" class="btn btn-success">Logout</a></div>
                    </c:otherwise>
                    </c:choose>
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
                                <div class="input-wrapper" id="posti">
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
                    <p><a href="/CoHome-war/MainServlet?op=InserisciAnnuncioCasa"  class="btn btn-primary">Inserisci Annuncio</a></p>
                </div>
                <div class="col-xs-4">
                    <c:choose>
                    <c:when test="${pageContext.request.userPrincipal == null}">
                        <h2>Login</h2>
                        <p>Entra per accedere al tuo profilo.</p>
                        <img width="192" height="192" src="dist/image/login.png">
                        <p><a class="fancybox btn btn-primary" href="#login" >Login</a></p>
                    </c:when>
                    <c:otherwise> 
                        <h2>Area Riervata</h2>
                        <p>Entra per accedere al tuo profilo.</p>
                        <img width="192" height="192" src="dist/image/area-riservata.png">
                        <p><a class="fancybox btn btn-primary" href="#areaRiservata" >Area Riservata</a></p>
                    </c:otherwise>
                    </c:choose>           
                </div>
                <div class="col-xs-4">
                    <h2>Registrati</h2>
                    <p>Condividi la tua casa in pochissimi passi.</p>
                    <img width="192" height="192" src="dist/image/home.png">
                    <p><a href="/CoHome-war/MainServlet?op=registrazione"  class="btn btn-primary">Registrati</a></p>
                </div>
            </div>
            <hr>
        </div>
        <div id="login" style="display:none">
            <form id="login_form" method="post" action="j_security_check">
                <center><h2>Login CoHome</h2></center>
                <p>
                        <label for="login_name">Username: </label>
                        <input type="text" id="login_name" name="j_username" size="30" />
                </p>
                <p>
                        <label for="login_pass">Password: </label>
                        <input type="password" id="login_pass" name="j_password" size="30" />
                </p>
                <p>
                        <center><input type="submit" value="Login"  class="btn btn-success"></center>
                </p>
            </form>
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
        $( "#checkout" ).datepicker("destroy");
        $( "#checkout" ).datepicker({
            minDate:  new Date($( "#checkin" ).val())
        });
    });
    </script>
</body>
</html>