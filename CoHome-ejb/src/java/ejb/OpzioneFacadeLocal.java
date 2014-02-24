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
public interface OpzioneFacadeLocal {

    void create(Opzione opzione);

    void edit(Opzione opzione);

    void remove(Opzione opzione);

    Opzione find(Object id);

    List<Opzione> findAll();

    List<Opzione> findRange(int[] range);

    int count();
    
}
