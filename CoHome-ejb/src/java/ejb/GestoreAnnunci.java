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
    
    
    
    
    
    public void addAnnuncioCasa(String indirizzo){
        AnnuncioCasa annuncioCasa = new AnnuncioCasa();
        annuncioCasa.setIndirizzo(indirizzo);
        annuncioCasaFacade.create(annuncioCasa);
        PropostaPrenotazione propostaPrenotazione = new PropostaPrenotazione();
        propostaPrenotazione.setNumeroPosti(44);
        propostaPrenotazione.setDescrizione("proposta da annuncio");
        propostaPrenotazione.setAnnuncio(annuncioCasa);
        //propostaPrenotazione.setAnnuncio(annuncioCasa);
        //propostaPrenotazioneFacade.create(propostaPrenotazione);
        AnnuncioVisita annuncioVisita = new AnnuncioVisita();
        annuncioVisita.setAttrazione("colosseo");
        List<PropostaPrenotazione> proposte = annuncioVisita.getPropostaPrenotazione();
        proposte.add(propostaPrenotazione);
        annuncioVisita.setPropostaPrenotazione(proposte);
        annuncioVisitaFacade.create(annuncioVisita);
        RichiestaCasa richiestaCasa = new RichiestaCasa();
        richiestaCasa.setNumeroPosti(7);
        richiestaCasaFacade.create(richiestaCasa);
        RichiestaVisita richiestaVisita = new RichiestaVisita();
        richiestaVisita.setAttrazione("museo");
        Commento commento = new Commento();
        commento.setCommento("commento annuncio prova");
        List<Commento> commenti = richiestaVisita.getCommenti();
        commenti.add(commento);
        commento = new Commento();
        commento.setCommento("commento annuncio prova");
        commenti.add(commento);
        richiestaVisita.setCommenti(commenti);
        List<Opzione> opzioni = richiestaVisita.getOpzioni();
        Opzione opzione = new Opzione();
        opzione.setNome("opzione annuncio");
        opzione.setValore("valore opzione");
        opzioni.add(opzione);
        richiestaVisita.setOpzioni(opzioni);
        richiestaVisitaFacade.create(richiestaVisita);
        long i = 2810;
        Annuncio annuncio = annuncioFacade.find(i);
        int k = 3;
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public List<Annuncio> trovaAnnunciCasa() {
        return annuncioFacade.findAll();
    }
}
