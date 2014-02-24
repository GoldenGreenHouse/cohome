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
public interface PrenotazioneFacadeLocal {

    void create(Prenotazione prenotazione);

    void edit(Prenotazione prenotazione);

    void remove(Prenotazione prenotazione);

    Prenotazione find(Object id);

    List<Prenotazione> findAll();

    List<Prenotazione> findRange(int[] range);

    int count();
    
}
