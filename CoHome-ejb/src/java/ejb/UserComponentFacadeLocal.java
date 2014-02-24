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
public interface UserComponentFacadeLocal {

    void create(UserComponent utente);

    void edit(UserComponent utente);

    void remove(UserComponent utente);

    UserComponent find(Object id);

    List<UserComponent> findAll();

    List<UserComponent> findRange(int[] range);

    int count();
    
}
