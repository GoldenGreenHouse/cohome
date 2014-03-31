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
        
        if(action.equals("InserisciAnnuncioCasa")){
            rd = getServletContext().getRequestDispatcher("/AnnuncioCasa.jsp");
            rd.forward(request,response);
        }
  
        if(action.equals("creaAnnuncioCasa")){
            rd = getServletContext().getRequestDispatcher("/CreaAnnuncioCasaServlet");
            rd.forward(request,response);
        }
        
        if(action.equals("cercaAnnunci")){
            //double lat= Double.parseFloat(request.getParameter("lat"));
            //double lng= Double.parseFloat(request.getParameter("lng"));
            List<AnnuncioCasa> annunci=gestoreAnnunci.trovaAnnunciCasa(new Double(45.070260),new Double(7.680389));
            request.setAttribute("annunci", annunci);
            getServletContext().getRequestDispatcher("/viewAnnunci.jsp").forward(request,response);
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
            int id = Integer.parseInt(request.getParameter("id"));
            gestoreCommenti.deleteCommento(id);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }
        
        if(action.equals("addCommento")){
            int id_utente;
            String testoCommento;
            UserComponent utente;
            testoCommento = request.getParameter("newCommento");
            id_utente = Integer.parseInt(request.getParameter("utente"));
            utente = gestoreUtenti.findUtente(1);        
            gestoreCommenti.addCommento(utente, "Prova1");
            getServletContext().getRequestDispatcher("/viewDetailsAnnuncio.jsp").forward(request,response);
        }
       
        if(action.equals("jaas")){
            String message="";
            try (PrintWriter out = response.getWriter()) {
                if(request.isUserInRole("administrator")){
                    message = "Username : " + principal.getName() + " You are an Administrator";
                }else if(request.isUserInRole("moderatore")){
                    message = "Username : " + principal.getName() + " You are a Moderatore";
                }else if(request.isUserInRole("guest")){
                    message = "Username : " + principal.getName() + " You are a Guest";
                }
                else{
                    message = " You're simply user";
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
