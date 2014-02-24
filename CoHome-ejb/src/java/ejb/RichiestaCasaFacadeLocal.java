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
public interface RichiestaCasaFacadeLocal {

    void create(RichiestaCasa richiestaCasa);

    void edit(RichiestaCasa richiestaCasa);

    void remove(RichiestaCasa richiestaCasa);

    RichiestaCasa find(Object id);

    List<RichiestaCasa> findAll();

    List<RichiestaCasa> findRange(int[] range);

    int count();
    
}
