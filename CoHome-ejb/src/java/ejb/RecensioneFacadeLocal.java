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
public interface RecensioneFacadeLocal {

    void create(Recensione recensione);

    void edit(Recensione recensione);

    void remove(Recensione recensione);

    Recensione find(Object id);

    List<Recensione> findAll();

    List<Recensione> findRange(int[] range);

    int count();
    
}
