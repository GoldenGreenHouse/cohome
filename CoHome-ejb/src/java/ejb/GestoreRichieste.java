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
public class GestoreRichieste {
    @EJB
    private RichiestaCasaFacadeLocal richiestaCasaFacade;

    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    
    public List<RichiestaCasa> getRichiesteByAnnuncio(Long idAnnuncio){
        List<RichiestaCasa> richieste;
        Query query = em.createNamedQuery("getRichiesteByAnnuncio").setParameter("Annuncio_Id", idAnnuncio);;
        richieste = query.getResultList();
        return richieste;
    }
    
}
