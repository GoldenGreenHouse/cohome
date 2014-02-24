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

/**
 *
 * @author marco
 */
@Stateless
@LocalBean
public class GestoreCommenti {
    @EJB
    private ModeratoreFacadeLocal moderatoreFacade;
    
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

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
