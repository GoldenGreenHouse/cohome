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
public interface AnnuncioCasaFacadeLocal {

    void create(AnnuncioCasa annuncioCasa);

    void edit(AnnuncioCasa annuncioCasa);

    void remove(AnnuncioCasa annuncioCasa);

    AnnuncioCasa find(Object id);

    List<AnnuncioCasa> findAll();

    List<AnnuncioCasa> findRange(int[] range);

    int count();
    
}
