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
public interface AnnuncioVisitaFacadeLocal {

    void create(AnnuncioVisita annuncioVisita);

    void edit(AnnuncioVisita annuncioVisita);

    void remove(AnnuncioVisita annuncioVisita);

    AnnuncioVisita find(Object id);

    List<AnnuncioVisita> findAll();

    List<AnnuncioVisita> findRange(int[] range);

    int count();
    
}
