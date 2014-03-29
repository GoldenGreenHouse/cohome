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
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NamedNativeQuery;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marco
 */
@Stateless
@LocalBean


public class GestoreAnnunci {
    @EJB
    private RichiestaVisitaFacadeLocal richiestaVisitaFacade;
    @EJB
    private RichiestaCasaFacadeLocal richiestaCasaFacade;
    @EJB
    private AnnuncioVisitaFacadeLocal annuncioVisitaFacade;
    @EJB
    private PropostaPrenotazioneFacadeLocal propostaPrenotazioneFacade;
    @EJB
    private AnnuncioFacadeLocal annuncioFacade1;
    @EJB
    private AnnuncioFacadeLocal annuncioFacade;
    @EJB
    private AnnuncioCasaFacadeLocal annuncioCasaFacade;
    
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
   

   public void addAnnuncioCasa(AnnuncioCasa annuncioCasa){
        annuncioCasaFacade.create(annuncioCasa);
        //Controlla mapping con tabella UserComponent_Annuncio
   }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<AnnuncioCasa> trovaAnnunciCasa(float lat, float lng) {
        Query query = em.createNamedQuery("findAllAnnunciCasa").setParameter("lat", lat).setParameter("lng", lng);
        List<AnnuncioCasa> l = query.getResultList(); 
        return l;
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
