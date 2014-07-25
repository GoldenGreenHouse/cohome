<%-- 
    Document   : user
    Created on : 7-apr-2014, 10.21.42
    Author     : Alessandro
--%>

<%@page import="ejb.UserComponent"%>
<%@page import="java.util.Iterator"%>
<%@page import="ejb.Annuncio"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% UserComponent utente = (UserComponent)request.getAttribute("utente"); %>
<% List<Annuncio> annunci = utente.getAnnunci(); %>
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
                </div><!--/.nav-collapse -->
            </div>
        </div>
        
        <div class="container">
            <div class="col-md-3">
                <div class="well well-lg">
                    <img src="img/profilo.jpg" alt="foto profilo" class="img-circle" width="130">
                </div>
            </div>
            
            <div class="col-md-9">
                <div class="well well-lg">
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
        <script src="dist/js/bootstrap.min.js"></script> 
    </body>
</html>
