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
public interface ModeratoreFacadeLocal {

    void create(Moderatore moderatore);

    void edit(Moderatore moderatore);

    void remove(Moderatore moderatore);

    Moderatore find(Object id);

    List<Moderatore> findAll();

    List<Moderatore> findRange(int[] range);

    int count();
    
}
