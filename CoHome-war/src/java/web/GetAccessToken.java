package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.restfb.DefaultWebRequestor;
import com.restfb.WebRequestor.Response;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import utility.Configurazione;

@WebServlet(name = "GetAccessToken", urlPatterns = {"/GetAccessToken"})
public class GetAccessToken extends HttpServlet {
    
    private static String clientId="";
    private static String clientSecret="";
    private static String urlGoogleApp="";
    
    public void init(ServletConfig conf)throws ServletException{
        super.init(conf);
        ServletContext ctx = conf.getServletContext();
        clientId = ctx.getInitParameter("clientId");
        clientSecret = ctx.getInitParameter("clientSecret");
        urlGoogleApp = ctx.getInitParameter("urlGoogleApp");
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");       
        //String clientId = "250827445119511";
        //String clientSecret = "a2d124f9892d000aadbb0d83925251c6";
        
        //Richiede il code.
        String redirectURL = request.getRequestURL().toString();
        String code = request.getParameter("code");
        if ((code == null)) {
            String tokenReqUrl = "https://graph.facebook.com/oauth/authorize?redirect_uri=" + redirectURL + "&client_id=" + clientId;
            response.sendRedirect(tokenReqUrl);
            return;
        } 
        
        // scambia il code con l'access token
        String str = "https://graph.facebook.com/oauth/access_token?client_id=" + clientId + "&redirect_uri=" + redirectURL + "&client_secret=" + clientSecret + "&code=" + code;  
        DefaultWebRequestor webRequestor = new DefaultWebRequestor();
        Response fbresponse = webRequestor.executeGet(str);
        String rawAccessToken = new String(fbresponse.getBody());
        String accessToken = rawAccessToken.split("&")[0];
        
        Configurazione conf = Configurazione.getConfigurazione();
        conf.setAccessToken(accessToken);
        conf.setUrlGoogleApp(urlGoogleApp);
        
        getServletContext().getRequestDispatcher("/accessToken.jsp").forward(request,response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
