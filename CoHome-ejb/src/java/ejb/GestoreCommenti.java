/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;
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
    private UserComponentFacadeLocal userComponentFacade;
    @EJB
    private AnnuncioFacadeLocal annuncioFacade;
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
     public void addCommento(Long idUtente, Long idAnnuncio, String commento){
        Commento c = new Commento();
        UserComponent u;
        Annuncio a;
        u = userComponentFacade.find(idUtente);
        a = annuncioFacade.find(idAnnuncio);
//        Query query = em.createNamedQuery("findAllAnnunciCasa");
//        a = (Annuncio) query.getResultList().get(0);
        c.setAutore(u);
        c.setCommento(commento);
        a.getCommenti().add(c);
        u.getCommenti().add(c);
//        em.persist(c);
//        em.merge(a);
//        em.merge(u);
        
        commentoFacade.create(c);
        annuncioFacade.edit(a);
        userComponentFacade.edit(u);
    }
    
    public void delCommento(Long id, Long idUtente, Long idAnnuncio){
        Commento c;
        Annuncio a;
        UserComponent u;
        c = commentoFacade.find(id);
        u = userComponentFacade.find(idUtente);
        a = annuncioFacade.find(idAnnuncio);
//        u.getCommenti().remove(c);
//        userComponentFacade.edit(u);
//        a.getCommenti().remove(c);
//        annuncioFacade.edit(a);
        List<Commento> commentiAnnuncio = a.getCommenti();
        List<Commento> commentiUtente = u.getCommenti();
        commentiAnnuncio.remove(c);
        commentiUtente.remove(c);
        a.setCommenti(commentiAnnuncio);
        u.setCommenti(commentiUtente);
        userComponentFacade.edit(u);
        annuncioFacade.edit(a);
        //commentoFacade.remove(c);
        
    }
    
    public void delCommentoProva(String id){
        Commento c;
        c = commentoFacade.find(Long.parseLong(id));
        commentoFacade.remove(c);
    }
    
//    public void deleteCommento(int id){
//        Query q;
//        q = em.createNamedQuery("deleteAnnuncioCommento").setParameter("id_commento", id);
//        q = em.createNamedQuery("deleteCommento").setParameter("id_commento", id);
//    }
    
    public List<Commento> findAllCommenti(int id_annuncio){
       Query q;
       q = em.createNamedQuery("findAllCommenti").setParameter("Annuncio_Id", id_annuncio);
       return q.getResultList();
    }
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
