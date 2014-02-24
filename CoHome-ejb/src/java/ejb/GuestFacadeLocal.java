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
public interface GuestFacadeLocal {

    void create(Guest guest);

    void edit(Guest guest);

    void remove(Guest guest);

    Guest find(Object id);

    List<Guest> findAll();

    List<Guest> findRange(int[] range);

    int count();
    
}
