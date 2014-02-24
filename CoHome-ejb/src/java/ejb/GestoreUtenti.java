/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author marco
 */
@Stateless
@LocalBean
public class GestoreUtenti {
    @EJB
    private GuestFacadeLocal guestFacade;
    @EJB
    private RegisteredFacadeLocal registeredFacade;
    @EJB
    private ModeratoreFacadeLocal moderatoreFacade;
    
    public void addModeratore(String moderatore){
        Moderatore utenteModeratore = new Moderatore();
        utenteModeratore.setEmail(moderatore);
        utenteModeratore.setPassword(moderatore);
        AnnuncioCasa annuncioCasa = new AnnuncioCasa();
        annuncioCasa.setDescrizione("annuncio da gestUtenti");
        List<Annuncio> annunci = utenteModeratore.getAnnunci();
        annunci.add(annuncioCasa);
        //annunci.add(annuncioCasa);
        utenteModeratore.setAnnunci(annunci);
        moderatoreFacade.create(utenteModeratore);
    }

    public void addRegistered(String registered){
        Registered utenteRegistered = new Registered();
        utenteRegistered.setEmail(registered);
        utenteRegistered.setPassword(registered);
        Prenotazione prenotazione = new Prenotazione();
        Calendar data = new GregorianCalendar();
        data.set(2013, 12, 14);
        prenotazione.setDataPrenotazione(data);
        List<Prenotazione> prenotazioni = utenteRegistered.getPrenotazioni();
        prenotazioni.add(prenotazione);
        utenteRegistered.setPrenotazioni(prenotazioni);
        registeredFacade.create(utenteRegistered);
    }  
    
    public void addGuest(String guest){
        Guest utenteGuest = new Guest();
        utenteGuest.setEmail(guest);
        utenteGuest.setPassword(guest);
        guestFacade.create(utenteGuest);
    
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
