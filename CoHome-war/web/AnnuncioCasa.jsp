<%--
    Document : AnnuncioCasa
    Created on : 28-feb-2014, 17.43.21
    Author : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Annuncio casa</title>
         <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">

        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.min.css">
        
        <script src="dist/js/jquery-1.10.2.js"></script>
        <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>

        
        <script src="dist/js/jquery.ui.core.js"></script>
        <script src="dist/js/jquery.ui.widget.js"></script>
        <script src="dist/js/jquery.ui.datepicker.js"></script>

        <meta http-equiv="content-type" content="text/html; charset=utf-8">

        <link href="css/jquery.custom.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&region=IT"></script>
        
        <script>
        $(document).ready(function() {
            $("#GetMaps").click(function(){
                var input_address = $("#address").val() + "  " + $("#country").val();
                var geocoder = new google.maps.Geocoder();
                geocoder.geocode( { address: input_address }, function(results, status) {
                    if (status === google.maps.GeocoderStatus.OK) {
                        var lat = results[0].geometry.location.lat();
                        var lng = results[0].geometry.location.lng();
                        $('<input type="hidden" name="lat" value="'+lat+'">').appendTo('form');
                        $('<input type="hidden" name="lng" value="'+lng+'">').appendTo('form');
                    }
                    else {
                        alert("Google Maps not found address!");
                        }
                    });
            });
        });
        </script>
        
        <script type="text/javascript">
            var i = 0;
            $(document).ready(function(){
                $("#openFinestra").click(function(){
                    $('#finestra').dialog("open");
                    return false;
                });

                $('#finestra').dialog({
                    modal: true,
                    autoOpen: false,
                    width:500,
                    
                    buttons: {
                        "Aggiungi": function(){

                            //recupera l'opzione
                            var contenuto = $("#descrizione_opzione").val();
                            //pulisce la textarea
                            document.getElementById("descrizione_opzione").value = " ";
                            //$("input[type = textarea ][name = opzione]").val(" ");
                            //incrementa il numero di opzioni
                            i++;
                            //setta il numero di opzioni
                            $("input[type=hidden][name=numerOpzioni]").val([i]);
                            //recupera il valore dell'opzione
                            var valore = $("input[type=radio][name=valore]:checked").val();
                            //crea il campo nascosto
                            $('<input type="hidden" name="opzione'+i+'" value="'+valore+'/'+contenuto+'">').appendTo('form');
                            //scrive l'opzione nella tabella
                            $('<li class="list-group-item"><label>'+valore+' </label>: '+contenuto+'</li>').appendTo('#addOption');
                            $( this ).dialog( "close" );
                            },
                        "Chiudi": function() {
                            $( this ).dialog( "close" );
                            }
                        }
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
        
        
          
        <div id="finestra" title="Gestione opzioni">
          <table>
              <tr>
                  <td>
                      <p>Valore dell'opzione</p>
                  </td>
                  <td>
                      <p>Descrivi l'opzione</p>
                  </td>
              </tr>
              <tr>
                  <td>
                      si <input type="radio" name="valore" value="Si" checked="checked"/>
                      no <input type="radio" name="valore" value="No"/>
                  </td>
                  <td>
                      <!--
<input id="descrizione_opzione" name="opzione" type="text" size="30" value=""/>
-->
                      <textarea id="descrizione_opzione" name="opzione" cols="24" rows="3"> </textarea>
                  </td>
              </tr>
          </table>
        </div>
        <div class="container">
            <div class="col-md-9">
                <h1>Inserisci i dati dell'annuncio della disponibilita' di una casa</h1>
                <form class="form-horizontal" role="form" name="f1" method="POST" action="/CoHome-war/MainServlet" enctype="multipart/form-data">
                <!-- Nav tabs -->
                    <ul id="myTab" class="nav nav-tabs">
                      <li class="active"><a href="#descrizione" data-toggle="tab">Descrizione</a></li>
                      <li><a href="#opzioni" data-toggle="tab">Opzioni</a></li>
                      <li><a href="#foto" data-toggle="tab">Foto</a></li>
                    </ul>

                    <!-- Tab panes -->
                    <div class="tab-content">
                        <div class="tab-pane active" id="descrizione">
                            <div class="form-group">
                                <label class="col-md-2 control-label">Titolo</label>
                                <div class="col-md-10"> <input class="form-control" name="titolo" type="text" size="20"/> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Indirizzo</label>
                                <div class="col-md-10"> <input class="form-control" name="indirizzo" type="text" id="address" size="20"/> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Città'</label>
                                <div class="col-md-10"> <input class="form-control" name="localita" type="text" id="country" size="20"/> </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Numero posti</label>
                                <div class="col-md-10">
                                    <select class="form-control" name="numeroPosti" id="GetMaps" style="width:100px;" >
                                        <option value="1" selected="selected">1 Ospite</option>
                                        <option value="2">2 Ospiti</option>
                                        <option value="3">3 Ospiti</option>
                                        <option value="4">4 Ospiti</option>
                                        <option value="5">5 Ospiti</option>
                                        <option value="6">6+ Ospiti</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Clicca sul campo per inserire la data di inizio</label>
                                <div class="col-md-10" id="dataInizio">
                                    <input class="form-control"
                                        name="dataInizio"
                                        id="dataInizioId"
                                        type="text"
                                        maxlength="20"
                                        size="20"
                                    />
                                </div>

                                <script type="text/javascript">
                                    $(document).ready(function(){
                                        $('#dataInizioId').datepicker({ 
                                            beforeShow: function(){    
                                                $("#dataInizioId").css('font-size', 12) 
                                            },
                                        minDate:0
                                        });

                                    });
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Clicca sul campo per inserire la data di fine</label>
                                <div class="col-md-10" id="dataFine">
                                    <input
                                        class="form-control"
                                        name="dataFine"
                                        id="dataFineId"
                                        type="text"
                                        maxlength="20"
                                        size="20"
                                />
                                </div>

                                <script type="text/javascript">
                                    $(document).ready(function(){
                                        $('#dataFineId').datepicker({ 
                                            beforeShow: function(){    
                                                $("#dataFineId").css('font-size', 12) 
                                            },
                                        minDate:0
                                        });

                                    });
                                </script>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Descrizione</label>
                                <div class="col-md-10"><textarea class="form-control" name ="descrizione "rows="4" cols="50"></textarea></div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-2 control-label">Localita'</label>
                                <div class="col-md-10"><input class="form-control" name="localita" type="text" size="20"/></div>
                            </div>
                        </div>
                        <div class="tab-pane" id="opzioni">
                            <div class="row well well-l">
                                <div class="col-md-9">
                                    <ul class="list-group" id="addOption" ></ul>
                                </div>
                                <div class="col-md-3">
                                    <button id="openFinestra" class="btn btn-warning" >Inserimento Opzioni</button>
                                </div>
                            </div>
                        </div>
                        <div class="tab-pane" id="foto">
                            <input type="file" name="nomeFile"/>
                        </div>

                    </div>
                    <div class="col-md-2"></div>
                    <div class="col-md-10">
                        <input type="hidden" name="numerOpzioni" value="">
                        <input type="submit" class="btn btn-primary" value="invio" id="GetMaps1"/>
                        <input type="hidden" name="op" value="creaAnnuncioCasa">
                    </div>
                </form>
            </div>
        </div>
        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
        <script src="dist/js/bootstrap.min.js"></script>
        <script>
            $("#dataInizioId").change( function() {
               
                $("#dataFineId").datepicker("destroy");
                $("#dataFineId").datepicker({
                    minDate:  new Date($("#dataInizioId").val())
                });
            });
        </script>
    </body>
</html>
