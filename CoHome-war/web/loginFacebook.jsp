<%-- 
    Document   : loginFacebook
    Created on : 18-giu-2014, 14.21.24
    Author     : marco
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="pathBrowser" scope="session" class="String"/>
<jsp:useBean id="clientId" scope="session" class="String"/>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta content="IE=edge" http-equiv="X-UA-Compatible">
        <meta content="width=device-width, initial-scale=1" name="viewport">
        <title>Login Facebook</title>
        
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="dist/css/bootstrap.min.css">
        <!-- Custom styles for this template -->
        <link rel="stylesheet" href="dist/css/template-cohome.css">
        <!--JQuery UI Theme!-->
        <link rel="stylesheet" href="dist/css/excite-bike/jquery-ui-1.10.4.custom.css">
        <!-- FancyBox css-->
        <link rel="stylesheet" type="text/css" href="dist/css/jquery.fancybox.css?v=2.1.5" media="screen" />
    </head>
    
    <body>
        <% 
        try {
            //Runtime.getRuntime().exec(pathBrowser + " https://www.facebook.com/dialog/oauth?client_id=" + clientId + "&redirect_uri=https://www.facebook.com/connect/login_success.html");
            Runtime.getRuntime().exec(pathBrowser + " https://www.facebook.com/dialog/oauth?client_id=" + clientId + "&redirect_uri=http://localhost:8080/CoHome-war/GetAccessToken"); 
        } catch (Exception e) {}         
        %>
        
        
        <div style="text-align:center">
            <h2>Home</h2>
            <p>Ritorna alla home page.</p>
            <img width="192" height="192" src="dist/image/home.png">
            <p><a href="/CoHome-war/MainServlet?op=index"  class="btn btn-primary">Home</a></p>
        </div>
    </body>
</html>
