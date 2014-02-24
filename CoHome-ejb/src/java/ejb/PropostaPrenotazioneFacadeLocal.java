/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ejb;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marco
 */
@Local
public interface PropostaPrenotazioneFacadeLocal {

    void create(PropostaPrenotazione propostaPrenotazione);

    void edit(PropostaPrenotazione propostaPrenotazione);

    void remove(PropostaPrenotazione propostaPrenotazione);

    PropostaPrenotazione find(Object id);

    List<PropostaPrenotazione> findAll();

    List<PropostaPrenotazione> findRange(int[] range);

    int count();
    
}
