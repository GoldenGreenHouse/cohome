<%-- 
    Document   : viewDetailsAnnuncio
    Created on : 12-mar-2014, 18.49.49
    Author     : Andr3A
--%>

<%@page import="java.util.ListIterator"%>
<%@page import="ejb.Commento"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.File"%>
<%@page import="java.util.List"%>
<%@page import="ejb.AnnuncioCasa"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="ricercaAnnunciCasa" scope="session" class="Bean.RicercaAnnunciCasa"/>
<c:set var="index" value="${pageContext.request.getParameter('index')}"/>
<c:set var="annuncio" value="${ricercaAnnunciCasa.getSingleAnnuncio(index)}"/>
<% List<Commento> commenti= (List<Commento>)request.getAttribute("commenti"); %>

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
        <!--<script type="text/javascript" src="dist/js/jquery_cycle.js"></script>!-->
       <script type="text/javascript" src=" http://malsup.github.io/jquery.cycle.all.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&sensor=false&libraries=places"></script>
        <script src="dist/js/mappaGoogle.js"></script>
        <!--<script type="text/javascript">
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
        </script>!-->
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
            <h1><c:out value="${annuncio.getTitolo()}"/></h1>
            <h3><c:out value="${annuncio.getIndirizzo()}"/>, <c:out value="${annuncio.getLocalita()}"/></h3>
        </div>
        <div id="cointainerLeft">
            <!-- Nav tabs -->
           <!-- <ul id="myTab" class="nav nav-tabs">
              <li class="active"><a href="#foto" data-toggle="tab">Foto</a></li>
              <li class="mappa"><a href="#mappa" data-toggle="tab">Mappa</a></li>
              <li><a href="#descrizione" data-toggle="tab">Descrizione</a></li>
              <li><a href="#calendario" data-toggle="tab">Calendario</a></li>
              <li><a href="#regole" data-toggle="tab">Regole</a></li>
            </ul>-->

            <!-- Tab panes -->
            <!--<div id="myTabContent" class="tab-content">
                <div class="tab-pane active" id="foto">
                    <div id="navPhotos">
                        <a id="prev" href=""><img src="dist/image/button_prev.png" alt="Prev photo"></a>
                        <a id="next" href=""><img src="dist/image/button_next.png" alt="Next photo"></a>
                    </div>
                    <div id="photos">                        
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/1.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/2.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/3.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/4.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/5.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/6.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/7.jpg" alt=""/>
                       <img src="gallery/<c:out value="${annuncio.getId()}"/>/8.jpg" alt=""/>
                       
                    </div>
                    <div id="thumbnails">
                       <ul></ul>
                    </div>
                </div>
                <div class="tab-pane" id="mappa">
                    <div id="map-canvas" style="height:400px;"></div>
                   
                </div>
                <div class="tab-pane" id="descrizione"><c:out value="${annuncio.getDescrizione()}"/></div>
                <div class="tab-pane" id="calendario">Calendario</div>
                <div class="tab-pane" id="regole">Regole</div>
            </div>-->
            
         <ul id="myTab2" class="nav nav-tabs">
                <li class="active"><a href="#commenti">Commenti</a></li>
                <li><a href="#recensioni">Recensioni</a></li>
            </ul>
            
            <button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#addComment" style="float:right; margin: 10px;">+ Comment</button>
            <!-- TabContent -->
            <div class="tab-content" id="myTabContent">
                <div id="commenti" class="tab-pane fade active in">
                    <br>
                    <% ListIterator<Commento> iter = commenti.listIterator();
                            int c=0;
                            while(iter.hasNext()){
                                out.println("<div class=\"well well-lg\">");
                                Commento a=iter.next();
                    %>
                    <h2><%= a.getAutore()%></h2><br>
                    <%= a.getCommento()%><br><br>
                    <a href="/CoHome-war/MainServlet?op=deleteCommento&id=<%= a.getId()%>"><font size="2">Cancella</font></a>
                                
                    <%
                                out.println("</div> <br>");
                                c++;
                            }
                    %>
                </div>
                
                <div id="recensioni" class="tab-pane fade">
                  <p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid. Exercitation +1 labore velit, blog sartorial PBR leggings next level wes anderson artisan four loko farm-to-table craft beer twee. Qui photo booth letterpress, commodo enim craft beer mlkshk aliquip jean shorts ullamco ad vinyl cillum PBR. Homo nostrud organic, assumenda labore aesthetic magna delectus mollit. Keytar helvetica VHS salvia yr, vero magna velit sapiente labore stumptown. Vegan fanny pack odio cillum wes anderson 8-bit, sustainable jean shorts beard ut DIY ethical culpa terry richardson biodiesel. Art party scenester stumptown, tumblr butcher vero sint qui sapiente accusamus tattooed echo park.</p>
                </div>
             
            </div>
        </div>
        
        <div id="cointainerRight">
            
        </div>
        <!-- Modal -->
        <div class="modal fade" id="addComment">
            <div class="modal-dialog">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                  <h4 class="modal-title">Modal title</h4>
                </div>
                  
                <!-- Inizio del Form -->
                <!-- Modificare parametricamente il passaggio dell'id utente -->
                <form role="form" action="/CoHome-war/MainServlet">
                    <div class="modal-body">
                      <div class="form-group">
                        <label for="textComment">Commento...</label>
                        <textarea name="newCommento" class="form-control" rows="3"></textarea>
                      </div>
                    </div>
                    <div class="modal-footer">
                      <input type="hidden" value="addCommento" name="op">
                      <input type="hidden" value="1" name="utente">
                      <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                      <button type="submit" class="btn btn-primary">Add Comment</button>
                    </div>
                </form>
              </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
         </div><!-- /.modal -->
         <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>!-->
        <script src="dist/js/bootstrap.min.js"></script> 
        <script>
             $( ".mappa").click(function() {
                initialize(<c:out value="${annuncio.getLat()}"/>,<c:out value="${annuncio.getLng()}"/>);
             });
        </script>
        
    </body>
</html>
