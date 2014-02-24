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
public interface CommentoFacadeLocal {

    void create(Commento commento);

    void edit(Commento commento);

    void remove(Commento commento);

    Commento find(Object id);

    List<Commento> findAll();

    List<Commento> findRange(int[] range);

    int count();
    
}
