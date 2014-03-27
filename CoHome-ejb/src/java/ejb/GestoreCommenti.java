/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marco
 */
@Stateless
@LocalBean
public class GestoreCommenti {
    @EJB
    private CommentoFacadeLocal commentoFacade;
    @EJB
    private ModeratoreFacadeLocal moderatoreFacade;
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    
    public void addModeratoreCommenti(String moderatore){
        Moderatore utenteModeratore = new Moderatore();
        utenteModeratore.setEmail(moderatore);
        utenteModeratore.setPassword(moderatore);
        Commento commento = new Commento();
        commento.setCommento(moderatore);
        commento.setAutore(utenteModeratore);
        List<Commento> commenti = utenteModeratore.getCommenti();
        commenti.add(commento);
        commento = new Commento();
        commento.setCommento("commento prova");
        commento.setAutore(utenteModeratore);
        commenti.add(commento);
        utenteModeratore.setCommenti(commenti);
        Recensione recensione = new Recensione();
        recensione.setDescrizione("recensione prova");
        recensione.setValutazione("valutazione prova");
        List<Recensione> recensioni = utenteModeratore.getRecensioni();
        recensioni.add(recensione);
        utenteModeratore.setRecensioni(recensioni);
        moderatoreFacade.create(utenteModeratore);    
    }
     public void addCommento(UserComponent user, String commento){
        Commento c = new Commento();
        c.setAutore(user);
        c.setCommento(commento);
        commentoFacade.create(c);
    }
    
    public void delCommento(int id){
        Commento c;
        c = commentoFacade.find(id);
        commentoFacade.remove(c);
    }
    
    public void deleteCommento(int id){
        Query q;
        q = em.createNamedQuery("deleteAnnuncioCommento").setParameter("id_commento", id);
        q = em.createNamedQuery("deleteCommento").setParameter("id_commento", id);
    }
    
    public List<Commento> findAllCommenti(int id_annuncio){
       Query q;
       q = em.createNamedQuery("findAllCommenti").setParameter("Annuncio_Id", id_annuncio);
       return q.getResultList();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
