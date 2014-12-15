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
import java.util.ListIterator;
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
                ListIterator iter = annunci.listIterator();
                String s ="{\"annunci\":[";
                
                while(iter.hasNext()){
                    JSONObject jsonc = new JSONObject(iter.next());
                    s+=jsonc+",";
                }
                s+="{}]"+",coordinate:"+location.toString()+"}";
                out.println(s);
                
            }catch(JSONException e){} 
        }
        if(action.equals("cercaAnnunciFromGPS")){      
            try(PrintWriter out = response.getWriter()){
                String lat=request.getParameter("latitude");
                String lng=request.getParameter("longitude");
                List<AnnuncioCasa> annunci=gestoreAnnunci.trovaAnnunciCasa2(Double.parseDouble(lat),Double.parseDouble(lng));
                ListIterator iter = annunci.listIterator();
                String s ="{\"annunci\":[";
                
                while(iter.hasNext()){
                    JSONObject jsonc = new JSONObject(iter.next());
                    s+=jsonc+",";
                }
                s+="{}]}";
                out.println(s);
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
