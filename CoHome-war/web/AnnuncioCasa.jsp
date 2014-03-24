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
                var input_address = $("#address").val();
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
                            $('<label>'+valore+' : '+contenuto+'</label><br/>').appendTo('#addOption');
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
        
        <h1>Inserisci i dati dell'annuncio della disponibilita' di una casa</h1>
           
        <div id="finestra" title="Gestione opzioni">
          <table>
              <tr>
                  <td>
                      <p>Valore dell'opzione&nbsp&nbsp&nbsp&nbsp</p>
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

        <form name="f1" method="POST" action="/CoHome-war/MainServlet" enctype="multipart/form-data">
        <!-- Nav tabs -->
            <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#descrizione" data-toggle="tab">Descrizione</a></li>
              <li><a href="#opzioni" data-toggle="tab">Opzioni</a></li>
              <li><a href="#foto" data-toggle="tab">Foto</a></li>
            </ul>

            <!-- Tab panes -->
            <div class="tab-content">
                <div class="tab-pane active" id="descrizione">
                    <table id="tabella"
                        <tr>
                            <td><label>Titolo</label></td>
                            <td><input name="titolo" type="text" size="20"/></td>
                        </tr>
                        <tr>
                            <td><label>Indirizzo</label></td>
                            <td><input name="indirizzo" type="text" id="address" size="20"/></td>
                        </tr>
                        <tr>
                            <td><label>Numero posti</label></td>
                            <td><input name="numeroPosti" type="text" id="GetMaps" size="20"/></td>
                        </tr>
                        <tr>
                            <td><label>Clicca sul campo per inserire la data di inizio</label></td>
                            <td id="dataInizio">
                                <input
                                    name="dataInizio"
                                    id="dataInizioId"
                                    type="text"
                                    maxlength="20"
                                    size="20"
                                />
                            </td>

                            <script type="text/javascript">
                                $(document).ready(function(){
$('#dataInizioId').datepicker({ dateFormat: 'dd/mm/yy' });
                                });
                            </script>
                        </tr>
                        <tr>
                            <td><label>Clicca sul campo per inserire la data di fine</label></td>
                            <td id="dataFine">
                                <input
                                    name="dataFine"
                                    id="dataFineId"
                                    type="text"
                                    maxlength="20"
                                    size="20"
                            />
                            </td>

                            <script type="text/javascript">
                                $(document).ready(function(){
                                    $('#dataFineId').datepicker({ dateFormat: 'dd/mm/yy' });
                                });
                            </script>
                        </tr>
                        <tr>
                            <td><label>Descrizione</label></td>
                            <td><input name="descrizione" type="text" size="20"/></td>
                        </tr>
                        <tr>
                            <td><label>Localita'</label></td>
                            <td><input name="localita" type="text" size="20"/></td>
                        </tr>
                    </table>
                </div>
                <div class="tab-pane" id="opzioni">
                    <button id="openFinestra" class="btn btn-warning" >Inserimento Opzioni</button></td>
                    <div id="addOption"></div>
                </div>
                <div class="tab-pane" id="foto">
                    <input type="file" name="nomeFile"/>
                </div>
                
            </div>
            <input type="hidden" name="numerOpzioni" value="">
            <input type="submit" class="btn btn-primary" value="invio" id="GetMaps1"/>
            <input type="hidden" name="op" value="creaAnnuncioCasa">
        </form>
        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
        <script src="dist/js/bootstrap.min.js"></script>
      
    </body>
</html>
