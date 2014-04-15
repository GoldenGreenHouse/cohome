/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import javax.ejb.Local;

/**
 *
 * @author Andr3A
 */
@Local
public interface GestorePrenotazioneLocal {

    void addPropostaPrenotazione(String checkin,String checkout,String guests,String desc,int idUser, Annuncio a);
    
}
