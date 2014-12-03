

<%-- 
    Document   : viewDetailsAnnuncio
    Created on : 12-mar-2014, 18.49.49
    Author     : Andr3A
--%>

<%@page import="ejb.Annuncio"%>
<%@page import="java.util.Iterator"%>
<%@page import="ejb.PropostaPrenotazione"%>
<%@page import="java.util.ListIterator"%>
<%@page import="ejb.Commento"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="ejb.AnnuncioCasa"%>
<%@page import="ejb.Prenotazione"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ricercaAnnunciCasa" scope="session" class="bean.RicercaAnnunciCasa"/>
<% Annuncio annuncio= (Annuncio)request.getAttribute("annuncio");
   List<Prenotazione> prenotazioni = (List<Prenotazione>)request.getAttribute("prenotazioni");
%>
<% Long idUtenteAnnuncio= (Long)request.getAttribute("idUtenteAnnuncio"); %>
<c:set var="opzioni" value="${annuncio.getOpzioni()}"/>
<% List<Commento> commenti= (List<Commento>)request.getAttribute("commenti"); %>
<%
ServletConfig conf = getServletConfig();
ServletContext ctx = conf.getServletContext();
String pathImage=ctx.getInitParameter("pathImage");
int prop;
%>
<%HttpSession s = request.getSession();%>
<c:set var="lp" value="${annuncio.getPropostaPrenotazione()}"/>
<% String index = request.getParameter("index"); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        <script type="text/javascript" src="dist/js/jquery-1.10.1.min.js"></script>
        <!--<script src="dist/js/jquery-1.10.2.js"></script>!-->
        <!--<script src="dist/js/jquery-ui-1.10.4.custom.js"></script>-->
        <!--<script type="text/javascript" src="dist/js/jquery_cycle.js"></script>!-->
        <script type="text/javascript" src=" http://malsup.github.io/jquery.cycle.all.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script src="dist/js/mappaGoogle.js"></script>
        <script src="dist/js/jquery.ui.core.js"></script>
        <script src="dist/js/jquery.ui.widget.js"></script>
        <script src="dist/js/jquery.ui.datepicker.js"></script>
        
        <script>
            function unavailable(date) {
                var busyStarts = ["2014-11-28"];
                var busyEnds = ["2014-11-29"];
                var cssClass = '';
                for (var i=0; i < busyStarts.length; i++) {
                    var busyStart = new Date(busyStarts[i]).setHours(0, 0, 0, 0);
                    var busyEnd = new Date(busyEnds[i]).setHours(0, 0, 0, 0);  
                    if (date >= busyStart && date <= busyEnd) {
                        cssClass = 'ui-datepicker-unselectable ui-state-disabled';
                    }
                 }
                 return [true, cssClass];
            }

            
            $(function() {
                $( "#checkin" ).datepicker({
                    numberOfMonths: 1,
                    beforeShow: function(){
                        $(".ui-datepicker").css('font-size', 12)
                    },
                    showButtonPanel: true,
                    minDate: new Date( $( "#start" ).html()),
                    maxDate: new Date( $( "#end" ).html()),
                    beforeShowDay: unavailable
                });
                $( "#checkout" ).datepicker({
                    numberOfMonths: 1,
                    beforeShow: function(){
                        $(".ui-datepicker").css('font-size', 12)
                    },
                    showButtonPanel: true,
                    minDate: new Date( $( "#start" ).html()),
                    maxDate: new Date( $( "#end" ).html()),
                    beforeShowDay: unavailable
                });
            });
        </script>
        <script>
            $(document).ready(function() {
                $('#photos').cycle({
                    fx: 'fade',
                    timeout: 3500,  // milliseconds between slide transitions (0 to disable auto advance)
                    speed: 1000,  // speed of the transition (any valid fx speed value)
                    fit: 0,  // force slides to fit container 
                    containerResize: 1,  // resize container to fit largest slide 
                    next:   '#next',
                    prev:   '#prev',
                    pager:  '#thumbnails ul',
                    pagerAnchorBuilder: function(idx, slide) {
                        return '<li class="thumb"><a href="#" title="Thumbnail"><img width="40" height="40" src="' + slide.src + '"/></a></li>';
                    }
                });
                

                $('#photos').find('img').batchImageLoad({
                      loadingCompleteCallback: myCallback
                });
            });
        </script>
        <title>Dettaglio Annuncio</title>
    </head>
    <body>
        
        <!--p.getDataInizio().getTime().toString() !-->
        
        <div id="start" style="display:none">
            <c:out value="${annuncio.getDataInizioString()}"/>
        </div>
        <div id="end" style="display:none">
            <c:out value="${annuncio.getDataFineString()}"/>
        </div>    
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
                    
                    <!--Visualizzare nome--> 
                    <ul class="nav navbar-nav navbar-right">
                        <% if(s.getAttribute("userID") != null){  %>
                            <li><a href="/CoHome-war/MainServlet?op=viewUserLogged"> <%= request.getUserPrincipal().getName() %> </a></li>
                            <li><div class="logout"><a href="/CoHome-war/MainServlet?op=logout" class="btn btn-success">Logout</a></div></li>
                        <% } %> 
                    </ul>
                        
                </div><!--/.nav-collapse -->
            </div>
        </div>
        <div id="containerTop">
            <h1><c:out value="${annuncio.getTitolo()}"/></h1>
            <h3><c:out value="${annuncio.getIndirizzo()}"/>, <c:out value="${annuncio.getLocalita()}"/></h3>
            <h4><a href="/CoHome-war/MainServlet?op=viewUser&userID=<%= idUtenteAnnuncio.toString() %>"> Autore </a></h4>
        </div>
        <!--<div id="cointainerLeft">-->
        <div class="container">
            <div class="col-md-9">
                <!-- Nav tabs -->
               <ul id="myTab" class="nav nav-tabs">
                  <li class="active"><a href="#foto" data-toggle="tab">Foto</a></li>
                  <li class="mappa"><a href="#mappa" data-toggle="tab">Mappa</a></li>
                  <li><a href="#descrizione" data-toggle="tab">Descrizione</a></li>
                  <li><a href="#calendario" data-toggle="tab">Calendario</a></li>
                  <li><a href="#regole" data-toggle="tab">Regole</a></li>
                </ul>

                <!-- Tab panes -->
                <div id="myTabContent" class="tab-content">
                    <div class="tab-pane active" id="foto">
                        <div id="navPhotos">
                            <a id="prev" href=""><img src="dist/image/button_prev.png" alt="Prev photo"></a>
                            <a id="next" href=""><img src="dist/image/button_next.png" alt="Next photo"></a>
                        </div>
                        <div id="photos">
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_1.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_2.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_3.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_4.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_5.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_6.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_7.jpg" alt=""/>
                            <img src="gallery/<c:out value="${annuncio.getId()}"/>_8.jpg" alt=""/>
                        </div>
                        <div id="thumbnails">
                            <ul></ul>
                        </div>  
                    </div>
                    <div class="tab-pane" id="mappa">
                        <div id="map-canvas" style="height:400px;"></div>

                    </div>
                    <div class="tab-pane" id="descrizione">
                        <div class="col-7">
                            <c:out escapeXml="false" value="${annuncio.getDescrizione()}"/>
                        </div>
                        <div class="col-5">
                            <table class="table table-bordered table-striped" id="description_details" itemprop="breadcrumb">
                                <tbody>
                                    <c:forEach items="${annuncio.getOpzioni()}" var="opt" varStatus="index">
                                        <tr>
                                            <td><c:out value="${opt.getNome()}"/></td>
                                            <td class="value"><c:out value="${opt.getValore()}"/></td>   
                                        </tr>
                                    </c:forEach>  
                                </tbody>
                            </table>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="tab-pane" id="calendario">Calendario</div>
                    <div class="tab-pane" id="regole">Regole</div>
                </div>

                <ul id="myTab2" class="nav nav-tabs">
                       <li class="active"><a href="#commenti">Commenti</a></li>
                       <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addComment" style="float:right; margin: 10px;">+ Comment</button>
                </ul>

               
    <!-- TabContent Commenti -->
               <div class="tab-content" id="myTabContent">
                   <div id="commenti" class="tab-pane fade active in">
                       <br>
                       <% ListIterator<Commento> iter = commenti.listIterator();
                               int c=0;
                               out.println("<ul class=\"list-group\">");
                               while(iter.hasNext()){
                                   out.println("<li class=\"list-group-item\">");
                                   Commento com = iter.next();
                       %>
                                    <a href="/CoHome-war/MainServlet?op=viewUser&userID=<%= com.getAutore().getId()%>">
                                        <font color="#2A92D1"><h4><%= com.getAutore().getName()%></h4></font>
                                    </a><br>
                                    <%= com.getCommento()%><br><br>
                                    <!-- fare controllo su u -->
                                    <% if( (request.isUserInRole("administrator")) || (com.getAutore().getId().equals(s.getAttribute("userID"))) ){ %>
                                        <a class="deleteComment" data-id="<%= com.getId()%>" href="#">
                                             <span class="glyphicon glyphicon-trash"></span>
                                         </a>
                                    <% } %>

                       <%
                                   out.println("</li>");
                                   c++;
                               }
                               out.println("</ul>");
                       %>
                   </div>

               </div>
            </div>

            <!--<div id="cointainerRight">-->
            <div class="col-md-3">
                <div class="row">
                    <button id="buttonRichiestaPrenotazione" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addPrenotazione" style="float:right; margin: 10px;">Richiesta Prenotazione</button>
                </div>
<!-- ******************  Visualizzazione richieste *********************-->
                <div class="row">
                    <div class="panel-group" id="accordion">
                        <c:forEach items="${lp}" var="a" varStatus="c">
                            <div class="panel panel-default" style="text-align: left;">
                                <c:if test="${a.isAttivo()}">    
                                    <div class="panel-heading">
                                        <h4 class="panel-title text-primary"> 
                                            <a data-toggle="collapse" data-parent="#accordion" href="#collapse<c:out value='${c.index}'/>">
                                                Proposal by <font color="#2A92D1"><c:out value="${a.getUtente().getUsername()}"/></font>
                                            </a>
                                        </h4>
                                    </div>
                                    <div id="collapse<c:out value='${c.index}'/>" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <table class="table">
                                                    <tr> <td>
                                                        <strong>User</strong>: </td> <td>
                                                        <a href="/CoHome-war/MainServlet?op=viewUser&userID=<c:out value="${a.getUtente().getId()}"/>" >
                                                            <c:out value="${a.getUtente().getUsername()}"/>
                                                        </a> 
                                                    </td> </tr>
                                                    
                                                    <tr><td><strong># Guests:</strong></td> <td> <c:out value="${a.getNumeroPosti()}"/></td></tr>
                                                    <tr><td><strong>Start:</strong></td> <td>  <c:out value="${a.getDataInizio().getTime().toString()}"/></td></tr>
                                                    <tr><td><strong>End:</strong></td> <td> <c:out value="${a.getDataFine().getTime().toString()}"/></td></tr>
                                                    <tr><td><strong>Description:</strong></td> <td> <c:out value="${a.getDescrizione()}"/></td></tr>
                                                    <% if(idUtenteAnnuncio.equals(s.getAttribute("userID"))){ %>
                                                    <tr>
                                                        <td>
                                                            <a class="accettaProposta" data-id="<c:out value="${a.getId()}"/>" href="#">
                                                                <button type="button" class="btn btn-success">
                                                                    Accept
                                                                </button>
                                                            </a>
                                                        </td>
                                                        <td>
                                                            <a class="rifiutaProposta" data-id="<c:out value="${a.getId()}"/>" href="#">
                                                                <button type="button" class="btn btn-danger">
                                                                    Refuse
                                                                </button>
                                                            </a>
                                                        </td>
                                                    </tr>
                                                    <% } %>
                                            </table>
                                        </div>
                                    </div>
                                </c:if>
                            </div>
                        </c:forEach>
                    </div>
                </div>
    <!-- ******************  Fine richieste *********************--> 
    
                <div class="modal fade" id="addPrenotazione">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">Invia la tua richiesta di prenotazione</h4>
                        </div>

                    <form role="form" action="/CoHome-war/MainServlet" >
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="textComment">Descrizione.</label>
                                <textarea id="description" name="description" class="form-control" rows="3"></textarea>
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
                                        <c:forEach begin="1" end="${annuncio.getNumeroPosti()}" step="1" var="i">
                                            <option value="<c:out value='${i}'/>"><c:out value="${i}"/> Ospite</option>
                                        </c:forEach>>
                                    </select>
                                    <div aria-hidden="true" class="btn btn-large dropdown-toggle input-contrast"></div>
                                </div>
                            </div>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Annulla</button>
                            <button id="sendRichiestaPrenotazione" type="button" class="btn btn-primary">Invia Richiesta</button>
                        </div>
                    </form>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

            </div>
        </div>
        <div id="risultati">
        </div>
        <div id="stato">
        </div>
    <!-- ********** Modal Add Commenti ************* -->
        <div class="modal fade" id="addComment">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title">Aggiungi Commento</h4>
                </div>

                <!-- Inizio del Form -->
                <!-- Modificare parametricamente il passaggio dell'id utente -->
                <form role="form" action="/CoHome-war/MainServlet">
                    <div class="modal-body">
                      <div class="form-group">
                        <label for="textComment">Commento...</label>
                        <textarea id="newCommento" name="newCommento" class="form-control" rows="3"></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                        <input type="hidden" value="addCommento" name="op">
    <!-- utente e annuncio da fare in modo parametrico -->
                        <input id="utente" type="hidden" value="<%= s.getAttribute("userID") %>" name="utente">
                        <input id="annuncio" type="hidden" value="<%= annuncio.getId() %>" name="annuncio">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button id="sendComment" type="button" class="btn btn-primary">Add Comment</button>
                    </div>
                </form>
              </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
         </div><!-- /.modal add commenti -->
        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
        <script src="dist/js/bootstrap.min.js"></script> 
        <script>
             $( ".mappa").click(function() {
                initializeSingleMarker(<c:out value="${annuncio.getLat()}"/>,<c:out value="${annuncio.getLng()}"/>);
             });
        </script>
        <script>
            $("#sendRichiestaPrenotazione").click(function(){
                var id= <%= annuncio.getId()%>;
                $.ajax({
                    type: "POST",
                    url : "MainServlet",
                    //data: "op=provaAjax",
                    data: "op=addPropostaPrenotazione&checkin=" + $("#checkin").val() + 
                            "&checkout=" + $("#checkout").val() + "&guests=" + $("#guests").val() +
                            "&desc=" + $("#description").val()+ "&id="+ id + 
                            "&utenteAnnuncioId=" + $("#utenteAnnuncioId").val(),
                    success : function (data,stato) {
                        $("#addPrenotazione").modal('hide');
                        alert("Proposta di prenotazione correttamente inviata");
                        setTimeout(
                            function() 
                            {
                               location.reload();
                            }, 0001); 
                    },
                    error : function (richiesta,stato,errori) {
                        alert("ERRORE. "+errori);
                    }
                });
            });
        </script>
        <script>
            $("#sendComment").click(function(){
//                alert("valori: "+$("#newCommento").val()+" "+ $("#annuncio").val()+" "+$("#utente").val());
                $.ajax({
                    type: "POST",
                    url : "MainServlet",
                    //data: "op=provaAjax",
                    data: "op=addCommento&newCommento="+ $("#newCommento").val() +
                            "&utente="+ $("#utente").val()+"&annuncio="+ $("#annuncio").val(),
                    success : function (data) {
                        setTimeout(
                            function() 
                            {
                               location.reload();
                            }, 0001); 
                    },
                    error : function (richiesta,stato,errori) {
                        alert("ERRORE. "+errori);
                    }
                });
            });
        </script>
        <script>
            $(".deleteComment").click(function(){
                    var aid= <%= annuncio.getId()%>;
                    var uid= <%= s.getAttribute("userID") %>;
                    $.ajax({
                        type: "POST",
                        url : "MainServlet",
                        data: "op=deleteCommento&id="+$(this).attr('data-id')+"&utente="+uid+"&annuncio="+aid,
                        success : function (data,stato) {
//                            alert("Commento correttamente eliminato");
                            setTimeout(
                                function() 
                                {
                                   location.reload();
                                }, 0001);
                        },
                        error : function (richiesta,stato,errori) {
                            alert("ERRORE. "+errori);
                        }
                    });

                
            });
        </script>
        
        <script>
            $(".accettaProposta").click(function(){
                    var pid= $(this).attr('data-id');
                    var uid= <%= idUtenteAnnuncio %>;
                    $.ajax({
//                        /CoHome-war/MainServlet?op=addPrenotazione&propostaID=<c:out value="${a.getId()}"/>&userID=<c:out value="${idUtenteAnnuncio}"/>
                        type: "POST",
                        url : "MainServlet",
                        data: "op=addPrenotazione&propostaID="+pid+"&userID="+uid,
                        success : function (data,stato) {
//                            alert("Commento correttamente eliminato");
                            setTimeout(
                                function() 
                                {
                                   location.reload();
                                }, 0001);
                        },
                        error : function (richiesta,stato,errori) {
                            alert("ERRORE. "+errori);
                        }
                    });

                
            });
        </script>
        
        <script>
            $(".rifiutaProposta").click(function(){
                    var pid= $(this).attr('data-id');
                    var uid= <%= idUtenteAnnuncio %>;
                    $.ajax({
//                        /CoHome-war/MainServlet?op=addPrenotazione&propostaID=<c:out value="${a.getId()}"/>&userID=<c:out value="${idUtenteAnnuncio}"/>
                        type: "POST",
                        url : "MainServlet",
                        data: "op=refPropostaPrenotazione&propostaID="+pid+"&userID="+uid,
                        success : function (data,stato) {
//                            alert("Commento correttamente eliminato");
                            setTimeout(
                                function() 
                                {
                                   location.reload();
                                }, 0001);
                        },
                        error : function (richiesta,stato,errori) {
                            alert("ERRORE. "+errori);
                        }
                    });

                
            });
        </script>
        
        <script>
        $("#checkin").change( function() {
            $( "#checkout" ).datepicker("destroy");
            $( "#checkout" ).datepicker({
                minDate: new Date($( "#checkin" ).val()),
                maxDate: new Date( $( "#end" ).html())
            });
        });
        </script>
    </body>
</html>