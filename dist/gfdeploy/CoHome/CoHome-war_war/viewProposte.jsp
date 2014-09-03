<%-- 
    Document   : viewRichieste
    Created on : 24-apr-2014, 18.11.36
    Author     : Alessandro
--%>

<%@page import="ejb.PropostaPrenotazione"%>
<%@page import="ejb.Annuncio"%>
<%@page import="java.util.Iterator"%>
<%@page import="ejb.RichiestaCasa"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% Annuncio a = (Annuncio)request.getAttribute("annuncio"); 
List<PropostaPrenotazione> lp = a.getPropostaPrenotazione();
int prop;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Proposte</title>
                <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        
        <script src="dist/js/jquery-1.10.2.js"></script>
        <script src="dist/js/jquery-ui-1.10.4.custom.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="col-md-3">
                <div class="well well-lg">
                    <img src="img/profilo.jpg" alt="foto profilo" class="img-circle" width="130">
                </div>
            </div>
            
            <div class="col-md-3">
                <div class="panel-group" id="accordion">
                    
                    <% 
                        Iterator<PropostaPrenotazione> it = lp.iterator();
                        int c = 0;
                        while(it.hasNext()){
                            PropostaPrenotazione p = it.next();
                            out.println(""
                            + "<div class=\"panel panel-default\" style=\"text-align: left;\"> " +
                                "<div class=\"panel-heading\">" +
                                    "<h4 class=\"panel-title text-primary\">" +
                                        "<a data-toggle=\"collapse\" data-parent=\"#accordion\" "+
                                        " href=\"#collapse"+c+"\"> "
                            );
                            out.println("Proposal by "+p.getUtente().getUsername());
                            out.println(""
                            + "</a> </h4> </div> <div id=\"collapse"+c+"\""
                            + " class=\"panel-collapse collapse" );
//                            if(c == 0)
//                                out.print(" in");
                            out.println( 
                            " \"> <div class=\"panel-body\"> "
                            + "");
                            
                            out.println("User: "+p.getUtente().getName());
                            out.println("</br># Guests: "+p.getNumeroPosti());
                            out.println("</br>Start: "+p.getDataInizio().getTime().toString());
                            out.println("</br>End: "+p.getDataFine().getTime().toString());
                            out.println("</br>Descrizione: "+p.getDescrizione());
                            out.println("<button type=\"button\" class=\"btn btn-success\" "
                                    + "style=\"float: right;\">Accept</button> ");
                            
                            out.println( "</div> </div> </div>" );
                            c++;
                        }
                    %>
                </div>
            </div>
        </div>
        <script src="dist/js/bootstrap.min.js"></script>
    </body>
</html>
