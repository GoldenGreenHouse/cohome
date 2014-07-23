/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import com.google.gson.Gson;
import ejb.AnnuncioCasa;
import ejb.GestoreAnnunci;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jabsorb.JSONSerializer;
import org.jabsorb.serializer.MarshallException;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author Andr3A
 */
public class JSONServlet extends HttpServlet {
    @EJB
    private GestoreAnnunci gestoreAnnunci;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action= request.getParameter("op");
        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet JSONServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet JSONServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
        
        if(action.equals("cercaAnnunci")){      
            try(PrintWriter out = response.getWriter()){
                JSONObject location = gestoreAnnunci.getCoordinate(request.getParameter("location"));
                List<AnnuncioCasa> annunci=gestoreAnnunci.trovaAnnunciCasa(Double.parseDouble(location.opt("lat").toString()),Double.parseDouble(location.opt("lng").toString()));
                Articolo2 a= new Articolo2("Prova1",true,true,new Info("Andrea"));
                  
                Gson gson = new Gson();
                out.println(annunci);
                out.println(location.toString());
                out.println(gson.toJson(annunci)); 
                JSONSerializer serializer = new JSONSerializer();
                out.println(serializer.toJSON(a));
                //request.setAttribute("annunci", annunci);
                //request.setAttribute("lat", location.opt("lat") );
                //request.setAttribute("lng", location.opt("lng") );
                //getServletContext().getRequestDispatcher("/viewAnnunci.jsp").forward(request,response);
            }catch(JSONException e){} catch (MarshallException ex) {
                Logger.getLogger(JSONServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
class Articolo2 {
 
    private String articolo;
    private boolean pubblicato;
    private boolean interessante;
    
     
    Info info;
    Articolo2(String a, boolean b ,boolean b1, Info info){
        articolo=a;
        pubblicato=b;
        interessante=b1;
        info=info;
    }
   
}
 
class Info {
     
    private String autore;
    Info(String a){
        autore=a;
    }
     
    
}