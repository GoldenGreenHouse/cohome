/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andr3A
 */
@Stateless
public class GestorePrenotazione implements GestorePrenotazioneLocal {
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
    

    @Override
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
        
        //prenotazioneFacade.create(p);
        Long idUtente = gestoreAnnunci.getIdUtenteByIdAnnuncio(pp.getAnnuncio().getId());
        UserComponent user = userComponentFacade.find(idUtente);
        user.getPrenotazioni().add(p);
        userComponentFacade.edit(user);
        annuncioFacade.edit(a);
        propostaPrenotazioneFacade.edit(pp);
        em.flush();
        
    }
    
    
}
