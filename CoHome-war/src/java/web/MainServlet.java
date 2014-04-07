/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;


import ejb.AnnuncioCasa;
import ejb.Commento;
import ejb.GestoreAnnunci;
import ejb.GestoreCommenti;
import ejb.GestoreUtenti;
import ejb.UserComponent;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;



/**
 *
 * @author Andr3A
 */
@MultipartConfig
public class MainServlet extends HttpServlet {
    @EJB
    private GestoreUtenti gestoreUtenti;
    @EJB
    private GestoreCommenti gestoreCommenti;
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        RequestDispatcher rd;
        HttpSession s = request.getSession();
        String action= request.getParameter("op");
        String str = "";
        Principal principal = request.getUserPrincipal();
        if(principal!=null && s.getAttribute("userID")==null){
           UserComponent u = gestoreUtenti.findUtenteFromName(principal.getName());
           s.setAttribute("userID", u.getId());
        }
        if(action.equals("InserisciAnnuncioCasa")){
            rd = getServletContext().getRequestDispatcher("/AnnuncioCasa.jsp");
            rd.forward(request,response);
        }
  
        if(action.equals("creaAnnuncioCasa")){
            rd = getServletContext().getRequestDispatcher("/CreaAnnuncioCasaServlet");
            rd.forward(request,response);
        }
        
        if(action.equals("cercaAnnunci")){
            
            try{
                JSONObject location = gestoreAnnunci.getCoordinate(request.getParameter("location"));
                List<AnnuncioCasa> annunci=gestoreAnnunci.trovaAnnunciCasa(Double.parseDouble(location.opt("lat").toString()),Double.parseDouble(location.opt("lng").toString()));
                request.setAttribute("annunci", annunci);
                request.setAttribute("lat", location.opt("lat") );
                request.setAttribute("lng", location.opt("lng") );
                getServletContext().getRequestDispatcher("/viewAnnunci.jsp").forward(request,response);
            }catch(JSONException e){}
        }
        
        if(action.equals("viewDettaglioAnnuncioCasa")){
           int index = Integer.parseInt(request.getParameter("index")); 
           List<Commento> c = gestoreCommenti.findAllCommenti(2);
           request.setAttribute("commenti", c);
           getServletContext().getRequestDispatcher("/viewDetailsAnnuncio.jsp").forward(request,response);
        }
        
        if(action.equals("logout")){
            request.logout();
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
        
        if(action.equals("deleteCommento")){
            Long idUtente;
            Long idAnnuncio;
            Long id;
            id = Long.parseLong(request.getParameter("id"));
//          Long idAnnuncio = Long.parseLong(request.getParameter("idAnnuncio"));
            idUtente = Long.parseLong(request.getParameter("utente"));
            idAnnuncio = Long.parseLong(request.getParameter("annuncio"));
            gestoreCommenti.delCommento(id, idUtente, idAnnuncio);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
        
        if(action.equals("addCommento")){
            Long idUtente;
            Long idAnnuncio;
            String testoCommento;
            testoCommento = request.getParameter("newCommento");
            idUtente = Long.parseLong(request.getParameter("utente"));
            idAnnuncio = Long.parseLong(request.getParameter("annuncio"));
            gestoreCommenti.addCommento(idUtente, idAnnuncio, testoCommento);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
        
        if(action.equals("registrazione")){
            UserComponent c = new UserComponent();
            c.setEmail("xxandrea87@msn.com");
            c.setName("Andrea");
            c.setPassword("5f3d6952c5c5e22077fabf461de80f1ce475752fe75afcf5ca46bac438405619");
            c.setRuolo("admin");
            c.setUsername("andrea");
            gestoreUtenti.addUtente(c);
            c = new UserComponent();
            c.setEmail("marco.camino@msn.com");
            c.setName("Marco");
            c.setPassword("7c8ccc86c11654af029457d90fdd9d013ce6fb011ee8fdb1374832268cc8d967");
            c.setRuolo("admin");
            c.setUsername("marco");
            gestoreUtenti.addUtente(c);
            c = new UserComponent();
            c.setEmail("ale@msn.com");
            c.setName("Alessandro");
            c.setPassword("5c85bb36f3869809fb738a3ba6f990aedbfeca3df2dc1a997fa49c50d0eed8e6");
            c.setRuolo("moderatore");
            c.setUsername("ale");
            gestoreUtenti.addUtente(c);
            c = new UserComponent();
            c.setEmail("geust@msn.com");
            c.setName("Guest");
            c.setPassword("84983c60f7daadc1cb8698621f802c0d9f9a3c3c295c810748fb048115c186ec");
            c.setRuolo("guest");
            c.setUsername("guest");
            gestoreUtenti.addUtente(c);
        }
        if(action.equals("jaas2")){
            String message="";
            try (PrintWriter out = response.getWriter()) {
                if(principal != null && s.getAttribute("userID") != null){
                    Long userID = (Long) s.getAttribute("userID");
                    message += "id: " + userID;
                }
                if(request.isUserInRole("administrator")){
                    message += "Username : " + principal.getName() + " You are an Administrator";
                }else if(request.isUserInRole("moderatore")){
                    message += "Username : " + principal.getName() + " You are a Moderatore";
                }else if(request.isUserInRole("guest")){
                    message += "Username : " + principal.getName() + " You are a Guest";
                }
                else{
                    message += " You're simply user";
                }
                out.println(message);
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
