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
public interface RegisteredFacadeLocal {

    void create(Registered registered);

    void edit(Registered registered);

    void remove(Registered registered);

    Registered find(Object id);

    List<Registered> findAll();

    List<Registered> findRange(int[] range);

    int count();
    
}
