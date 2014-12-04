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
 * @author Alessandro
 */
@Stateless
@LocalBean
public class GestoreRecensioni {

    @EJB
    private RecensioneFacadeLocal recensioneFacade;
    @EJB
    private UserComponentFacadeLocal userComponentFacade;
    
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    
    public void addRecensione(Long idUtente, Long idUtenteLoggato, String recensione, String voto){
        Recensione r = new Recensione();
        UserComponent u;
        UserComponent uLoggato;
        r.setDescrizione(recensione);
        r.setValutazione(voto);
        r.setId(idUtente);
        u = userComponentFacade.find(idUtente);
        uLoggato = userComponentFacade.find(idUtenteLoggato);
        r.setAutoreID(uLoggato);
        u.getRecensioni().add(r);
        
        recensioneFacade.create(r);
        userComponentFacade.edit(u);
        userComponentFacade.edit(uLoggato);
    }
    
    public List<Recensione> findAllRecensioniById(int id_utente){
       Query q;
       q = em.createNamedQuery("findAllRecensioniById").setParameter("Utente_Id", id_utente);
       return q.getResultList();
    }
}
