<%-- 
    Document   : user
    Created on : 7-apr-2014, 10.21.42
    Author     : Alessandro
--%>

<%@page import="ejb.Prenotazione"%>
<%@page import="ejb.Recensione"%>
<%@page import="ejb.UserComponent"%>
<%@page import="java.util.Iterator"%>
<%@page import="ejb.Annuncio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UserComponent utente = (UserComponent)request.getAttribute("utente"); %>
<% List<Annuncio> annunci = utente.getAnnunci(); %>
<% List<Prenotazione> prenotazioni = utente.getPrenotazioni(); %>
<% List<Recensione> recensioni = utente.getRecensioni(); %>
<% HttpSession s = request.getSession();%>
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

                    <!--Visualizzare nome per logout--> 
                    <ul class="nav navbar-nav navbar-right">
                        <% if(s.getAttribute("userIDLogged") != null){  %>
                            <li><a href="/CoHome-war/MainServlet?op=viewUserLogged"> <%= request.getUserPrincipal().getName() %> </a></li>
                            <li><div class="logout"><a href="/CoHome-war/MainServlet?op=logout" class="btn btn-success">Logout</a></div></li>
                        <% } %>
                    </ul>
                                
                </div><!--/.nav-collapse -->
            </div>
        </div>
        
        <div class="container">
            <div class="row"><h2><%= utente.getName() %></h2></div>
            <!-- colonna sx -->
            <div class="col-md-3">
                <div class="well well-lg">
                    <div class="row"><img src="img/profilo.jpg" alt="foto profilo" class="img-circle" width="130"></div>
                </div>
                <div class="well well-lg">
                    <% int sum=0;
                        if(recensioni.size() > 0){
                            Iterator<Recensione> itRec = recensioni.iterator();
                            while(itRec.hasNext()){
                                sum += Integer.parseInt(itRec.next().getValutazione());
                            }
                            sum = sum/recensioni.size();
                            if(sum > 3)
                                out.println("<h2><strong><span class=\"text-success\">"+sum+"/5</span></strong></h2>");
                            else
                                out.println("<h2><strong><span class=\"text-danger\">"+sum+"/5</span></strong></h2>");
                        }
                        else 
                            out.println("<h2><strong><span class=\"text-danger\">Nessun voto pervenuto</span></strong></h2>");
                    %>
                    
                    <% if(!(utente.getId().equals(s.getAttribute("userID")))){ %>
                        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#evaluateModal">Recensisci questo utente</button>
                    <% } %>
                </div>
                <!-- Modal -->
                <div class="modal fade" id="evaluateModal" tabindex="-1" role="dialog" aria-labelledby="evaluateModalLabel" aria-hidden="true">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                                <h4 class="modal-title" id="myModalLabel">Valutazione Utente</h4>
                            </div>
                            <div class="modal-body">
                                <h3> Voto: </h3>
                                <select id="vote" name="vote">
                                    <option value="1"> 1 </option>
                                    <option value="2"> 2 </option>
                                    <option value="3"> 3 </option>
                                    <option value="4"> 4 </option>
                                    <option value="5"> 5 </option>
                                </select>
                                <h3> Commento: </h3>
                                <textarea id="new-evaluate" name="newEvaluate" class="form-control" rows="3"></textarea>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button id="add-evaluate" type="button" class="btn btn-primary">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="col-md-9">
                <div class="panel-group" id="accordion">
                <!--I miei annunci-->
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">
                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                  I miei annunci
                                </a>
                            </h3>
                        </div>
                        <div id="collapseOne" class="panel-collapse collapse in">
                            <div class="panel-body">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>
                                            #
                                        </th>
                                        <th>
                                            Titolo
                                        </th>
                                        <th>
                                            Inizio
                                        </th>
                                        <th>
                                            Fine
                                        </th>
                                        <th>
                                            Dettagli
                                        </th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        Iterator<Annuncio> it = annunci.iterator();
                                        int c = 1;
                                        while(it.hasNext()){
                                            Annuncio a = it.next();

                                            out.println("<tr>");
                                                out.println("<td>");
                                                    out.println(c);
                                                out.println("</td>");
                                                out.println("<td>");
                                                    out.println(a.getTitolo());
                                                out.println("</td>");
                                                out.println("<td>");
                                                    out.println(a.getDataInizio().getTime().toString());
                                                out.println("</td>");
                                                out.println("<td>");
                                                    out.println(a.getDataFine().getTime().toString());
                                                out.println("</td>");
                                                out.println("<td>");
                                                    out.println("<a href=\"MainServlet?op=viewProposte&idAnnuncio="+a.getId()+"\"> View </a> ");
                                                out.println("</td>");
                                            out.println("</tr>");
                                            c++;
                                        }
                                    %>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                <!-- le prenotazioni accettate -->
                    <% if(utente.getId().equals(s.getAttribute("userID"))){ %>
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                      Le prenotazioni accettate
                                    </a>
                                </h4>
                            </div>
                            <div id="collapseTwo" class="panel-collapse collapse">
                                <div class="panel-body">
                                    <table class="table table-striped">
                                        <thead>
                                            <tr>
                                                <th>
                                                    #
                                                </th>
                                                <th>
                                                    Annuncio
                                                </th>
                                                <th>
                                                    Utente
                                                </th>
                                                <th>
                                                    Data
                                                </th>
                                                <th>
                                                    # posti
                                                </th>
                                                <th>
                                                    Descrizione
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
                                                Iterator<Prenotazione> it3 = prenotazioni.iterator();
                                                int c2 = 1;
                                                while(it3.hasNext()){
                                                    Prenotazione p = it3.next();

                                                    out.println("<tr>");
                                                        out.println("<td>");
                                                            out.println(c2);
                                                        out.println("</td>");
                                                        out.println("<td>");
                                                            out.println(p.getAnnuncio().getTitolo());
                                                        out.println("</td>");
                                                        out.println("<td>");
                                                            out.println(p.getUtente().getName());
                                                        out.println("</td>");
                                                        out.println("<td>");
                                                            out.println(p.getDataPrenotazione().getTime().toString());
                                                        out.println("</td>");
                                                        out.println("<td>");
                                                            out.println(p.getNumeroPosti());
                                                        out.println("</td>");
                                                        out.println("<td>");
                                                            out.println(p.getDescrizione());
                                                        out.println("</td>");
                                                    out.println("</tr>");
                                                    c2++;
                                                }
                                            %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    <%}%>
                    
                </div>
                    
                <!-- le recensioni ricevute -->
                <div class="well well-lg"> 
                    <h3>Recensioni</h3>
                    <ul class="list-group">
                        <%
                            Iterator<Recensione> it2 = recensioni.iterator();
                            while(it2.hasNext()){
                                Recensione r = it2.next();

                                out.println("<li class=\"list-group-item\">");
                                out.println("<strong>Voto: </strong> <span class=\"text-danger\">"+r.getAutoreID().getName()+"</span>");
                                if(Integer.parseInt(r.getValutazione()) < 4){
                                    out.println("<strong>Voto: </strong> <span class=\"text-danger\">"+r.getValutazione()+"</span>");
                                }
                                else{
                                    out.println("<strong>Voto: </strong> <span class=\"text-success\">"+r.getValutazione()+"</span>");
                                }
                                out.println("<strong> Commento: </strong>"+r.getDescrizione());
                                out.println("</li>");
                            }
                        %>
                    </ul>
                </div>
            </div>
        </div>
        <script src="dist/js/bootstrap.min.js"></script> 
        <script>
            $("#add-evaluate").click(function(){
                $.ajax({
                    type: "POST",
                    url : "MainServlet",
                    //data: "op=provaAjax",
                    data: "op=addEvaluate&newEvaluate="+ $("#new-evaluate").val() +
                            "&vote="+ $("#vote").val() +"&utente="+ <%= utente.getId() %> +"&utenteLoggato="+ <%= s.getAttribute("userIDLogged") %>,
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
    </body>
</html>
