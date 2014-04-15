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
    

    @Override
    public void addPropostaPrenotazione(String checkin,String checkout,String guests,String desc,int idUser, Annuncio a) {
        Calendar dataGCI = new GregorianCalendar();
        Calendar dataGCF= new GregorianCalendar();
        PropostaPrenotazione pp = new PropostaPrenotazione();
        pp.setAttivo(true);   
        String[] data = checkin.split("/");
        dataGCI.set(Integer.parseInt(data[2]),(Integer.parseInt(data[1]) -1),Integer.parseInt(data[0]));
        pp.setDataInizio(dataGCI);
        data = checkin.split("/");
        dataGCF.set(Integer.parseInt(data[2]),(Integer.parseInt(data[1]) -1),Integer.parseInt(data[0]));
        pp.setDataFine(dataGCF);
        pp.setDescrizione(desc);
        pp.setNumeroPosti(Integer.parseInt(guests));
        
        
        propostaPrenotazioneFacade.create(pp);
        UserComponent user = userComponentFacade.find(idUser);
        user.getPropostaPrenotazione().add(pp);
        a.getPropostaPrenotazione().add(pp);
        userComponentFacade.edit(user);
        annuncioFacade.edit(a);
        
        
    }
    
    
}
