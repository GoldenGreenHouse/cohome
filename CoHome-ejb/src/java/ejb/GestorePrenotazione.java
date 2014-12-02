/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Andr3A
 */
@Stateless
@LocalBean
public class GestorePrenotazione {
    @EJB
    private AnnuncioFacadeLocal annuncioFacade;
    @EJB
    private UserComponentFacadeLocal userComponentFacade;
    @EJB
    private PrenotazioneFacadeLocal prenotazioneFacade;
    @EJB
    private PropostaPrenotazioneFacadeLocal propostaPrenotazioneFacade;
    @EJB
    private GestoreAnnunci gestoreAnnunci;
    @PersistenceContext(unitName = "CoHome-ejbPU")
    private EntityManager em;
    
    public void addPropostaPrenotazione(String checkin, String checkout, String guests, String desc, long idUser, Annuncio a) {
        UserComponent user = userComponentFacade.find(idUser);
        Calendar dataGCI = new GregorianCalendar();
        Calendar dataGCF= new GregorianCalendar();
        PropostaPrenotazione pp = new PropostaPrenotazione();
        pp.setAttivo(true);   
        String[] data = checkin.split("/");
        dataGCI.set(Integer.parseInt(data[2]),(Integer.parseInt(data[0]) -1),Integer.parseInt(data[1]));
        pp.setDataInizio(dataGCI);
        data = checkout.split("/");
        dataGCF.set(Integer.parseInt(data[2]),(Integer.parseInt(data[0]) -1),Integer.parseInt(data[1]));
        pp.setDataFine(dataGCF);
        pp.setDescrizione(desc);
        pp.setNumeroPosti(Integer.parseInt(guests));
        pp.setAnnuncio(a);
        pp.setUtente(user);
        
        propostaPrenotazioneFacade.create(pp);
        user.getPropostaPrenotazione().add(pp);
        a.getPropostaPrenotazione().add(pp);
        userComponentFacade.edit(user);
        annuncioFacade.edit(a);
    }
    
    public void addPrenotazione(long id){
        PropostaPrenotazione pp = propostaPrenotazioneFacade.find(id);
        Annuncio a = pp.getAnnuncio();
        Prenotazione p = new Prenotazione();
        p.setAnnuncio(pp.getAnnuncio());
        p.setDataFine(pp.getDataFine());
        p.setDataInizio(pp.getDataInizio());
        p.setDescrizione(pp.getDescrizione());
        p.setNumeroPosti(pp.getNumeroPosti());
        p.setUtente(pp.getUtente());
        p.setAttivo(true);
        p.setDataPrenotazione(Calendar.getInstance());
        pp.setAttivo(false);
        
        Long idUtente = gestoreAnnunci.getIdUtenteByIdAnnuncio(pp.getAnnuncio().getId());
        UserComponent user = userComponentFacade.find(idUtente);
        user.getPrenotazioni().add(p);
        userComponentFacade.edit(user);
        annuncioFacade.edit(a);
        
        
        List<PropostaPrenotazione> proposte = getPropostaByAnnuncio(a.getId());
        Iterator iter = proposte.listIterator();
        while(iter.hasNext()){
            PropostaPrenotazione x = (PropostaPrenotazione)iter.next();
            if ( (x.getDataInizio().after(p.getDataInizio()) && x.getDataInizio().before(p.getDataFine())) ||
                 (x.getDataFine().after(p.getDataInizio()) && x.getDataFine().before(p.getDataFine())) ||
                 (x.getDataInizio().before(p.getDataInizio()) && x.getDataFine().after(p.getDataFine())) )
                    x.setAttivo(false);
        }
        propostaPrenotazioneFacade.edit(pp);
        em.flush();
        
    }
    
    public void refPropostaPrenotazione(long id){
        PropostaPrenotazione pp = propostaPrenotazioneFacade.find(id);
        Annuncio a = pp.getAnnuncio();
        
        pp.setAttivo(false);
        
//        Long idUtente = gestoreAnnunci.getIdUtenteByIdAnnuncio(pp.getAnnuncio().getId());
//        UserComponent user = userComponentFacade.find(idUtente);
//        userComponentFacade.edit(user);
        annuncioFacade.edit(a);
        
        propostaPrenotazioneFacade.edit(pp);
        em.flush();
        
    }
    
    
    public List<PropostaPrenotazione> getPropostaByAnnuncio(Long idAnnuncio){
        List<PropostaPrenotazione> proposte;
        Query query = em.createNamedQuery("getProposteByAnnuncio").setParameter("Annuncio_Id", idAnnuncio);
        proposte = query.getResultList();
        return proposte;
    }
    
    
}
