/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package web;

import bean.RicercaAnnunciCasa;
import ejb.Annuncio;
import ejb.AnnuncioCasa;
import ejb.Commento;
import ejb.GestoreAnnunci;
import ejb.GestoreCommenti;
import ejb.GestorePrenotazione;
import ejb.GestoreRecensioni;
import ejb.GestoreRichieste;
import ejb.GestoreUtenti;
import ejb.PropostaPrenotazione;
import ejb.RichiestaCasa;
import ejb.UserComponent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    private GestorePrenotazione gestorePrenotazione;
    @EJB
    private GestoreUtenti gestoreUtenti;
    @EJB
    private GestoreCommenti gestoreCommenti;
    @EJB
    private GestoreAnnunci gestoreAnnunci;
    @EJB
    private GestoreRichieste gestoreRichieste;
    @EJB
    private GestoreRecensioni gestoreRecensioni;
    
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
           s.setAttribute("userName", u.getName());
        }
        
        if(action.equals("InserisciAnnuncioCasa")){
            if(request.isUserInRole("administrator")){
                rd = getServletContext().getRequestDispatcher("/AnnuncioCasa.jsp");
                rd.forward(request,response);
            }
            else{
                //response.sendError(401, "Eseguire il login prima di effettuare questa operazione");
                request.setAttribute("error", "Eseguire il login prima di effettuare questa operazione");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
            }
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
           Annuncio a = gestoreAnnunci.findAnnuncio(Long.parseLong(request.getParameter("idAnnuncio")));
           request.setAttribute("annuncio", a);
           List<PropostaPrenotazione> lp = a.getPropostaPrenotazione();
           List<Commento> c = gestoreCommenti.findAllCommenti(Integer.parseInt(request.getParameter("idAnnuncio")));
           request.setAttribute("commenti", c);
           request.setAttribute("proposte", lp);
           request.setAttribute("idUtenteAnnuncio", gestoreAnnunci.getIdUtenteByIdAnnuncio(a.getId())); 
           getServletContext().getRequestDispatcher("/viewDetailsAnnuncio.jsp").forward(request,response);
        }
        
        if(action.equals("viewUser")){
           UserComponent u = gestoreUtenti.findUtente(Long.parseLong(request.getParameter("userID"))); 
           request.setAttribute("utente", u);
           getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
        }
        
        if(action.equals("viewUserLogged")){
           UserComponent u = gestoreUtenti.findUtente((Long)s.getAttribute("userID")); 
           request.setAttribute("utente", u);
           getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
        }
        
        if(action.equals("viewProposte")){
            String annuncio = request.getParameter("idAnnuncio");
            Annuncio a = gestoreAnnunci.findAnnuncio(Long.parseLong(annuncio));
            request.setAttribute("annuncio", a);
            List<PropostaPrenotazione> lp = a.getPropostaPrenotazione(); 
            List<Commento> c = gestoreCommenti.findAllCommenti(Long.parseLong(annuncio));
            
            request.setAttribute("commenti", c);
            request.setAttribute("proposte", lp);
            request.setAttribute("idUtenteAnnuncio", gestoreAnnunci.getIdUtenteByIdAnnuncio(a.getId()));
            
            getServletContext().getRequestDispatcher("/viewDetailsAnnuncio.jsp").forward(request,response);
            //getServletContext().getRequestDispatcher("/viewProposte.jsp").forward(request,response);
        }
        
        if(action.equals("logout")){
            request.logout();
            s.invalidate();
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
        }
        
        if(action.equals("addCommento")){
            Long idUtente;
            Long idAnnuncio;
            String testoCommento;
            testoCommento = request.getParameter("newCommento");
            idUtente = Long.parseLong(request.getParameter("utente"));
            idAnnuncio = Long.parseLong(request.getParameter("annuncio"));
            gestoreCommenti.addCommento(idUtente, idAnnuncio, testoCommento);
        }
        
        if(action.equals("addEvaluate")){
            Long idUtente;
            Long idUtenteLoggato;
            String testoValutazione;
            String voto;
            testoValutazione = request.getParameter("newEvaluate");
            voto = request.getParameter("vote");
            idUtente = Long.parseLong(request.getParameter("utente"));
            idUtenteLoggato = Long.parseLong(request.getParameter("utenteLoggato"));
            try{
                gestoreRecensioni.addRecensione(idUtente, idUtenteLoggato, testoValutazione, voto);
            }
            catch(javax.ejb.EJBTransactionRolledbackException e){
                response.sendError(401, e+" - You have just eveluate this user!");
            }
        }
        
        if(action.equals("addPropostaPrenotazione")){
//controllo se l'utente è registrato
            if(request.isUserInRole("registered")){
              //  RicercaAnnunciCasa rac = (RicercaAnnunciCasa) s.getAttribute("ricercaAnnunciCasa");
                Annuncio a = gestoreAnnunci.findAnnuncio(Long.parseLong(request.getParameter("id")));
                //Annuncio a = rac.getSingleAnnuncio(Integer.parseInt(request.getParameter("index")));
                gestorePrenotazione.addPropostaPrenotazione(request.getParameter("checkin"), request.getParameter("checkout"), request.getParameter("guests"), request.getParameter("desc"),(Long)s.getAttribute("userID") , a);
            }
            else
                response.sendError(401, "Eseguire il login prima di effettuare questa operazione");
        }
        
        if(action.equals("addPrenotazione")){
            UserComponent u = gestoreUtenti.findUtente(Long.parseLong(request.getParameter("userID"))); 
            long propostaID = Long.parseLong(request.getParameter("propostaID"));
            gestorePrenotazione.addPrenotazione(propostaID);
            request.setAttribute("utente", u);
            getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
        }
        
        if(action.equals("refPropostaPrenotazione")){
            UserComponent u = gestoreUtenti.findUtente(Long.parseLong(request.getParameter("userID"))); 
            long propostaID = Long.parseLong(request.getParameter("propostaID"));
            gestorePrenotazione.refPropostaPrenotazione(propostaID);
            request.setAttribute("utente", u);
            getServletContext().getRequestDispatcher("/user.jsp").forward(request,response);
        }
        

        if(action.equals("loginFacebook")){ 
            if(request.isUserInRole("administrator")){
                getServletContext().getRequestDispatcher("/LoginFacebook").forward(request,response);
            }
            response.sendError(401, "Eseguire il login prima di effettuare questa operazione");
        }
 
        if(action.equals("index")){
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
        }        
        
        if(action.equals("accessToken")){
            if(request.isUserInRole("administrator")){
                rd = getServletContext().getRequestDispatcher("/GetAccessToken");
                rd.forward(request,response);
            } else response.sendError(401, "Eseguire il login prima di effettuare questa operazione");
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
            c.setEmail("pippo@msn.com");
            c.setName("Pippo");
            c.setPassword("a2242ead55c94c3deb7cf2340bfef9d5bcaca22dfe66e646745ee4371c633fc8");
            c.setRuolo("registered");
            c.setUsername("pippo");
            gestoreUtenti.addUtente(c);
            c = new UserComponent();
            c.setEmail("user1@msn.com");
            c.setName("User1");
            c.setPassword("0a041b9462caa4a31bac3567e0b6e6fd9100787db2ab433d96f6d178cabfce90");
            c.setRuolo("registered");
            c.setUsername("user1");
            gestoreUtenti.addUtente(c);
            c = new UserComponent();
            c.setEmail("user2@msn.com");
            c.setName("User2");
            c.setPassword("6025d18fe48abd45168528f18a82e265dd98d421a7084aa09f61b341703901a3");
            c.setRuolo("registered");
            c.setUsername("user2");
            gestoreUtenti.addUtente(c);
            
            request.setAttribute("success", "Utenti Registrati con successo");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
                }else if(request.isUserInRole("registered")){
                    message += "Username : " + principal.getName() + " You are a Registered";
                }
                else{
                    message += " You're simply user";
                }
                out.println(message);
            }
        }
        
// DA ELIMINAREEEEE!!!        
        if(action.equals("eliminaCommentoProva")){
            gestoreCommenti.delCommentoProva("302");
            getServletContext().getRequestDispatcher("/index.jsp").forward(request,response);
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
