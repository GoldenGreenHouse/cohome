/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import Bean.RicercaAnnunciCasa;
import ejb.Annuncio;
import ejb.AnnuncioCasa;
import ejb.GestoreAnnunci;
import ejb.GestoreCommenti;
import ejb.GestoreUtenti;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Andr3A
 */
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
        
        HttpSession s = request.getSession();
        String action= request.getParameter("op");
        String str = "";
        Principal principal = request.getUserPrincipal();
        
        if(action.equals("inserisciAnnuncio")){
            str = request.getParameter("userComponent");
            gestoreUtenti.addModeratore(str);
            gestoreUtenti.addRegistered(str);
            gestoreUtenti.addGuest(str);
            gestoreAnnunci.addAnnuncioCasa(str);
            gestoreCommenti.addModeratoreCommenti(str);   
        }
        if(action.equals("cercaAnnunci")){
            List<AnnuncioCasa> annunci=gestoreAnnunci.trovaAnnunciCasa();
            request.setAttribute("annunci", annunci);
            getServletContext().getRequestDispatcher("/viewAnnunci.jsp").forward(request,response);
        }
        if(action.equals("viewDettaglioAnnuncioCasa")){
           int index = Integer.parseInt(request.getParameter("index"));         
           getServletContext().getRequestDispatcher("/viewDetailsAnnuncio.jsp").forward(request,response);
        }
        if(action.equals("logout")){
            request.logout();
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
